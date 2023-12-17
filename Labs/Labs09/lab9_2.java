// Lab 9.2

// At a competition, there are competitors who score a certain number of points.
// You should determine the rank of the competitor who scored a given number of points.
// In the first line, you're given the number of competitors N, аnd in the next N lines
// you're given the points that each competitor scored.

// In the last line, you're given the score M of the competitor whose rank you should determine.
// You should print the rank of the competitor who scored M points.
// You should solve this problem without using any sorting methods.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int m = in.nextInt(), p = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] > m) {
                p++;
            }
        }

        System.out.println(p + 1);
    }
}
