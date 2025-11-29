import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private List<Task> loadTasksFromFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File("../data/todo-list.json");
        if (!file.exists()) return tasks;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }

            String content = sb.toString();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
                if (!content.isEmpty()) {
                    String[] items = content.split("\\},\\{");
                    for (int i = 0; i < items.length; i++) {
                        String item = items[i];
                        if (i != 0) item = "{" + item;
                        if (i != items.length - 1) item = item + "}";
                        tasks.add(Task.jsonToTask(item));
                    }
                }
            }

        } catch (Exception e){
            throw e;
        }

        return tasks;
    }
}

