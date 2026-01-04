package command;

import model.GenreNode;
import model.Movie;
import recommendation.TopRatedStrategy;
import tree.GenreTree;

public class RecommendCommand implements Command {

    private GenreTree tree;
    private String genre;

    public RecommendCommand(GenreTree tree, String genre) {
        this.tree = tree;
        this.genre = genre;
    }

    @Override
    public String execute() {
        GenreNode node = tree.findGenre(genre);
        if (node == null) {
            return "ERROR: Genre not found.";
        }

        TopRatedStrategy strategy = new TopRatedStrategy();
        Movie movie = strategy.recommend(node, tree);

        if (movie == null) {
            return "No recommendation available.";
        }
        return "Recommended movie: " + movie;
    }
}
