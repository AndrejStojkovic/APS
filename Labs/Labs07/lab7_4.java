// Lab 7.4

// It is necessary to make a computer application that will speed up the operation of a pharmacy.
// The application should enable the user (pharmacist) to quickly search through the huge set of drugs entered into the system.
// The way he should search is as follows: it is enough to enter the first 3 letters of the name of the drug so that
// a list of the drugs available in the system can be displayed. The job of the pharmacist is to check if the drug is in the system
// and to give information to the client. The information he should give to the customer is whether the drug is on the positive list of drugs,
// what is the price and how many pieces of the drug are in stock. If the drug exists, the customer orders it,
// stating how many pieces he will buy. The pharmacist should record this action on the system
// (that is, reduce the stock of drugs by as many pieces as he dispensed to the client).
// If the customer's order is greater than the drug stock in the system, no action is taken.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

class Lek{
    String ime;
    int pozLista;
    int cena;
    int kolicina;

    public String getIme() {                return ime;                 }
    public void setIme(String ime) {        this.ime = ime;	            }
    public int getCena() {      		    return cena;	            }
    public void setCena(int cena) {		    this.cena = cena;           }
    public int getKolicina() {  		    return kolicina;	        }
    public void setKolicina(int kolicina) { this.kolicina = kolicina;	}
    public int getPozLista() {      		return pozLista;        	}

    public Lek(String ime, int pozLista, int cena, int kolicina) {
        this.ime = ime.toUpperCase();
        this.pozLista = pozLista;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        if(pozLista==1) return ime+"\n"+"POZ\n"+cena+"\n"+kolicina;
        else return ime+"\n"+"NEG\n"+cena+"\n"+kolicina;
    }
}

class LekKluch{
    String ime;
    public LekKluch(String ime) {
        this.ime = ime.toUpperCase();
    }

    @Override
    public int hashCode() {
        return (29 * (29 * ((int)ime.charAt(0) + (int)ime.charAt(1) + (int)ime.charAt(2)))) % 102780;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LekKluch lekKluch = (LekKluch) o;
        return Objects.equals(ime, lekKluch.ime);
    }
}


public class Apteka {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<LekKluch, Lek> hash = new CBHT<>(128);

        for(int i = 0; i < N; i++) {
            String [] input = br.readLine().split(" ");
            Lek lek = new Lek(input[0], Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
            hash.insert(new LekKluch(input[0]), lek);
        }

        while(true) {
            String input = br.readLine();
            if(input.equals("KRAJ")) break;
            int amount = Integer.parseInt(br.readLine());

            SLLNode<MapEntry<LekKluch, Lek>> node = hash.search(new LekKluch(input));

            if(node == null) {
                System.out.println("Nema takov lek"); continue;
            }

            Lek lek = node.element.value;
            System.out.println(node.element.value);

            if(lek.getKolicina() >= amount) {
                lek.setKolicina(lek.getKolicina() - amount);
                System.out.println("Napravena naracka");
            } else {
                System.out.println("Nema dovolno lekovi");
            }
        }
    }
}


class CBHT<K, E> {
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(curr.element.key))     return curr;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                if (pred == null)   buckets[b] = curr.succ;
                else                pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}
class MapEntry<K,E> {
    K key;
    E value;
    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }
    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;
    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
    @Override
    public String toString() {
        return element.toString();
    }
}
