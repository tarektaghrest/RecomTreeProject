package recommendation;

import model.GenreNode;
import model.Movie;
import tree.GenreTree;

/**
 * Interface for recommendation strategies.
 * Implementations provide different algorithms for recommending movies
 * based on genre and user ratings.
 */
public interface RecommendationStrategy {

    /**
     * Recommends a movie from the specified genre.
     *
     * @param genre the GenreNode to get a recommendation from
     * @param tree  the GenreTree for accessing movie data
     * @return the recommended Movie, or null if no recommendation is available
     */
    Movie recommend(GenreNode genre, GenreTree tree);
}
