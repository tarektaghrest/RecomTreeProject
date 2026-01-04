package command;

import tree.GenreTree;

public class AddMovieCommand implements Command {

    private GenreTree tree;
    private String genre;
    private String movie;

    public AddMovieCommand(GenreTree tree, String genre, String movie) {
        this.tree = tree;
        this.genre = genre;
        this.movie = movie;
    }

    @Override
    public String execute() {
        boolean success = tree.addMovie(genre, movie);
        if (success) {
            return "Movie '" + movie + "' added to genre '" + genre + "'";
        }
        return "ERROR: Genre not found.";
    }
}
