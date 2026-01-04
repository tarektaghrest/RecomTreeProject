package command;

import tree.GenreTree;

/**
 * Command to list the contents of a genre and its subtree.
 * Displays all child genres and movies within the specified genre.
 */
public class ListSubtreeCommand implements Command {

    private GenreTree tree;
    private String genre;

    /**
     * Constructs a ListSubtreeCommand with the specified genre tree and genre name.
     *
     * @param tree  the GenreTree to query
     * @param genre the genre name to list
     */
    public ListSubtreeCommand(GenreTree tree, String genre) {
        this.tree = tree;
        this.genre = genre;
    }

    /**
     * Executes the list subtree command.
     *
     * @return a formatted string of the genre tree or error message
     */
    @Override
    public String execute() {
        return tree.listSubTree(genre);
    }
}
