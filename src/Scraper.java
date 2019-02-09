/*
 *
 *
 * Resources
 * RegEx: https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper implements Constants {
    private File file;
    private GUI gui;

    public Scraper(File file, GUI gui){
        this.file = file;
        this.gui = gui;
    }

    public String scrapeComments() {
        List<String> input = openFile();
        Boolean multilineComment = false;
        String outputString = "";
        int counter = 1;

        for(String line: input) {
            if (multilineComment) {
                // Check if matches a multiline end
                if(hasMatch(TRADITIONAL_END, line)) {
                    multilineComment = false;
                    // Add comment from start of line to ending comment
                    outputString += getOutput(getComment(SOL_TO_TRADITIONAL_END, line), counter);
                } else {
                    outputString += getOutput(getComment(TRADITIONAL_TO_EOL, line), counter);
                }
            } else  {
                // Check if matches any type of comment
                if (hasMatch(COMMENT_STARTS, line)) {
                    // check if match is /*
                    if (hasMatch(TRADITIONAL_START, line)) {
                        // check if using multiline syntax on single line
                        if (hasMatch(TRADITIONAL_END, line)) {

                            // Add comment from /* to */
                            outputString = getOutput(getComment(TRADITIONAL_RANGE, line), counter);
                        }
                        else multilineComment = true;

                        // Add comment from /* to end of line
                        outputString = getOutput(getComment(TRADITIONAL_TO_EOL, line), counter);

                    } else {
                        // Add end of line comment from start of comment
                        outputString = getOutput(getComment(EOL_RANGE, line), counter);
                    }
                }
            }

            counter++;
        }

        return outputString;
    }

    private boolean hasMatch(String patternString, String checkString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(checkString);

        return matcher.matches();

    }

    private String getComment(String patternString,String checkString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(checkString);

        return matcher.toString();
    }

    private String getOutput(String text, int lineNum) {
        return lineNum + ". " + text;
    }

    public List<String> openFile() {
        List<String> fileInput = new ArrayList<>();

        try {
            Path input = Paths.get(file.getPath());
            fileInput = Files.readAllLines(input);
        } catch(IOException e) {
            System.out.println(ERROR + e.getMessage());
        }

        return fileInput;
    }
}
