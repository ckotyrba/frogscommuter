package com.company.gamelogic;

import java.util.List;

import com.company.figures.Container;
import com.company.figures.Frog;

public class GameController {

    private final List<Container> containers;

    public GameController(List<Container> containers) {
        this.containers = containers;
    }

    public boolean jumpAllowed(Container from, Container to) {
        return jumpAllowed(containers.indexOf(from), containers.indexOf(to));
    }

    public void doJumpIfPossible(Container from, Container to) {
        if (jumpAllowed(from, to)) {
            Frog fromFrog = from.getContent();
            Frog toFrog = to.getContent();
            to.setContent(fromFrog);
            from.setContent(toFrog);
        }
    }

    private boolean jumpAllowed(int indexFrom, int indexTo) {
        if (containers.get(indexTo).getContent() == null) {
            return true;
        }
        return false;
    }

}
