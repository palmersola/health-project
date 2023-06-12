package food;

import java.time.LocalDate;
import java.util.ArrayList;

public class FoodDaily {
    private ArrayList<Food> foodsDaily;
    private int calories;
    private LocalDate date;

    public FoodDaily() {
        this.foodsDaily = new ArrayList<>();
        this.calories = totalCalories();
        this.date = LocalDate.now();
    }

    public FoodDaily(int calories, LocalDate date) {
        this.foodsDaily = new ArrayList<>();
        this.calories = calories;
        this.date = date;
    }

    public FoodDaily addFood(Food food){
        this.foodsDaily.add(food);
        setCalories();
        return null;
    }

    public void setCalories() {
        this.calories = totalCalories();
    }

    public ArrayList<Food> getFoodsDaily() {
        return foodsDaily;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setFoodsDaily(ArrayList<Food> foodsDaily) {
        this.foodsDaily = foodsDaily;
    }

    public int getCalories() {
        return calories;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int totalCalories(){
        return foodsDaily
                .stream()
                .mapToInt(Food::getCalories)
                .sum();
    }
}
