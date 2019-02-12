/* Rane Wallin
 *
 * This class does all of the file handling, including opening files and writing to files.
 *
 * Resources:
 * Event handling: https://www.tutorialspoint.com/javafx/javafx_event_handling.htm
 * Catching multiple exceptions: https://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html
 */

import java.awt.*;
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
    private File outputFile;

    public FileHandler(GUI gui) {
        this.gui = gui;
    }


    protected void getInputFile() {
        String filename = gui.getFilename();
        File file = new File(filename);

        // get the comments from the Scraper and send them to createOutputFile
        if (file.exists()) {
            List fileInput = openFile(file);
            createOutputFile(Scraper.scrapeComments(fileInput), filename);
        } else {
            displayMessage(FILE_NOT_FOUND);
        }
    }


    protected void openOutputFile() {
        try {
            // Opens file for viewing using default application
            Desktop.getDesktop().open(outputFile);
        } catch (FileNotFoundException|NoSuchFileException e) {
            displayMessage(FILE_NOT_FOUND);
        } catch (Exception e) {
            displayMessage(ERROR + e.getMessage());
        }
    }

    // Opens a provided file and reads lines into an ArrayList
    public List<String> openFile(File file) {
        List<String> fileInput = new ArrayList<>();

        try {
            Path input = Paths.get(file.getPath());
            fileInput = Files.readAllLines(input);
        }
        catch (NoSuchFileException|FileNotFoundException e) {
            displayMessage(FILE_NOT_FOUND);
        }
        catch(IOException e) {
            displayMessage(ERROR + e.getMessage());
        }

        return fileInput;
    }

    // Create an output file for the comments
    private void createOutputFile(List<String> comments, String filename) {
        // split filename into name and extension
        String tmp[] = filename.split(FILE_DELIMITER);

        // create outputFile name (name + file extension)
        String outputFile = tmp[0] + OUTPUT_EXTENSION;

        boolean exceptionThrown = false;

        File file = new File(outputFile);

        try {
            Path output = Paths.get(file.getPath());
            Files.write(output, comments, Charset.forName(UNICODE));
        }
        catch(IOException e) {
            displayMessage(ERROR + e.toString());
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            this.outputFile = new File(outputFile);

            gui.doSuccess(outputFile);
        }
    }

    private void displayMessage(String message) {
        gui.setOutputTextAreaText(message);
    }
}
