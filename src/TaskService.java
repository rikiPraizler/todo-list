import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskService {
    private TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    //Mark a task as DONE.
    public void markTaskAsDone(int taskId) throws Exception {
        Task task = repo.getById(taskId);
        if (task != null) {
            task.setStatus(EnumStatus.DONE);
            repo.update(task);
        }
    }

    //Search text in task list.
    public List<Task> searchText(String text) throws Exception {
        List<Task> filteredList = new ArrayList<>();
        for (Task task : repo.listAll()) {
            if (task.getTitle().contains(text) ||
                    task.getDescription().contains(text)) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    //Sort the task list by Status.
    public List<Task> sortTaskListByStatus() throws Exception {
        List<Task> sortedTasks = new ArrayList<>(repo.listAll());

        Map<EnumStatus, Integer> order = Map.of(
                EnumStatus.NEW, 1,
                EnumStatus.IN_PROGRESS, 2,
                EnumStatus.DONE, 3
        );

        sortedTasks.sort((t1, t2) -> {
            Integer o1 = order.get(t1.getStatus());
            Integer o2 = order.get(t2.getStatus());

            if (o1 == null) o1 = 0;
            if (o2 == null) o2 = 0;

            return o1 - o2;
        });

        return sortedTasks;
    }
}
