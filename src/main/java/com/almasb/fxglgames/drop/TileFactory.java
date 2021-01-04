
package com.almasb.fxglgames.drop;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IDComponent;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.Iterator;

import static com.almasb.fxgl.dsl.FXGL.*;

public class TileFactory implements EntityFactory {



    protected static void getBoardState(String boardToCheck){
        Entity temp;


        switch (boardToCheck){
            case "ship" -> {
                Iterator<Entity>  iterator = shipTiles.iterator();
                for (int i = 0; i < shipTiles.size(); i++){
                    temp = iterator.next();
                    int tempId = temp.getProperties().getValue("Player");



                    if (
                            (BattleshipMain.player1.getStateOfShipsCell(temp.getProperties().getValue("x"),  temp.getProperties().getValue("y")) == 1) &&
                                    (tempId == 1)
                    ){
                        TileViewComponent blue = new TileViewComponent(Color.BLUE);


                        temp.removeComponent(TileViewComponent.class);

                        temp.addComponent(blue);

                    }
                }
            }
        }

    }

    static ArrayList<Entity> shipTiles = new ArrayList<Entity>();
    static ArrayList<Entity> hitTiles = new ArrayList<Entity>();











    @Spawns("tile")
    public Entity newTile(SpawnData data) {









        Ship ship = null;

        TileViewComponent original = new TileViewComponent(Color.GRAY);











        var tile = entityBuilder(data)

                //.type(BattleshipMain.Type.TILE)
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .with(original)
                .build();

        //todo remove duplicate tiletype

        /*String tiletype = tile.getProperties().getValue("boardType");

        getBoardState(tiletype, tile);*/







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

                                    if (
                                            BattleshipMain.player1.placeShip(new Ship(BattleshipMain.player1ShipsToPlace,
                                                            e.getButton() == MouseButton.PRIMARY),
                                                    tile.getProperties().getValue("x"), tile.getProperties().getValue("y"))) {
                                        System.out.println("Ship");



                                        getBoardState(tileType);

                               /* Entity temp;

                                Iterator<Entity>  iterator = shipTiles.iterator();
                                for (int i = 0; i < shipTiles.size(); i++){
                                    temp = iterator.next();
                                    int tempId = temp.getProperties().getValue("Player");



                                    if (
                                            (BattleshipMain.player1.getStateOfShipsCell(temp.getProperties().getValue("x"),  temp.getProperties().getValue("y")) == 1) &&
                                                    (tempId == 1)
                                    ){
                                        temp.getViewComponent().setOpacity(0.5);
                                        int tempState = BattleshipMain.player1.getStateOfShipsCell(tile.getProperties().getValue("x"),  tile.getProperties().getValue("y"));
                                        System.out.println("Temp:" + tempState);
                                    }
                                }*/


                                        if (--BattleshipMain.player1ShipsToPlace == 0) {

                                            BattleshipMain.gameRunning = true;
                                            //todo exchange test for menu with real player change submenu
                                            BattleshipMain.showStartMenu();
                                        }

                                    }
                                }
                            }
                            case 2 -> {
                            }
                        }
                    }else{
                        return;
                    }
                   /* {

                        if (
                            BattleshipMain.player1.placeShip(new Ship(BattleshipMain.player1ShipsToPlace,
                                    e.getButton() == MouseButton.PRIMARY),
                            tile.getProperties().getValue("x"),  tile.getProperties().getValue("y"))){
                            System.out.println("Ship");

                            Entity temp;

                            Iterator<Entity>  iterator = shipTiles.iterator();
                            for (int i = 0; i < shipTiles.size(); i++){
                                temp = iterator.next();
                                int tempId = temp.getProperties().getValue("Player");



                                if (
                                        (BattleshipMain.player1.getStateOfShipsCell(temp.getProperties().getValue("x"),  temp.getProperties().getValue("y")) == 1) &&
                                                (tempId == 1)
                                ){
                                    temp.getViewComponent().setOpacity(0.5);
                                    int tempState = BattleshipMain.player1.getStateOfShipsCell(tile.getProperties().getValue("x"),  tile.getProperties().getValue("y"));
                                    System.out.println("Temp:" + tempState);
                                }
                            }


                            if (--BattleshipMain.player1ShipsToPlace == 0){

                                BattleshipMain.gameRunning = true;
                            }

                         }
                    }*/
                }
                case "hit" -> {
                    if (BattleshipMain.gameRunning) {

                        switch (playerId) {
                            case 1 -> {
                                //todo: hook up boolean return to player change
                                BattleshipMain.player2.shoot(tile.getProperties().getValue("x"), tile.getProperties().getValue("y"));
                            }
                            case 2 -> BattleshipMain.player1.shoot(tile.getProperties().getValue("x"), tile.getProperties().getValue("y"));
                        }

                    }
                }


            }

            //todo find fix for texture loading bug (backlog)
           //spawn("ship", tile.getX(), tile.getY());

            getBoardState(tileType);















        });










        return tile;









    }

}


