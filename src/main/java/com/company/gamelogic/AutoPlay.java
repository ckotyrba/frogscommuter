package com.company.gamelogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.company.Measurement;
import com.company.figures.Container;
import com.company.figures.FroschGrün;
import com.company.figures.FroschRot;
import com.company.gamelogic.GameController.Move;

public class AutoPlay implements ActionListener {
    private GameController gameController;
    private Timer timer = new Timer(0, this);
    private List<Move> winningRoute;

    public AutoPlay(GameController gameController) {
        this.gameController = gameController;
        recalculateWinningRoute();
    }

    public void startWithTimer(int millisDelay) {
        timer.setDelay(millisDelay);
        timer.start();
        System.out.println("timer started");
    }

    public Move doNextMove() {
        if (gameController.won()) {
            timer.stop();
            return null;
        }

        Move firstMove = winningRoute.remove(0);
        // moves contains deepCopy so references are wrong
        Container containerFrom = gameController.getCurrentState().get(firstMove.indexFrom);
        Container containerTo = gameController.getCurrentState().get(firstMove.indexTo);
        gameController.doJumpIfPossible(containerFrom, containerTo);
        return firstMove;
    }

    private List<Move> getFirstWinningRoute(List<Move> movesAlreadyGone, List<Container> currentState) {
        if (!winningPossible(currentState)) {
            return null;
        }
        GameController gameController = new GameController(deepCopy(currentState));
        List<Move> allPossibleMoves = gameController.getAllPossibleMoves();
        if (allPossibleMoves.isEmpty()) {
            if (gameController.won()) {
                return movesAlreadyGone;
            }
            return null;
        }
        for (Move move : allPossibleMoves) {
            movesAlreadyGone.add(move);
            gameController.doJumpIfPossible(move.containerFrom, move.containerTo);
            List<Move> winningRoute = getFirstWinningRoute(movesAlreadyGone, gameController.getCurrentState());
            if (winningRoute != null) {
                return winningRoute;
            }
            gameController.unDoLastJump();
            movesAlreadyGone.remove(movesAlreadyGone.size() - 1);
        }
        return null;
    }

    private List<Container> deepCopy(List<Container> currentState) {
        List<Container> result = new ArrayList<>();
        for (Container container : currentState) {
            result.add(container.copy());
        }
        return result;
    }

    private boolean winningPossible(List<Container> currentState) {
        return !doubleGreenBehindRed(currentState) && !doubleRedBehindGreen(currentState);
    }

    private boolean doubleRedBehindGreen(List<Container> currentState) {
        boolean greenFrogFound = false;
        boolean doubleRedFound = false;
        for (int containerIndex = 0; containerIndex < currentState.size() - 1 /*-1 because we check +1*/; containerIndex++) {
            Container container = currentState.get(containerIndex);

            if (!greenFrogFound && containerHasGreenFrog(container)) {
                greenFrogFound = true;
            }
            if (!doubleRedFound && greenFrogFound) {
                if (containerHasRedFrog(container) && containerHasRedFrog(currentState.get(containerIndex + 1))) {
                    doubleRedFound = true;
                }
            } else if (doubleRedFound) {
                if (containerHasGreenFrog(container)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containerHasGreenFrog(Container container) {
        return container.getContent() != null && container.getContent() instanceof FroschGrün;
    }

    private boolean doubleGreenBehindRed(List<Container> currentState) {
        boolean redFrogFound = false;
        boolean doubleGreenFound = false;

        for (int containerIndex = 0; containerIndex < currentState.size() - 1 /*-1 because we check +1*/; containerIndex++) {
            Container container = currentState.get(containerIndex);

            if (!redFrogFound && containerHasRedFrog(container)) {
                redFrogFound = true;
            }
            if (!doubleGreenFound && redFrogFound) {
                if (containerHasGreenFrog(container) && containerHasGreenFrog(currentState.get(containerIndex + 1))) {
                    doubleGreenFound = true;
                }
            } else if (doubleGreenFound) {
                if (containerHasRedFrog(container)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containerHasRedFrog(Container container) {
        return container.getContent() != null && container.getContent() instanceof FroschRot;
    }

    public void recalculateWinningRoute() {
        Measurement.measure(() -> {
            List<Move> firstWinningRoute = getFirstWinningRoute(new ArrayList<>(), gameController.getCurrentState());
            if (firstWinningRoute == null) {
                throw new IllegalStateException("Level impossible");
            }
            this.winningRoute = firstWinningRoute;
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.doNextMove();
    }
}


