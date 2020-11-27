package com.company.gamelogic;

import java.util.ArrayList;
import java.util.List;

import com.company.figures.Container;
import com.company.figures.Frog;

public class GameController {

    private final List<Container> containers;

    private List<Move> moves = new ArrayList<>();

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
            doJump(from, to);
        }
    }

    public void unDoLastJump() {
        if (!moves.isEmpty()) {
            Move latestMove = moves.remove(moves.size() - 1);
            swap(latestMove.containerTo, latestMove.containerFrom);
        }
    }

    private void doJump(Container from, Container to) {
        swap(from, to);
        moves.add(new Move(from, to, containers.indexOf(from), containers.indexOf(to)));
    }

    private void swap(Container from, Container to) {
        Frog fromFrog = from.getContent();
        Frog toFrog = to.getContent();
        to.setContent(fromFrog);
        from.setContent(toFrog);
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
        return !getAllPossibleMoves().isEmpty();
    }

    public List<Move> getAllPossibleMoves() {
        List<Move> result = new ArrayList<>();
        for (Container from : containers) {
            for (Container to : containers) {
                if (jumpAllowed(from, to)) {
                    result.add(new Move(from, to, containers.indexOf(from), containers.indexOf(to)));
                }
            }
        }
        return result;
    }

    public List<Container> getCurrentState() {
        return containers;
    }


    public static class Move {
        final int indexFrom;
        final int indexTo;
        Container containerFrom;
        Container containerTo;

        Move(Container from, Container to, int indexFrom, int indexTo) {
            this.containerFrom = from;
            this.containerTo = to;
            this.indexFrom = indexFrom;
            this.indexTo = indexTo;
        }

        @Override
        public String toString() {
            return indexFrom + "|" + indexTo;
        }
    }

}
