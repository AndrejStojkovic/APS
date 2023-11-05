// Lab 3.1

// Before the battalion commander are lined up all the soldiers, and in a doubly linked list are given their IDs.
// The commander doesn't like how the soldiers are lined up so he decides to choose one sub-interval and reverse it.

// Note 1: The sub-interval, as well as the list itself, will have at least two soldiers.
// Note 2: Pay special attention when the interval begins with the first soldier of the list, or ends with the last one.

// Pay attention:
// 1. All the needed code for the structure that you need to use is given. The test class DLLVojska.java is also given,
// with completely implemented input and output. You only need to change the code of the void battalion(DLL<Integer> list, int a, int b) method.
// 2. The moving of the intervals needs to be done with changing the links of the nodes in the list.
// 3. You must not change the main method !

import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    public DLLNode(DLLNode<E> node) {
        this.element = node.element;
        this.pred = node.pred;
        this.succ = node.succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void setFirst(DLLNode<E> node) {
        this.first = node;
    }

    public void setLast(DLLNode<E> node) {
        this.last = node;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}

public class DLLBattalion {

    //TODO: implement function
    public static void swap(DLLNode<Integer> a, DLLNode<Integer> b) {
        // Set values of neighbours
        if(a.pred != null)
            a.pred.succ = b;
        if(b.succ != null)
            b.succ.pred = a;

        // Adjacent Case
        if(a.succ == b) {
            a.succ = b.succ;
            b.pred = a.pred;
            b.succ = a;
            a.pred = b;
            return;
        }

        // Set values of neighbours if not adjacent case
        if(a.succ != null)
            a.succ.pred = b;
        if(b.pred != null)
            b.pred.succ = a;

        // Not Adjacent Case

        DLLNode<Integer> temp;

        temp = a.succ;
        a.succ = b.succ;
        b.succ = temp;

        temp = a.pred;
        a.pred = b.pred;
        b.pred = temp;
    }

    public static void battalion(DLL<Integer> list, int a, int b) {
        if(a == list.getFirst().element && b == list.getLast().element) {
            list.mirror();
            return;
        }

        DLLNode<Integer> curr = list.find(a);
        DLLNode<Integer> last = list.find(b);

        if(curr == null || last == null || curr == last) {
            return;
        }

        if(curr == list.getFirst()) {
            list.setFirst(last);
        } else {
            curr.pred.succ = last;
        }

        if(last == list.getLast()) {
            list.setLast(curr);
        } else {
            last.succ.pred = curr;
        }

        while(curr != null && curr != last) {
            DLLNode<Integer> next = curr.succ;
            DLLNode<Integer> prev = last.pred;

            swap(curr, last);

            curr = next;
            if(curr == last) {
                break;
            }
            last = prev;
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        DLL<Integer> list = new DLL<>();
        for(int i=0;i<n;i++) {
            list.insertLast(input.nextInt());
        }

        int a = input.nextInt();
        int b = input.nextInt();

        battalion(list, a, b);

        System.out.println(list);
        System.out.println(list.toStringR());


    }
}
