// K1 6

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

interface Queue<E> {
    int size();
    boolean isEmpty();
    void clear();
    E peek();
    void enqueue(E el);
    E dequeue();
}

class ArrayQueue<E> implements Queue<E> {
    public E [] elements;
    int len, front, rear;

    @SuppressWarnings("unchecked")
    ArrayQueue() {
        elements = (E[]) new Object[0];
        clear();
    }

    @SuppressWarnings("unchecked")
    ArrayQueue(int size) {
        elements = (E[]) new Object[size];
        clear();
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public void clear() {
        len = front = rear = 0;
    }

    @Override
    public E peek() {
        if(len == 0) {
            throw new NoSuchElementException();
        }
        return elements[front];
    }

    @Override
    public void enqueue(E el) {
        if(len != elements.length) {
            if(rear == elements.length) rear = 0;
            elements[rear++] = el;
            len++;
        }
    }

    @Override
    public E dequeue() {
        if(len == 0) {
            throw new NoSuchElementException();
        }
        E frontElement = elements[front++];
        if(front == elements.length) front = 0;
        len--;
        return frontElement;
    }
}

public class card_trick {
    public static int count(int N){
        ArrayQueue<Integer> cards = new ArrayQueue<>(51);
        Stack<Integer> inverted = new Stack<>();

        for(int i = 1; i <= 51; i++) {
            cards.enqueue(i);
        }

        int ct = 0;
        while(cards.peek() != N) {
            for(int i = 0; i < 7; i++) {
                inverted.add(cards.dequeue());
            }

            while(!inverted.isEmpty()) {
                cards.enqueue(inverted.pop());
                cards.enqueue(cards.dequeue());
            }

            ct++;
        }

        return ct;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
