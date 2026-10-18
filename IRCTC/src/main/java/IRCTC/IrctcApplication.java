package IRCTC;
import java.util.*;

import IRCTC.Entities.Train;
import IRCTC.Entities.User;
import IRCTC.Service.UserBookingService;
import IRCTC.util.UserServiceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@SpringBootApplication
public class IrctcApplication {

	public static void main(String[] args) {
		System.out.println("Running Train Booking System\n");
		Scanner input = new Scanner(System.in);
		int option = 0;
		UserBookingService userBookingService;

		try {
            userBookingService = new UserBookingService();
        }catch (IOException ex){
			System.out.println("There is Something wrong!");
			return;
		}
		while (option!=7){
			System.out.println("Choose Option");
			System.out.println("1. SignUp");
			System.out.println("2. Login");
			System.out.println("3. Fetch Booking");
			System.out.println("4. Search Train");
			System.out.println("5. Book a Seat");
			System.out.println("6. Cancel my Booking");
			System.out.println("7. Exit the App");
			System.out.print("\nSelect The Option Above Given: ");
			option = input.nextInt();
			Train trainSelectedForBooking = new Train();
			switch (option){
				case 1:{
					System.out.print("\nEnter the Username to SignUp: ");
					String nameToSignUp = input.next();
					System.out.print("\nEnter The Password For SignUp: ");
					String passwordToSignUp = input.next();
					User userToSignup = new User(nameToSignUp,passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp),new ArrayList<>(),UUID.randomUUID().toString());
					userBookingService.signUp(userToSignup);
					break;}

				case 2:{
					System.out.print("\nEnter The Username to Login: ");
					String nameToLogin = input.next();
					System.out.print("\nEnter The Password To Login: ");
					String passwordToLogin = input.next();
					User userToLogin = new User(nameToLogin,passwordToLogin,UserServiceUtil.hashPassword(passwordToLogin),new ArrayList<>(),UUID.randomUUID().toString());
					try{
						userBookingService = new UserBookingService(userToLogin);
					}catch (IOException ex){
						System.out.println("Please Enter Correct Details!");
						return;

					}
					break;
				}
				case 3:{
					System.out.print("\nFetch Your Ticket Bookings");
					userBookingService.fetchBookings();
					break;
				}
				case 4: {
					System.out.print("\nType Your Source Station: ");
					String source = input.next();
					System.out.print("\nType Your Destination Station: ");
					String destination = input.next();
					List<Train> trains = userBookingService.getTrains(source, destination);
					int index = 1;
					for (Train t: trains){
						System.out.println(index+" Train id: "+t.getTrainId());
						for (Map.Entry<String,String> entry: t.getStationTime().entrySet()){
							System.out.println("Station: "+entry.getKey()+" Time: "+entry.getValue());
						}
					}
					System.out.print("\nSelect a Train by Typing 1,2,3,...: ");
					trainSelectedForBooking = trains.get(input.nextInt());
					break;
				}
				case 5: {
					System.out.print("\nSelect a Seat out of These Seats: ");
					List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
					for(List<Integer> row: seats){
						for (Integer val: row){
							System.out.print(val+" ");
						}
						System.out.println();
					}
					System.out.println("Select the seat by Typing the Row and Column");
					System.out.print("Enter the Row: ");
					int row = input.nextInt();
					System.out.print("Enter the Column: ");
					int col = input.nextInt();
					System.out.println("Booking Your Seat....");
					Boolean booked = userBookingService.bookTrainSeats(trainSelectedForBooking, row, col);
					if (booked.equals(Boolean.TRUE)){
						System.out.println("Booked! Enjoy Your Journey");
					}
					else {
						System.out.println("Can't Book this Seat!");
					}
					break;
				}
				case 6:{
					System.out.println("Welcome to Ticket Cancellation\n");
					System.out.print("Enter Your Ticket Id: ");
					String ticketId = input.next();
					boolean cancel =userBookingService.cancelBooking(ticketId);
					if (cancel){
						System.out.println("Your Ticket TicketId no. "+ticketId+" is Cancelled Successfully!");
					}else {
						System.out.println("Ticket is Not Cancelled!");
					}
					break;
				}
                default:{
					break;
				}



			}
		}
	}
}
