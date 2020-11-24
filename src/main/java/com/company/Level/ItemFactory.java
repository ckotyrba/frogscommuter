package com.company.Level;

import java.io.File;

import javax.swing.*;

import com.company.figures.Container;
import com.company.figures.Empty;
import com.company.figures.FroschGrün;
import com.company.figures.FroschRot;
import com.company.figures.Item;
import com.company.figures.WaterLilyRed;

public class ItemFactory {

    public static Item buildItem(String toBuild) {
        switch (toBuild) {
            case "r":
                return new FroschRot();
            case "g":
                return new FroschGrün();
            case "#":
                return new Empty();
            case "l":
                return new WaterLilyRed();
            case "b":
                return new Container(new ImageIcon("images/bridge.png").getImage());
            case "c":
                return new Container(new ImageIcon("images/Seerose.png").getImage());
            case "w":
                return new Empty(new File("images/Welle.png"));
            default:
                throw new RuntimeException("Unknown Buildparameter: " + toBuild);
        }

    }
}
