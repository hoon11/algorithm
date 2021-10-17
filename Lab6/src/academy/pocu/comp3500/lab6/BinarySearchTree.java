package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.Node;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;

public final class BinarySearchTree {
    private Node root;

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public Node insert(Node newNode) {
        return insertReclusive(this.root, newNode);
    }

    private Node insertReclusive(Node parent, Node newNode) {
        if (parent.getPlayer().getRating() > newNode.getPlayer().getRating()) {
            if (parent.getLeftChild() != null) {
                return insertReclusive(parent.getLeftChild(), newNode);
            } else {
                parent.setLeftChild(newNode);
                newNode.setParent(parent);
                return newNode;
            }
        }
        if (parent.getRightChild() != null) {
            return insertReclusive(parent.getRightChild(), newNode);
        } else {
            parent.setRightChild(newNode);
            newNode.setParent(parent);
            return newNode;
        }
    }

    public Node searchByRating(int rating) {
        return searchByRatingReclusive(this.root, rating);
    }

    private Node searchByRatingReclusive(Node node, int rating) {
        if (node == null) {
            return null;
        }

        if (node.getPlayer().getRating() == rating) {
            return node;
        }

        if (rating < node.getPlayer().getRating()) {
            return searchByRatingReclusive(node.getLeftChild(), rating);
        }

        return searchByRatingReclusive(node.getRightChild(), rating);
    }

    public Node searchById(int id) {
        ArrayList<Node> list = new ArrayList<Node>();
        searchByIdReclusive(list,this.root, id);

        return list.size() == 1 ? list.get(0) : null;
    }

    private void searchByIdReclusive(ArrayList<Node> list, Node node, int id) {
        if (node == null) {
            return;
        }
        int targetId = node.getPlayer().getId();
        if (targetId == id) {
            list.add(node);
            return;
        }

        searchByIdReclusive(list, node.getLeftChild(), id);
        searchByIdReclusive(list, node.getRightChild(), id);
    }

    public Node delete(Node node) {

        Node candidate = findNextParentFromLeftReclusive(node.getLeftChild());
        if (candidate != null) {
            Node parent = node.getParent();
            candidate.setParent(parent);
            if (parent.getLeftChild() != null && parent.getLeftChild().getPlayer().getId() == node.getPlayer().getId()) {
                parent.setLeftChild(candidate);
            } else if (parent.getRightChild() != null && parent.getRightChild().getPlayer().getId() == node.getPlayer().getId()) {
                parent.setRightChild(candidate);
            }
        }

        if (candidate == null) {
            candidate = findNextParentFromRightReclusive(node.getRightChild());
            if (candidate != null) {
                Node parent = node.getParent();
                candidate.setParent(parent);
                if (parent.getLeftChild() != null && parent.getLeftChild().getPlayer().getId() == node.getPlayer().getId()) {
                    parent.setLeftChild(candidate);
                } else if (parent.getRightChild() != null && parent.getRightChild().getPlayer().getId() == node.getPlayer().getId()) {
                    parent.setRightChild(candidate);
                }
            }
        }

        if (candidate == null) {
            this.root = null;
        }

        return this.root;
    }

    private Node findNextParentFromLeftReclusive(Node node) {
        if (node == null) {
            return null;
        }

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return node;
        }

        if (node.getRightChild() != null) {
            return findNextParentFromLeftReclusive(node.getRightChild());
        }

        return findNextParentFromLeftReclusive(node.getLeftChild());
    }

    private Node findNextParentFromRightReclusive(Node node) {
        if (node == null) {
            return null;
        }

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return node;
        }

        if (node.getLeftChild() != null) {
            return findNextParentFromRightReclusive(node.getLeftChild());
        }

        return findNextParentFromRightReclusive(node.getRightChild());
    }

    public ArrayList<Player> toArrayByRataingAsc() {
        ArrayList<Player> list = new ArrayList<Player>();
        toArrayByRataingAscReclusive(list, this.root);

        return list;
    }

    private void toArrayByRataingAscReclusive(ArrayList<Player> list, Node node) {
        if (node == null) {
            return;
        }

        toArrayByRataingAscReclusive(list, node.getLeftChild());
        list.add(node.getPlayer());
        toArrayByRataingAscReclusive(list, node.getRightChild());
    }

    public ArrayList<Player> toArrayByRataingDesc() {
        ArrayList<Player> list = new ArrayList<Player>();
        toArrayByRataingDescReclusive(list, this.root);

        return list;
    }

    private void toArrayByRataingDescReclusive(ArrayList<Player> list, Node node) {
        if (node == null) {
            return;
        }

        toArrayByRataingDescReclusive(list, node.getRightChild());
        list.add(node.getPlayer());
        toArrayByRataingDescReclusive(list, node.getLeftChild());
    }
}
