package recommendation;

import model.GenreNode;
import model.Movie;
import tree.GenreTree;

public interface RecommendationStrategy {
    Movie recommend(GenreNode genre, GenreTree tree);
}
