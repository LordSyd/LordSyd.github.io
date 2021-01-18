package com.almasb.fxglgames.Battleship;

import java.util.Random;

//TODO --GewinnBedingung damit AI nicht endlos weiterschießt (Sieges Screen)? --Speichern der Schüsse der AI notwendig für extern oder speichert hitMethod Schüsse ab?


public class AI {


    public void EasyAI ()
    {

            Random randomGenerator=new Random();


                int SchussX = randomGenerator.nextInt(10) + 1; // Ausgabe der Werte im Bereich 1-10
                int SchussY = randomGenerator.nextInt(10) + 1;

/*
                while (hit(SchussY, SchussX) == true) //übergeben an HitMethode und solange Treffer, Übergabe weiterer Schüsse an hitMethod
                {
                 int SchussX = randomGenerator.nextInt(10) + 1);
                 int SchussY = randomGenerator.nextInt(10) + 1);
                 }

                 player.shoot; /Benachrichtigung über Zug des Spielers


                //if (Board[SchussY][SchussX])
                //if (tile.getProperties().getValue("x") == true && tile.getProperties().getValue(SchussY) == true ) {

                 }



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



    }



    //Computer weiß wo Schiffe stehen und schießt nach gezieltem //versenken von Schiffen random im wasser umher

    public void HardAI (AnzahlSpielfelder)
    {

        int SchussX = get.ShipX[1];
        int SchussY = get.ShipY[1];

        while (hit(SchussY, SchussX) == true)
              {
                int SchussX = get.ShipX[i];
                int SchussY = get.ShipY[i];

                if (getHealthShip(i) == 0) {
                break;
                }
              }


          player.shoot; /Benachrichtigung über Zug des Spielers



    }



 */
}





/*

Zugtimer??

andere Java Battleship Computer AI:

https://github.com/GrahamBlanshard/AI-Battleship/blob/master/prograham/battleship/player/AIPlayer.java

*/



