//Represents a task in the todo-list application.
//olds the task's ID, title, description and status.
//and a status defined by EnumStatus.

public class Task {

    //Auto-incremented counter used to assign unique IDs to tasks.
    private static int nextId = 1;

    //Unique identifier of the task.
    private int id;

    //Short title describing the task.
    private String title;

    //Optional detailed description of the task.
    private String description;

    //Current status of the task.
    private EnumStatus status;

    public Task() {
        this.id = nextId++;
        this.title = "";
        this.description = "";
        this.status = EnumStatus.NEW;
    }

    public Task(String title) {
        this.id = nextId++;
        this.title = title;
        this.description = "";
        this.status = EnumStatus.NEW;
    }

    public Task(String title, String description) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.status = EnumStatus.NEW;
    }

    public Task(String title, EnumStatus status) {
        this.id = nextId++;
        this.title = title;
        this.description = "";
        this.status = status;
    }

    public Task(String title, String description, EnumStatus status) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.status = status;
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
        String[] parts = json.split(",");
        for (String part : parts) {
            String[] kv = part.split(":");
            switch (kv[0].trim()) {
                case "id" -> task.setId(Integer.parseInt(kv[1].trim()));
                case "title" -> task.setTitle(kv[1].trim());
                case "description" -> task.setDescription(kv[1].trim());
                case "status" -> task.setStatus(EnumStatus.valueOf(kv[1].trim()));
            }
        }
        return task;
    }
}
