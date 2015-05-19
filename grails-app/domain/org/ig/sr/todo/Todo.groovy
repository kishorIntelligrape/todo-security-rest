package org.ig.sr.todo

class Todo {

    String name
    String groupName
    boolean complete

    static constraints = {
        groupName nullable: true
    }
}
