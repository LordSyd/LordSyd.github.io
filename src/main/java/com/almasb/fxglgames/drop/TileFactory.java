
package com.almasb.fxglgames.drop;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * One of the most important classes of this app and where most of the magic happens. Uses a mouse event handler attached
 *  to each tile spawn to get if it was clicked. Logic what should happen on click is handled in here as well,
 *  not best practice, but I am lacking a method to pass which button was clicked on to a separate component - so it will
 *  remain like this for the moment
 */

public class TileFactory implements EntityFactory {

    /**
     * these arrays get filed with tiles on spawn, used to iterate over them on click to show change of state on screen
     * by changing the tile's color. Uses 4 array lists to make check logic inside getBoardState() less complicated
     */
    static ArrayList<Entity> player1shipTiles = new ArrayList<Entity>();
    static ArrayList<Entity> player1hitTiles = new ArrayList<Entity>();
    static ArrayList<Entity> player2shipTiles = new ArrayList<Entity>();
    static ArrayList<Entity> player2hitTiles = new ArrayList<Entity>();


    /**
     * method to streamline update between frames
     */
    protected static void updateBoardState(){

        getBoardState("ship",1);
        getBoardState("hit",1);
        getBoardState("ship",2);
        getBoardState("hit",2);
    }

    /**
     * Important method that gets and updates the tiles, maybe should be called differently but I was scared messing
     * around with names after the logic was hooked into everything. Maybe will clear up naming when time remains.
     *
     * the method takes in the board to check identifier "ship" or "hit", and the player ID 1 or 2 to signal which
     * player should be checked. The rest is handled via switches and booleans
     *
     * @param boardToCheck board identifier as string
     * @param playerID player ID as int
     */
    protected static void getBoardState(String boardToCheck, int playerID){



        Entity temp;



        switch (boardToCheck){
            case "ship" -> {
                switch (playerID) {
                    case 1 -> {
                        Iterator<Entity>  iterator = player1shipTiles.iterator();
                        for (int i = 0; i < player1shipTiles.size(); i++) {
                            temp = iterator.next();
                            int tempShipState = BattleshipMain.player1.getStateOfShipsCell(temp.getProperties().getValue("x"), temp.getProperties().getValue("y"));

                            if (tempShipState == 1) {
                                TileViewComponent blue = new TileViewComponent(Color.BLUE);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(blue);
                            } else if (tempShipState == 2) {
                                TileViewComponent red = new TileViewComponent(Color.RED);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(red);
                            }
                        }
                    }
                    case 2 -> {
                        Iterator<Entity>  iterator = player2shipTiles.iterator();
                        for (int i = 0; i < player2shipTiles.size(); i++) {
                            temp = iterator.next();
                            int tempShipState = BattleshipMain.player2.getStateOfShipsCell(temp.getProperties().getValue("x"), temp.getProperties().getValue("y"));

                            if (tempShipState == 1) {
                                TileViewComponent blue = new TileViewComponent(Color.BLUE);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(blue);
                            } else if (tempShipState == 2) {
                                TileViewComponent red = new TileViewComponent(Color.RED);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(red);
                            }
                        }
                    }
                }
            }
            case "hit" -> {
                switch (playerID) {
                    case 1 -> {
                        Iterator<Entity> iterator = player1hitTiles.iterator();
                        for (int i = 0; i < player1hitTiles.size(); i++) {
                            temp = iterator.next();
                            int hitState = BattleshipMain.player2.getStateOfHitCell(temp.getProperties().getValue("x"), temp.getProperties().getValue("y"));

                            if (hitState == 1) {
                                TileViewComponent black = new TileViewComponent(Color.BLACK);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(black);
                            }else if (hitState == 2){
                                TileViewComponent red = new TileViewComponent(Color.RED);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(red);
                            }
                        }
                    }
                    case 2 -> {
                        Iterator<Entity> iterator = player2hitTiles.iterator();
                        for (int i = 0; i < player2hitTiles.size(); i++) {
                            temp = iterator.next();
                            int hitState = BattleshipMain.player1.getStateOfHitCell(temp.getProperties().getValue("x"), temp.getProperties().getValue("y"));

                            if (hitState == 1) {
                                TileViewComponent black = new TileViewComponent(Color.BLACK);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(black);
                            }else if (hitState == 2) {
                                TileViewComponent red = new TileViewComponent(Color.RED);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(red);
                            }


                        }
                    }
                }
            }
        }
    }


