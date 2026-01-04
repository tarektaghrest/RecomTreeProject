package model;

import java.util.ArrayList;
import java.util.List;

public class GenreNode {

    private String name;
    private List<GenreNode> children = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    public GenreNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<GenreNode> getChildren() {
        return children;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addChild(GenreNode child) {
        children.add(child);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
