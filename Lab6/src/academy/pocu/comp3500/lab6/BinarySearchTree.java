package academy.pocu.comp3500.lab6;

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
        if (newNode.getRating() < parent.getRating()) {
            if (parent.getLeftChildOrNull() != null) {
                return insertReclusive(parent.getLeftChildOrNull(), newNode);
            } else {
                parent.setLeftChild(newNode);
                newNode.setParent(parent);
                return newNode;
            }
        }
        if (parent.getRightChildOrNull() != null) {
            return insertReclusive(parent.getRightChildOrNull(), newNode);
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

        if (node.getRating() == rating) {
            return node;
        }

        if (rating < node.getRating()) {
            return searchByRatingReclusive(node.getLeftChildOrNull(), rating);
        }

        return searchByRatingReclusive(node.getRightChildOrNull(), rating);
    }

    public Node searchById(int id) {
        ArrayList<Node> list = new ArrayList<Node>();
        searchByIdReclusive(list, this.root, id);

        return list.size() == 1 ? list.get(0) : null;
    }

    private void searchByIdReclusive(ArrayList<Node> list, Node node, int id) {
        if (node == null) {
            return;
        }

        if (node.getPlayerOrNull(id) != null) {
            list.add(node);
        }

        searchByIdReclusive(list, node.getLeftChildOrNull(), id);
        searchByIdReclusive(list, node.getRightChildOrNull(), id);
    }

    public Node delete(Node node) {

        Node candidate = findNextParentFromLeftReclusive(node.getLeftChildOrNull());
        if (candidate != null) {
            Node parent = node.getParentOrNull();
            candidate.setParent(parent);
            if (parent.getLeftChildOrNull() != null && parent.getLeftChildOrNull().getRating() == node.getRating()) {
                parent.setLeftChild(candidate);
            } else if (parent.getRightChildOrNull() != null && parent.getRightChildOrNull().getRating() == node.getRating()) {
                parent.setRightChild(candidate);
            }
        }

        if (candidate == null) {
            candidate = findNextParentFromRightReclusive(node.getRightChildOrNull());
            if (candidate != null) {
                Node parent = node.getParentOrNull();
                candidate.setParent(parent);
                if (parent.getLeftChildOrNull() != null && parent.getLeftChildOrNull().getRating() == node.getRating()) {
                    parent.setLeftChild(candidate);
                } else if (parent.getRightChildOrNull() != null && parent.getRightChildOrNull().getRating() == node.getRating()) {
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

        if (node.getLeftChildOrNull() == null && node.getRightChildOrNull() == null) {
            return node;
        }

        if (node.getRightChildOrNull() != null) {
            return findNextParentFromLeftReclusive(node.getRightChildOrNull());
        }

        return findNextParentFromLeftReclusive(node.getLeftChildOrNull());
    }

    private Node findNextParentFromRightReclusive(Node node) {
        if (node == null) {
            return null;
        }

        if (node.getLeftChildOrNull() == null && node.getRightChildOrNull() == null) {
            return node;
        }

        if (node.getLeftChildOrNull() != null) {
            return findNextParentFromRightReclusive(node.getLeftChildOrNull());
        }

        return findNextParentFromRightReclusive(node.getRightChildOrNull());
    }

    public ArrayList<Node> toArrayByRataingAsc() {
        ArrayList<Node> list = new ArrayList<Node>();
        toArrayByRataingAscReclusive(list, this.root);

        return list;
    }

    private void toArrayByRataingAscReclusive(ArrayList<Node> list, Node node) {
        if (node == null) {
            return;
        }

        toArrayByRataingAscReclusive(list, node.getLeftChildOrNull());
        list.add(node);
        toArrayByRataingAscReclusive(list, node.getRightChildOrNull());
    }

    public ArrayList<Node> toArrayByRataingDesc() {
        ArrayList<Node> list = new ArrayList<Node>();
        toArrayByRataingDescReclusive(list, this.root);

        return list;
    }

    private void toArrayByRataingDescReclusive(ArrayList<Node> list, Node node) {
        if (node == null) {
            return;
        }

        toArrayByRataingDescReclusive(list, node.getRightChildOrNull());
        list.add(node);
        toArrayByRataingDescReclusive(list, node.getLeftChildOrNull());
    }
}
