//Represents a task in the todo-list application.
//holds the task's ID, title, description,
//and a status defined by EnumStatus.
//Provides multiple constructors for different initialization options.

public class Task {

    //Auto-incremented counter used to assign unique IDs to tasks.
    private static int nextId = 4;

    //Unique identifier of the task.
    private int id;

    //Short title describing the task.
    private String title;

    //Optional detailed description of the task.
    private String description;

    //Current status of the task.
    private EnumStatus status;

    public Task(String title, String description, EnumStatus status) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task() {
        this("", "", EnumStatus.NEW);
    }

    public Task(String title) {
        this(title, "", EnumStatus.NEW);
    }

    public Task(String title, String description) {
        this(title, description, EnumStatus.NEW);
    }

    public Task(String title, EnumStatus status) {
        this(title, "", status);
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    //Returns a readable string of the task.
    //useful for debugging or logging.
    @java.lang.Override
    public java.lang.String toString() {
        return "Task: " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status;
    }

    //Converting from Task object to Json
    public String taskToJson() {
        return "{"
                + "\"id\":" + id + ","
                + "\"title\":\"" + title + "\","
                + "\"description\":\"" + description + "\","
                + "\"status\":\"" + status + "\""
                + "}";
    }

    //Converting from Json to Task object
    public static Task jsonToTask(String json) {
        Task task = new Task();
        json = json.replaceAll("[{}\"]", "");

        String[] parts = json.split(",(?=[a-zA-Z]+:)");

        for (String part : parts) {
            String[] kv = part.split(":", 2);
            if (kv.length < 2) continue;
            String key = kv[0].trim();
            String value = kv[1].trim();

            switch (key) {
                case "id" -> task.setId(Integer.parseInt(value));
                case "title" -> task.setTitle(value);
                case "description" -> task.setDescription(value);
                case "status" -> task.setStatus(EnumStatus.valueOf(value));
            }
        }

        return task;
    }


}
