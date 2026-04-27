package S1;

public class MovieTicketSystem {

    public static void main(String[] args) {

        MovieBooking bookingSystem = new MovieBooking(5);

        UserThread user1 = new UserThread(bookingSystem, "Ajit", 2);
        UserThread user2 = new UserThread(bookingSystem, "Ayush", 2);
        UserThread user3 = new UserThread(bookingSystem, "Akhil", 2);

        user1.start();
        user2.start();
        user3.start();

        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All movie ticket booking requests completed.");
    }
}
