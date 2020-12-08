package com.company.Level;

import static com.company.Level.ItemFactory.getFileFromResource;

import com.company.figures.Bridge;
import com.company.figures.Empty;
import com.company.figures.FroschGrün;
import com.company.figures.FroschRot;
import com.company.figures.WaterLily;
import com.company.figures.WaterLilyGreen;
import com.company.figures.WaterLilyRed;


public class LevelLoader {

    public static Level getLevel3() {
        Level level = new Level(3, 5);
        level.setContainer(0, 4, new WaterLilyRed(new FroschGrün()), 0);
        level.setContainer(0, 3, new WaterLilyRed(new FroschGrün()), 1);
        level.setContainer(0, 2, new WaterLilyRed(new FroschGrün()), 2);
        level.setContainer(0, 1, new WaterLilyRed(new FroschGrün()), 3);
        level.setContainer(0, 0, new WaterLilyRed(new FroschGrün()), 4);
        level.setContainer(1, 0, new Bridge(), 5);
        level.setContainer(2, 0, new WaterLilyGreen(new FroschRot()), 6);
        level.setContainer(2, 1, new WaterLilyGreen(new FroschRot()), 7);
        level.setContainer(2, 2, new WaterLilyGreen(new FroschRot()), 8);
        level.setContainer(2, 3, new WaterLilyGreen(new FroschRot()), 9);
        level.setContainer(2, 4, new WaterLilyGreen(new FroschRot()), 10);
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

        level.setBackground(1, 0, new Empty(getFileFromResource("/images/Fisch.png")));
        level.setBackground(1, 2, new Empty(getFileFromResource(("/images/Welle.png"))));
        return level;
    }

    public static Level getLevel4() {
        Level level = new Level(3, 20);
        for (int i = 0; i < 20; i++) {
            level.setContainer(0, 19 - i, new WaterLilyRed(new FroschGrün()), i);
        }
        level.setContainer(1, 0, new Bridge(), 20);
        for (int i = 0; i < 20; i++) {
            level.setContainer(2, i, new WaterLilyGreen(new FroschRot()), 21 + i);
        }
        return level;
    }


    public static Level getLevel(int levelId) {
        switch (levelId) {
            case 1:
                return getLevel1();
            case 2:
                return getLevel2();
            case 3:
                return getLevel3();
            case 4:
                return getLevel4();
        }
        throw new IllegalStateException("Unknown levelId " + levelId);
    }
}
