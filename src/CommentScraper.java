import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CommentScraper extends Application implements Constants {
    public static void main(String[] args) {
        try {
            launch(args);
        }
        catch(Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        initUI(stage);
    }

    // Create main GUI
    private void initUI(Stage stage) {
        HBox mainGUI = new HBox(MAIN_GUI_SPACING);
        VBox outputBox = new VBox();
        HBox inputBox = new HBox();
        Scene scene = new Scene(mainGUI);

        stage.setMinHeight(SCENE_HEIGHT);
        stage.setMinWidth(SCENE_WIDTH);
        stage.setMaxHeight(SCENE_HEIGHT);
        stage.setMaxWidth(SCENE_WIDTH);

        outputBox.setMinHeight(VBOX_HEIGHT);
        outputBox.setMinWidth(VBOX_WIDTH);
        outputBox.setMaxHeight(VBOX_HEIGHT);
        outputBox.setMaxWidth(VBOX_WIDTH);

        inputBox.setMinHeight(VBOX_HEIGHT);
        inputBox.setMinWidth(VBOX_WIDTH);
        inputBox.setMaxHeight(VBOX_HEIGHT);
        inputBox.setMaxWidth(VBOX_WIDTH);

        outputBox.setStyle("-fx-border-color: black");
        inputBox.setStyle("-fx-border-color: black; -fx-padding: 25");

        outputBox.setAlignment(Pos.CENTER);
        inputBox.setAlignment(Pos.CENTER);

        setupInputBox(inputBox);

        mainGUI.getChildren().addAll(inputBox, outputBox);
        mainGUI.setAlignment(Pos.CENTER);

        stage.setScene(scene);
        stage.setTitle(GUI_TITLE);
        stage.show();
    }

    // add UI components to the input box
    private void setupInputBox(HBox inputBox) {
        Button startButton = new Button(START_BUTTON_TXT);
        TextField filePath = new TextField();

        inputBox.getChildren().addAll(filePath, startButton);
    }
}
