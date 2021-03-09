package ex2;

//Con la extracción de metodos me permite hacer test de manera unitaria y poder reutilizar esta clase en otras, me da mas felixibilidad
public class HashEntry {
    String key;
    String value;

    // Linked list of same hash entries.
    HashEntry next;
    HashEntry prev;

    public HashEntry(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return "[" + key + ", " + value + "]";
    }
}
