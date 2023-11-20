// Lab 5.1

// Given a sequence of N natural numbers. It is necessary to sort the sequence so that in the first part
// of the sequence the odd numbers from it will be sorted in ascending order,
// and in the second part the even numbers will be sorted in descending order.
// In the first line of the input, the number of elements in the array N is given, and in the second line,
// the numbers are given. The output should print the sorted array.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {
    static void sortAscending(int arr[], int n) {
        int temp = 0;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void sortDescending(int arr[], int n) {
        int temp = 0;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - i - 1; j++) {
                if(arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

	static void oddEvenSort(int a[], int n) {
        int [] parni = new int[n];
        int [] neparni = new int[n];
        int p_ct = 0, n_ct = 0;

        for(int i = 0; i < n; i++) {
            if((a[i] & 1) == 0) {
                parni[p_ct++] = a[i];
            } else {
                neparni[n_ct++] = a[i];
            }
        }

        sortAscending(neparni, n_ct);
        sortDescending(parni, p_ct);

        for(int i = 0; i < n_ct; i++) {
            a[i] = neparni[i];
        }

        int idx = 0;
        for(int i = n_ct; i < n; i++) {
            a[i] = parni[idx++];
        }
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
		oddEvenSort(a,n);
		for(i=0;i<n-1;i++)
			System.out.print(a[i]+" ");
		System.out.print(a[i]);
	}
}
