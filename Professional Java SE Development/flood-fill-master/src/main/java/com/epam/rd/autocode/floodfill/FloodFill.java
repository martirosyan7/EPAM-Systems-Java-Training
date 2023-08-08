package com.epam.rd.autocode.floodfill;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public interface FloodFill {
    void flood(final String map, final FloodLogger logger);

    static FloodFill getInstance() {
        return new FloodFill() {
            @Override
            public void flood(String map, FloodLogger logger) {
                logger.log(map);
                ArrayList<String[]> matrix = new ArrayList<>();
                String[] lines = map.split("\n");
                ArrayList<Integer[]> waters = new ArrayList<>();
                for (int i = 0; i < lines.length; i++) {
                    matrix.add(lines[i].split(""));
                    for (int j = 0; j < matrix.get(i).length; j++) {
                        if (Objects.equals(matrix.get(i)[j], "" + WATER)) {
                            waters.add(new Integer[]{i, j});
                        }
                    }
                }
                for (Integer[] water : waters) {
                    int x = water[0];
                    int y = water[1];
                    if (x != 0) {
                        String[] newLine = matrix.get(x - 1);
                        newLine[y] = String.valueOf(WATER);
                        matrix.set(x - 1, newLine);
                    }
                    if (x != matrix.size() - 1) {
                        String[] newLine = matrix.get(x + 1);
                        newLine[y] = String.valueOf(WATER);
                        matrix.set(x + 1, newLine);
                    } //matrix.get(x + 1)[y] = String.valueOf(WATER);
                    if (y != 0) {
                        String[] newLine = matrix.get(x);
                        newLine[y - 1] = String.valueOf(WATER);
                        matrix.set(x, newLine);
                    }// matrix.get(x)[y - 1] = String.valueOf(WATER);
                    if (y != matrix.get(x).length - 1) {
                        String[] newLine = matrix.get(x);
                        newLine[y + 1] = String.valueOf(WATER);
                        matrix.set(x, newLine);
                    } //matrix.get(x)[y + 1] = String.valueOf(WATER);
                }
                String newMap = "";
                for (String[] line : matrix) {
                    if (!newMap.equals("")) newMap += "\n";
                    newMap += String.join("", line);
                }

                if (!newMap.equals(map)) {
                    this.flood(newMap, logger);
                }
            }
        };
//        return (final String map, final FloodLogger logger) -> {
//
//        };
    }
    char LAND = '█';
    char WATER = '░';
}
