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
            lastNode = rootOrNull.getNextOrNull();
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
        if (rootOrNull == null) {
            return new Node(data);
        }

        Node previous = null;
        Node indexingNode = rootOrNull;
        int i = 0;
        while (i < index && indexingNode.getNextOrNull() != null) {
            previous = indexingNode;
            indexingNode = indexingNode.getNextOrNull();
            i++;
        }

        Node newNode = new Node(data);
        newNode.setNext(indexingNode);
        Node newRoot = newNode;
        if (previous != null) {
            previous.setNext(newNode);
            newRoot = rootOrNull;
        }

        return newRoot;
    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        if (rootOrNull == null) {
            return null;
        }

        if (index == 0) {
            return rootOrNull.getNextOrNull();
        }

        Node previous = rootOrNull;
        Node removeTarget = rootOrNull;
        int i = 0;
        while (i < index && removeTarget.getNextOrNull() != null) {
            previous = removeTarget;
            removeTarget = removeTarget.getNextOrNull();
            i++;
        }

        previous.setNext(removeTarget.getNextOrNull());

        return rootOrNull;
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            return -1;
        }

        if (rootOrNull.getData() == data) {
            return 0;
        }

        Node compareTarget = rootOrNull;
        int i = 1;
        while (compareTarget.getNextOrNull() != null) {
            compareTarget = compareTarget.getNextOrNull();
            if (compareTarget.getData() == data) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {

        Node compareTarget = rootOrNull;
        int i = 0;
        while (i < index && compareTarget != null) {
            compareTarget = compareTarget.getNextOrNull();
            i++;
        }

        if (i < index) {
            return null;
        }

        return compareTarget;
    }

    public static Node reverse(final Node rootOrNull) {

        Node previous   = null;
        Node current    = rootOrNull;
        while (current != null) {
            Node temp = current.getNextOrNull();
            current.setNext(previous);
            previous = current;
            current = temp;
        }

        return previous;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        
        Node current = root0OrNull;
        Node next    = root1OrNull;
        while (current != null) {
            Node temp = current.getNextOrNull();
            current.setNext(next);
            current = next;
            next = temp;
        }
    
        return root0OrNull;
    }
}