import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public class CirclePane extends Pane {
    public CirclePane(double centerX, double centerY) {
        Circle circle = new Circle();
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
        circle.setRadius(20);
        circle.setStrokeWidth(2);
        circle.setStroke(Color.BLUE);
        circle.setFill(Color.color(0, 0, 0, 0));
        getChildren().add(circle);
    }
}
