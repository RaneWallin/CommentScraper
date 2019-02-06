import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CommentScraper extends Application implements Constants {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        initUI(stage);
    }

    private void initUI(Stage stage) {
        HBox mainGUI = new HBox(MAIN_GUI_SPACING);
        VBox rightBox = new VBox();
        VBox leftBox = new VBox();
        Scene scene = new Scene(mainGUI);



        stage.setMinHeight(SCENE_HEIGHT);
        stage.setMinWidth(SCENE_WIDTH);
        stage.setMaxHeight(SCENE_HEIGHT);
        stage.setMaxWidth(SCENE_WIDTH);

        rightBox.setMinHeight(VBOX_HEIGHT);
        rightBox.setMinWidth(VBOX_WIDTH);
        leftBox.setMinHeight(VBOX_HEIGHT);
        leftBox.setMinWidth(VBOX_WIDTH);
        rightBox.setMaxHeight(VBOX_HEIGHT);
        rightBox.setMaxWidth(VBOX_WIDTH);
        leftBox.setMaxHeight(VBOX_HEIGHT);
        leftBox.setMaxWidth(VBOX_WIDTH);

        rightBox.setStyle("-fx-border-color: black");
        leftBox.setStyle("-fx-border-color: black");
        rightBox.setAlignment(Pos.CENTER);
        leftBox.setAlignment(Pos.CENTER);

        mainGUI.getChildren().addAll(leftBox, rightBox);
        mainGUI.setAlignment(Pos.CENTER);

        stage.setScene(scene);
        stage.setTitle(GUI_TITLE);
        stage.show();
    }
}
