package com.almasb.fxglgames.drop;


import com.almasb.fxgl.dsl.components.view.ChildViewComponent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * This governs the look of each tile. The color gets set on construction, by passing Color.color identifier on to the
 * constructor
 */

public class TileViewComponent extends ChildViewComponent {




    public TileViewComponent(Color color) {

        Rectangle bg = new Rectangle(30, 30, color);

        Rectangle bg2 = new Rectangle(31, 31, Color.rgb(250, 250, 250, 0.25));
        bg2.setStrokeWidth(2);
        bg2.setStroke(Color.BLACK);


        getViewRoot().getChildren().addAll(new StackPane(bg, bg2));
    }


}