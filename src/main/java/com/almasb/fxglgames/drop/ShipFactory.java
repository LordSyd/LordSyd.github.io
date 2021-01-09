
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
import java.net.URI;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

/**
 * This class is not functional at the moment. There seems to be a bug inside the textureloader of FXGL that is out of
 * my ability to fix
 */

public class ShipFactory implements EntityFactory {





    @Spawns("ship")
    public Entity newShip(SpawnData data) throws FileNotFoundException {
        File test = new File("src/main/assets/textures/ship_1x1.png");

        switch ( BattleshipMain.player1ShipsToPlace ) {
            case 4 ->  test = new File("src/main/assets/textures/ship_1x5.png");
            case 3 ->  test = new File("src/main/assets/textures/ship_1x4.png");
            case 2 ->  test = new File("src/main/assets/textures/ship_1x3.png");
            case 1 ->  test = new File("src/main/assets/textures/ship_1x2.png");
            case 0 ->  test = new File("src/main/assets/textures/ship_1x1.png");
        }

        //File test = new File("src/main/assets/textures/ship_1x5.png");



        String str = test.getAbsolutePath();





        System.out.println(test.getAbsolutePath());
        System.out.println(str);



        FileInputStream input = new FileInputStream(str);



        Image img = new Image(input);

        ImageView view = new ImageView(img);


        var ship = FXGL.entityBuilder(data)

                //.type(BattleshipMain.Type.TILE)
                .viewWithBBox(view)
                .build();



        return ship;









    }
}
