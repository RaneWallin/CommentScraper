/*
* Rane Wallin
* CSC205AB
*
* This class is all setting up the GUI and event listeners. No scraper logic here.
*
* References:
* GridPane layout: https://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm#JFXLY102
* GridPane layout: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
*/

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GUI implements Constants {

    private Button openButton;
    private Button inputButton;
    private Label outputTextArea;
    private TextField inputField;
    private FileHandler fileHandler;

    public GUI() {
        this.fileHandler = new FileHandler(this);
    }


    // Create main GUI
    public void initUI(Stage stage) {
        GridPane main = getGridPane();
        Scene scene = new Scene(main);

        setEventHandlers();

        stage.setScene(scene);
        stage.setTitle(GUI_TITLE);
        stage.show();
    }



    public GridPane getGridPane() {
        GridPane grid = new GridPane();

        // Styles
        grid.setStyle(SMOKE_BG);
        setDimensions(grid);

        // Add content
        addHeader(grid);
        addInputArea(grid);
        addOutputArea(grid);


        return grid;
    }


    // add(Node child, int columnIndex, int rowIndex)
    // add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)

    // Add header elements
    private void addHeader(GridPane grid) {
        // Create header components
        Text title = new Text(GUI_TITLE);
        title.setFont(Font.font(DEFAULT_FONT, FontWeight.BOLD, 20));
        title.setTextAlignment(TextAlignment.CENTER);

        // Add components to the grid
        grid.add(title, 0, 1,9, 1);
    }

    // Add input elements
    private void addInputArea(GridPane grid) {
        // Create input area components
        Text inputInstructions = new Text(INPUT_INSTRUCTIONS);
        inputField = new TextField();
        inputButton = new Button(START_BUTTON_TXT);

        inputButton.setId(START_ID);


        // Add components to the grid
        grid.add(inputInstructions, 1, 3);
        grid.add(inputField, 1, 4);
        grid.add(inputButton, 2, 4);

        // Row padding
        Text padding1 = new Text(BLANK_ROW);
        grid.add(padding1, 1, 5, 1, 2);
    }

    // Add output elements
    private void addOutputArea(GridPane grid) {
        // Create output components
        this.outputTextArea = new Label();
        openButton = new Button(OPEN_BUTTON_TXT);
        Label outputCompleteText = new Label(OUTPUT_COMPLETE_TXT);
        AnchorPane outputArea= new AnchorPane(outputTextArea, openButton, outputCompleteText);


        // Styles
        outputArea.setStyle(GRAY_BG + BLACK_BORDER + PADDING5PX);
        outputArea.setMinHeight(150);
        outputTextArea.setWrapText(true);
        outputCompleteText.setWrapText(true);
        openButton.setId(OPEN_ID);

        // Hide completion text and button
        openButton.setStyle(HIDDEN);
        outputCompleteText.setStyle(HIDDEN);

        // Position outputTextArea on AnchorPane
        AnchorPane.setTopAnchor(outputTextArea, 10.0);
        AnchorPane.setLeftAnchor(outputTextArea, 10.0);
        AnchorPane.setRightAnchor(outputTextArea, 10.0);
        AnchorPane.setBottomAnchor(outputTextArea, 80.0);

        // Position openBtn on AnchorPane
        AnchorPane.setBottomAnchor(openButton, 5.0);
        AnchorPane.setLeftAnchor(openButton, 10.0);

        // Position outputCompleteText on AnchorPane
        AnchorPane.setBottomAnchor(outputCompleteText, 20.0);
        AnchorPane.setLeftAnchor(outputCompleteText, 10.0);

        // Add outputArea to grid
        grid.add(outputArea, 6, 3, 4, 6);


    }

    private void setDimensions(GridPane grid) {
        // Establish minimum widths for columns. Widths are added in order
        // and there doesn't appear to be a way to apply a width to a
        // specific column.
        grid.getColumnConstraints().add(new ColumnConstraints()); // col 0
        grid.getColumnConstraints().add(new ColumnConstraints()); // col 1
        grid.getColumnConstraints().add(new ColumnConstraints(45)); // col 2
        grid.getColumnConstraints().add(new ColumnConstraints(40)); // col 3
        grid.getColumnConstraints().add(new ColumnConstraints()); // col 4
        grid.getColumnConstraints().add(new ColumnConstraints()); // col 5
        grid.getColumnConstraints().add(new ColumnConstraints(50)); // col 6
        grid.getColumnConstraints().add(new ColumnConstraints(50)); // col 7
        grid.getColumnConstraints().add(new ColumnConstraints(50)); // col 8
        grid.getColumnConstraints().add(new ColumnConstraints(50)); // col 9


        // Set grid spacing and padding
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(0, 10, 0, 10));
    }


    // getters

    public String getInputTextFieldText() {
        return inputField.getText();
    }

    // setters
    public void setOutputTextAreaText(String string) {
        this.outputTextArea.setText(string);
    }

    public void doSuccess(String filename) {
        // Show success message
        setOutputTextAreaText(SCRAPE_COMPLETE + filename);

        // Show open button
        openButton.setStyle(SHOWN);
        openButton.setText(OPEN_BUTTON_TXT + filename);
    }

    // Add event listeners to buttons
    private void setEventHandlers() {

        // create event handlers for buttons
        EventHandler<MouseEvent> buttonClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String buttonText = ((Button) e.getSource()).getId();

                switch(buttonText) {
                    case OPEN_ID:
                        fileHandler.openOutputFile();
                        break;
                    case START_ID:
                        fileHandler.getInputFile();
                        break;
                    default:
                }
            }
        };

        // add handlers to buttons
        inputButton.addEventFilter(MouseEvent.MOUSE_CLICKED, buttonClick);
        openButton.addEventFilter(MouseEvent.MOUSE_CLICKED, buttonClick);
    }
}


