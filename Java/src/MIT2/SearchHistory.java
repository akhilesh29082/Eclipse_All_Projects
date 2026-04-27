package MIT2;

import java.util.*;

public class SearchHistory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<String> searches = new Vector<>();

        while (true) {
            System.out.println("\n--- Search History ---");
            System.out.println("1. Add new search term");
            System.out.println("2. Display all search terms");
            System.out.println("3. Remove duplicate terms (keep latest)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter search term: ");
                    String term = sc.nextLine();
                    searches.add(term);
                    break;

                case 2:
                    System.out.println("Search Terms:");
                    for (String s : searches)
                        System.out.println("- " + s);
                    break;

                case 3:
                    LinkedHashSet<String> set = new LinkedHashSet<>();
                    ListIterator<String> itr = searches.listIterator(searches.size());
                    while (itr.hasPrevious()) {
                        String s = itr.previous();
                        set.add(s);
                    }
                    searches.clear();
                    List<String> temp = new ArrayList<>(set);
                    Collections.reverse(temp);
                    searches.addAll(temp);
                    System.out.println("Duplicates removed.");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

