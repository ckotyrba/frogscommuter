package com.company.gamelogic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.company.Level.LevelLoader;
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

        assertThat(gameController.getCurrentState().get(0).getContent()).isNull();
        assertThat(gameController.getCurrentState().get(1).getContent()).isNotNull();
    }

    @Test
    void testLevel2() {
        GameController gameController = new GameController(LevelLoader.getLevel2().getLogicalOrder());
        AutoPlay autoPlay = new AutoPlay(gameController);
        assertThat(autoPlay.doNextMove().toString()).isEqualTo("1|2");
        assertThat(autoPlay.doNextMove().toString()).isEqualTo("3|1");
        assertThat(autoPlay.doNextMove().toString()).isEqualTo("4|3");
        assertThat(autoPlay.doNextMove().toString()).isEqualTo("2|4");
        assertThat(autoPlay.doNextMove().toString()).isEqualTo("0|2");
        assertThat(autoPlay.doNextMove().toString()).isEqualTo("1|0");
        assertThat(autoPlay.doNextMove().toString()).isEqualTo("3|1");
        assertThat(autoPlay.doNextMove().toString()).isEqualTo("2|3");
    }
}