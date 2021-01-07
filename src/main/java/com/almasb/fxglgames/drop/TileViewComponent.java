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


public class TileViewComponent extends ChildViewComponent {


    private Line line1 = new Line(0, 0, 0, 0);
    private Line line2 = new Line(75, 0, 75, 0);

    public TileViewComponent(Color color) {

        Rectangle bg = new Rectangle(30, 30, color);

        Rectangle bg2 = new Rectangle(31, 31, Color.rgb(250, 250, 250, 0.25));
        bg2.setStrokeWidth(2);
        bg2.setStroke(Color.BLACK);






        line1.setStrokeWidth(3);
        line2.setStrokeWidth(3);

        line1.setVisible(false);
        line2.setVisible(false);

        getViewRoot().getChildren().addAll(new StackPane(bg, bg2, line1, line2));
    }


}