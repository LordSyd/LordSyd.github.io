package com.almasb.fxglgames.drop;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;


import java.util.concurrent.atomic.AtomicBoolean;

import static com.almasb.fxgl.dsl.FXGL.*;
//todo fix placement of boards

public class BattleshipMain extends GameApplication {

    static Player player1 = new Player();
    static Player player2 = new Player();
    static int player1ShipsToPlace = 5;
    static int player2ShipsToPlace = 5;
    static boolean gameRunning = false;
    static boolean player1Turn = true;
    static boolean betweenTurnMenuActive = false;

    int deadPlayer = 0;

    public enum Type {
        DROPLET,TILE
    }



    @Override
    protected void initSettings(GameSettings settings) {

        settings.setMainMenuEnabled(true);



        /*settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new MainMenu(MenuType.MAIN_MENU);
            }
        });*/





        settings.setTitle("Battleship");
        settings.setVersion("1.0");
        settings.setWidth(1200);
        settings.setHeight(800);




    }

    /*public Entity[][] shipBoard = new Entity[10][10];

    public Entity[][] hitBoard = new Entity[10][10];

    public Entity[][] getShipBoard() {
        return shipBoard;
    }

    public Entity[][] getHitBoard() {
        return hitBoard;
    }*/



    @Override
    protected void initGame() {



        getGameWorld().addEntityFactory(new TileFactory());
        getGameWorld().addEntityFactory(new ShipFactory());



        //Spawn  hitBoard
        spawnHitBoard(1);


        //Spawn shipBoard

        spawnShipBoard(1);




       /* BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);
        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));*/

        /*enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot){
                System.out.println("already shot");
                return;}





            enemyTurn = !cell.shoot();



            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });*/

        /*playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (player1.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {

            }



            System.out.println(player1.getStateOfShipsCell(cell.x, cell.y));
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }*/


          /*  System.out.println(cell.localToScene(cell.getLayoutBounds()).getMinX());




            Entity Droplet = spawnDroplet(cell.localToScene(cell.getLayoutBounds()).getMinX(), cell.localToScene(cell.getLayoutBounds()).getMinY());

            getGameWorld().addEntity(Droplet);


            //getGameWorld().getEntitiesByType(Type.DROPLET).forEach(droplet -> droplet.);



        });*/

       /* VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);*/








        /*root.setCenter(vbox);*/









    }

    @Override
    protected void onUpdate(double tpf) {
        deadPlayer = checkPlayerDead();

        if (deadPlayer != 0){
            showGameOverMenu();
        }
        TileFactory.updateBoardState();
        if (betweenTurnMenuActive){
            showTurnMenu();
        }
    }

    private int checkPlayerDead() {
        if (player1.isDead()){
            return 1;
        }else if (player2.isDead()){
            return 2;
        }else{
            return 0;
        }
    }

    @Override
    protected void initUI(){
        /*BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);
        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));
        //FXGL.getGameScene().addUINode(root);

        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot){
                System.out.println("already shot");
                return;}





            enemyTurn = !cell.shoot();



            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }


            System.out.println(playerBoard.getBoundsInParent());

            spawnDroplet(68, 20);


        });

        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);*/




    }


   /* Scene scene2;
    Stage window;*/

    static protected void showStartMenu(){
        getGameWorld().getEntitiesCopy().forEach(e -> e.removeFromWorld());
        getSceneService().pushSubScene(new StartMenuSubScene());
    }

    static protected void showTurnMenu(){
        getGameWorld().getEntitiesCopy().forEach(e -> e.removeFromWorld());
        if (player1Turn){
            getSceneService().pushSubScene(new NewTurnSubScene(1, gameRunning));
        }else{
            getSceneService().pushSubScene(new NewTurnSubScene(2, gameRunning));
        }

    }

    protected void showGameOverMenu(){
        getGameWorld().getEntitiesCopy().forEach(e -> e.removeFromWorld());

            getSceneService().pushSubScene(new GameOverScreen(deadPlayer));
    }

    static protected void clearTileArrays(){
        TileFactory.player1shipTiles.clear();
        TileFactory.player2shipTiles.clear();
        TileFactory.player1hitTiles.clear();
        TileFactory.player2hitTiles.clear();
    }

