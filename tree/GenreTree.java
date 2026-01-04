package tree;

import model.GenreNode;
import model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hierarchical genre tree for organizing movies.
 * Provides methods for managing genres, adding movies, and traversing
 * the tree structure. All public methods are synchronized for thread safety
 * in a multi-client server environment.
 */
public class GenreTree {

    private GenreNode root;

    /**
     * Constructs a GenreTree with a root node having the specified name.
     *
     * @param rootName the name of the root genre node
     */
    public GenreTree(String rootName) {
        this.root = new GenreNode(rootName);
    }

    /**
     * Returns the root node of the genre tree.
     *
     * @return the root GenreNode
     */
    public synchronized GenreNode getRoot() {
        return root;
    }

    // ============================================================================
    // GENRE MANAGEMENT
    // ============================================================================

    /**
     * Finds a genre node by name (case-insensitive).
     * Searches recursively through the entire tree.
     *
     * @param name the name of the genre to find
     * @return the GenreNode if found, null otherwise
     */
    public synchronized GenreNode findGenre(String name) {
        return findGenreRecursive(root, name);
    }

    /**
     * Recursively searches for a genre node.
     *
     * @param current the current node to search
     * @param name    the name of the genre to find
     * @return the GenreNode if found, null otherwise
     */
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

    /**
     * Adds a new sub-genre as a child of an existing genre.
     *
     * @param parentName the name of the parent genre
     * @param childName  the name of the new child genre
     * @return true if successful, false if parent genre not found
     */
    public synchronized boolean addSubGenre(String parentName, String childName) {
        GenreNode parent = findGenre(parentName);
        if (parent == null) {
            return false;
        }
        parent.addChild(new GenreNode(childName));
        return true;
    }

    // ============================================================================
    // MOVIE MANAGEMENT
    // ============================================================================

    /**
     * Adds a new movie to a specified genre.
     *
     * @param genreName  the name of the genre
     * @param movieTitle the title of the movie to add
     * @return true if successful, false if genre not found
     */
    public synchronized boolean addMovie(String genreName, String movieTitle) {
        GenreNode genre = findGenre(genreName);
        if (genre == null) {
            return false;
        }
        genre.addMovie(new Movie(movieTitle));
        return true;
    }

    /**
     * Finds a movie by title (case-insensitive).
     * Searches recursively through the entire tree.
     *
     * @param title the title of the movie to find
     * @return the Movie if found, null otherwise
     */
    public synchronized Movie findMovie(String title) {
        return findMovieRecursive(root, title);
    }

    /**
     * Recursively searches for a movie by title.
     *
     * @param node  the current node to search
     * @param title the title of the movie to find
     * @return the Movie if found, null otherwise
     */
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

    // ============================================================================
    // TREE TRAVERSAL / DISPLAY
    // ============================================================================

    /**
     * Returns a formatted string representation of a genre subtree.
     * Includes all child genres and movies with their ratings.
     *
     * @param genreName the name of the genre to list
     * @return a formatted string representation or error message
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

    /**
     * Recursively builds a formatted string representation of the tree.
     *
     * @param node   the current node to print
     * @param indent the current indentation level
     * @param sb     the StringBuilder to append to
     */
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

    // ============================================================================
    // RECOMMENDATION SUPPORT
    // ============================================================================

    /**
     * Collects all movies in a genre subtree.
     * Used by recommendation strategies to find candidate movies.
     *
     * @param genre the root genre node of the subtree
     * @return a list of all movies in the subtree
     */
    public synchronized List<Movie> getAllMoviesInSubTree(GenreNode genre) {
        List<Movie> movies = new ArrayList<>();
        collectMoviesRecursive(genre, movies);
        return movies;
    }

    /**
     * Recursively collects all movies from a node and its descendants.
     *
     * @param node   the current node
     * @param movies the list to collect movies into
     */
    private void collectMoviesRecursive(GenreNode node, List<Movie> movies) {
        movies.addAll(node.getMovies());

        for (GenreNode child : node.getChildren()) {
            collectMoviesRecursive(child, movies);
        }
    }
}