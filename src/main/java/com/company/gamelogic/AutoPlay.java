package com.company.gamelogic;

import java.util.ArrayList;
import java.util.List;

import com.company.figures.Container;
import com.company.gamelogic.GameController.Move;

public class AutoPlay {
    private GameController gameController;

    public AutoPlay(GameController gameController) {
        this.gameController = gameController;
    }

    public void doNextMove() {
        if(gameController.won())
            return;
        List<Move> firstWinningRoute = getFirstWinningRoute(new ArrayList<>(), gameController.getCurrentState());
        if (firstWinningRoute == null) {
            throw new IllegalStateException("Level impossible");
        }
        Move firstMove = firstWinningRoute.get(0);
        // moves contains deepCopy so references are wrong
        Container containerFrom = gameController.getCurrentState().get(firstMove.indexFrom);
        Container containerTo = gameController.getCurrentState().get(firstMove.indexTo);
        gameController.doJumpIfPossible(containerFrom, containerTo);
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
}


