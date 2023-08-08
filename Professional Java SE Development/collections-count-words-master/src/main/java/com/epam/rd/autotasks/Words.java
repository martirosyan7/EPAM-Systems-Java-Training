package com.epam.rd.autotasks;


import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Words {

    public String countWords(List<String> lines) {
        String newLines = String.join(" ", lines);
        newLines = " " + newLines.replaceAll("[^\\p{L}|0-9]+", "  "); //, "  ") + " ";
        String[] linesInString = newLines.split("([ ][\\p{L}|0-9][ ]|[ ][\\p{L}|0-9][\\p{L}|0-9][ ]|[ ][\\p{L}|0-9][\\p{L}|0-9][\\p{L}|0-9][ ]|[ ])+");
        List<String> linesInStringList = Arrays.asList(linesInString);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(linesInStringList);
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, arrayList.get(i).toLowerCase());
        }

        Map<String, Integer> words = new HashMap<>();
        int count = 0;
        Set<String> arraySet = new HashSet<>(arrayList);
        for (String word : arraySet) {
            count = Collections.frequency(arrayList, word.toLowerCase());
            if (count >= 10) {
                words.put(word.toLowerCase(), count);
            }
        }
        System.out.println(words);
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.putAll(words);
        System.out.println(treeMap);
        List<Map.Entry<String, Integer>> entryList = sortByValue(treeMap);
        String result = "";
        for (Map.Entry<String, Integer> aa : entryList) {
            if (!result.equals("")) result += "\n";
            result += aa.getKey() + " - " + aa.getValue();
        }
        return result;
    }
    public static List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
//                if (o1.getValue() == o2.getValue()) {
//                    int len = min(o1.getKey().length(), o2.getKey().length());
//                    int a = o1.getKey().substring(0, len).compareTo(o2.getKey().substring(0, len));
//                    return (a  > 0) ? 1 : -1;
//                }
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        return list;
    }
}
