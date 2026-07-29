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

			option = input.nextInt();
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
					userBookingService.fetchTicket();
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
				}


			}
		}
	}
}
