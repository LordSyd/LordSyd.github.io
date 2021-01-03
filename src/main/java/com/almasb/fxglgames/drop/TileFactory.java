
package com.almasb.fxglgames.drop;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IDComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


import java.util.ArrayList;
import java.util.Iterator;

import static com.almasb.fxgl.dsl.FXGL.*;

public class TileFactory implements EntityFactory {

    static ArrayList<Entity> shipTiles = new ArrayList<Entity>();
    static ArrayList<Entity> hitTiles = new ArrayList<Entity>();











    @Spawns("tile")
    public Entity newTile(SpawnData data) {





        Ship ship = null;











        var tile = FXGL.entityBuilder(data)

                //.type(BattleshipMain.Type.TILE)
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .with(new TileViewComponent())
                .build();







        tile.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {


            System.out.println("clicked:  "+ tile.getProperties().getValue("x") + tile.getProperties().getValue("y"));
            

            int playerId = tile.getProperties().getValue("Player");
            String tileType = tile.getProperties().getValue("boardType");
            
            switch (tileType) {

                case "ship" -> {
                    if (!BattleshipMain.gameRunning)
                    switch (playerId) {
                        case 1 -> { {

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
                        }}
                        case 2 -> {}
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
                case "hit" -> System.out.println("Hit");


            }

           spawn("ship", tile.getX(), tile.getY());











        });









        return tile;









    }

}
