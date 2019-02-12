/* Rane Wallin
 * CSC205AB
 *
 * This interface holds all of the constant data
 *
 */

public interface Constants {

    // Strings
    String GUI_TITLE = "CScrape: Comment Scraper";
    String INPUT_INSTRUCTIONS = "Enter filename";
    String OUTPUT_COMPLETE_TXT = "Your new file has been created.  \nClick \"Open\" to view it.";
    String ERROR = "Something went wrong: ";
    String FILE_NOT_FOUND = "That file doesn't exist. \nPlease enter a new file name.";
    String SCRAPE_COMPLETE = "Your comments have been written to ";
    String OUTPUT_EXTENSION = ".txt";

    // Button text
    String START_BUTTON_TXT = "Start";
    String OPEN_BUTTON_TXT = "Open ";

    // styles and formatting
    String GRAY_BG = "-fx-background-color: lightgray; ";
    String SMOKE_BG = "-fx-background-color: whitesmoke; ";
    String BLACK_BORDER = "-fx-border-color: black; ";
    String PADDING5PX = "-fx-padding: 5";
    String HIDDEN = "-fx-opacity: 0";
    String SHOWN = "-fx-opacity: 1";
    String OPEN_ID = "open-button";
    String START_ID = "start-button";
    String DEFAULT_FONT = "Arial";
    String UNICODE = "UTF-8";
    String OUTPUT_FORMAT = "%d. %s";
    String EMPTY_STRING = "";
    String BLANK_ROW = "";

    // RegEx patterns
    String COMMENT_STARTS = ".*(//|/\\*).*";
    String TRADITIONAL_START = ".*(/\\*).*";
    String TRADITIONAL_END = ".*(\\*/).*";
    String CATCH_ALL = "(//|/\\*)(.*?)(\\*/|\\Z)";
    String SOL_TO_TRADITIONAL_END = "^(.*?\\*/)";
    String FILE_DELIMITER = "\\.";
    String QUOTE_CATCHER =".*\".*";
    String EOL_RANGE = ".*(//.*)";

    // patterns replaced by CATCH_ALL keeping here for reference
    //String EOL_RANGE = ".*(//.*)";
    //String TRADITIONAL_RANGE = "(/\\*.*?\\*/)";
    //String TRADITIONAL_TO_EOL = ".*(/\\*.*)";


    String LOREM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
}
