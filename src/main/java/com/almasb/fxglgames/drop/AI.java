package com.almasb.fxglgames.drop;

import java.util.Random;


public class AI {
/*
//Zugtimer??
// https://github.com/GrahamBlanshard/AI-Battleship/blob/master/prograham/battleship/player/AIPlayer.java
    public void placeShip()
    {
        Random generator = new Random();	//Used to simulate computer "guessing"
        Boolean successful = false;
        boolean direction;

        // Iterate through ships and place them
        for (Ship c : ships) {
            do {
                Integer orientation = generator.nextInt(2);
                if(orientation == 0)
                    direction = true; // Horizontal
                else
                    direction = false; // Vertical

                Coordinate shipSpot = new Coordinate(generator.nextInt(grid.getGridSize()),generator.nextInt(grid.getGridSize()));

                successful = c.placeShip(direction, grid, shipSpot.x(), shipSpot.y(), c.getSize());
                grid.addShip(c.shipCoordsToGrid());
            } while(!successful);
        }
    }


*/


    public void EasyAI ()
    {

            Random randomGenerator=new Random();

            /*
                int Schuss = randomGenerator.nextInt(100) + 1);
                if (HitMethode == 1) sofern Treffer auf Schiff führen

                getroffen ?

                übergeben an HitMethode


             */


    }






/*
    //Computer schießt um das Schiff herum
//Vorsicht nicht über das Spielfeld hinausschießen
//Wenn Computer Schiff trifft x-Achse abfragen (links rechts), //wenn nicht y-Achse abfragen bis Schüsse ins Leere gehen
//Zusatzklausel, so lange schießen bis Leben aus ist?
//Counterbalance zur x-Achsen abfragen, Computer weiß Schiffsleben
//Schiffe als Objekte mit Leben true/false??
    public void MediumAI (AnzahlSpielfelder)
    {

// nextInt ist normalerweise exklusive des höchsten wertes, deswegen plus +1

        int RNG = ThreadLocalRandom.current().nextInt(min, max + 1);




    }



    //Computer weiß wo Schiffe stehen und schießt nach gezieltem //versenken von Schiffen random im wasser umher
    public void HardAI (AnzahlSpielfelder)
    {

        // nextInt ist normalerweise exklusive des höchsten wertes, deswegen plus +1

        int RNG = ThreadLocalRandom.current().nextInt(min, max + 1);




    }



 */
}



