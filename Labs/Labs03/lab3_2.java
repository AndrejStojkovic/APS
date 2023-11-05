// Lab 3.2

// Given N words (N>=2), find the number of pairs of words that begin with the same letter.

import java.util.Scanner;

public class CountWordPairs {

    //TODO: implement function
    public static int countWordPairs(String [] words) {
        if(words.length == 0) return 0;
        
        int pairs = 0;

        for(int i = 0; i < words.length - 1; i++) {
            if(words[i].isEmpty()) continue;
            for(int j = i + 1; j < words.length; j++) {
                if(!words[j].isEmpty() && words[i].charAt(0) == words[j].charAt(0)) {
                    pairs++;
                }
            }
        }

        return pairs;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        String words[] = new String[N];

        for(int i=0;i<N;i++) {
            words[i] = input.next();
        }

        System.out.println(countWordPairs(words));

    }
}
