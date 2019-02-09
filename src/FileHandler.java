/*
 * Resources:
 * https://www.tutorialspoint.com/javafx/javafx_event_handling.htm
 */

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.io.File;

public class FileHandler implements Constants {

    private static GUI gui;

    public FileHandler(GUI gui) {
        this.gui = gui;

        setListeners();
    }

    // Add event listeners to buttons
    private void setListeners() {

        // create event handlers
        EventHandler<MouseEvent> buttonClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String buttonText = ((Button) e.getSource()).getText();

                switch(buttonText) {
                    case OPEN_BUTTON_TXT:
                        openOutputFile();
                        break;
                    case CHOOSER_BUTTON_TXT:
                        chooseFile();
                        break;
                    case START_BUTTON_TXT:
                        getInputFile();
                        break;
                    default:
                }
            }
        };

        // add handlers to elements
        gui.getInputButton().addEventFilter(MouseEvent.MOUSE_CLICKED, buttonClick);
        gui.getOpenButton().addEventFilter(MouseEvent.MOUSE_CLICKED, buttonClick);
        gui.getChooserButton().addEventFilter(MouseEvent.MOUSE_CLICKED, buttonClick);

    }

    private void getInputFile() {
        System.out.println("input file (not provided) click");
    }

    private void getInputFile(File file) {
        System.out.println("input (from chooser) click");
    }

    private void chooseFile() {
        System.out.println("Choose file");
    }

    private void getOutputFile() {
        System.out.println("get output file");
    }

    private void openOutputFile() {
        System.out.println("open output file");
    }
}
