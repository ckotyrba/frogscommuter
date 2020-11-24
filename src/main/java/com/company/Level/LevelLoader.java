package com.company.Level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {

//    public static Level getLevel1() {
//        Level level = new Level(1, 2);
//        level.setField(0, 0, ItemFactory.buildItem("l"));
//        level.setField(0, 1, ItemFactory.buildItem("c"));
//        level.setField(0, 1, ItemFactory.buildItem("r"));
//        return level;
//    }

        public static Level parseLevel(File... sources) {
            Level result = null;
            for (File source : sources) {
                List<String> strings = readFileLines(source);
                if (result == null) {
                    result = new Level(strings.get(0).length(), strings.size());
                }
                for (int y = 0; y < strings.size(); y++) {
                    for (int x = 0; x < strings.get(y).length(); x++) {
//                        result.setField(x, y, ItemFactory.buildItem(Character.toString(strings.get(y).charAt(x))));
                    }
                }
            }
            return result;
        }

        private static List<String> readFileLines(File source) {
            List<String> result = new ArrayList<>();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
                String line = bufferedReader.readLine();
                while (line != null) {
                    result.add(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                System.exit(1);
            }

            return result;
        }
}
