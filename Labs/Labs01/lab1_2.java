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
