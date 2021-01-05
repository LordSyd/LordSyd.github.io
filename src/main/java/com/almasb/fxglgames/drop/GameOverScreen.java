package com.almasb.fxglgames.drop;

import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.FXGLButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import javax.swing.*;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GameOverScreen  extends SubScene {


    public GameOverScreen(int deadPlayer) {


        String deadPlayerText = "I should not appear";
        var text = getUIFactoryService().newText(deadPlayerText , Color.GOLD, 32);


            switch (deadPlayer){
                case 1 -> deadPlayerText = "Player One Lost!";
                case 2 -> deadPlayerText = "Player Two Lost!";
            }


        text = getUIFactoryService().newText(deadPlayerText , Color.RED, 30);

        text.setTextAlignment(TextAlignment.CENTER);

        var bg = new Rectangle(600, 230, Color.color(0.3627451f, 0.3627451f, 0.5627451f, 0.55));
        bg.setArcWidth(50);
        bg.setArcHeight(50);
        bg.setStroke(Color.WHITE);
        bg.setStrokeWidth(10);




        var stackPane = new StackPane(bg, text);
        getContentRoot().getChildren().add(stackPane);



    }
}

