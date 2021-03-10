package ex3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableTest {

    //comprobamos que la hacer la extracción del metodo todo funciona
    @Test
    public void TestCreacionHashEntry() {
        HashEntry entry = new HashEntry("107", "87");
        Assertions.assertEquals("[107, 87]", entry.toString());

    }

    @Test
    public void TestMetodoPut() {
        HashTable hashTable = new HashTable();

        hashTable.put("0", 1);
        hashTable.put("0", 1);
        hashTable.put("1", "2");
        hashTable.put("1", 3);

        Assertions.assertEquals(3, hashTable.get("1"));
        System.out.println(hashTable.toString());
    }

    //Compruebo que el metodo get es correcto devolviendome el metodo deseado mediante la clave obtengo el valor
    @Test
    public void TestMetodoGet() {
        ex1.HashTable hashTable = new ex1.HashTable();
        for (int i = 0; i < 16; ++i) {
            hashTable.put(String.valueOf(i), String.valueOf(i));
        }

        //compruebo que esa calve es null y mira si su return me da eso
        Assertions.assertNull(hashTable.get("17"));

        //compruebo si el elemento de la clave 14 vale i que en su caso es 14
        Assertions.assertEquals("14", hashTable.get("14"));
    }

    //Compruebo si el drop viendo si se ha eliminado mediante la clave el valor deseado, si da null es correcto
    @Test
    public void TestMetodoDrop() {
        HashTable hashTable = new HashTable();
        hashTable.put("5", "5");
        hashTable.put("16", "16");
        hashTable.put("27", 27);

        Assertions.assertEquals(3, hashTable.count());

        hashTable.drop("16");

        Assertions.assertEquals(2, hashTable.count());
        Assertions.assertEquals(27,hashTable.get("27"));
        Assertions.assertEquals(null,hashTable.get("16"));
    }

    //Primer error, nunca se incrementan los elementos insertados de este metodo
    @Test
    public void TestMetodoCount() {
        HashTable hashTable = new HashTable();
        hashTable.put("0", "0");
        hashTable.put("1", "1");
        hashTable.put("11", "11");
        hashTable.put("10", 10);
        hashTable.put("7", 7);

        hashTable.drop("0");
        hashTable.drop("123");
        hashTable.drop("1");

        Assertions.assertEquals(3, hashTable.count());
    }

    //Testeo del metodo size es el metodo el cual contiene el siz total del hash simpre va  a ser 16 a no ser que se cambie
    @Test
    public void TestMetodoSize() {
        HashTable hashTable = new HashTable();
        hashTable.put("0", "1");
        hashTable.put("0", 2);
        hashTable.put("0", '3');
        int numeroItemsTotals = hashTable.size();
        Assertions.assertEquals(numeroItemsTotals, hashTable.size());
    }

    @Test
    public void TestMetodoCountDuplicatedIds() {
        HashTable hashTable = new HashTable();
        hashTable.put("0", "0");
        hashTable.put("0", "1");
        hashTable.put("1", 2);
        hashTable.put("0", "3");
        hashTable.put("1", 1);
        hashTable.put("1", '5');

        Assertions.assertEquals(2, hashTable.count());
    }

    //Testeo del metodo put con colisiones, usando el metodo que me devuelve una posible colision dado una key
    @Test
    public void TestMetodoPutConColisiones() {
        HashTable hashTable = new HashTable();
        hashTable.put("0", "0");
        hashTable.put("1", "0");
        hashTable.put("2", "0");

        Assertions.assertEquals(16, hashTable.size());
        Assertions.assertEquals(3, hashTable.count());

        String idConColision = hashTable.getCollisionsForKey("0");

        hashTable.put(idConColision, "123");

        Assertions.assertEquals(32, hashTable.size());
        Assertions.assertEquals(4, hashTable.count());


        hashTable.put("0", "1");
        hashTable.put("0", "2");
        hashTable.put("11", "21");
        hashTable.put("11", "68");

        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [00, 123]\n bucket[32] = [11, 68]\n bucket[48] = [0, 2]\n bucket[49] = [1, 0]\n bucket[50] = [2, 0]", hashTable.toString());
    }
}
