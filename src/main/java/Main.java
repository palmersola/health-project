import exercise.Exercise;
import exercise.ExerciseDaily;
import food.Food;
import food.FoodDaily;
import food.FoodList;
import sleep.Sleep;
import user.User;
import user.UserList;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static UserList userList = new UserList();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        loadData();
        System.out.println("Enter Username:");
        String name = scanner.nextLine();
        User user = userList.findUser(name);
        System.out.println("Welcome " + name);
        boolean loop = true;
        while(loop) {
            System.out.println("""
                    Menu
                       1.) See caloric difference for today
                       2.) Check low sleep
                       3.) See all workouts
                       8.) Quit
                    """);
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    int caloricDiff = user.getFoodCurrent().getCalories() - user.getExerciseCurrent().getCalories();
                    System.out.println("You are sitting at " + caloricDiff + " calories after exercise.");
                }
                case 2 -> {
                    for(Sleep sleep : user.getSleep().getSleepList()) {
                        boolean sleeper = true;
                        if (sleep.getTotal()<= user.getSleep().getAverage()*.75) {
                            sleeper = false;
                            System.out.println(sleep.getDate() + " Only slept for" + sleep.getTotal() + " hours");
                        }
                        if(sleeper) System.out.println("You are sleeping great!!!");
                    }
                }
                case 3 -> {
                    for(ExerciseDaily daily : user.getExercise().getDaily()){
                        System.out.println(daily.getDate());
                        for(Exercise exercise : daily.getExerciseDaily()){
                            System.out.println("Excercise: "+ exercise.getName());
                            System.out.println("Duration: "+ exercise.getDuration());
                            System.out.println("Calories burned: "+ exercise.getCalories() + "\n");
                        }
                    }
                }
                case 8 -> loop = false;
            }
        }


        saveData();

    }

    public static void saveData() throws IOException {
        // Write the users and their health data to a file(s)
        FileWriter writer = new FileWriter("src/main/java/data.txt");
        writer.flush();

        for (User user : userList.getUsers()) {
            writer.write(user.getUsername() + ",\n");

            writer.write("Food,\n");
            for (FoodDaily daily : user.getFood().getDailyList()) {
                writer.write(daily.getDate() + ","+ daily.getCalories() +",\n");
                for(Food food : daily.getFoodsDaily()){
                    writer.write(food.getName() + "," + food.getCalories() + ",\n");
                }
            }

            writer.write("Exercise,\n");
            for(ExerciseDaily daily : user.getExercise().getDaily()) {
                writer.write(daily.getDate() + "," + daily.getCalories() + ",\n");
                for(Exercise exercise : daily.getExerciseDaily()){
                    writer.write(exercise.getName() + "," + exercise.getDuration() + "," + exercise.getCalories() + ",\n");
                }
            }

            writer.write("Sleep,\n");
            writer.write(user.getSleep().getAverage() + ",\n");
            for(Sleep sleep : user.getSleep().getSleepList()){
                writer.write(sleep.getSleep() + "," + sleep.getAwake() + "," + sleep.getTotal() + "," + sleep.getDate() + ",\n");
            }
            writer.write("done,\n");
        }
        writer.close();
    }

    public static void loadData() throws IOException {
        FileReader reader = new FileReader("src/main/java/data.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        User currentUser = null;
        while (true) {
            if (line == null || line.isEmpty()) {
                break;
            }
            switch (line) {
                case "Food," -> {
                    ArrayList<FoodDaily> foodDailyList = new ArrayList<>();
                    while (true) {
                        if (line.equals("Exercise,")) break;
                        line = bufferedReader.readLine();
                        String[] foodData = line.split(",");
                        LocalDate date = LocalDate.parse(foodData[0]);
                        int calories = Integer.parseInt(foodData[1]);
                        FoodDaily foodDaily = new FoodDaily(calories, date);
                        foodDailyList.add(foodDaily);

                        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                            if (line.equals("Exercise,")) break;
                            String[] foodInfo = line.split(",");
                            String foodName = foodInfo[0];
                            int foodCalories = Integer.parseInt(foodInfo[1]);
                            Food food = new Food(foodName, foodCalories);
                            foodDaily.addFood(food);
                        }
                        if (line.equals("Exercise,")) break;
                    }
                    if (currentUser != null) {
                        currentUser.getFood().setDailyList(foodDailyList);
                    }
                }
                case "Exercise," -> {
                    // Read exercise data
                    ArrayList<ExerciseDaily> exerciseDailyList = new ArrayList<>();
                    while (true) {
                        if (line.equals("Sleep,")) break;
                        line = bufferedReader.readLine();
                        String[] exerciseData = line.split(",");
                        LocalDate date = LocalDate.parse(exerciseData[0]);
                        int calories = Integer.parseInt(exerciseData[1]);
                        ExerciseDaily exerciseDaily = new ExerciseDaily(date, calories);
                        exerciseDailyList.add(exerciseDaily);

                        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                            if (line.equals("Sleep,")) break;
                            String[] exerciseInfo = line.split(",");
                            String exerciseName = exerciseInfo[0];
                            double duration = Double.parseDouble(exerciseInfo[1]);
                            int exerciseCalories = Integer.parseInt(exerciseInfo[2]);
                            Exercise exercise = new Exercise(exerciseName, duration, exerciseCalories);
                            exerciseDaily.addExercise(exercise);
                        }
                        if (line.equals("Sleep,")) break;
                    }
                    if (currentUser != null) {
                        currentUser.getExercise().setDailyList(exerciseDailyList);
                    }
                }
                case "Sleep," -> {
                    ArrayList<Sleep> sleepList = new ArrayList<>();
                    line = bufferedReader.readLine(); // Read average sleep
                    String[]poopSock = line.split(",");
                    double averageSleep = Double.parseDouble(poopSock[0]);
                    while (true) {
                        line = bufferedReader.readLine();
                        if (line.equals("done,")) break;
                        String[] sleepInfo = line.split(",");
                        double sleepHours = Double.parseDouble(sleepInfo[0]);
                        double awakeHours = Double.parseDouble(sleepInfo[1]);
                        double totalHours = Double.parseDouble(sleepInfo[2]);
                        LocalDate date = LocalDate.parse(sleepInfo[3]);
                        Sleep sleep = new Sleep(sleepHours, awakeHours, totalHours, date);
                        sleepList.add(sleep);
                    }
                    if (currentUser != null) {
                        currentUser.getSleep().setSleepList(sleepList);
                        currentUser.getSleep().setAverage(averageSleep);
                    }
                }
                default -> {
                    // Assume it's a username
                    String[]whoopDaDeScoop = line.split(",");
                    String username = whoopDaDeScoop[0];
                    if (username.equals("done")) {
                        line = bufferedReader.readLine();
                        break;
                    }
                    currentUser = new User(username);
                    userList.addUser(currentUser);
                    line = bufferedReader.readLine();
                }
            }
        }
    }
}
