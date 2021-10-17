package academy.pocu.comp3500.lab6;

import java.util.HashMap;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public final class Node {
    private HashMap<Integer, Player> playerMap;
    private int rating;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    public Node(Player player) {
        this.playerMap = new HashMap<Integer, Player>();
        this.playerMap.put(player.getId(), player);
        this.rating = player.getRating();
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

    public Player getPlayerOrNull(int id) {
        return this.playerMap.get(id);
    }

    public int getRating() {
        return rating;
    }

    public Node getParentOrNull() {
        return parent;
    }

    public Node getLeftChildOrNull() {
        return leftChild;
    }

    public Node getRightChildOrNull() {
        return rightChild;
    }

    public HashMap<Integer, Player> getPlayers() {
        return this.playerMap;
    }
}
