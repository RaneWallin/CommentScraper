import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CScrape extends Application implements Constants {
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
        GridPane main = getGridPane();
        Scene scene = new Scene(mainGUI);

        mainGUI.getChildren().addAll(main);
        mainGUI.setAlignment(Pos.CENTER);

        stage.setScene(scene);
        stage.setTitle(GUI_TITLE);
        stage.show();
    }



    // add(Node child, int columnIndex, int rowIndex)
    // add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
    public GridPane getGridPane() {
        GridPane grid = new GridPane();

        setDimensions(grid);

        addHeader(grid);

        addInputArea(grid);


        return grid;
    }

    private void setDimensions(GridPane grid) {
        // Establish minimum widths for columns. Widths are added in order
        // and there doesn't appear to be a way to apply a width to a
        // specific column.
        grid.getColumnConstraints().add(new ColumnConstraints());
        grid.getColumnConstraints().add(new ColumnConstraints());
        grid.getColumnConstraints().add(new ColumnConstraints(45));
        grid.getColumnConstraints().add(new ColumnConstraints(40));


        // Set grid spacing and padding
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(0, 10, 0, 10));
    }

    private void addHeader(GridPane grid) {
        // Create header components
        Text title = new Text(GUI_TITLE);
        title.setFont(Font.font(DEFAULT_FONT, FontWeight.BOLD, 20));

        // Add components to the grid
        grid.add(title, 1, 0,3, 1);
    }

    private void addInputArea(GridPane grid) {
        // Create input area components
        Text inputInstructions = new Text(INPUT_INSTRUCTIONS);
        TextField inputField = new TextField();
        Button startButton = new Button(START_BUTTON_TXT);
        Button fileChooserBtn = new Button(CHOOSER_BUTTON_TXT);


        // Add components to the grid
        grid.add(inputInstructions, 1, 3);
        grid.add(inputField, 1, 4);
        grid.add(startButton, 2, 4);
        grid.add(fileChooserBtn, 3, 4);

        // Row padding
        Text padding1 = new Text(BLANK_ROW);
        grid.add(padding1, 1, 5, 1, 2);
    }

    private void addOutputArea(GridPane grid) {
        // Create output components
        Label outputTextArea = new Label();


    }
}
