package S1;

public class UserThread extends Thread {

    private MovieBooking booking;
    private String userName;
    private int seats;

    public UserThread(MovieBooking booking, String userName, int seats) {
        this.booking = booking;
        this.userName = userName;
        this.seats = seats;
    }

    @Override
    public void run() {
        booking.bookTicket(userName, seats);
    }
}
