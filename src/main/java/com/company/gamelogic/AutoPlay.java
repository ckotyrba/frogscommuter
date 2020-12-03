package com.company.gamelogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.company.Measurement;
import com.company.figures.Container;
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


