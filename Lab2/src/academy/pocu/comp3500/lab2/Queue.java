package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node front;
    private int size;
    
    public Queue() {
        this.size = 0;
    }

    public void enqueue(final int data) {
        this.front = LinkedList.prepend(this.front, data);
        this.size++;
    }

    public int peek() {
        return this.front.getData();
    }

    public int dequeue() {
        int queueData = this.peek();
        this.front = LinkedList.removeAt(this.front, 0);
        this.size--;

        return queueData;
    }

    public int getSize() {
        return this.size;
    }
}