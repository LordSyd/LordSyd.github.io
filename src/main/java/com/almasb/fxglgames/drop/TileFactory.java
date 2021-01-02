
package com.almasb.fxglgames.drop;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IDComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.input.MouseEvent;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class TileFactory implements EntityFactory {




    @Spawns("tile")
    public Entity newTile(SpawnData data) {

        int x = (int) (data.getX() - 68) / 30;
        int y = (int) (data.getY() - 420) /30;



        var tile = FXGL.entityBuilder(data)

                //.type(BattleshipMain.Type.TILE)
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .with(new TileViewComponent())
                .build();




        tile.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("clicked:  "+ tile.getProperties().getValue("x") + tile.getProperties().getValue("y"));





            //Entity Droplet = BattleshipMain.spawnDroplet(tile.getX(), tile.getY());

            //getGameWorld().addEntity(Droplet);
        });

        return tile;









    }
}
