package com.efimchick.ifmo.util;

import java.util.Map;

public class CourseResult {
    private final Person person;
    private final Map<String, Integer> taskResults;

    public CourseResult(final Person person, final Map<String, Integer> taskResults) {
        this.person = person;
        this.taskResults = taskResults;
    }

    public Person getPerson() {
        return person;
    }

    public Map<String, Integer> getTaskResults() {
        return taskResults;
    }

    public int getTaskResult(String task) {
        if (!taskResults.containsKey(task)) return 0;
        return taskResults.get(task);
    }

    public String getName() {
        return person.getLastName()+ " " + person.getFirstName();
    }
}
