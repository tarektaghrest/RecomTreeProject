package command;

import model.GenreNode;
import model.Movie;
import recommendation.TopRatedStrategy;
import tree.GenreTree;

/**
 * Command to get a movie recommendation based on a genre.
 * Uses the TopRatedStrategy to recommend the highest-rated movie
 * from the specified genre and its subtree.
 */
public class RecommendCommand implements Command {

    private GenreTree tree;
    private String genre;

    /**
     * Constructs a RecommendCommand with the specified genre tree and genre name.
     *
     * @param tree  the GenreTree to query
     * @param genre the genre to get a recommendation from
     */
    public RecommendCommand(GenreTree tree, String genre) {
        this.tree = tree;
        this.genre = genre;
    }

    /**
     * Executes the recommendation command.
     * Uses the TopRatedStrategy to find the best movie.
     *
     * @return a recommended movie or error/no recommendation message
     */
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
