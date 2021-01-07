
package com.almasb.fxglgames.drop;

import com.almasb.fxgl.app.services.FXGLAssetLoaderService;
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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

/**
 * This class is not functional at the moment. Logic for spawning needs to be written and hooked up
 */

public class ShipFactory implements EntityFactory {



    //todo implement logic to check which ship should be spawned in what orientation when

    //todo fix entity despawning and respawning behaviour


    @Spawns("ship")
    public Entity newShip(SpawnData data) throws IOException {

        File test = new File("src/main/resources/assets/textures/ship5.png");



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
