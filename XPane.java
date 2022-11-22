import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class XPane extends Pane{
   public XPane(double x1, double y1, double x2, double y2,double x3, double y3, double x4,double y4) {
       Line line1 = new Line(x1 + 8, y1 + 8, x2 - 8, y2 - 8);
       line1.setStrokeWidth(3);
       line1.setStroke(Color.RED);
       Line line2 = new Line(x3 - 8, y3 + 8, x4 + 8, y4 - 8);
       line2.setStrokeWidth(3);
       line2.setStroke(Color.RED);
       getChildren().addAll(line1, line2);
   }
}
