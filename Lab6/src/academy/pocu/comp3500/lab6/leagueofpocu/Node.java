package academy.pocu.comp3500.lab6.leagueofpocu;

public final class Node {
    private Player player;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    public Node(Player player) {
        this.player = player;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Player getPlayer() {
        return player;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public boolean equalTo(Player player) {
        return this.player.getId() == player.getId() ? true : false;
    }
}
