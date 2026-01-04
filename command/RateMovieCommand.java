package command;

import model.Movie;
import tree.GenreTree;

/**
 * Command to add a rating to a movie.
 * Updates the movie's average rating based on the provided rating value.
 */
public class RateMovieCommand implements Command {

    private GenreTree tree;
    private String movieTitle;
    private int rating;

    /**
     * Constructs a RateMovieCommand with the specified genre tree,
     * movie title, and rating value.
     *
     * @param tree       the GenreTree to search for the movie
     * @param movieTitle the title of the movie to rate
     * @param rating     the rating value (1-5)
     */
    public RateMovieCommand(GenreTree tree, String movieTitle, int rating) {
        this.tree = tree;
        this.movieTitle = movieTitle;
        this.rating = rating;
    }

    /**
     * Executes the rate movie command.
     *
     * @return a success message or error message
     */
    @Override
    public String execute() {
        Movie movie = tree.findMovie(movieTitle);
        if (movie == null) {
            return "ERROR: Movie not found.";
        }
        boolean ok = movie.addRating(rating);
        if (!ok) {
            return "ERROR: Rating must be between 1 and 5.";
        }
        return "Rating added to '" + movieTitle + "'";
    }
}