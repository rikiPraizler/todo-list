import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        TaskRepository repo = new TaskRepository();
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        Task t;
        Boolean flag = true;
        char c = ' ';
        System.out.println("=== Welcome to the Todo List Manager ===");

        while (flag) {
            System.out.println("\n-------------------------------");
            System.out.println("Main Menu:");

            System.out.println("1. List All Tasks");
            System.out.println("2. Get Task by ID");
            System.out.println("3. Add Task");
            System.out.println("4. Update Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Mark Task as DONE");
            System.out.println("7. Search Tasks");
            System.out.println("8. Sort Tasks by Status");
//            System.out.println("0. Exit");
            System.out.print("Choose option (1-8) ");
//            System.out.print("to exit the Todo List Manager enter 0: ");
            option = scanner.nextInt();

            scanner.nextLine();
            System.out.println("-------------------------------");

            switch (option) {
                case 1:
                    List<Task> allTasks = repo.listAll();
                    if (allTasks.isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        System.out.println("All Tasks:");
                        for (Task task : allTasks) {
                            System.out.println(task.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Task ID to display: ");
                    int idToGet = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Task task = repo.getById(idToGet);
                        System.out.println(task.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: {
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = scanner.nextLine();
                    t = new Task(title, desc, EnumStatus.NEW);
                    repo.add(t);
//                    TaskRepository.add(t);
                    System.out.println("Task added successfully!");
                    System.out.println(t.toString());
                }
                break;

                case 4: {
                    System.out.print("Enter Task ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Task taskToUpdate = repo.getById(updateId);
                    if (taskToUpdate != null) {
                        System.out.print("New Title: ");
                        String title = scanner.nextLine();
                        if (!Objects.equals(title, ""))
                            taskToUpdate.setTitle(title);
                        System.out.print("New Description: ");
                        String desc = scanner.nextLine();
                        if (!Objects.equals(desc, ""))
                            taskToUpdate.setDescription(desc);
                        repo.update(taskToUpdate);
                        System.out.println("Task updated successfully!");
                        System.out.println(taskToUpdate.toString());
                    } else {
                        System.out.println("Task not found!");
                    }
                }
                break;
//                case 4: {
//                    System.out.print("Enter Task ID to display: ");
//                    int idToGet = scanner.nextInt();
//                    scanner.nextLine();
//
//                    try {
//                        Task task = repo.getById(idToGet);
//                        System.out.println(task);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                }
                case 5: {
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        repo.getById(deleteId); // רק לבדוק שהוא קיים
                        repo.delete(deleteId);  // אם עבר את זה - הוא קיים
                        System.out.println("Task deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("Task not found!");
                    }
                }
                break;
                case 6: {
                    System.out.print("Enter Task ID to mark as DONE: ");
                    int doneId = scanner.nextInt();
                    scanner.nextLine();
                    Task task = repo.getById(doneId);
                    if (task != null && task.getStatus() != EnumStatus.DONE) {
                        service.markTaskAsDone(doneId);
                        System.out.println("Task marked as DONE!");
                    } else if (task != null) {
                        System.out.println("Task is already DONE!");
                    } else {
                        System.out.println("Task not found!");
                    }
                }
                break;
                case 7: {
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    List<Task> found = service.searchText(keyword);
                    if (found.isEmpty()) {
                        System.out.println("No tasks found matching: " + keyword);
                    } else {
                        System.out.println("Search Results:");
                        found.forEach(System.out::println);
                    }
                }
                break;
                case 8: {
                    List<Task> all = service.sortTaskListByStatus();
                    if (all.isEmpty()) {
                        System.out.println("No tasks available.");
                    } else {
                        System.out.println("All Tasks (sorted by status):");
                        all.forEach(System.out::println);
                    }
                }
                break;
                default:
                    System.out.println("Invalid option, try again.");
            }
            while (c != 'y' && c != 'n') {
                System.out.println("Do you want to take another action? yes: y  no: n");
                String input = scanner.nextLine();
                if (!input.isEmpty()) c = input.charAt(0);
            }
            if (c == 'n')
                flag = false;
            c = ' ';
        }
        System.out.println("Goodbye!");
    }
}

