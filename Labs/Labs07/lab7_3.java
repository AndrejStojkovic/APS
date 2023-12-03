// Lab 7.3

// You should simulate routing by using a hash table. Every router is one bucket in the hash table and
// it receives the input packets only through one interface. Since the router performs the routing of
// the packet by using the static routes it knows, when it receives a packet it should tell if it is possible
// to route the packet in the network (postoi or nepostoi).

// It is important that all addresses have network mask /24 which means that the last 8 bits are allocated for addressing.
// We assume that all addresses are busy and the packet can be transferred to any device in
// a network if the network exists in the routing table.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;
    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
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



class CBHT<K extends Comparable<K>, E> {
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
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }



    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
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

public class RoutingHashJava {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, String[]> hash = new CBHT<>(128);

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            String [] ips = br.readLine().split(",");
            hash.insert(input, ips);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            String staticIp = br.readLine();
            String ip = br.readLine();
            SLLNode<MapEntry<String, String[]>> node = hash.search(staticIp);

            if(node == null) {
                System.out.println("ne postoi"); continue;
            }

            String [] targetIp = node.element.value;
            boolean valid = false;

            for(int j = 0; j < targetIp.length; j++) {
                String a = targetIp[j].substring(0, targetIp[j].lastIndexOf('.'));
                if(a.equals(ip.substring(0, ip.lastIndexOf('.')))) {
                    valid = true; break;
                }
            }

            System.out.println(valid ? "postoi" : "ne postoi");
        }
    }
}
