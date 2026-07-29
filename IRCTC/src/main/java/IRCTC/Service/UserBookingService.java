package IRCTC.Service;

import IRCTC.Entities.Ticket;
import IRCTC.Entities.Train;
import IRCTC.Entities.User;
import IRCTC.util.UserServiceUtil;
import org.springframework.cglib.proxy.NoOp;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {
    private User user;

    private  List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USER_PATH="../LocalDB/users.json";

    public UserBookingService(User user1) throws IOException {
          this.user = user1;
          loadUsers();
    }
    public UserBookingService() throws IOException{
        loadUsers();
    }
    public List<User> loadUsers() throws IOException{
        File users = new File(USER_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
    public Boolean signUp(User user1){
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File usersFile= new File(USER_PATH);
        objectMapper.writeValue(usersFile,userList);
    }


    public void fetchBooking(){
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        if (userFetched.isPresent()){
            userFetched.get().printTickets();
        }
    }

    public Boolean cancelBooking(String ticketId){
            Scanner input = new Scanner(System.in);
            System.out.print("\nEnter the Ticket id to Cancel: ");
            ticketId = input.next();

            if (ticketId == null || ticketId.isEmpty()){
                System.out.println("Ticket id Can't be Null and Empty!");
                return Boolean.FALSE;
            }

            String finalTicketId1 = ticketId;
            boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

            String finalTicketId = ticketId;
            user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
            if (removed){
                System.out.println("Ticket with ID "+ticketId+" has Been Canceled.");
                return Boolean.TRUE;
            }else {
                System.out.println("No, Ticket Found With ID "+ticketId);
                return Boolean.FALSE;
            }


    }

    public void fetchTicket(){
        user.printTickets();
    }
    public List<Train> getTrains(String source,String destination){
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source,destination);
        }catch (IOException ex){
            return new ArrayList<>();
        }
    }
    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeats(Train train,int row, int seat){
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row< seats.size() && seat>= 0 && seat<seats.get(row).size()){
                if (seats.get(row).get(seat)==0){
                    seats.get(row).set(seat,1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true;
                }else {
                    return false;   // Seat is Already Booked
                }
            }else {
                return false; // Invalid Row and Seat Index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }


}
