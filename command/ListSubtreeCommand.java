package command;

import tree.GenreTree;

public class ListSubtreeCommand implements Command {

    private GenreTree tree;
    private String genre;

    public ListSubtreeCommand(GenreTree tree, String genre) {
        this.tree = tree;
        this.genre = genre;
    }

    @Override
    public String execute() {
        return tree.listSubTree(genre);
    }
}
