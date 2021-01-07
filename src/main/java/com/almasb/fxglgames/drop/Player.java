package com.almasb.fxglgames.drop;




public class Player {


    private BoardState ships = new BoardState();
    private BoardState shots = new BoardState();

    //todo change player health to correct logic after testing is finished
    private int health = 5+4+3+2+1; //numbers of tiles per ship type

    public Player() {
    }

    public int getStateOfShipsCell(int x, int y) {
        return ships.getStateOfCell(x, y);
    }

    public int getStateOfHitCell(int x, int y) {
        return shots.getStateOfCell(x, y);
    }

    public boolean shoot(int x, int y){
        /**
         * returns
         */
        if (shots.getStateOfCell(x,y) != 0){
            return false;
        }else{
            if (ships.getStateOfCell(x,y) == 1){
                shots.setStateOfCell(x,y, 2);
                ships.setStateOfCell(x,y, 2);
                hit();
                return false;
            }else{
                shots.setStateOfCell(x,y, 1);
            }

            return true;
        }

    }

    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.type;

            if (ship.vertical) {
                for (int i = y; i < y + length; i++) {
                    ships.setStateOfCell(x, i, 1);


                }
            }
            else {
                for (int i = x; i < x + length; i++) {
                    ships.setStateOfCell(i, y, 1);
                }
            }

            return true;
        }

        return false;

    }

    public boolean isDead(){return health == 0;};

    public void hit() {
        health--;
    }

    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.type;

        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                if (!ships.isValidPoint(x, i))
                    return false;

                //Board.Cell cell = getCell(x, i);
                if (ships.getStateOfCell(x, i) != 0)
                    return false;

                for (int neighbor : ships.getNeighbors(x, i)) {
                    if (!ships.isValidPoint(x, i))
                        return false;

                    if (neighbor != 0)
                        return false;
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!ships.isValidPoint(i, y))
                    return false;

                if (ships.getStateOfCell(i, y) != 0)
                    return false;

                for (int neighbor : ships.getNeighbors(i, y)) {
                    if (!ships.isValidPoint(i, y))
                        return false;

                    if (neighbor != 0)
                        return false;
                }
            }
        }
        return true;
    }
}



