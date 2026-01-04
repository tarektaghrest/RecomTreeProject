package recommendation;

import model.GenreNode;
import model.Movie;
import tree.GenreTree;

import java.util.List;

/**
 * Recommendation strategy that returns the highest-rated movie.
 * Searches through all movies in the specified genre and its subtree,
 * returning the movie with the highest average rating.
 */
public class TopRatedStrategy implements RecommendationStrategy {

    /**
     * Recommends the highest-rated movie from the specified genre.
     * Searches recursively through all child genres as well.
     *
     * @param genre the GenreNode to get a recommendation from
     * @param tree  the GenreTree for accessing all movies in the subtree
     * @return the movie with the highest average rating, or null if no movies exist
     */
    @Override
    public Movie recommend(GenreNode genre, GenreTree tree) {

        List<Movie> movies = tree.getAllMoviesInSubTree(genre);

        Movie best = null;

        for (Movie movie : movies) {
            if (best == null ||
                    movie.getAverageRating() > best.getAverageRating()) {
                best = movie;
            }
        }

        return best;
    }
}
