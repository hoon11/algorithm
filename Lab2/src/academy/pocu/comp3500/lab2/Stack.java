package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Stack {
    private Node top;
    private int size;

    public Stack() {
        this.size = 0;
    }

    public void push(final int data) {
        this.top = LinkedList.prepend(this.top, data);
        this.size++;
    }

    public int peek() {
        return LinkedList.getOrNull(this.top, 0).getData();
    }

    public int pop() {
        int popData = this.peek();
        this.top = LinkedList.removeAt(this.top, 0);
        this.size--;

        return popData;
    }

    public int getSize() {
        return this.size;
    }
}