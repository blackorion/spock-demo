package myapp;

import java.math.BigDecimal;

public class D1HourValueProvider implements HourValueProvider {
    @Override
    public BigDecimal getValue() {
        return BigDecimal.valueOf(10);
    }
}
