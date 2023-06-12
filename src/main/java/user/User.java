package user;

import exercise.Exercise;
import exercise.ExerciseDaily;
import exercise.ExerciseList;
import food.Food;
import food.FoodDaily;
import food.FoodList;
import sleep.Sleep;
import sleep.SleepList;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String username;
    private FoodList food;
    private ExerciseList exercise;
    private SleepList sleep;

    public User(String username) {
        this.username = username;
        this.food = new FoodList();
        this.exercise = new ExerciseList();
        this.sleep = new SleepList();
    }

    public void addFood(String name, int calories){
        this.getFoodCurrent().addFood(new Food(name, calories));
    }

    public void addFoodDaily(FoodDaily daily){
        this.food.addFoodDaily(daily);
    }

    public void addExercise(String name, double duration, int calories){
        this.getExerciseCurrent().addExercise(new Exercise(name, duration, calories));
    }

    public void addSleep(double sleep, double awake){
        this.sleep.addSleep(new Sleep(sleep, awake));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FoodList getFood() {
        return food;
    }

    public void setFood(FoodList food) {
        this.food = food;
    }

    public ExerciseList getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseList exercise) {
        this.exercise = exercise;
    }

    public SleepList getSleep() {
        return sleep;
    }

    public void setSleep(SleepList sleep) {
        this.sleep = sleep;
    }

    public FoodDaily getFoodCurrent() {
        if(this.food.getDailyList().size() == 0) this.food.getDailyList().add(new FoodDaily());
        return this.food.getDailyList().get(this.food.getDailyList().size() - 1);
    }

    public ExerciseDaily getExerciseCurrent(){
        if(this.exercise.getDaily().size() == 0) this.exercise.getDaily().add(new ExerciseDaily());
        return this.exercise.getDaily().get(this.exercise.getDaily().size() - 1);
    }

//    public Sleep getSleepCurrent(){
//        return this.sleep.getSleepList().get(this.sleep.getSleepList().size() - 1);
//    }

    public LocalDate getDate(){
        return this.food.getDailyList().get(this.food.getDailyList().size()-1).getDate();
    }
}
