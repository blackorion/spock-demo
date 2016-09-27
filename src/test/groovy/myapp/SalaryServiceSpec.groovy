package myapp

import spock.lang.Specification
import spock.lang.Unroll

class SalaryServiceSpec extends Specification {
    SalaryService service = new SalaryService()

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

}