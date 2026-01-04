package command;

/**
 * Interface for executing commands in the recommendation system.
 * Implementations of this interface handle different user commands
 * such as adding movies, listing genres, rating, and recommending.
 */
public interface Command {

    /**
     * Executes the command and returns a result message.
     *
     * @return the result of executing the command
     */
    String execute();
}
