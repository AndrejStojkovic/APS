// K1 3

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        if(M == 0) {
            System.out.printf("%d\n%d\n", N * 100, N * 100);
            return;
        }

        int min = 0;

        if(N < M) {
            k = M / N - 1;
            int ostatok = M % N;
            min = N * 100 + N * k * 100 + ostatok * 100;
        } else if(N > M) {
            min = M * 100 + (N - M) * 100;
        }

        int max = N * 100 + (M - 1) * 100;
        System.out.printf("%d\n%d\n", min, max);
    }

}
