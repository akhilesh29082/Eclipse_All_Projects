package S1;

 public class MovieBooking {

    private int availableSeats;

    public MovieBooking(int seats) {
        this.availableSeats = seats;
    }

    public synchronized void bookTicket(String userName, int seatsRequested) {

        System.out.println(userName + " is trying to book " + seatsRequested + " seat(s)");

        if (seatsRequested <= availableSeats) {
            System.out.println("Seats available. Processing booking for " + userName);

            try {
                Thread.sleep(1000); // Simulate booking delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            availableSeats -= seatsRequested;
            System.out.println("Booking successful for " + userName);
        } else {
            System.out.println("Booking failed for " + userName + " (Not enough seats)");
        }

        System.out.println("Remaining seats: " + availableSeats);
        System.out.println("--------------------------------");
    }
}

