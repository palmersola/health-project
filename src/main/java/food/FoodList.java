package food;

import java.util.ArrayList;

public class FoodList {
    private ArrayList<FoodDaily> dailyList;

    public FoodList() {
        this.dailyList = new ArrayList<>();
    }

    public void addFoodDaily(FoodDaily dailyList) {
        this.dailyList.add(dailyList);
    }

    public ArrayList<FoodDaily> getDailyList() {
        return this.dailyList;
    }

    public void addNew() {
        this.dailyList.add(new FoodDaily());
    }

    public void setDailyList(ArrayList<FoodDaily> dailyList) {
        this.dailyList = dailyList;
    }
}
