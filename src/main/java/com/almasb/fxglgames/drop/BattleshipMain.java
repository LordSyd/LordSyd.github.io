package com.almasb.fxglgames.drop;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;


import static com.almasb.fxgl.dsl.FXGL.*;
//todo fix placement of boards

public class BattleshipMain extends GameApplication {
    /**
     * Because this App was written using an existing javaFX Battleship implementation by Almas Baimagambetov as
     * reference, some classes and methods may either be redundant or might me exchanged for simple variables. In the
     * sake of getting a finished App done this optimisation is viewed as optional, as long as the overall game works.
     *
     * Maybe if time remains the logic will be streamlined.
     *
     * Also for testing purposes the boards of player one and player two are spawned on different sides of the screen
     */

    static Player player1 = new Player();
    static Player player2 = new Player();
    static int player1ShipsToPlace = 5;
    static int player2ShipsToPlace = 5;
    static boolean gameRunning = false;
    static boolean player1Turn = true;
    static boolean betweenTurnMenuActive = false;

    private int deadPlayer = 0;



    @Override
    protected void initSettings(GameSettings settings) {

        settings.setMainMenuEnabled(true);

        //todo implement main menu spawning

        /*settings.setSceneFactory(new SceneFactory()

        {
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



    /**
     * adds Entity Factories and calls spawn method for boards
     */
    @Override
    protected void initGame() {


        getGameWorld().addEntityFactory(new TileFactory());

        //todo find workaround for engine bug with texture assignment
        getGameWorld().addEntityFactory(new ShipFactory());

        //Spawn  hitBoard player 1
        spawnHitBoard(1);


        //Spawn shipBoard player1

        spawnShipBoard(1);


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

    /**
     * simple method to check if any player has died
     */
    private int checkPlayerDead() {

        if (player1.isDead()){
            return 1;
        }else if (player2.isDead()){
            return 2;
        }else{
            return 0;
        }
    }

    /**
     * not used at the moment, test code remains inside for the time beeing
     */
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


    /**
     * Some methods in here are only for future proofing and not in use at the moment
     */

    static protected void showStartMenu(){
        getGameWorld().getEntitiesCopy().forEach(e -> e.removeFromWorld());
        getSceneService().pushSubScene(new StartMenuSubScene());
    }

    /**
     * removes all spawned entities and checks turn boolean for which scene to push
     */
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

    /**
     * used to clear arraylists holding the tiles after each round
     */
    static protected void clearTileArrays(){

        TileFactory.player1shipTiles.clear();
        TileFactory.player2shipTiles.clear();
        TileFactory.player1hitTiles.clear();
        TileFactory.player2hitTiles.clear();
    }

    static protected void closeTurnMenu(){
        betweenTurnMenuActive = false;
        clearTileArrays();

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


    /**
     * method spawning the boards for placing ships
     * @param player
     */
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

    /**
     * method for spawning the boards for shooting at other player
     * @param player
     */
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


    public static void main(String[] args) {
        launch(args);
    }
}
