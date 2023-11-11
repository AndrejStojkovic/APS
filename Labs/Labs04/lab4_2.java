// Lab 4.2

// A sequence of numbers is considered a zigzag sequence if the numbers in the sequence are alternately positive and negative i.e.
// for every pair of consecutive numbers, one is positive and the other is negative.
// For example -1 2 -9 8 -4 is a zigzag sequence, but -1 9 7 -3 8 -3 is not, because 9 and 7 are adjacent numbers, but both are positive.
// A zigzag sequence can start with either a positive or a negative number. A sequence of only one non-zero number is considered a zigzag sequence.
// For a given sequence of numbers, write an algorithm that will return the length of the longest subarray that represents a zigzag sequence.

// In the first line of the input, the number N is given for the length of the string. In each of the next N rows, one number from the original sequence is given.
// The output should print the length of the longest subarray that is a zigzag sequence of the original array.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {
    static int najdiNajdolgaCikCak(int a[]) {
        if(a.length == 0) return 0;

        int ct = 1, max = 0;
        for(int i = 1; i < a.length; i++) {
            if((a[i] > 0 && a[i - 1] < 0) || (a[i] < 0 && a[i - 1] > 0)) ct++;
            else ct = 1;
            max = Math.max(ct, max);
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }
}
