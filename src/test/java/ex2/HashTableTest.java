package ex2;
//

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableTest {

    @Test
    public void put_sin_colision() {
        HashTable hashTable = new HashTable();

        hashTable.put("1", "a");
        hashTable.put("2", "b");


        Assertions.assertEquals(2, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a]\n" +
                " bucket[2] = [2, b]", hashTable.toString());
    }

    @Test
    public void put_con_colision() {
        HashTable hashTable = new HashTable();

        hashTable.put("1", "a");
        hashTable.put("2", "b");

        hashTable.put("01", "b");
        hashTable.put("12", "d");



        Assertions.assertEquals(4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a] -> [01, b] -> [12, d]\n" +
                " bucket[2] = [2, b]", hashTable.toString());
    }

    @Test
    public void put_con_colision_actualizar() {
        HashTable hashTable = new HashTable();

        hashTable.put("1", "a");
        hashTable.put("2", "b");

        hashTable.put("01", "b");
        hashTable.put("12", "d");

        hashTable.put("01", "y");
        hashTable.put("12", "z");


        Assertions.assertEquals(4, hashTable.count());
        Assertions.assertEquals(16, hashTable.size());
        Assertions.assertEquals("\n" +
                " bucket[1] = [1, a] -> [01, y] -> [12, z]\n" +
                " bucket[2] = [2, b]", hashTable.toString());
    }



    @Test
    public void get() {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "a");
        hashTable.put("2", "b");

        Assertions.assertNull(hashTable.get("3"));

        Assertions.assertEquals("a", hashTable.get("1"));
    }

    @Test
    public void get_colission() {
        HashTable hashTable = new HashTable();
        hashTable.put("1", "a");
        hashTable.put("12", "ab");
        hashTable.put("2", "b");

        Assertions.assertNull(hashTable.get("3"));

        Assertions.assertEquals("ab", hashTable.get("12"));
    }

    @Test
    public void drop() {
        HashTable hashTable = new HashTable();

        hashTable.put("1", "a");
        hashTable.put("2", "b");

        Assertions.assertEquals(2, hashTable.count());

        hashTable.drop("1");

        Assertions.assertNull(hashTable.get("1"));
        Assertions.assertEquals(1, hashTable.count());
        Assertions.assertEquals("b", hashTable.get("2"));
    }

    @Test
    public void drop_colission() {
        HashTable hashTable = new HashTable();

        hashTable.put("1", "a");
        hashTable.put("2", "b");
        hashTable.put("12", "ab");


        Assertions.assertEquals(3, hashTable.count());

        hashTable.drop("1");

        Assertions.assertNull(hashTable.get("1"));
        Assertions.assertEquals(2, hashTable.count());
        Assertions.assertEquals("ab", hashTable.get("12"));
    }



}
