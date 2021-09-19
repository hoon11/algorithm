package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class LinkedList {
    private LinkedList() {
    }

    public static Node append(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            return new Node(data);
        }

        Node lastNode = rootOrNull;
        while (lastNode.getNextOrNull() != null) {
            lastNode = lastNode.getNextOrNull();
        }
        lastNode.setNext(new Node(data));

        return rootOrNull;
    }

    public static Node prepend(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            return new Node(data);
        }

        Node newRoot = new Node(data);
        newRoot.setNext(rootOrNull);

        return newRoot;
    }

    public static Node insertAt(final Node rootOrNull, final int index, final int data) {
        Node newNode = new Node(data);

        if (rootOrNull == null) {
            return newNode;
        }
        
        if (index == 0) {
            newNode.setNext(rootOrNull);
            return newNode;
        }

        if (index < 0) {
            return rootOrNull;
        }

        Node previous = rootOrNull;
        int i = 0;
        while (i < index - 1 && previous != null) {
            previous = previous.getNextOrNull();
            i++;
        }
        if (previous != null) {
            newNode.setNext(previous.getNextOrNull());
            previous.setNext(newNode);
        }

        return rootOrNull;
    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        if (rootOrNull == null) {
            return null;
        }

        if (index == 0) {
            return rootOrNull.getNextOrNull();
        }

        Node previous = rootOrNull;
        int i = 0;
        while (i < index - 1 && previous != null) {
            previous = previous.getNextOrNull();
            i++;
        }

        if (previous != null && previous.getNextOrNull() != null) {
            previous.setNext(previous.getNextOrNull().getNextOrNull());
        }

        return rootOrNull;
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        Node indexingNode = rootOrNull;
        int i = 0;
        while (indexingNode != null) {
            if (indexingNode.getData() == data) {
                return i;
            }
            indexingNode = indexingNode.getNextOrNull();
            i++;
        }

        return -1;
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
        if (index < 0) {
            return null;
        }

        Node indexingNode = rootOrNull;
        int i = 0;
        while (i < index && indexingNode != null) {
            indexingNode = indexingNode.getNextOrNull();
            i++;
        }

        return indexingNode;
    }

    public static Node reverse(final Node rootOrNull) {
        Node previous = null;
        Node current = rootOrNull;
        while (current != null) {
            Node temp = current.getNextOrNull();
            current.setNext(previous);
            previous = current;
            current = temp;
        }

        return previous;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        Node indexing0 = root0OrNull;
        Node indexing1 = root1OrNull;
        Node newRoot = null;
        if (indexing0 != null) {
            newRoot = indexing0;
            indexing0 = indexing0.getNextOrNull();
        } else if (indexing1 != null) {
            newRoot = indexing1;
            indexing0 = indexing1.getNextOrNull();
        }

        Node indexingNode = newRoot;
        while (indexingNode != null) {
            if (indexing1 != null) {
                indexingNode.setNext(indexing1);
                indexingNode = indexingNode.getNextOrNull();
                indexing1 = indexing1.getNextOrNull();
            }
            if (indexing0 != null) {
                indexingNode.setNext(indexing0);
                indexingNode = indexingNode.getNextOrNull();
                indexing0 = indexing0.getNextOrNull();
            }
            if (indexing0 == null && indexing1 == null) {
                indexingNode = indexingNode.getNextOrNull();
            }
        }

        return newRoot;
    }
}