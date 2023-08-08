package com.epam.rd.autotasks;


import java.util.*;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {

        String joinedLines = String.join(" ", lines);
        joinedLines = " " + joinedLines.replaceAll("[^\\p{L}|0-9]+", "  ");
        List<String> words = List.of(joinedLines.split("([ ][\\p{L}|0-9][ ]|[ ][\\p{L}|0-9][\\p{L}|0-9][ ]|[ ][\\p{L}|0-9][\\p{L}|0-9][\\p{L}|0-9][ ]|[ ])+"));
        final List<String> filteredList = words
                .stream()
                .map((word) -> word.toLowerCase())
                .collect(Collectors.toList());

        Set<String> set = new HashSet<>(filteredList);
        Map<String, Integer> map = set
                .stream()
                .filter((y) -> Collections.frequency(filteredList, y) >= 10)
                .collect(Collectors.toMap(
                        (word) -> word, (w) -> Collections.frequency(filteredList, w)));
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        List<Map.Entry<String, Integer>> entryList = sortByValue(treeMap);
        List<String> answer = entryList
                .stream()
                .map((entry) -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.toList());
        String joined = String.join("\n", answer);
        return joined;
    }

    public static List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        return list;
    }
}
