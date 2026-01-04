package model;

/**
 * Represents a movie in the recommendation system.
 * Tracks the movie's title and maintains an average rating
 * based on user ratings.
 */
public class Movie {

    private String title;
    private double averageRating;
    private int ratingCount;

    /**
     * Constructs a new Movie with the given title.
     * Initial average rating is set to 0.0 with no ratings.
     *
     * @param title the title of the movie
     */
    public Movie(String title) {
        this.title = title;
        this.averageRating = 0.0;
        this.ratingCount = 0;
    }

    /**
     * Returns the title of this movie.
     *
     * @return the movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the current average rating of this movie.
     *
     * @return the average rating
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Adds a new rating to this movie and updates the average.
     * Ratings must be between 1 and 5 (inclusive).
     *
     * @param rating the rating to add (1-5)
     * @return true if the rating was successfully added, false if invalid
     */
    public boolean addRating(int rating) {
        if (rating < 1 || rating > 5) {
            return false;
        }
        averageRating = (averageRating * ratingCount + rating) / (++ratingCount);
        return true;
    }

    /**
     * Returns a string representation of the movie with its average rating.
     *
     * @return a formatted string containing the title and rating
     */
    @Override
    public String toString() {
        return title + " (rating: " +
                String.format("%.1f", averageRating) + ")";
    }
}