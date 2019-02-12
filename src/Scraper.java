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

    private static boolean multilineComment = false;

    public static String scrapeComments(List<String> input) {


        String outputString = "";
        int counter = 1;

        // Check each line for pattern matches
        for(String line: input) {
            String comment = findComment(line);

            if(comment != "")
                outputString += String.format("%d. %s\n", counter, comment);


            counter++;
        }

        return outputString;
    }

    private static String findComment(String text) {
        String comment = "";

        if (multilineComment) {

            // Check if matches a multiline end
            if(hasMatch(TRADITIONAL_END, text)) {

                multilineComment = false;

                // Add comment from start of line to ending comment
                comment = getComment(SOL_TO_TRADITIONAL_END, text);

            } else {

                // Add line
                return text;

            }
        } else  {

            // Check if matches any type of comment
            if (hasMatch(COMMENT_STARTS, text)) {

                // check if match is /* and doesn't have an ending */ and go into multiline mode
                // if so
                if (hasMatch(TRADITIONAL_START, text)&&
                        !hasMatch(TRADITIONAL_END, text)) multilineComment = true;

                comment = getComment(CATCH_ALL, text);
            }
        }

        return comment;
    }

    // Searches for a match based on a provided pattern and string to check
    private static boolean hasMatch(String patternString, String checkString) {
        //don't match pattern if it is inside quotes
        String falseMatch = ".*\".*"+patternString+".*\".*";

        return checkString.matches(patternString) && !checkString.matches(falseMatch);

    }

    // Returns the string that matches the given pattern
    private static String getComment(String patternString,String checkString) {
        String matches = "";

        // create the pattern for matching
        Pattern pattern = Pattern.compile(patternString);

        // get matches (should be one match)
        Matcher matcher = pattern.matcher(checkString);

        // Iterate through matches and add each group to the
        // output string
        while(matcher.find()) {
            String match = matcher.group();

            matches += match;
        }

        return matches;
    }

    // Formats output to print to a file
    private static String getOutput(String text, int lineNum) {
        return String.format("%d. %s\n", lineNum, text);
    }

}
