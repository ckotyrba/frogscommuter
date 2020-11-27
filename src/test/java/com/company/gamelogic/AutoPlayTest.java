package com.company.gamelogic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.company.figures.Container;
import com.company.figures.FroschGrün;
import com.company.figures.WaterLilyGreen;
import com.google.common.collect.Lists;

class AutoPlayTest {

    @Test
    void testSimpleLevel() {
        Container bridge = new Container(null, new FroschGrün());
        WaterLilyGreen waterLilyRed = new WaterLilyGreen(null);
        GameController gameController = new GameController(Lists.newArrayList(bridge, waterLilyRed));
        AutoPlay autoPlay = new AutoPlay(gameController);
        autoPlay.doNextMove();

        Assertions.assertThat(gameController.getCurrentState().get(0).getContent()).isNull();
        Assertions.assertThat(gameController.getCurrentState().get(1).getContent()).isNotNull();
    }
}