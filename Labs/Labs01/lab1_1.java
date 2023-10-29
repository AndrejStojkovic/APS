import java.util.Scanner;

public class PushZeroes {
    static void pushZeroesToBeginning(int[] arr, int n) {
        int ct = n - 1;

        for(int i = n - 1; i >= 0; i--) {
            if(arr[i] != 0) {
                arr[ct--] = arr[i];
            }
        }

        while(ct >= 0) {
            arr[ct--] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        pushZeroesToBeginning(arr, n);

        System.out.print("Transformiranata niza e:\n");
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}