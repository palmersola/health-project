package exercise;

import java.util.ArrayList;

public class ExerciseList {
    private ArrayList<ExerciseDaily> daily;

    public ExerciseList() {
        this.daily = new ArrayList<>();
    }

    public ArrayList<ExerciseDaily> getDaily() {
        return daily;
    }

    public void setDaily(ExerciseDaily daily) {
        this.daily.add(daily);
    }

    public void setDailyList(ArrayList<ExerciseDaily> exerciseDailyList) {
        this.daily = exerciseDailyList;
    }
}
