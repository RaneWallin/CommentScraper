/*
 * Rane Wallin
 * CSC205AB
 *
 * This is the main class that launches the program.
 *
 */

import javafx.application.Application;
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
    }

}