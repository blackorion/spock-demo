package myapp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class SalaryService {

    public static final int POSSIBLE_WORKING_HOURS = 20;
    private final HourValueProvider hourValueProvider;
    private List<SalaryCalculatorListener> listeners = new ArrayList<>();

    public SalaryService(HourValueProvider provider) {
        this.hourValueProvider = provider;
    }

    public SalaryService() {
        this(new D1HourValueProvider());
    }

    public BigDecimal calculate(int workingHours) {
        if (workingHours > POSSIBLE_WORKING_HOURS)
            notifyListeners(workingHours);

        return salaryFor(standardHoursFrom(workingHours))
                .add(salaryFor(overtimeHoursFrom(workingHours)));
    }

    private void notifyListeners(int workingHours) {
        listeners.forEach(l -> l.notifySystemHacked(workingHours));
    }

    private BigDecimal salaryFor(float hours) {
        return hourValueProvider.getValue().multiply(BigDecimal.valueOf(hours));
    }

    private int standardHoursFrom(int hours) {
        return hours > 8 ? 8 : hours;
    }

    private float overtimeHoursFrom(int hours) {
        float overtime = hours > 8 ? hours - 8 : 0;

        return overtime * 1.5f;
    }

    public void addListener(SalaryCalculatorListener listener) {
        listeners.add(listener);
    }
}

