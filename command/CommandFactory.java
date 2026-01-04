package command;

import tree.GenreTree;
import java.util.Arrays;

/**
 * Factory class for creating Command objects based on user input.
 * Parses command strings and instantiates the appropriate Command
 * implementation.
 * Provides validation and error handling for all supported commands.
 */
public class CommandFactory {

    /**
     * Creates a Command object from the given input string.
     * Supported commands: ADD_MOVIE, LIST_SUBTREE, RATE, RECOMMEND
     *
     * @param input the user input string
     * @param tree  the GenreTree to operate on
     * @return a Command object ready to execute
     */
    public static Command create(String input, GenreTree tree) {
        if (input == null || input.trim().isEmpty()) {
            return () -> "ERROR: Empty command.";
        }

        String[] parts = input.trim().split(" ");
        String cmd = parts[0].toUpperCase();

        switch (cmd) {
            case "ADD_MOVIE":
                if (parts.length < 3) {
                    return () -> "Usage: ADD_MOVIE <genre> <movie>";
                }
                String movieTitle = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
                return new AddMovieCommand(tree, parts[1], movieTitle);

            case "LIST_SUBTREE":
                if (parts.length < 2) {
                    return () -> "Usage: LIST_SUBTREE <genre>";
                }
                return new ListSubtreeCommand(tree, parts[1]);

            case "RATE":
                if (parts.length < 3) {
                    return () -> "Usage: RATE <movie> <rating>";
                }
                try {
                    int r = Integer.parseInt(parts[parts.length - 1]);
                    if (r < 1 || r > 5) {
                        return () -> "ERROR: Rating must be an integer between 1 and 5.";
                    }
                    String movie = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1));
                    return new RateMovieCommand(tree, movie, r);
                } catch (NumberFormatException e) {
                    return () -> "ERROR: Rating must be an integer between 1 and 5.";
                }

            case "RECOMMEND":
                if (parts.length < 2) {
                    return () -> "Usage: RECOMMEND <genre>";
                }
                return new RecommendCommand(tree, parts[1]);

            default:
                return () -> "ERROR: Unknown command.";
        }
    }
}