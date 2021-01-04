
package com.almasb.fxglgames.drop;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class ShipFactory implements EntityFactory {





    @Spawns("ship")
    public Entity newShip(SpawnData data) {


        var ship = FXGL.entityBuilder(data)

                //.type(BattleshipMain.Type.TILE)
                .viewWithBBox("assets/textures/ship_1x5.png")
                .build();





        /*tile.getViewComponent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("clicked:  "+ tile.getProperties().getValue("x") + tile.getProperties().getValue("y"));

            Entity spawnDroplet(double x, double y) {

                //String path = this.getClass().getResource("/assets/textures/ship_1x5.png").toExternalForm();


                Entity Droplet = FXGL.entityBuilder()
                        .type(BattleshipMain.Type.DROPLET)
                        .at(x,y)
                        .viewWithBBox("ship_1x5.png")
                        .build();


            }






        });*/

        return ship;









    }
}
