package com.company.Level;

import java.io.File;

import com.company.figures.Empty;
import com.company.figures.Field;
import com.company.figures.FroschGrün;
import com.company.figures.Item;

public class ItemFactory {

    public static Item buildItem(String toBuild) {
        switch (toBuild) {
            case "r":
                return new Empty();
            case "g":
                return new FroschGrün();
            case "#":
                return new Empty();
            case "l":
                return new Field(new File("images/Seerose.png"));
            case "b":
                return new Field(new File("images/bridge.png"));
            case "w":
                return new Empty(new File("images/Welle.png"));
            default:
                throw new RuntimeException("Unknown Buildparameter: " + toBuild);
        }

    }
}
