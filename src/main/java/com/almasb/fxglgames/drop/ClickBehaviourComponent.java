package com.almasb.fxglgames.drop;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ClickBehaviourComponent extends Component implements EventHandler<MouseEvent> {

    Entity tile;

    public ClickBehaviourComponent(Entity entity) {

        this.tile = entity;

    }

    @Override
    public void handle(MouseEvent event) {
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
                                                event.getButton() == MouseButton.PRIMARY),
                                        tile.getProperties().getValue("x"), tile.getProperties().getValue("y"))) {
                                System.out.println("Ship");



                                TileFactory.getBoardState(tileType, 1);

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

                            BattleshipMain.player2.shoot(tile.getProperties().getValue("x"), tile.getProperties().getValue("y"));
                        }
                        case 2 -> BattleshipMain.player1.shoot(tile.getProperties().getValue("x"), tile.getProperties().getValue("y"));
                    }

                }
            }


        }

        //todo find fix for texture loading bug (backlog)
        //spawn("ship", tile.getX(), tile.getY());

        /*getBoardState(tileType);*/















    }




}
