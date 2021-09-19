package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node front;
    private Node back;
    private int size;
    
    public Queue() {
        this.size = 0;
    }

    public void enqueue(final int data) {
        Node newNode = new Node(data);
        if (size == 0) {
            this.back = newNode;
            this.front = newNode;
        } else {
            this.back.setNext(newNode);
        }
        
        this.size = this.size + 1;
    }

    public int peek() {
        return this.front.getData();
    }

    public int dequeue() {
        Node temp = this.front;
        this.front = this.front.getNextOrNull();
        this.size = this.size - 1;

        return temp.getData();
    }

    public int getSize() {
        return this.size;
    }
}