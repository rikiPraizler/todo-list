

public class Task {

    private static int nextId = 1;
    private int id;
    private String title;
    private String description;
    private EnumStatus status;

    public Task() {
        this.id = nextId++;
    }

    public Task(String title, String description, EnumStatus status) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task(String title, EnumStatus status) {
        this.id = nextId++;
        this.title = title;
        this.status = status;
    }

}
