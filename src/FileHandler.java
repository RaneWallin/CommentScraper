/*
 * Resources:
 * Event handling: https://www.tutorialspoint.com/javafx/javafx_event_handling.htm
 * Catching multiple exceptions: https://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html
 */

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    }

    private void getInputFile() {
        String filename = gui.getInputTextFieldText();
        List fileInput = openFile(new File(filename));
        List<String> comments = Scraper.scrapeComments(fileInput);

        //System.out.println(comments);
        createOutputFile(comments, filename);

    }

    private void getInputFile(File file) {
        //String filename =
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

    // Opens a provided file and reads lines into an ArrayList
    public List<String> openFile(File file) {
        List<String> fileInput = new ArrayList<>();

        try {
            Path input = Paths.get(file.getPath());

            fileInput = Files.readAllLines(input);
        }
        catch (NoSuchFileException|FileNotFoundException e) {
            gui.setOutputTextAreaText(FILE_NOT_FOUND);
        }
        catch(IOException e) {
            gui.setOutputTextAreaText(ERROR + e.toString());
            e.printStackTrace();
        }

        return fileInput;
    }

    // Create an output file for the comments
    private void createOutputFile(List<String> comments, String filename) {
        //System.out.println(filename);
        String tmp[] = filename.split("\\.");
        String outputFile = tmp[0] + OUTPUT_EXTENSION;
        boolean exceptionThrown = false;

        File file = new File(outputFile);

        try {
            Path output = Paths.get(file.getPath());
            Files.write(output, comments, Charset.forName("UTF-8"));
        }
        catch(IOException e) {
            gui.setOutputTextAreaText(ERROR + e.toString());
            e.printStackTrace();
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            gui.setOutputTextAreaText(SCRAPE_COMPLETE + outputFile);
        }
    }
}
