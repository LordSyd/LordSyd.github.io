
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

import static com.almasb.fxgl.dsl.FXGL.*;

public class TileFactory implements EntityFactory {
    /*static ArrayList<Entity> shipTiles = new ArrayList<Entity>();
    static ArrayList<Entity> hitTiles = new ArrayList<Entity>();*/
    static ArrayList<Entity> player1shipTiles = new ArrayList<Entity>();
    static ArrayList<Entity> player1hitTiles = new ArrayList<Entity>();
    static ArrayList<Entity> player2shipTiles = new ArrayList<Entity>();
    static ArrayList<Entity> player2hitTiles = new ArrayList<Entity>();



    protected static void updateBoardState(){
        getBoardState("ship",1);
        getBoardState("hit",1);
        getBoardState("ship",2);
        getBoardState("hit",2);
    }

    protected static void getBoardState(String boardToCheck, int playerID){


        Entity temp;
        int tempId = 0;


        switch (boardToCheck){
            case "ship" -> {
                switch (playerID) {
                    case 1 -> {
                        Iterator<Entity>  iterator = player1shipTiles.iterator();
                        for (int i = 0; i < player1shipTiles.size(); i++) {
                            temp = iterator.next();

                            if (BattleshipMain.player1.getStateOfShipsCell(temp.getProperties().getValue("x"), temp.getProperties().getValue("y")) == 1) {
                                TileViewComponent blue = new TileViewComponent(Color.BLUE);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(blue);
                            }
                        }
                    }
                    case 2 -> {
                        Iterator<Entity>  iterator = player2shipTiles.iterator();
                        for (int i = 0; i < player2shipTiles.size(); i++) {
                            temp = iterator.next();
                            if (BattleshipMain.player2.getStateOfShipsCell(temp.getProperties().getValue("x"), temp.getProperties().getValue("y")) == 1) {
                                TileViewComponent blue = new TileViewComponent(Color.BLUE);

                                temp.removeComponent(TileViewComponent.class);

                                temp.addComponent(blue);
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













    @Spawns("tile")
    public Entity newTile(SpawnData data) {









        Ship ship = null;




        TileViewComponent original = new TileViewComponent(Color.GRAY);



        var tile = entityBuilder(data)

                //.type(BattleshipMain.Type.TILE)
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .with(original)
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


                                            //todo exchange test for menu with real player change submenu
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


                                    //todo exchange test for menu with real player change submenu
                                    BattleshipMain.showTurnMenu();
                                }

                            }

                            }
                        }
                    }else{
                        return;
                    }
                    /*{

                        if (
                            BattleshipMain.player1.placeShip(new Ship(BattleshipMain.player1ShipsToPlace,
                                    e.getButton() == MouseButton.PRIMARY),
                            tile.getProperties().getValue("x"),  tile.getProperties().getValue("y"))){
                            System.out.println("Ship");

                            Entity temp;




                            if (--BattleshipMain.player1ShipsToPlace == 0){

                                BattleshipMain.showTurnMenu();
                            }

                         }
                    }*/
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

            //todo find fix for texture loading bug (backlog)
           //spawn("ship", tile.getX(), tile.getY());

















        });










        return tile;









    }

}


