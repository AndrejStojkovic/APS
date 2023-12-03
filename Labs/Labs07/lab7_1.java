// Lab 7.1

// You are supposed to simulate a log in system. The user enters username and password.
// If such user exists in the system you are supposed to print "Najaven" to the standard output,
// else print "Nenajaven" and give the user another prompt for credentials.
// This stops when the user will enter username and password that match, a certain user credentials in the system.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Lozinki {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Hashtable<String, String> hash = new Hashtable<>();

        for(int i=1;i<=N;i++){
            String imelozinka = br.readLine();
            String [] pom = imelozinka.split(" ");
            hash.put(pom[0], pom[1]);
        }

        while(true) {
            String line = br.readLine();
            if(line.equals("KRAJ")) break;

            String [] data = line.split(" ");
            if(hash.containsKey(data[0]) && hash.get(data[0]).equals(data[1])) {
                System.out.println("Najaven"); break;
            }
            System.out.println("Nenajaven");
        }
    }
}
