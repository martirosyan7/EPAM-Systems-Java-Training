package com.efimchick.ifmo;


import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Collecting {
    List<CourseResult> results = new ArrayList<>();

    public int sum(IntStream intStream) {
        return intStream.sum();
    }

    public int production(IntStream intStream) {
        return intStream.reduce(1, (x, y) -> x * y);
    }

    public int oddSum(IntStream intStream) {
        return intStream.filter((x) -> x % 2 != 0).sum();
    }

    public Map<Integer, Integer> sumByRemainder(int divider, IntStream intStream) {
        List<Integer> list = intStream.boxed().collect(Collectors.toList());
        return IntStream
                .iterate(0, i -> i + 1)
                .limit(divider)
                .mapToObj((remainder) -> new int[] {remainder, list
                        .stream().filter((x) -> x % divider == remainder).mapToInt((i) -> i).sum()})
                .filter((x) -> x[1] != 0)
                .collect(Collectors
                        .toMap((keyValue) -> keyValue[0], (keyValue) -> keyValue[1]));
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> results) {
        List<CourseResult> list = results.collect(Collectors.toList());
        int subjectCount = list.stream()
                .map((course) -> course.getTaskResults().keySet())
                .flatMap(Set::stream)
                .collect(Collectors.toSet()).size();
        return list.stream()
                .collect(Collectors.toMap((person) -> person.getPerson(), (score) -> score
                        .getTaskResults()
                        .values()
                        .stream()
                        .collect(Collectors.summingDouble((num) -> Double.parseDouble(String.valueOf(num)))) / subjectCount));
    }

    public double averageTotalScore(Stream<CourseResult> results) {
        return averageScoresPerTask(results).values().stream().mapToDouble((i) -> i).average().getAsDouble();
    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> results) {
        List<CourseResult> list = results.collect(Collectors.toList());
        double personCount = list.size();
        List<String> courses = list
                .stream()
                .map((course) -> course
                        .getTaskResults()
                        .keySet())
                .flatMap(Set::stream)
                .collect(Collectors.toSet()).stream().collect(Collectors.toList());
        List<Double> scores = new ArrayList<>();
        for (String course: courses) {
            scores.add(list.stream().mapToInt((courseResult) -> courseResult.getTaskResult(course)).sum() / personCount);
        }
        return IntStream.range(0, courses.size()).boxed().collect(Collectors.toMap(courses::get, scores::get));
    }

    public Map<Person, String> defineMarks(Stream<CourseResult> results) {
        Map<Person, String> result = new HashMap<>();
        Map<Person, Double> map = totalScores(results);
        for (Person person: map.keySet()) {
            result.put(person, scoreToMark(map.get(person)));
        }
        return result;
    }

    public String scoreToMark(double score) {
        if (score > 90) return "A";
        else if (score >= 83) return "B";
        else if (score >= 75) return "C";
        else if (score >= 68) return "D";
        else if (score >= 60) return "E";
        else return "F";
    }


    public String easiestTask(Stream<CourseResult> results) {
        Map<String, Double> map = averageScoresPerTask(results);
        double value = map.values().stream().mapToDouble((i) -> i).max().getAsDouble();
        for (String key: map.keySet()) {
            if (map.get(key) == value) return key;
        }
        return "";
    }

    public Collector<CourseResult, ?, String> printableStringCollector() {
        return Collector.of(
                () -> "",
                (result, courseResult) -> {
                    renderCourseResult(courseResult);
                },
                (result1, result2) -> {
                    return result1;
                },
                total -> getTable()
        );
    }

    public void renderCourseResult(CourseResult result) {
        results.add(result);
    }

    public String getTable() {
        results.sort((courseResult1, courseResult2) -> courseResult1.getName().compareTo(courseResult2.getName()));
        List<Integer> maxLength = new ArrayList<>();
        maxLength.add(7);
        List<CourseResult> list = results;
        List<List<String>> cells = new ArrayList<>();
        List<String> firstRow = new ArrayList<>();
        firstRow.add("Student");

        List<String> courses = list
                .stream()
                .map((course) -> course
                        .getTaskResults()
                        .keySet())
                .flatMap(Set::stream)
                .collect(Collectors.toSet()).stream().collect(Collectors.toList());
        courses = courses.stream().sorted().collect(Collectors.toList());
        firstRow.addAll(courses);
        firstRow.add("Total");
        firstRow.add("Mark");
        cells.add(firstRow);
        for (CourseResult result: results) {
            List<String> line = new ArrayList<>();
            List<String> grades = new ArrayList<>();
            String name = result.getName();
            if (name.length() > maxLength.get(0)) maxLength.set(0, name.length());
            for (String course: courses) {
                grades.add(String.valueOf(result.getTaskResult(course)));
            }
            double totalScore = totalScores(results.stream()).get(result.getPerson());
            String mark = scoreToMark(totalScore);
            line.add(name);
            line.addAll(grades);
            line.add(String.format("%.2f", totalScore));
            line.add(mark);
            cells.add(line);
        }
        List<String> lastLine = new ArrayList<>();
        Map<String, Double> averagesPerTask = averageScoresPerTask(results.stream());
        List<Double> averages = new ArrayList<>();
        lastLine.add("Average");
        for (String course: courses) {
            maxLength.add(course.length());
            averages.add(averagesPerTask.get(course));
            lastLine.add(String.format("%.2f", averagesPerTask.get(course)));
        }
        maxLength.add(5);
        maxLength.add(4);
        double totalScore = averages
                .stream()
                .mapToDouble((x) -> x)
                .average()
                .getAsDouble();
        String averageMark = scoreToMark(totalScore);
        lastLine.add(String.format("%.2f", totalScore));
        lastLine.add(averageMark);
        cells.add(lastLine);
        String sep = " |";
        String result = "";
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                result += stringFormat(cells.get(i).get(j), maxLength.get(j), j != 0);
                result += sep + ((j == cells.get(i).size() - 1) ? "" : " ");
            }
            result += (i == cells.size() - 1) ? "" : "\n";
        }

        return result;
    }

    public String stringFormat(String text, int length, boolean isRight) {
        String spaces = "";
        for (int i = 0; i < length - text.length(); i++) {
            spaces += " ";
        }
        return isRight ? spaces + text : text + spaces;
    }
}

