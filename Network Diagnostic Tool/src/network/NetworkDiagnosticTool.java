package network;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkDiagnosticTool { 
    public static void generateReport(String input) {
        try {
            System.out.println("\n*************NETWORK DIAGNOSTIC REPORT*************");
            System.out.println("Input Provided : " + input);

            // Get primary InetAddress
            InetAddress address = InetAddress.getByName(input);

            System.out.println("\n--- BASIC HOST INFORMATION ---");
            System.out.println("Host Name           : " + address.getHostName());
            System.out.println("Canonical Host Name : " + address.getCanonicalHostName());
            System.out.println("IP Address          : " + address.getHostAddress());

            System.out.println("\n--- ADDRESS TYPE CHECKS ---");
            System.out.println("Is Any Local Address   : " + address.isAnyLocalAddress());
            System.out.println("Is Loopback Address    : " + address.isLoopbackAddress());
            System.out.println("Is Link Local Address  : " + address.isLinkLocalAddress());
            System.out.println("Is Site Local Address  : " + address.isSiteLocalAddress());
            System.out.println("Is Multicast Address   : " + address.isMulticastAddress());

            System.out.println("\n--- REACHABILITY TEST ---");
            boolean reachable = address.isReachable(3000);
            System.out.println("Reachable within 3 sec : " + reachable);

            System.out.println("\n--- ALL IPs FOR HOST (DNS RESOLUTION) ---");
            InetAddress[] allAddresses = InetAddress.getAllByName(input);
            for (int i = 0; i < allAddresses.length; i++) {
                System.out.println("IP " + (i + 1) + " : " + allAddresses[i].getHostAddress());
            }

            System.out.println("\n--- LOCAL HOST INFORMATION ---");
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("Local Host Name : " + local.getHostName());
            System.out.println("Local IP        : " + local.getHostAddress());

            System.out.println("\n========== END OF REPORT ==========");

        } catch (UnknownHostException e) {
            System.out.println("\nError: Unable to resolve host -> " + input);
            System.out.println("Reason: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nUnexpected error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("      JAVA NETWORK DIAGNOSTIC TOOL");

        System.out.print("Enter Hostname or IP Address: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Error: Input cannot be empty.");
        } else {
            generateReport(input);
        }

        scanner.close();
    }
}
