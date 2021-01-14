package com.almasb.fxglgames.drop;

import com.almasb.fxgl.entity.component.Component;

import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;


/**
 * Class governs behavior on click - was handled by TileFactory before. Logic is basically the same
 */

public class ClickBehaviourComponent extends Component{


        
    boolean primary = true;

    /**
     * Called when secondary mouse button was clicked - only changes the boolean and then calls onPrimaryClick()
     */

    public void onSecondaryClick() {

        primary = false;
        onPrimaryClick();

    }

    /**
     * Same as it was in Tilefactory, just has a boolean instead of a check for a mouse button
     */


    public void onPrimaryClick() {
        System.out.println("clicked:  "+ entity.getProperties().getValue("x") + entity.getProperties().getValue("y"));

        int playerId = entity.getProperties().getValue("Player");
        String tileType = entity.getProperties().getValue("boardType");

        switch (tileType) {

            case "ship" -> {
                if (!BattleshipMain.isGameRunning()) {
                    switch (playerId) {
                        case 1 -> {
                            {
                                if (BattleshipMain.player1.placeShip(
                                        new Ship(BattleshipMain.player1ShipsToPlace,
                                                primary),
                                        entity.getProperties().getValue("x"), entity.getProperties().getValue("y")))
                                {
                                    System.out.println("Ship");

                                    TileFactory.getBoardStateColors(tileType, 1);

                                    if (--BattleshipMain.player1ShipsToPlace == 0) {

                                        BattleshipMain.setPlayer1Turn(false);

                                        //todo exchange test for menu with real player change submenu
                                        BattleshipMain.showTurnMenu();
                                    }
                                }
                            }
                        }
                        case 2 -> {
                            if (BattleshipMain.player2.placeShip(
                                    new Ship(
                                            BattleshipMain.player2ShipsToPlace,
                                            primary
                                    ),
                                    entity.getProperties().getValue("x"), entity.getProperties().getValue("y"))
                                )
                            {
                                System.out.println("Ship");



                                TileFactory.getBoardStateColors(tileType,2);




                                if (--BattleshipMain.player2ShipsToPlace == 0) {

                                    BattleshipMain.setPlayer1Turn(true);


                                    //todo exchange test for menu with real player change submenu
                                    BattleshipMain.showTurnMenu();
                                }

                            }

                        }
                    }
                }
            }
            case "hit" -> {
                if (BattleshipMain.isGameRunning()) {

                    switch (playerId) {
                        case 1 -> {
                            TileFactory.updateBoardState();
                            if (BattleshipMain.betweenTurnMenuActive =
                                    BattleshipMain.player2.shoot(entity.getProperties().getValue("x"), entity.getProperties().getValue("y"))) {
                                BattleshipMain.setPlayer1Turn(false);
                            }

                        }


                        case 2 -> {
                            TileFactory.getBoardStateColors(tileType, 2);

                            if (BattleshipMain.betweenTurnMenuActive =
                                    BattleshipMain.player1.shoot(entity.getProperties().getValue("x"), entity.getProperties().getValue("y"))) {
                                BattleshipMain.setPlayer1Turn(true);
                            }
                        }
                    }

                }
            }


        }

        //todo find fix for texture loading bug (backlog)


















    }



}
