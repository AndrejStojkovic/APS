// Lab 1.2
// For a given word entered from standard input, print it reversed.
// On input in the first line, the number of words that will be entered is given.
// In the following lines, the words are entered.

import java.util.Scanner;

public class ReverseWord {
    public static void printReversed(String word) {
        for(int i = word.length() - 1; i >= 0; i--) 
            System.out.print(word.charAt(i));
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();

        for(int i = 0; i < n; i++) {
            String str = in.nextLine();
            printReversed(str);
        }
    }
}
