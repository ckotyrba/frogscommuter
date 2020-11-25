package com.company.figures;

public interface Frog extends Item {
    boolean jumpPossible(int indexFrom, int indexTo);

    boolean jumpPossible(int indexFrom, int indexTo, Frog contentMiddle);
}