    static protected void closeTurnMenu(){
        betweenTurnMenuActive = false;
        clearTileArrays();

        /*if (player1Turn){
            player1Turn = false;
        }*/

        getGameWorld().getEntitiesCopy().forEach(e -> e.removeFromWorld());
        getSceneService().popSubScene();

        if (player1Turn){
            spawnHitBoard(1);
            spawnShipBoard(1);
        }else{
            spawnHitBoard(2);
            spawnShipBoard(2);
        }



        if (player1ShipsToPlace == 0 && player2ShipsToPlace ==0){

            gameRunning = true;
        }




    }


    static protected void closeStartMenu(){
        getGameWorld().getEntitiesCopy().forEach(e -> e.removeFromWorld());
        getSceneService().popSubScene();

        spawnHitBoard(1);
        spawnShipBoard(1);

    }


    private static void spawnShipBoard(int player){


        int startX = 0;
        int startY = 0;

        switch (player){
            case 1 -> {
                startX = 68;
                startY = 420;
            }

            case 2 -> {
                startX = 600;
                startY = 420;
            }
        }



        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {

                Entity tile = spawn("tile", x * 30 + startX, y * 30 + startY);

                tile.setProperty("x", x);
                tile.setProperty("y", y);
                tile.setProperty("boardType", "ship");
                tile.setProperty("Player", player);
                if (player == 1){
                    TileFactory.player1shipTiles.add(tile);
                }else{
                    TileFactory.player2shipTiles.add(tile);
                }


            }

        }
    }

    private static void spawnHitBoard(int player){
        TileFactory.player1hitTiles.clear();
        TileFactory.player2hitTiles.clear();
        int startX = 0;
        int startY = 0;

        switch (player){
            case 1 -> {
                startX = 68;
                startY = 60;
            }

            case 2 -> {
                startX = 600;
                startY = 60;
            }
        }


        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {

                Entity tile = spawn("tile", x * 30 + startX, y * 30 + startY);

                tile.setProperty("x", x);
                tile.setProperty("y", y);
                tile.setProperty("boardType", "hit");
                tile.setProperty("Player", player);
                if (player == 1){
                    TileFactory.player1hitTiles.add(tile);
                }else{
                    TileFactory.player2hitTiles.add(tile);
                }

            }

        }
    }




    /*private boolean running = false;
    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);

        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));

        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot){
                System.out.println("already shot");
                return;}





            enemyTurn = !cell.shoot();



            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }

            spawnDroplet(cell.x, cell.y);
        });

        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        return root;
    }*/

   /* private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }*/

    /*private void startGame() {
        // place enemy ships
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }*/

    /*static Entity spawnDroplet(double x, double y) {
        
        //String path = this.getClass().getResource("/assets/textures/ship_1x5.png").toExternalForm();


       Entity Droplet = FXGL.entityBuilder()
                .type(BattleshipMain.Type.DROPLET)
                .at(x,y)
                .viewWithBBox("assets/textures/ship_1x5.png")
                .build();

       return Droplet;
    }*/










    /*public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        BorderPane root = new BorderPane();
        BorderPane root2 = new BorderPane();
        root.setPrefSize(600, 800);
        root2.setPrefSize(600, 800);

        root.setRight(new Text("RIGHT SIDEBAR - CONTROLS"));

        VBox layoutTest = new VBox(20);
        Scene scene2 = new Scene(root2);

        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            System.out.println(cell.x + ": " + cell.y);
            if (cell.wasShot){
                System.out.println("already shot");
                return;}





            enemyTurn = !cell.shoot();



            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn) {
                enemyMove();
                VBox vbox2 = new VBox(50, playerBoard, enemyBoard);
                vbox2.setAlignment(Pos.CENTER);
                root2.setCenter(vbox2);
                window.setScene(scene2);
            }
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            System.out.println(cell.x + ": " + cell.y);
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }

            spawnDroplet(cell.x, cell.y);
        });

        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        //
        vbox.setAlignment(Pos.CENTER);
        //

        root.setCenter(vbox);
        //root2.setCenter(vbox2);


        Scene scene = new Scene(root);
        window.setTitle("Battleship");
        window.setScene(scene);
        window.setResizable(false);





        window.show();






    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
