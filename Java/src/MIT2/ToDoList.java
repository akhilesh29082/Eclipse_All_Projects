package MIT2;

import java.util.*;

public class ToDoList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> tasks = new LinkedList<>();

        while (true) {
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Add a task");
            System.out.println("2. View all tasks");
            System.out.println("3. Remove a completed task");
            System.out.println("4. Display tasks in reverse order");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter task: ");
                    String task = sc.nextLine();
                    tasks.add(task);
                    break;

                case 2:
                    System.out.println("Your Tasks:");
                    for (String t : tasks)
                        System.out.println("- " + t);
                    break;

                case 3:
                    System.out.print("Enter task to remove: ");
                    String removeTask = sc.nextLine();
                    if (tasks.remove(removeTask))
                        System.out.println("Task removed!");
                    else
                        System.out.println("Task not found!");
                    break;

                case 4:
                    System.out.println("Tasks in Reverse:");
                    ListIterator<String> itr = tasks.listIterator(tasks.size());
                    while (itr.hasPrevious())
                        System.out.println("- " + itr.previous());
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
