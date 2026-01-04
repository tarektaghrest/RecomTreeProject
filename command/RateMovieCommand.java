package command;

import model.Movie;
import tree.GenreTree;

public class RateMovieCommand implements Command {

    private GenreTree tree;
    private String movieTitle;
    private int rating;

    public RateMovieCommand(GenreTree tree, String movieTitle, int rating) {
        this.tree = tree;
        this.movieTitle = movieTitle;
        this.rating = rating;
    }

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