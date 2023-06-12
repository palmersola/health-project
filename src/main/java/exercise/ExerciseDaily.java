package exercise;

import food.Food;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExerciseDaily {
    private ArrayList<Exercise> exerciseDaily;
    private LocalDate date;
    private int calories;

    public ExerciseDaily() {
        this.exerciseDaily = new ArrayList<>();
        this.date = LocalDate.now();
    }

    public ExerciseDaily(LocalDate date, int calories) {
        this.exerciseDaily = new ArrayList<>();
        this.date = date;
        this.calories = calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCalories() {
        return calories;
    }

    public void addExercise(Exercise exercise){
        this.exerciseDaily.add(exercise);
        this.calories = totalCalories();
    }


    public ArrayList<Exercise> getExerciseDaily() {
        return exerciseDaily;
    }

    public void setExerciseDaily(ArrayList<Exercise> exerciseDaily) {
        this.exerciseDaily = exerciseDaily;
    }

    public int totalCalories(){
        return exerciseDaily
                .stream()
                .mapToInt(Exercise::getCalories)
                .sum();
    }
}
