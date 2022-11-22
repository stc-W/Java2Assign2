import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessBoard extends StackPane {
  int[][] chessBoard = new int[3][3];
  GridPane recGridPane = new GridPane();
  
  public ChessBoard() {
    recGridPane.setAlignment(Pos.TOP_CENTER);
    for (int i = 0; i < 3; i++) {
      for (int k = 0; k < 3; k++) {
        Rectangle r1 = new Rectangle(50, 50, 50, 50);
        r1.setStroke(Color.BLACK);
        r1.setFill(Color.WHITE);
        r1.setStrokeWidth(1);
        recGridPane.add(r1, i, k);
      }
    }
    getChildren().addAll(recGridPane);
  }

}
