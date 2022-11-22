import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer {
  
  public static void main(String[] args) throws IOException {
    final int GAME_PORT = 4399;
    ServerSocket server = new ServerSocket(GAME_PORT);
    System.out.println("Waiting for clients to connect...");
    while (true) {
      Socket s1 = server.accept();
      PrintWriter out1 = new PrintWriter(s1.getOutputStream());
      System.out.println("A player enter the game.");
      out1.println(1);
      out1.flush();
      out1.println("Please wait another Player.");
      out1.flush();
      Socket s2 = server.accept();
      PrintWriter out2 = new PrintWriter(s2.getOutputStream());
      System.out.println("A game start");
      out1.println("Game Start.");
      out1.flush();
      out2.println(-1);
      out2.flush();
      out2.println("Game Start.");
      out2.flush();
      new Thread(new GameService(s1, s2)).start();
    }
  }
}
