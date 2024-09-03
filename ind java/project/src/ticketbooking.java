import java.util.InputMismatchException;
import java.util.Scanner;

//abstract ticket class
abstract class Ticket {

    public String ticketId;
    public String passengerName;
    public double ticketPrice = 2500;

    public Ticket(String ticketId, String passengerName, double ticketPrice) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.ticketPrice = 2500;
    }

    abstract void displayTicketDetails();
}

// flight ticket inherite with ticket
class FlightTicket extends Ticket {

    public String flightNumber;
    public String airlineName;
    public String departureAirport = " Ahmedabad";
    public String destinationAirport = "Banglore";

    public FlightTicket(String ticketId, String passengerName, double ticketPrice, String flightNumber,
            String airlineName, String departureAirport, String destinationAirport) {
        super(ticketId, passengerName, ticketPrice);
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.departureAirport = "Ahemedabad";
        this.destinationAirport = "Banglore";
    }

    // display ticket
    void displayTicketDetails() {
        System.out.println("Ticket Id : " + this.ticketId);
        System.out.println("Passenger Name : " + this.passengerName);
        System.out.println("Ticket Price : " + this.ticketPrice);
        System.out.println("Flight Number : " + this.flightNumber);
        System.out.println("Airline Name : " + this.airlineName);
        System.out.println("Depature Airport : " + this.departureAirport);
        System.out.println("Destination Airport : " + this.destinationAirport);
    }
}

// train ticket inherite with ticket
class TrainTicket extends Ticket {

    public String trainNummber;
    public String depatureStation;
    public String destinationStation;
    public int seatNumber;

    public TrainTicket(String ticketId, String passengerName, double ticketPrice, String trainNummber,
            String depatureStation, String destinationStation, int seatNumber) {
        super(ticketId, passengerName, ticketPrice);
        this.trainNummber = trainNummber;
        this.depatureStation = depatureStation;
        this.destinationStation = destinationStation;
        this.seatNumber = seatNumber;
    }

    void displayTicketDetails() {
        System.out.println("Ticket Id : " + this.ticketId);
        System.out.println("Passenger Name : " + this.passengerName);
        System.out.println("Ahmedabad to benglore ticket Price : " + this.ticketPrice);
        System.out.println("Train Number : " + this.trainNummber);
        System.out.println("Depature Station : " + this.depatureStation);
        System.out.println("Destination Station : " + this.depatureStation);
        System.out.println("Seat Number : " + this.seatNumber);
    }

}

/**
 * InnerFlightTicketBooking
 */
interface TicketBooking {

    void bookTicket();

    void displayTicket();

}

// runtime exception
class TicketNotFoundException extends RuntimeException {
    TicketNotFoundException(String t) {
        super(t);
    }
}

class MaximumTicketReachedException extends RuntimeException {
    MaximumTicketReachedException(String t) {
        super(t);
    }
}

// interface ticket booking
class TicketBookingImpl implements TicketBooking {
    public Ticket[] tickets = new Ticket[2];
    public int ticketCount = 0;

    public void displayTicket() {
        int c = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Ticket Id : ");
        String ticket = sc.nextLine();
        for (int i = 0; i < ticketCount; i++) {
            if (ticket.equals(tickets[i].ticketId)) {
                tickets[i].displayTicketDetails();
                c++;
            }
            if (c != 1) {
                try {
                    throw new TicketNotFoundException("Ticket Not Found");
                } catch (TicketNotFoundException t) {
                    System.out.println(t);
                }
            }
        }
    }

    public void bookTicket() {
        Scanner sc = new Scanner(System.in);
        // for (int i = 0; i < tickets.length; i++) {

        System.out.println("Welcome To R & K Ticket Booking");
        System.out.print("Enter Number Of Tickets : ");
        int count = sc.nextInt();
        if (count > 2 && ticketCount >= 2) {
            try {
                throw new MaximumTicketReachedException("Not More Than 2 Allowed");
            } catch (MaximumTicketReachedException m) {
                System.out.println(m);
                return;
            }
        } else {

            System.out.println("1.Flight");
            System.out.println("2.Train");
            System.out.print("Enter Your Choice : ");
            int Choice = sc.nextInt();
            sc.nextLine();
            double ticketPrice;
            switch (Choice) {
                case 1:
                    System.out.print("Enter Ticket Id : ");
                    String ticketId = sc.nextLine();
                    System.out.print("Enter Passenger Name : ");
                    String passengerName = sc.nextLine();
                    System.out.print("Enter Ticket Price : ");
                    sc.nextLine();
                    System.out.print("Enter Flight Number : ");
                    String flightNumber = sc.nextLine();
                    System.out.print("Enter Airline Name : ");
                    String airlineName = sc.nextLine();
                    System.out.print("Enter Depature Airport : ");
                    String depatureAirport = sc.nextLine();
                    System.out.print("Enter Destination Airport : ");
                    String destinationAirport = sc.nextLine();
                    tickets[0] = new FlightTicket(ticketId, passengerName, ticketPrice, flightNumber, airlineName,
                            depatureAirport, destinationAirport);
                    ticketCount += 1;
                    break;

                case 2:
                    System.out.print("Enter Ticket Id : ");
                    String ticketId1 = sc.nextLine();
                    System.out.print("Enter Passenger Name : ");
                    String passengerName1 = sc.nextLine();
                    System.out.print("Enter Ticket Price : ");
                    double ticketPrice1 = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Train Number : ");
                    String trainNumber = sc.nextLine();
                    System.out.print("Enter Depature Station : ");
                    String depatureStation = sc.nextLine();
                    System.out.print("Enter Destination Station : ");
                    String destinationStation = sc.nextLine();
                    System.out.print("Enter Seat Number : ");
                    int seatNumber = sc.nextInt();
                    sc.nextLine();
                    tickets[1] = new TrainTicket(ticketId1, passengerName1, ticketPrice1, trainNumber, depatureStation,
                            destinationStation, seatNumber);
                    ticketCount += 1;
                    break;
                default:
                    try {
                        throw new InputMismatchException("Entered Choice is Not Valid");
                    } catch (InputMismatchException e) {
                        System.out.println("Entered Choice is Not Valid");
                    }
                    break;
                // }
            }
        }
    }

}

class FlightTicketBooking {
    public static void main(String[] args) {
        TicketBookingImpl tb = new TicketBookingImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Booking Ticket");
        System.out.println("2.Display Ticket");
        System.out.println("3.Exit");
        System.out.print("Enter Your Choice : ");
        int Choice = sc.nextInt();
        sc.nextLine();
        switch (Choice) {
            case 1:
                tb.bookTicket();
                break;
            case 2:
                tb.displayTicket();
                break;
            case 3:
                System.out.println("thank you");
                break;
            default:
                try {
                    throw new InputMismatchException("Entered Choice is Not Valid");
                } catch (InputMismatchException e) {
                    System.out.println("Entered Choice is Not Valid");
                }
                break;
        }
    }
}