package com.company.gamelogic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.company.figures.Container;
import com.company.figures.FroschGrün;
import com.company.figures.FroschRot;
import com.google.common.collect.Lists;

class GameControllerTest {
    @Test
    void testJump_allowed() {
        Container containerSource = new Container(null);
        containerSource.setContent(new FroschGrün());
        Container containerEmpty2 = new Container(null);

        GameController gameController = new GameController(Lists.newArrayList(containerSource, containerEmpty2));
        Assertions.assertThat(gameController.jumpAllowed(containerSource, containerEmpty2)).isTrue();
    }

    @Test
    void testJump_besetzt() {
        Container containerSource = new Container(null);
        containerSource.setContent(new FroschGrün());
        Container containerEmpty2 = new Container(null);
        containerEmpty2.setContent(new FroschGrün());

        GameController gameController = new GameController(Lists.newArrayList(containerSource, containerEmpty2));
        Assertions.assertThat(gameController.jumpAllowed(containerSource, containerEmpty2)).isFalse();
    }

    @Test
    void testJump_canJumpOver() {
        Container containerSource = new Container(null);
        containerSource.setContent(new FroschGrün());
        Container containerEmpty2 = new Container(null);
        containerEmpty2.setContent(new FroschRot());
        Container containerEmpty3 = new Container(null);

        GameController gameController = new GameController(Lists.newArrayList(containerSource, containerEmpty2, containerEmpty3));
        Assertions.assertThat(gameController.jumpAllowed(containerSource, containerEmpty3)).isTrue();
    }

    @Test
    void testJump_cantJumpOver() {
        Container containerSource = new Container(null);
        containerSource.setContent(new FroschGrün());
        Container containerEmpty2 = new Container(null);
        containerEmpty2.setContent(new FroschGrün());
        Container containerEmpty3 = new Container(null);

        GameController gameController = new GameController(Lists.newArrayList(containerSource, containerEmpty2, containerEmpty3));
        Assertions.assertThat(gameController.jumpAllowed(containerSource, containerEmpty3)).isFalse();
    }

    @Test
    void testJump_cantJumpOver2Fields() {
        Container containerSource = new Container(null);
        containerSource.setContent(new FroschGrün());
        Container containerEmpty2 = new Container(null);
        Container containerEmpty3 = new Container(null);

        GameController gameController = new GameController(Lists.newArrayList(containerSource, containerEmpty2, containerEmpty3));
        Assertions.assertThat(gameController.jumpAllowed(containerSource, containerEmpty3)).isFalse();
    }

}