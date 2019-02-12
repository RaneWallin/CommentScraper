/* Rane Wallin
 * CSC205AB
 *
 * This class is an abstract class that holds all of the comment scraper logic.
 *
 * Resources
 * RegEx: https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Scraper implements Constants {

    private static boolean multilineComment = false;

    public static List<String> scrapeComments(List<String> input) {

        List<String> outputString = new ArrayList<>();
        int counter = 1;

        // Check each line for pattern matches
        for(String line: input) {
            String comment = findComment(line);

            if(comment != EMPTY_STRING)
                outputString.add(String.format(OUTPUT_FORMAT, counter, comment));

            counter++;
        }

        return outputString;
    }

    private static String findComment(String text) {
        String comment = EMPTY_STRING;

        if (multilineComment) {

            // Check if matches a multiline end
            if(hasMatch(TRADITIONAL_END, text)) {

                // Add comment from start of line to ending comment
                comment = getComment(SOL_TO_TRADITIONAL_END, text);

            } else {

                // Add line
                return text;

            }
        } else  {

            // Check if matches any type of comment
            if (hasMatch(COMMENT_STARTS, text)) {

                comment = getComment(CATCH_ALL, text);
            }
        }

        return comment;
    }

    // Searches for a match based on a provided pattern and string to check
    private static boolean hasMatch(String patternString, String checkString) {
        //don't match pattern if it is inside quotes
        String falseMatch = QUOTE_CATCHER+patternString+QUOTE_CATCHER;

        return checkString.matches(patternString) && !checkString.matches(falseMatch);

    }

    // Returns the string that matches the given pattern
    private static String getComment(String patternString,String checkString) {
        String matches = EMPTY_STRING;
        boolean isEOL = false;

        // create the pattern for matching
        Pattern pattern = Pattern.compile(patternString);

        // get matches (should be one match)
        Matcher matcher = pattern.matcher(checkString);

        // Iterate through matches and add each group to the
        // output string
        while(matcher.find()) {
            String match = matcher.group();

            // Handles edge case where there is a /* text */ comment followed
            // by an /* to open a multiline comment
            if (match.matches(EOL_RANGE)) isEOL = true;

            if (!isEOL) {
                if (match.matches(TRADITIONAL_START)) multilineComment = true;
                if (match.matches(TRADITIONAL_END)) multilineComment = false;
            }

            matches += match;
        }

        return matches;
    }

}
