package com.company.gamelogic;

import java.util.ArrayList;
import java.util.List;

import com.company.figures.Container;
import com.company.figures.Frog;

public class GameController  {

    private final List<Container> containers;

    private List<Move> moves = new ArrayList<>();
    private AutoPlay autoPlay;

    public GameController(List<Container> containers) {
        this.containers = containers;
    }

    public boolean jumpAllowed(Container from, Container to) {
        if (from == null || to == null)
            return false;
        return jumpAllowed(containers.indexOf(from), containers.indexOf(to));
    }

    public void doJumpIfPossibleManuel(Container from, Container to) {
        doJumpIfPossible(from, to);
        getAutoPlay().recalculateWinningRoute();
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
        for (int index = 0; index < containers.size(); index++) {
            if (containers.get(index).getContent() == null) {
                Container to = containers.get(index);
                for (int indexFrom = Math.max(index - 2, 0); indexFrom <= Math.min(index + 2, containers.size() - 1); indexFrom++) {
                    Container from = containers.get(indexFrom);
                    if (jumpAllowed(from, to)) {
                        result.add(new Move(from, to, containers.indexOf(from), containers.indexOf(to)));
                    }
                }
            }
        }
        return result;
    }

    public List<Container> getCurrentState() {
        return containers;
    }

    public AutoPlay getAutoPlay() {
        if (autoPlay == null)
            autoPlay = new AutoPlay(this);
        return autoPlay;
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
