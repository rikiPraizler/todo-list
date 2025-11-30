import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskService {
    //Mark a task as DONE.
    public void markTaskAsDone(int taskId) throws Exception {
        List<Task> tasks = TaskRepository.loadTasksFromFile();
        for (Task t : tasks) {
            if (t.getId() == taskId) {
                t.setStatus(EnumStatus.DONE);
                break;
            }
        }
        TaskRepository.saveTasksToFile(tasks);
    }

    //Search text in task list.
    public List<Task> searchText(String text) throws Exception {
        List<Task> filteredList = new ArrayList<>();
        List<Task> tasks = TaskRepository.loadTasksFromFile();
        for (Task t : tasks) {
            if (t.getTitle().contains(text) || t.getDescription().contains(text)) {
                filteredList.add(t);
            }
        }
        return filteredList;
    }

    //Sort the task list by Status.
    public List<Task> sortTaskListByStatus() throws Exception {
        List<Task> tasks = TaskRepository.loadTasksFromFile();

        Map<EnumStatus, Integer> order = Map.of(
                EnumStatus.NEW, 1,
                EnumStatus.IN_PROGRESS, 2,
                EnumStatus.DONE, 3
        );

        tasks.sort((t1, t2) -> {
            Integer o1 = order.get(t1.getStatus());
            Integer o2 = order.get(t2.getStatus());

            if (o1 == null) o1 = 0;
            if (o2 == null) o2 = 0;

            return o1 - o2;
        });

        return tasks;
    }
}
