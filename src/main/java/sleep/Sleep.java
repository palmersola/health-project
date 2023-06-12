package sleep;

import java.time.LocalDate;
import java.util.Date;

public class Sleep {
    private double sleep;
    private double awake;
    private double total;
    private LocalDate date;

    public Sleep(double sleep, double awake) {
        this.sleep = sleep;
        this.awake = awake;
        this.total = (24 - sleep) + awake;
        this.date = LocalDate.now();
    }

    public Sleep(double sleep, double awake, double total, LocalDate date) {
        this.sleep = sleep;
        this.awake = awake;
        this.total = total;
        this.date = date;
    }

    public double getSleep() {
        return sleep;
    }

    public double getAwake() {
        return awake;
    }

    public double getTotal() {
        return total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setSleep(double sleep) {
        this.sleep = sleep;
    }

    public void setAwake(double awake) {
        this.awake = awake;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void addSleep(Sleep sleep) {
    }
}
