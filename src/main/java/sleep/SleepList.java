package sleep;

import java.util.ArrayList;
import java.util.OptionalDouble;

public class SleepList {
    private ArrayList<Sleep> sleepList;
    private double average;

    public SleepList() {
        this.sleepList = new ArrayList<>();
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void addSleep(Sleep sleep){
        this.sleepList.add(sleep);
        setAverage();
    }

    public ArrayList<Sleep> getSleepList() {
        return sleepList;
    }

    public void setSleepList(ArrayList<Sleep> sleepList) {
        this.sleepList = sleepList;
    }

    public double getAverage() {
        return average;
    }


    public void setAverage() {
        this.average = sleepList
            .stream()
            .mapToDouble(Sleep::getTotal)
            .average()
                .getAsDouble();
        //return average.isPresent() ? average.getAsDouble() : 0;
    }
}
