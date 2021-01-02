package com.almasb.fxglgames.drop;


import java.util.Random;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.almasb.fxglgames.drop.Board.Cell;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.loopBGM;


public class BattleshipMain extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {


        settings.setTitle("Battleship");
        settings.setVersion("1.0");
        settings.setWidth(600);
        settings.setHeight(800);




    }

    @Override
    protected void initGame() {
        BoardState State = new BoardState();

        Player player1 = new Player();
        Player player2 = new Player();

        System.out.println("State before: " + State.getStateOfCell(0,9));
        State.setStateOfCell(0,9, 2);
        System.out.println("State after: " + State.getStateOfCell(0,9));

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
            if (player1.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {

            }



            System.out.println(player1.getStateOfShipsCell(cell.x, cell.y));
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }


            System.out.println(cell.localToScene(cell.getLayoutBounds()).getMinX());

            //cell.localToScene(cell.getLayoutBounds()).getMinY()


            Entity Droplet = spawnDroplet(cell.localToScene(cell.getLayoutBounds()).getMinX(), cell.localToScene(cell.getLayoutBounds()).getMinY());

            getGameWorld().addEntity(Droplet);



        });

        VBox vbox = new VBox(50, enemyBoard, playerBoard);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        FXGL.getGameScene().addChild(root);







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

    public enum Type {
        DROPLET
    }

    Scene scene2;
    Stage window;


    private boolean running = false;
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
    }

    private void enemyMove() {
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
    }

    private void startGame() {
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
    }

    private Entity spawnDroplet(double x, double y) {
       Entity Droplet = FXGL.entityBuilder()
                .type(DropApp.Type.DROPLET)
                .at(x,y)
                .viewWithBBox("droplet.png")

                .build();

       return Droplet;
    }




    public void start(Stage primaryStage) throws Exception {
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
                /*VBox vbox2 = new VBox(50, playerBoard, enemyBoard);
                vbox2.setAlignment(Pos.CENTER);
                root2.setCenter(vbox2);
                window.setScene(scene2);*/
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






    }

    public static void main(String[] args) {
        launch(args);
    }
}
