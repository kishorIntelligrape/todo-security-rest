package org.ig.sr.todo

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Todo)
class TodoSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test simple validation"() {
        when:
        Todo todo = new Todo(name: name, groupName: groupName, complete: complete)
        todo.validate()
        println(todo.errors)
        println(todo.save(flush: true))

        then:
        hasError == todo.hasErrors()

        where:
        sno | name    | groupName   | complete | hasError
        1   | "Test1" | "TestGroup" | true     | false
        2   | "Test2" | null        | true     | false
        3   | null    | "TestGroup" | false    | true
        4   | "Test4" | "TestGroup" | null     | true
    }
}
