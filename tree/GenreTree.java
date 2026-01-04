package tree;

import model.GenreNode;
import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class GenreTree {

    private GenreNode root;

    public GenreTree(String rootName) {
        this.root = new GenreNode(rootName);
    }

    public synchronized GenreNode getRoot() {
        return root;
    }

    /*
     * =========================
     * GENRE MANAGEMENT
     * =========================
     */

    public synchronized GenreNode findGenre(String name) {
        return findGenreRecursive(root, name);
    }

    private GenreNode findGenreRecursive(GenreNode current, String name) {
        if (current == null) {
            return null;
        }

        if (current.getName().equalsIgnoreCase(name)) {
            return current;
        }

        for (GenreNode child : current.getChildren()) {
            GenreNode found = findGenreRecursive(child, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public synchronized boolean addSubGenre(String parentName, String childName) {
        GenreNode parent = findGenre(parentName);
        if (parent == null) {
            return false;
        }
        parent.addChild(new GenreNode(childName));
        return true;
    }

    /*
     * =========================
     * MOVIE MANAGEMENT
     * =========================
     */

    public synchronized boolean addMovie(String genreName, String movieTitle) {
        GenreNode genre = findGenre(genreName);
        if (genre == null) {
            return false;
        }
        genre.addMovie(new Movie(movieTitle));
        return true;
    }

    public synchronized Movie findMovie(String title) {
        return findMovieRecursive(root, title);
    }

    private Movie findMovieRecursive(GenreNode node, String title) {
        for (Movie movie : node.getMovies()) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }

        for (GenreNode child : node.getChildren()) {
            Movie found = findMovieRecursive(child, title);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /*
     * =========================
     * TREE TRAVERSAL / DISPLAY
     * =========================
     */

    public synchronized String listSubTree(String genreName) {
        GenreNode genre = findGenre(genreName);
        if (genre == null) {
            return "ERROR: Genre not found.";
        }

        StringBuilder sb = new StringBuilder();
        printSubTreeRecursive(genre, "", sb);
        return sb.toString();
    }

    private void printSubTreeRecursive(GenreNode node, String indent, StringBuilder sb) {
        sb.append(indent).append("- ").append(node.getName()).append("\n");

        for (Movie movie : node.getMovies()) {
            sb.append(indent)
                    .append("  * ")
                    .append(movie.toString())
                    .append("\n");
        }

        for (GenreNode child : node.getChildren()) {
            printSubTreeRecursive(child, indent + "  ", sb);
        }
    }

    /*
     * =========================
     * RECOMMENDATION SUPPORT
     * =========================
     */

    public synchronized List<Movie> getAllMoviesInSubTree(GenreNode genre) {
        List<Movie> movies = new ArrayList<>();
        collectMoviesRecursive(genre, movies);
        return movies;
    }

    private void collectMoviesRecursive(GenreNode node, List<Movie> movies) {
        movies.addAll(node.getMovies());

        for (GenreNode child : node.getChildren()) {
            collectMoviesRecursive(child, movies);
        }
    }
}