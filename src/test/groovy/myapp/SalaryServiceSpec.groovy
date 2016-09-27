package myapp

import spock.lang.Specification

class SalaryServiceSpec extends Specification {
    SalaryService service = new SalaryService()

    def "should initiate application field"() {
        expect:
        service
    }

}