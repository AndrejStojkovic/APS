// Lab 3.3

// There are N Mice and N holes are placed in a straight line. Each hole can accommodate only 1 mouse.
// A mouse can stay at his position, move one step right from x to x + 1, or move one step left from x to x -1.
// Any of these moves consumes 1 minute. More than 1 mouse can move at the same time.
// Assign mice to holes so that the time when the last mouse gets inside a hole is minimized.

import java.util.Arrays;
import java.util.Scanner;

public class MiceHoles {

    //TODO: implement function
    public static int minTime(int[] mice, int[] holes) {
        Arrays.sort(mice);
        Arrays.sort(holes);

        int time = 0;
        for(int i = 0; i < mice.length; i++) {
            time = Math.max(time, Math.abs(mice[i] - holes[i]));
        }

        return time;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String line = input.nextLine();
        String parts[] = line.split(" ");
        int mice[] = new int[parts.length];
        for(int i=0;i<parts.length;i++) {
            mice[i] = Integer.parseInt(parts[i]);
        }

        line = input.nextLine();
        parts = line.split(" ");
        int holes[] = new int[parts.length];
        for(int i=0;i<parts.length;i++) {
            holes[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(minTime(mice, holes));
    }
}
