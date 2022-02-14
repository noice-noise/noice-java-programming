package Lab18;

/**
 * Created by Serato, Jay Vince on January 14, 2020.
 * This is a given class. DO NOT MODIFY.
 */
public class Entry<K, V> {
    private K key;
    private V value;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String toString() {
        return "{".concat(key + ", " + value).concat("}");
    }
}
