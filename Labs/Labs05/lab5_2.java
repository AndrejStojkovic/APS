// Lab 5.2

// Given a sequence of N natural numbers. The array should be sorted using the so-called shaker (cocktail) sort.
// This sort is a variation of the bubble sort in that in each iteration the array is traversed twice.
// In the first pass, the smallest element is moved to the beginning of the array, and in the second pass, the largest element is moved to the end.
// In the first line of the input, the number of elements in the array N is given, and in the second line, the numbers are given.
// The output should print the string after each pass in a separate line.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void shakerSort(int a[], int n)
    {
        int pos = 0, temp;
        for(int i = 0; i < (n + 1) / 2; i++) {
            boolean valid = false;

            for(int j = n - 1; j > pos; j--) {
                if(a[j] < a[j - 1]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                    valid = true;
                }
            }
            print(a, n);

            for(int j = pos; j < n - 1; j++) {
                if(a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    valid = true;
                }
            }
            pos++;
            print(a, n);

            if(!valid) break;
        }
    }

    static void print(int [] arr, int n) {
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}