    /**
     *
     *Main part of the entity factory. Adds components on spawn and adds an event handler that handles click events
     *on the tiles. Logic is planed to be put into its seperate component class, ClickBehaviourComponent, but that
     *is not implemented yet. Event handler checks what to do via different static booleans inside Main, maybe not
     *best practice but way faster to implement for me. Cry me a river ;)
     *
     * @param data spawn data
     * @return Entity
     */

    @Spawns("tile")
    public Entity newTile(SpawnData data) {




        TileViewComponent original = new TileViewComponent(Color.GRAY);

        var tile = entityBuilder(data)

                //todo get ClickBehaviourComponent hooked up correctly
                //.onClick((Consumer<Entity>) e -> e.getComponent(ClickBehaviourComponent.class).onClick())
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .with(original)
                .with(new ClickBehaviourComponent())
                .build();



        tile.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {


            System.out.println("clicked:  "+ tile.getProperties().getValue("x") + tile.getProperties().getValue("y"));
            

            int playerId = tile.getProperties().getValue("Player");
            String tileType = tile.getProperties().getValue("boardType");
            
            switch (tileType) {

                case "ship" -> {
                    if (!BattleshipMain.gameRunning) {
                        switch (playerId) {
                            case 1 -> {
                                {

                                    if (BattleshipMain.player1.placeShip(
                                            new Ship(BattleshipMain.player1ShipsToPlace,
                                            e.getButton() == MouseButton.PRIMARY),
                                            tile.getProperties().getValue("x"), tile.getProperties().getValue("y")))
                                    {
                                        System.out.println("Ship");

                                        getBoardState(tileType, 1);

                                        if (--BattleshipMain.player1ShipsToPlace == 0) {

                                            BattleshipMain.player1Turn = false;


                                            BattleshipMain.showTurnMenu();
                                        }

                                    }
                                }
                            }
                            case 2 -> {
                                if (BattleshipMain.player2.placeShip(
                                    new Ship(BattleshipMain.player2ShipsToPlace,
                                            e.getButton() == MouseButton.PRIMARY),
                                    tile.getProperties().getValue("x"), tile.getProperties().getValue("y")))
                            {
                                System.out.println("Ship");

                                getBoardState(tileType,2);

                                if (--BattleshipMain.player2ShipsToPlace == 0) {

                                    BattleshipMain.player1Turn = true;


                                    BattleshipMain.showTurnMenu();
                                }

                            }

                            }
                        }
                    }else{
                        return;
                    }
                }
                case "hit" -> {
                    if (BattleshipMain.gameRunning) {

                        switch (playerId) {
                            case 1 -> {
                                updateBoardState();
                                if (BattleshipMain.betweenTurnMenuActive =
                                        BattleshipMain.player2.shoot(tile.getProperties().getValue("x"), tile.getProperties().getValue("y"))) {
                                    BattleshipMain.player1Turn = false;
                                }

                            }


                            case 2 -> {
                                getBoardState(tileType, 2);

                                if (BattleshipMain.betweenTurnMenuActive =
                                        BattleshipMain.player1.shoot(tile.getProperties().getValue("x"), tile.getProperties().getValue("y"))) {
                                    BattleshipMain.player1Turn = true;
                                }
                            }
                        }
                    }
                }
            }
            //todo complete logic for ship spawning
           /*spawn("ship", tile.getX(), tile.getY());*/
        });
        return tile;
    }
}


