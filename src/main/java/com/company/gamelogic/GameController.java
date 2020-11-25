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
        if (from == null || to == null)
            return false;
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
            Frog contentFrom = containers.get(indexFrom).getContent();
            if (Math.abs(indexFrom - indexTo) == 1) {
                return contentFrom.jumpPossible(indexFrom, indexTo);
            } else if (Math.abs(indexFrom - indexTo) == 2) {
                int indexMiddle = Math.min(indexFrom, indexTo) + 1;
                Frog contentMiddle = containers.get(indexMiddle).getContent();
                if (contentMiddle != null)
                    return contentFrom.jumpPossible(indexFrom, indexTo, contentMiddle);
            }
        }
        return false;
    }

    public boolean won() {
        for (Container container : containers) {
            if (!container.finished()) {
                return false;
            }
        }
        return true;
    }

    public boolean anyStepPossible() {
        for (Container from : containers) {
            for (Container to : containers) {
                if (jumpAllowed(from, to)) {
                    return true;
                }
            }
        }
        return false;
    }
}
