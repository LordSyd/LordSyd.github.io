package com.almasb.fxglgames.drop;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

import com.almasb.fxgl.app.scene.FXGLMenu;

import com.almasb.fxgl.app.scene.MenuType;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends FXGLMenu {

    public MainMenu(MenuType type) {
        super(type);
        Button startGame = createActionButton("Start", () -> fireNewGame());

        getContentRoot().getChildren().add(startGame);


    }

    @Override
    protected Button createActionButton(String name, Runnable action) {
        return new Button(name);
    }

    @Override
    protected Button createActionButton(StringBinding name, Runnable action) {
        return new Button(name.get());
    }

    @Override
    protected Node createBackground(double width, double height) {
        return new Rectangle(width, height, Color.GRAY);
    }

    @Override
    protected Node createTitleView(String title) {
        return new Text(title);
    }

    @Override
    protected Node createVersionView(String version) {
        return new Text(version);
    }

    @Override
    protected Node createProfileView(String profileName) {
        return null;
    }

}
