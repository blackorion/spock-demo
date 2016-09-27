package myapp

import spock.lang.Specification

class SalaryServiceSpec extends Specification {
    SalaryService service = new SalaryService()

    def "should return zero when no working hours"() {
        when:
        def salary = service.calculate(0)

        then:
        salary == 0
    }

    def "should return 80 when worked 8 hours"() {
        when:
        def salary = service.calculate(8)

        then:
        salary == 80
    }

    def "should return 110 when worked 10 hours"() {
        when:
        def salary = service.calculate(10)

        then:
        salary == 110
    }
}