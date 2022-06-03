package hu.progmatic;

public class Runner {
    private String name;
    private int id;
    private String category;
    private String time;
    private int percentage;

    public Runner() {
    }

    public Runner(String name, int id, String category, String time, int percentage) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.time = time;
        this.percentage = percentage;
    }

    public Runner(String line) {
        String[] pieces = line.split(";");
        this.name = pieces[0];
        this.id = Integer.parseInt(pieces[1]);
        this.category = pieces[2];
        this.time = pieces[3];
        this.percentage = Integer.parseInt(pieces[4]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Runner{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", category='" + category + '\'' +
                ", time='" + time + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
