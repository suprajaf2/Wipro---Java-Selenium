package day5;

abstract class TimerTask {
    abstract void showTask(String task, double time);
}

class MyTask extends TimerTask {
    public void showTask(String task, double time) {
        String period = (time < 12.00) ? "am" : "pm";
        System.out.println("Now the time is: " + time + " " + period);
        System.out.println("So your task is: " + task);
        System.out.println("---------------------------");
    }
}
public class TimerPrgram {
    public static void main(String[] args) {
        String[] tasks = {
            "Morning Exercise",
            "Attend Online Class",
            "Complete Assignment",
            "Take a Short Nap",
            "Revise Today's Topics"
        };

        double[] times = {6.00, 9.00, 11.00, 14.00, 18.00};

        MyTask task = new MyTask();

        for (int i = 0; i < tasks.length; i++) {
            task.showTask(tasks[i], times[i]);
        }
    }
}
