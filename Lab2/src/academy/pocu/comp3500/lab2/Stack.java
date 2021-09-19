package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    private Node rootOrNull;
    private int size;

    public Stack() {
        this.size = 0;
    }

    public void push(final int data) {
        LinkedList.append(this.rootOrNull, data);
        this.size++;
    }

    public int peek() {
        return LinkedList.getOrNull(this.rootOrNull, this.size - 1).getData();
    }

    public int pop() {
        int popData = this.peek();
        LinkedList.removeAt(this.rootOrNull, this.size - 1);
        this.size--;

        return popData;
    }

    public int getSize() {
        return this.size;
    }
}