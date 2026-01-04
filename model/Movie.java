package model;

public class Movie {

    private String title;
    private double averageRating;
    private int ratingCount;

    public Movie(String title) {
        this.title = title;
        this.averageRating = 0.0;
        this.ratingCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public boolean addRating(int rating) {
        if (rating < 1 || rating > 5) {
            return false;
        }
        averageRating = (averageRating * ratingCount + rating) / (++ratingCount);
        return true;
    }

    @Override
    public String toString() {
        return title + " (rating: " +
                String.format("%.1f", averageRating) + ")";
    }
}