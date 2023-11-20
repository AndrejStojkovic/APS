// Lab 6.2

// Some modified XML code is provided. The modified XML code uses the symbols '[' and ']', to open and close a tag,
// respectively, instead of the default '<' and '>'. It should be checked if all the tags in the code are nested correctly
// For simplicity, it is assumed that every open tag must have its own closing tag and that tags have no attributes.
// The input is given the number of lines in the code and the XML itself with each tag in a separate line,
// and the output should print 1 or 0 for a valid or invalid code, respectively.

// Explanation: In the modified XML code each open tag is of the form [tagName] and the corresponding closed tag is of the form [/tagName].

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class CheckXML {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid = 1;

        // Vasiot kod tuka
        Stack<String> stack = new ArrayStack<>(n);
        for(int i = 0; i < redovi.length; i++) {
            char start = redovi[i].charAt(0);
            char end = redovi[i].charAt(redovi[i].length() - 1);

            if((start != '[' && end == ']') || (start == '[' && end != ']')) { valid = 0; break; }
            if(start != '[') { continue; }

            if(redovi[i].charAt(1) == '/' && !stack.isEmpty()) {
                String previousTag = stack.peek();
                if(previousTag.charAt(1) == '/') {
                    valid = 0; break;
                }
                String tagText = previousTag.substring(1, previousTag.length() - 1);
                if(!tagText.equals(redovi[i].substring(2, redovi[i].length() - 1))) {
                    valid = 0; break;
                }
                stack.pop();
            } else {
                stack.push(redovi[i]);
            }
        }

        System.out.println(valid);

        br.close();
    }
}
