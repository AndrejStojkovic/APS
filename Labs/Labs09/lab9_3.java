// Lab 9.3

// A bus with capacity capacity drives its route and picks up and drops off passengers.
// For each passenger, you're given the time when they boarded the bus and the time when they got off the bus.
// You should determine whether all passengers can ride the bus, that is, whether the number of passengers currently
// on the bus doesn't exceed the capacity at any moment. In the first line, you're given the capacity.
// In the next line, you're given the number of passengers N. In the next N lines,
// you're given the time each passenger boarded and got off the bus in the format HH:MM.

// To solve this problem you should use an appropriate data structure that will
// let you achieve the desired result with minimal complexity. The bus rides until 23:59.
// You should print true if the maximum number of passengers on the bus at the same time doesn't exceed the capacity, otherwise false.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Passenger implements Comparable<Passenger> {
    int time;
    boolean isBoarded;

    Passenger(int time, boolean isBoarded) {
        this.time = time;
        this.isBoarded = isBoarded;
    }

    @Override
    public int compareTo(Passenger o) {
        return Integer.compare(time, o.time);
    }
}

public class Main {
    public static boolean result(List<Passenger> list, int k) {
        list.sort(Passenger::compareTo);
        int curr = 0, max = 0;
        for(Passenger passenger : list) {
            curr = passenger.isBoarded ? curr + 1 : curr - 1;
            max = Math.max(curr, max);
            if(curr > k) return false;
        }
        return true;
    }

    public static int getTime(String time) {
        String [] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int n = in.nextInt();
        ArrayList<Passenger> list = new ArrayList<>();
        in.nextLine();

        for(int i = 0; i < n; i++) {
            String [] line = in.nextLine().split(" ");
            list.add(new Passenger(getTime(line[0]), true));
            list.add(new Passenger(getTime(line[1]), false));
        }

        System.out.println(result(list, k));
    }
}
