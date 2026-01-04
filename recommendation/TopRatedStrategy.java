package recommendation;

import model.GenreNode;
import model.Movie;
import tree.GenreTree;

import java.util.List;

public class TopRatedStrategy implements RecommendationStrategy {

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
