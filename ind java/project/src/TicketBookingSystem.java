import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Abstract ticket class
abstract class Ticket {
    public String ticketId;
    public String passengerName;
    public double ticketPrice = 2500;

    public Ticket(String ticketId, String passengerName) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
    }

    abstract void displayTicketDetails();
}

// Flight ticket inherits from Ticket
class FlightTicket extends Ticket {
    public String flightNumber;
    public String airlineName;
    public String departureAirport;
    public String destinationAirport;

    public FlightTicket(String ticketId, String passengerName, String flightNumber,
            String airlineName, String departureAirport, String destinationAirport) {
        super(ticketId, passengerName);
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
    }

    // Display ticket details
    void displayTicketDetails() {
        System.out.println("Ticket Id : " + this.ticketId);
        System.out.println("Passenger Name : " + this.passengerName);
        System.out.println("Ticket Price : " + this.ticketPrice);
        System.out.println("Flight Number : " + this.flightNumber);
        System.out.println("Airline Name : " + this.airlineName);
        System.out.println("Departure Airport : " + this.departureAirport);
        System.out.println("Destination Airport : " + this.destinationAirport);
    }
}

// Train ticket inherits from Ticket
class TrainTicket extends Ticket {
    public String trainNumber;
    public String departureStation;
    public String destinationStation;
    public int seatNumber;

    public TrainTicket(String ticketId, String passengerName, String trainNumber,
            String departureStation, String destinationStation, int seatNumber) {
        super(ticketId, passengerName);
        this.trainNumber = trainNumber;
        this.departureStation = departureStation;
        this.destinationStation = destinationStation;
        this.seatNumber = seatNumber;
    }

    void displayTicketDetails() {
        System.out.println("Ticket Id : " + this.ticketId);
        System.out.println("Passenger Name : " + this.passengerName);
        System.out.println("Ticket Price : " + this.ticketPrice);
        System.out.println("Train Number : " + this.trainNumber);
        System.out.println("Departure Station : " + this.departureStation);
        System.out.println("Destination Station : " + this.destinationStation);
        System.out.println("Seat Number : " + this.seatNumber);
    }
}

interface TicketBooking {
    void bookTicket();

    void displayTicket();
}

// Custom exceptions
class TicketNotFoundException extends RuntimeException {
    TicketNotFoundException(String message) {
        super(message);
    }
}

class MaximumTicketReachedException extends RuntimeException {
    MaximumTicketReachedException(String message) {
        super(message);
    }
}

class TicketBookingImpl implements TicketBooking {
    public ArrayList<Ticket> tickets = new ArrayList<>();

    public void displayTicket() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Ticket Id : ");
        String ticketId = sc.nextLine();
        boolean ticketFound = false;

        for (Ticket ticket : tickets) {
            if (ticketId.equals(ticket.ticketId)) {
                ticket.displayTicketDetails();
                ticketFound = true;
                break;
            }
        }

        if (!ticketFound) {
            throw new TicketNotFoundException("Ticket Not Found");
        }
    }

    public void bookTicket() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome To R & K Ticket Booking");
        System.out.print("Enter Number Of Tickets : ");
        int count = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (count > 2 && tickets.size() >= 2) {
            throw new MaximumTicketReachedException("Not More Than 2 Tickets Allowed");
        }

        System.out.println("1. Flight");
        System.out.println("2. Train");
        System.out.print("Enter Your Choice : ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Ticket Id : ");
                String ticketId = sc.nextLine();
                System.out.print("Enter Passenger Name : ");
                String passengerName = sc.nextLine();
                System.out.print("Enter Flight Number : ");
                String flightNumber = sc.nextLine();
                System.out.print("Enter Airline Name : ");
                String airlineName = sc.nextLine();
                System.out.print("Enter Departure Airport : ");
                String departureAirport = sc.nextLine();
                System.out.print("Enter Destination Airport : ");
                String destinationAirport = sc.nextLine();
                tickets.add(new FlightTicket(ticketId, passengerName, flightNumber, airlineName,
                        departureAirport, destinationAirport));
                break;

            case 2:
                System.out.print("Enter Ticket Id : ");
                String ticketId1 = sc.nextLine();
                System.out.print("Enter Passenger Name : ");
                String passengerName1 = sc.nextLine();
                System.out.print("Enter Train Number : ");
                String trainNumber = sc.nextLine();
                System.out.print("Enter Departure Station : ");
                String departureStation = sc.nextLine();
                System.out.print("Enter Destination Station : ");
                String destinationStation = sc.nextLine();
                System.out.print("Enter Seat Number : ");
                int seatNumber = sc.nextInt();
                sc.nextLine(); // Consume newline
                tickets.add(new TrainTicket(ticketId1, passengerName1, trainNumber, departureStation,
                        destinationStation, seatNumber));
                break;

            default:
                throw new InputMismatchException("Entered Choice is Not Valid");
        }
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketBookingImpl tb = new TicketBookingImpl();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Book Ticket");
            System.out.println("2. Display Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter Your Choice : ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    tb.bookTicket();
                    break;
                case 2:
                    try {
                        tb.displayTicket();
                    } catch (TicketNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Thank you");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
}