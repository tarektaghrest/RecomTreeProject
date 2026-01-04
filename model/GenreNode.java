package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in the genre tree hierarchy.
 * Each node can have child genres and contains movies.
 * This hierarchical structure supports genre categorization
 * and movie organization in the recommendation system.
 */
public class GenreNode {

    private String name;
    private List<GenreNode> children;
    private List<Movie> movies;

    /**
     * Constructs a new GenreNode with the given name.
     * Initializes empty lists for children and movies.
     *
     * @param name the name of the genre
     */
    public GenreNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        this.movies = new ArrayList<>();
    }

    /**
     * Returns the name of this genre node.
     *
     * @return the genre name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of child genres under this node.
     *
     * @return a list of child GenreNode objects
     */
    public List<GenreNode> getChildren() {
        return children;
    }

    /**
     * Returns the list of movies in this genre node.
     *
     * @return a list of Movie objects
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Adds a child genre node to this node.
     *
     * @param child the GenreNode to add as a child
     */
    public void addChild(GenreNode child) {
        children.add(child);
    }

    /**
     * Adds a movie to this genre node.
     *
     * @param movie the Movie to add
     */
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
