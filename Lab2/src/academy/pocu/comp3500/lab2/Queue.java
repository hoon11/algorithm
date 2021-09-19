package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node rootOrNull;
    private int size;
    
    public Queue() {
        this.size = 0;
    }

    public void enqueue(final int data) {
        LinkedList.append(this.rootOrNull, data);
        this.size++;
    }

    public int peek() {
        return this.rootOrNull.getData();
    }

    public int dequeue() {
        int queueData = this.rootOrNull.getData();
        LinkedList.removeAt(this.rootOrNull, 0);
        this.size--;
        return queueData;
    }

    public int getSize() {
        return this.size;
    }
}