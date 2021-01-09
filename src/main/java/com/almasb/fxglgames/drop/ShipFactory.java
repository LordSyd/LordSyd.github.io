
package com.almasb.fxglgames.drop;

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


/**
 * This class is not functional at the moment. Logic for spawning needs to be written and hooked up
 */

public class ShipFactory implements EntityFactory {



    //todo implement logic to check which ship should be spawned in what orientation when

    //todo fix entity despawning and respawning behaviour


    @Spawns("ship")
    public Entity newShip(SpawnData data) throws FileNotFoundException {
        File test = new File("src/main/assets/textures/ship_1x1.png");

        switch ( BattleshipMain.player1ShipsToPlace ) {
            case 4 ->  test = new File("src/main/assets/textures/ship5.png");
            case 3 ->  test = new File("src/main/assets/textures/ship_1x4.png");
            case 2 ->  test = new File("src/main/assets/textures/ship_1x3.png");
            case 1 ->  test = new File("src/main/assets/textures/ship_1x2.png");
            case 0 ->  test = new File("src/main/assets/textures/ship_1x1.png");
        }


        FileInputStream input = new FileInputStream(test.getAbsolutePath());


        Image img = new Image(input);

        ImageView view = new ImageView(img);


        var ship = FXGL.entityBuilder(data)


                .viewWithBBox(view)
                .build();







        return ship;









    }
}
