package sample;

import java.util.EmptyStackException;
import java.util.Stack;

class MinStackNode {
    MinStackNode nextNode = null;
    MinStackNode minNode;
    int val;

    MinStackNode(int val) {
        this.val = val;
        this.minNode = this;
    }
}

class MinStack {
    MinStackNode head;
    private int size;

    public MinStack() {
        this.head = new MinStackNode(0);
        this.size = 0;
    }

    public int peek() {
        if(this.isEmpty()) {throw new EmptyStackException();}
        return this.head.nextNode.val;
    }

    public int pop() {
        if(this.isEmpty()) {throw new EmptyStackException();}
        MinStackNode tmp = this.head.nextNode;
        this.head.nextNode = tmp.nextNode;
        this.size--;
        return tmp.val;
    }

    public void push(int val) {
        MinStackNode node = new MinStackNode(val);
        node.nextNode = this.head.nextNode;
        if(node.nextNode != null && node.nextNode.minNode.val < node.val) {
            node.minNode = node.nextNode.minNode;
        }
        this.head.nextNode = node;
        this.size++;
    }

    public int min(){
        if(this.isEmpty()) {throw new EmptyStackException();}
        return this.head.nextNode.minNode.val;
    }

    public int size() {return this.size;}

    public boolean isEmpty() {return this.size == 0;}
}

public class MinDeque {
    private MinStack minStackFirst;
    private MinStack minStackLast;
    private int size;

    public MinDeque() {
        minStackFirst = new MinStack();
        minStackLast = new MinStack();
        this.size = 0;
    }

    public boolean isEmpty() {return this.size == 0;}

    public int size() {return this.size;}

    public void addFirst(int val) {
        this.minStackFirst.push(val);
        this.size++;
    }

    public void addLast(int val) {
        this.minStackLast.push(val);
        this.size++;
    }

    private void refreshQueue() {
        if(this.isEmpty()) {throw new EmptyStackException();}
        Stack<Integer> tmpStack = new Stack<>();
        MinStack emptyStack = null;
        MinStack nonEmptyStack = null;
        if(this.minStackFirst.isEmpty()) {
            emptyStack = this.minStackFirst;
            nonEmptyStack = this.minStackLast;
        } else {
            emptyStack = this.minStackLast;
            nonEmptyStack = this.minStackFirst;
        }
        for(int i = 0; i < this.size/2; i++) {tmpStack.push(nonEmptyStack.pop());}
        while(!nonEmptyStack.isEmpty()) {emptyStack.push(nonEmptyStack.pop());}
        while(!tmpStack.isEmpty()) {nonEmptyStack.push(tmpStack.pop());}
    }

    public int removeFirst() {
        if(this.isEmpty()) {throw new EmptyStackException();}
        if(this.minStackFirst.isEmpty()) {this.refreshQueue();}
        this.size--;
        return this.minStackFirst.pop();
    }

    public int removeLast() {
        if(this.isEmpty()) {throw new EmptyStackException();}
        if(this.minStackLast.isEmpty()) {this.refreshQueue();}
        this.size--;
        return this.minStackLast.pop();
    }

    public int min() {
        if(this.isEmpty()) {throw new EmptyStackException();}
        int firstStackMin = this.minStackFirst.isEmpty()? Integer.MAX_VALUE: this.minStackFirst.min();
        int lastStackMin = this.minStackLast.isEmpty()? Integer.MAX_VALUE: this.minStackLast.min();
        return Integer.min(firstStackMin, lastStackMin);
    }

    public static void main(String[] args) {
        // Some tests
//        MinStack minStack = new MinStack();
//        minStack.push(0);
//        minStack.push(1);
//        minStack.push(2);
//        minStack.push(-1);
//        System.out.println(minStack.min());
//        System.out.println(minStack.pop());
//        System.out.println(minStack.min());
//        System.out.println(minStack.pop());
//        System.out.println(minStack.min());
//        System.out.println(minStack.pop());
//        System.out.println(minStack.min());
//        System.out.println(minStack.pop());
//        System.out.println(minStack.isEmpty());

        MinDeque minDeque = new MinDeque();
        minDeque.addFirst(0);
        minDeque.addFirst(2);
        minDeque.addFirst(4);
        minDeque.addLast(1);
        minDeque.addLast(3);
        minDeque.addLast(-1);
        for(int i = 0; i < 6; i++) {
            System.out.println(minDeque.min());
            System.out.println(minDeque.removeLast());
        }
        System.out.println(minDeque.isEmpty());

//        MinDeque minDeque = new MinDeque();
//        minDeque.addFirst(0);
//        minDeque.addFirst(2);
//        minDeque.addFirst(4);
//        minDeque.addLast(1);
//        minDeque.addLast(3);
//        minDeque.addLast(-1);
//        for(int i = 0; i < 6; i++) {
//            System.out.println(minDeque.min());
//            System.out.println(minDeque.removeFirst());
//        }
//        System.out.println(minDeque.isEmpty());
    }

}

