

public class Task {

    private static int nextId = 1;
    private int id;
    private String title;
    private String description;
    private EnumStatus status;

    public Task(String title) {
        this.id = nextId++;
        this.title = title;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Task: " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status;
    }
}
