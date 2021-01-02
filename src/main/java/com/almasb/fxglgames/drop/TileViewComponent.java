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
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class TileViewComponent extends ChildViewComponent {

    //private TileValue value = TileValue.NONE;

    private Arc arc = new Arc(34, 37, 34, 37, 0, 0);
    private Line line1 = new Line(0, 0, 0, 0);
    private Line line2 = new Line(75, 0, 75, 0);

    public TileViewComponent() {
        Rectangle bg = new Rectangle(30, 30, Color.GREY);

        Rectangle bg2 = new Rectangle(31, 31, Color.rgb(250, 250, 250, 0.25));
        bg2.setStrokeWidth(2);
        bg2.setStroke(Color.BLACK);


        /*arc.setFill(null);
        arc.setStroke(Color.BLACK);
        arc.setStrokeWidth(3);*/

        line1.setStrokeWidth(3);
        line2.setStrokeWidth(3);

        line1.setVisible(false);
        line2.setVisible(false);

        getViewRoot().getChildren().addAll(new StackPane(bg, bg2, arc, line1, line2));
    }


}