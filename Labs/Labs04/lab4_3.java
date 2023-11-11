// Lab 4.3

// Given is a sequence of N positive integers and a number K. Let the numbers be denoted by a[0] to a[N−1].
// Let us define the sum of absolute differences as abs(a1−a0)+abs(a2−a1)+…+abs(aN−1−aN−2).
// Choose exactly K numbers from the sequence so that when they are joined into a single sequence,
// the sum of absolute differences is maximal. Print this sum.

// Input: In the first line the two numbers N (1≤N≤100) and K (1≤K≤100, K≤N) are given.
// In the second line you N positive integers are given, each of which is less than 1,000.
// Output: Print the requested maximum sum of absolute differences.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {
    static int solve(int numbers[], int N, int K) {
        int [][] mat = new int[N][K];
        int max = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 1; j < K; j++) {
                for(int k = 0; k < i; k++) {
                    mat[i][j] = Math.max(mat[i][j], mat[k][j - 1] + Math.abs(numbers[i] - numbers[k]));
                }
                max = Math.max(mat[i][j], max);
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }
}
