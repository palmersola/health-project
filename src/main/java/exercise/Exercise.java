package exercise;

public class Exercise {
    private String name;
    private double duration;
    private int calories;

    public Exercise(String name, double duration, int calories) {
        this.name = name;
        this.duration = duration;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public int getCalories() {
        return calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
