/*
 *
 *
 * Resources
 * RegEx: https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
 *
 */

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Scraper implements Constants {

    public static String scrapeComments(List<String> input) {

        boolean multilineComment = false;
        String outputString = "";
        int counter = 1;

        // Check each line for pattern matches
        for(String line: input) {

            if (multilineComment) {
                // Check if matches a multiline end
                if(hasMatch(TRADITIONAL_END, line)) {
                    multilineComment = false;

                    // Add comment from start of line to ending comment
                    outputString += getOutput(getComment(SOL_TO_TRADITIONAL_END, line), counter);
                } else {
                    // Add comment from /* to end of line
                    outputString += getOutput(getComment(TRADITIONAL_TO_EOL, line), counter);
                }
            } else  {
                // Check if matches any type of comment
                if (hasMatch(COMMENT_STARTS, line)) {
                    System.out.println("Comment found " + line);
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

    // Searches for a match based on a provided pattern and string to check
    private static boolean hasMatch(String patternString, String checkString) {

        return checkString.matches(patternString);

    }

    // Returns the string that matches the given pattern
    private static String getComment(String patternString,String checkString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(checkString);
        matcher.matches();

        return matcher.group(1);
    }

    // Formats output to print to a file
    private static String getOutput(String text, int lineNum) {
        return lineNum + ". " + text;
    }

}
