package org.ig.sr.todo

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.test.spock.IntegrationSpec
import spock.lang.Shared

/**
 * Created by intelligrape on 19/5/15.
 */
class TodoFunctionalSpec  extends IntegrationSpec {

    @Shared
    def grailsApplication

    def setup() {
        // Initialize DB
        new Todo(name: "test", complete: false).save(flush: true)
    }

    def cleanup() {
    }

    void "test creating a book"() {
        given:
        RestBuilder rest = new RestBuilder()

        when:
        RestResponse response = rest.post("http://localhost:8080/${grailsApplication.metadata.'app.name'}/todos") {
            json([
                    name: "title2",
                    complete: false
            ])
        }

        then:
        response.status == 200
        response.json.name == "title2"
        response.json.complete == false
        Todo.count == 2
    }

}
