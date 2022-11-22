import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameClient2 extends Application {
    Scanner in;
    PrintWriter out;
    int player;
    int selectX;
    int selectY;
    int[] currentPlayer = {1};
    private boolean waiting = true;
    Boolean start = false;
    GameInterface gameInterface;
    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        final int GAME_PORT = 4399;
        gameInterface = new GameInterface();
        stage.setTitle("Game");
        Scene scene = new Scene(gameInterface, 250, 230);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        try {
            Socket s = new Socket("localhost",  GAME_PORT);
            in = new Scanner(s.getInputStream());
            out = new PrintWriter(s.getOutputStream());
            stage.setOnCloseRequest(e -> {
                try {
                    s.close();
                    System.exit(1);
                } catch (IOException ex) {
                }
            });
        } catch (ConnectException e) {
            stage.close();
            System.out.println("The server has disconnected");
            System.exit(1);
        }gameInterface.chessBoard.setOnMouseClicked(e -> {
            if(e.getX() > 48 && e.getX() < 198 && e.getY() > 2 && e.getY() < 152 && currentPlayer[0] == player && start) {
                selectX = (int)((e.getX() - 48) / 51);
                selectY = (int)((e.getY() - 2) / 51);
                setChess(selectX, selectY, currentPlayer);
                waiting = false;
            }
        });
        new Thread(() -> {
                player = Integer.parseInt(in.nextLine());
                if (player == 1) {
                    System.out.println(in.nextLine());
                }
                System.out.println(in.nextLine());
                 start = true;
                while (true) {
                if (player == 1) {
                    try {
                        waitForPlayer();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    sendMove();
                    receiveMove(stage);
                } else if (player == -1) {
                    receiveMove(stage);
                    try {
                        waitForPlayer();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    sendMove();
                }
            }
        }).start();
    }
    public void sendMove() {
        out.println(selectX);
        out.flush();
        out.println(selectY);
        out.flush();
    }
    public void waitForPlayer() throws InterruptedException {
        while (waiting) {
            Thread.sleep(100);
        }
        waiting = true;
    }
    public void receiveMove(Stage stage) {
        if (!gameInterface.gameOver) {
        try {
            int x = in.nextInt();
            if (x == -1) {
                System.out.println("Another player quit the game");
                Platform.runLater(stage::close);
                System.exit(1);
            }
            int y = in.nextInt();
            Platform.runLater(() -> setChess(x, y, currentPlayer));
        } catch (NoSuchElementException e) {
            Platform.runLater(stage::close);
            System.out.println("The server has disconnected");
            System.exit(1);
        }
        }
    }
    public void setChess(int x, int y, int[] currentPlayer) {
        double x1 = 48 + x * 51;
        double y1 = 2 + y * 51;
        double x2 = 48 + (x + 1) * 51;
        double y2 = 2 + (y + 1) * 51;
        if (currentPlayer[0] == 1 && !gameInterface.gameOver && gameInterface.chessBoard.chessBoard[x][y] == 0) {
            gameInterface.chessBoard.chessBoard[x][y] = currentPlayer[0];
            gameInterface.chessBoard.getChildren().add(new CirclePane(x1 + 25.5, y1 + 25.5));
            gameInterface.topStackPane.getChildren().clear();
            gameInterface.topStackPane.getChildren().add(gameInterface.playerX);
        } else if (!gameInterface.gameOver && gameInterface.chessBoard.chessBoard[x][y] == 0) {
            gameInterface.chessBoard.chessBoard[x][y] = currentPlayer[0];
            gameInterface.chessBoard.getChildren().add(new XPane(x1, y1, x2, y2, x2, y1, x1, y2));
            gameInterface.topStackPane.getChildren().clear();
            gameInterface.topStackPane.getChildren().add(gameInterface.playerO);
        }
        if (CheckWin.Check(gameInterface.chessBoard.chessBoard) == 2) {
            gameInterface.gameOver = true;
            gameInterface.setBottom(gameInterface.XWin);
        }else if (CheckWin.Check(gameInterface.chessBoard.chessBoard) == 1){
            gameInterface.gameOver = true;
            gameInterface.setBottom(gameInterface.OWin);
        }else if (CheckWin.Check(gameInterface.chessBoard.chessBoard) == 0) {
            gameInterface.gameOver = true;
            gameInterface.setBottom(gameInterface.Draw);
        }
        currentPlayer[0] = -currentPlayer[0];
    }
}




