import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameService implements Runnable{
    Socket s1;
    Socket s2;
    Scanner in1;
    PrintWriter out1;
    Scanner in2;
    PrintWriter out2;
    int[][] board = new int[3][3];
    boolean continueToPlay = true;
    public GameService(Socket S1, Socket S2) throws IOException {
        s1 = S1;
        s2 = S2;
    }
    @Override
    public void run() {
        try {
            in1 = new Scanner(s1.getInputStream());
            in2 = new Scanner(s2.getInputStream());
            out1 = new PrintWriter(s1.getOutputStream());
            out2 = new PrintWriter(s2.getOutputStream());
        } catch (IOException e) {
        }
            play();
    }
    public void play() {
        int player = 1;
        int x;
        int y;
        while (true) {
            if (player == 1) {
                try {
                    x = Integer.parseInt(in1.nextLine());
                    y = Integer.parseInt(in1.nextLine());
                } catch (NoSuchElementException e) {
                    System.out.println("A player quit");
                    out2.println(-1);
                    out2.flush();
                    break;
                }
                    board[x][y] = 1;
                    out2.println(x);
                    out2.flush();
                    out2.println(y);
                    out2.flush();
                    player = player * (-1);
                } else {
                try {
                x = Integer.parseInt(in2.nextLine());
                y = Integer.parseInt(in2.nextLine());
                } catch (NoSuchElementException e) {
                    System.out.println("A player quit");
                    out1.println(-1);
                    out1.flush();
                    break;
                }
                board[x][y] = -1;
                out1.println(x);
                out1.flush();
                out1.println(y);
                out1.flush();
                player = player * (-1);
            }
        }
    }
}


