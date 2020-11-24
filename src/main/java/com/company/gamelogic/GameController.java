package com.company.gamelogic;

import java.util.List;

import com.company.figures.Container;

public class GameController {

    private final List<Container> containers;

    public GameController(List<Container> containers) {
        this.containers = containers;
    }

    public boolean jumpAllowed(Container from, Container to) {
        return jumpAllowed(containers.indexOf(from), containers.indexOf(to));
    }

    private boolean jumpAllowed(int indexFrom, int indexTo) {
        if (containers.get(indexTo).getContent() == null) {
            return true;
        }
        return false;
    }

}
