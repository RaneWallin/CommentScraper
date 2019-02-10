import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CScrape extends Application implements Constants {
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.out.println(ERROR + e.getMessage());
        }
    }


    public void start(Stage stage) {
        GUI gui = new GUI(stage);
        FileHandler handler = new FileHandler(gui);
    }

}