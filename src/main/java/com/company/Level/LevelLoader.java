package com.company.Level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.company.figures.Bridge;
import com.company.figures.Empty;
import com.company.figures.FroschGrün;
import com.company.figures.FroschRot;
import com.company.figures.WaterLily;
import com.company.figures.WaterLilyGreen;
import com.company.figures.WaterLilyRed;


public class LevelLoader {

    public static Level getLevel3() {
        Level level = new Level(3, 4);
        level.setContainer(0, 3, new WaterLilyRed(new FroschGrün()), 0);
        level.setContainer(0, 2, new WaterLilyRed(new FroschGrün()), 1);
        level.setContainer(0, 1, new WaterLilyRed(new FroschGrün()), 2);
        level.setContainer(0, 0, new WaterLilyRed(new FroschGrün()), 3);
        level.setContainer(1, 0, new Bridge(), 4);
        level.setContainer(2, 0, new WaterLilyGreen(new FroschRot()), 5);
        level.setContainer(2, 1, new WaterLilyGreen(new FroschRot()), 6);
        level.setContainer(2, 2, new WaterLilyGreen(new FroschRot()), 7);
        level.setContainer(2, 3, new WaterLilyGreen(new FroschRot()), 8);
        return level;
    }

    public static Level getLevel1() {
        Level level = new Level(3, 3);
        level.setContainer(0, 1, new WaterLily(new FroschGrün()), 0);
        level.setContainer(1, 1, new Bridge(), 1);
        level.setContainer(2, 1, new WaterLilyGreen(new FroschRot()), 2);
        return level;
    }


    public static Level getLevel2() {
        Level level = new Level(3, 3);
        level.setContainer(0, 0, new WaterLily(new FroschGrün()), 0);
        level.setContainer(0, 1, new WaterLily(new FroschGrün()), 1);
        level.setContainer(1, 1, new Bridge(), 2);
        level.setContainer(2, 1, new WaterLilyGreen(new FroschRot()), 3);
        level.setContainer(2, 2, new WaterLilyGreen(new FroschRot()), 4);

        level.setBackground(1, 0, new Empty(new File("images/Fisch.png")));
        level.setBackground(1, 2, new Empty(new File("images/Welle.png")));
        return level;
    }

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
