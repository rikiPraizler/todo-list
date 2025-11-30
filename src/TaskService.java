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
        Map<String, Integer> order = Map.of(
                "NEW", 1,
                "IN_PROGRESS", 2,
                "DONE", 3
        );
        tasks.sort((t1, t2) -> order.get(t1.getStatus()) - order.get(t2.getStatus()));
        return tasks;
    }

}
