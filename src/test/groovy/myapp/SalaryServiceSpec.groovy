package myapp

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import spock.lang.Specification
import spock.lang.Unroll

class SalaryServiceSpec extends Specification {
    @Collaborator
    HourValueProvider provider = Mock()

    @Subject
    SalaryService service

    void setup() {
        provider.getValue() >> 10
    }

    def "should return zero when no working hours"() {
        when:
        def salary = service.calculate(0)

        then:
        salary == 0
    }

    @Unroll
    def "should return #expectedSalary when worked #workingHours hours"() {
        when:
        def salary = service.calculate(workingHours)

        then:
        salary == expectedSalary

        where:
        workingHours | expectedSalary
        8            | 80
        10           | 110
        20           | 260
    }

    def "should notify boss when entering more then 20 hours"() {
        setup:
        SalaryCalculatorListener mockBoss = Mock()
        service.addListener(mockBoss)

        when:
        service.calculate(21)

        then:
        1 * mockBoss.notifySystemHacked(21)
    }

    def "should calculate salary based on strategy"() {
        setup:
        HourValueProvider provider = Mock()
        provider.getValue() >> 100
        service.hourValueProvider = provider

        when:
        def salary = service.calculate(8)

        then:
        salary == 800
    }
}