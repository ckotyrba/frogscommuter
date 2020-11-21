package com.company.Level;

import com.company.figures.Empty;
import com.company.figures.FroschGrün;
import com.company.figures.FroschRot;
import com.company.figures.Item;

public class ItemFactory {

    public static Item buildItem(String toBuild) {
        switch (toBuild) {
            case "r":
                return new FroschRot();
            case "g":
                return new FroschGrün();
            case " ":
                return new Empty();
            default:
                throw new RuntimeException("Unknown Buildparameter");
        }

    }
}
