
package com.almasb.fxglgames.Battleship;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import static com.almasb.fxgl.dsl.FXGL.*;



/**
 * This class is not functional at the moment. Logic for spawning needs to be written and hooked up
 */

public class ShipFactory implements EntityFactory {

    private static String nextShipSpriteLocation;


    private static String locationResolver(int type, boolean vertical){
        StringBuilder location = new StringBuilder();
        location.append("src/main/resources/assets/textures/");

        if (!vertical){
            switch (type){
                case 1 -> location.append("ship_1x1.png");
                case 2 -> location.append("ship_1x2_vertical.png");
                case 3 -> location.append("ship_1x3_vertical.png");
                case 4 -> location.append("ship_1x4_vertical.png");
                case 5 -> location.append("ship_1x5_vertical.png");
            }
        }else{
            switch (type){
                case 1 -> location.append("ship_1x1.png");
                case 2 -> location.append("ship_1x2.png");
                case 3 -> location.append("ship_1x3.png");
                case 4 -> location.append("ship_1x4.png");
                case 5 -> location.append("ship_1x5.png");
            }
        }
        return location.toString();
    }

    public static void updateShipSpawns(Player player) {
        Ship ship;
        ArrayList<Ship> shipsList = player.getShipInstances();

        if (shipsList.size() != 0) {
            Iterator<Ship> iterator = shipsList.iterator();

            for (int i = 0; i < shipsList.size(); i++) {
                ship = iterator.next();
                nextShipSpriteLocation = locationResolver(ship.getType(), ship.isVertical());
                spawn("ship", ship.getX(), ship.getY());

            }
        }
    }

    @Spawns("ship")
    public Entity newShip(SpawnData data) throws FileNotFoundException {
        File test = new File(nextShipSpriteLocation);

        FileInputStream input = new FileInputStream(test.getAbsolutePath());

        Image img = new Image(input);

        ImageView view = new ImageView(img);

        var ship = FXGL.entityBuilder(data)

                .viewWithBBox(view)
                .build();

        return ship;
    }
}
