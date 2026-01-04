package command;

import tree.GenreTree;

/**
 * Command to add a new movie to a specified genre.
 * Handles creation and registration of movies in the genre tree.
 */
public class AddMovieCommand implements Command {

    private GenreTree tree;
    private String genre;
    private String movie;

    /**
     * Constructs an AddMovieCommand with the specified genre tree,
     * genre name, and movie title.
     *
     * @param tree  the GenreTree to add the movie to
     * @param genre the target genre name
     * @param movie the movie title to add
     */
    public AddMovieCommand(GenreTree tree, String genre, String movie) {
        this.tree = tree;
        this.genre = genre;
        this.movie = movie;
    }

    /**
     * Executes the add movie command.
     *
     * @return a success message or error message
     */
    @Override
    public String execute() {
        boolean success = tree.addMovie(genre, movie);
        if (success) {
            return "Movie '" + movie + "' added to genre '" + genre + "'";
        }
        return "ERROR: Genre not found.";
    }
}
