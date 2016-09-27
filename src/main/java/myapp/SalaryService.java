package myapp;

import java.util.ArrayList;
import java.util.List;

class SalaryService {

    public static final int HOUR_SALARY = 10;
    public static final int POSSIBLE_WORKING_HOURS = 20;
    private List<SalaryCalculatorListener> listeners = new ArrayList<>();

    public int calculate(int workingHours) {
        if (workingHours > POSSIBLE_WORKING_HOURS)
            notifyListeners(workingHours);

        return salaryFor(standardHoursFrom(workingHours))
                + salaryFor(overtimeHoursFrom(workingHours));
    }

    private void notifyListeners(int workingHours) {
        listeners.forEach(l -> l.notifySystemHacked(workingHours));
    }

    private int salaryFor(int hours) {
        return hours * HOUR_SALARY;
    }

    private int standardHoursFrom(int hours) {
        return hours > 8 ? 8 : hours;
    }

    private int overtimeHoursFrom(int hours) {
        int overtime = hours > 8 ? hours - 8 : 0;

        return (int) (overtime * 1.5);
    }

    public void addListener(SalaryCalculatorListener listener) {
        listeners.add(listener);
    }
}

