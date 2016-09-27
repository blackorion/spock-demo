package myapp;

class SalaryService {

    public static final int HOUR_SALARY = 10;

    public int calculate(int workingHours) {
        return salaryFor(standardHoursFrom(workingHours))
                + salaryFor(overtimeHoursFrom(workingHours));
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

}

