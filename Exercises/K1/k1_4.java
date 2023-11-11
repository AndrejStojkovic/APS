// K1 4

import java.util.Arrays;
import java.util.Scanner;

public class LDS {
    private static int najdolgaOpagackaSekvenca(int[] a) {
        if(a.length == 1) return 1;

        int [] lds = new int[a.length];
        Arrays.fill(lds, 1);

        for(int i = 1; i < a.length; i++) {
            for(int j = 0; j < i; j++) {
                if(a[i] < a[j] && lds[i] < lds[j] + 1) {
                    lds[i] = lds[j] + 1;
                }
            }
        }

        int r = Integer.MIN_VALUE;
        for(int el : lds) {
            r = Math.max(r, el);
        }

        return r;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }
}
