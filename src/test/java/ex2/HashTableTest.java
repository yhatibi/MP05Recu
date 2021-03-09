package ex2;

import static org.junit.jupiter.api.Assertions.*;

import ex3.HashEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Compruebo que el hastable funcione bien
class HashTableTest {



    //comprobamos que la hacer la extracción del metodo todo funciona
    @Test
    public void TestCreacionHashEntry() {
        ex3.HashEntry entry = new HashEntry("107", 87);
        Assertions.assertEquals("[107, 87]", entry.toString());

    }

    //compruebo que el metodo put del hash table funcione correctamente pasandonle un valor deseado, aparentemente funciona, sonbrescribiendo el primer valor en caso de ser una key igual
    @Test
    public void TestMetodoPut() {
        HashTable hashTable = new HashTable();

        hashTable.put("3", "1");
        hashTable.put("3", "4");
        hashTable.put("14", "7");
        hashTable.put("14", "8");
        hashTable.put("8", "9");
        hashTable.put("8", "10");
        hashTable.put("12", "12");
        hashTable.put("12", "57");

        //compruebo el valor de esa clave introducida
        Assertions.assertEquals("8", hashTable.get("14"));

        //compruebo que las claves repetidas se sobreescriben
        Assertions.assertEquals("\n bucket[1] = [12, 57]\n bucket[3] = [3, 4] -> [14, 8]\n bucket[8] = [8, 10]", hashTable.toString());
    }

    //Compruebo que el metodo get es correcto devolviendome el metodo deseado mediante la clave obtengo el valor
    @Test
    public void TestMetodoGet() {
        HashTable hashTable = new HashTable();
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
        ex3.HashTable hashTable = new ex3.HashTable();
        hashTable.put("5", "5");
        hashTable.put("16", "16");
        hashTable.put("27", "27");

        Assertions.assertEquals(3, hashTable.count());

        hashTable.drop("16");

        Assertions.assertEquals(2, hashTable.count());
        Assertions.assertEquals("27",hashTable.get("27"));
        Assertions.assertEquals(null,hashTable.get("16"));
    }

    //Primer error, nunca se incrementan los elementos insertados de este metodo
    @Test
    public void TestMetodoCount() {
        //hacemos un put de 5 claves, eliminamos dos claves de de la tabla y luego comprobamos que tenemos 3 items de los 2 eliminados
        HashTable hashTable = new HashTable();
        hashTable.put("0", "0");
        hashTable.put("1", "1");
        hashTable.put("11", "11");
        hashTable.put("10", "10");
        hashTable.put("7", "7");

        hashTable.drop("0");
        hashTable.drop("1");

        Assertions.assertEquals(3, hashTable.count());
    }

    //Testeo del metodo size es el metodo el cual contiene el siz total del hash simpre va  a ser 16 a no ser que se cambie
    @Test
    public void TestMetodoSize() {
        HashTable hashTable = new HashTable();
        hashTable.put("0", "1");
        hashTable.put("0", "2");
        hashTable.put("0", "3");
        int numeroItemsTotals = hashTable.size();
        System.out.println(hashTable.size());
        Assertions.assertEquals(numeroItemsTotals, hashTable.size());
    }

    //introducimos varias claves repetidas para comprobar que no se copian
    @Test
    public void TestMetodoCountDuplicatedIds() {
        HashTable hashTable = new HashTable();
        hashTable.put("0", "0");
        hashTable.put("0", "1");
        hashTable.put("1", "2");
        hashTable.put("0", "3");
        hashTable.put("1", "1");
        hashTable.put("1", "5");

        Assertions.assertEquals(2, hashTable.count());
    }
}
