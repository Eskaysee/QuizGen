package UnitTests;

import DataStructures.HashTable;

import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class HashTableTest {

    private HashTable hash;
    private final int nil = Integer.MIN_VALUE;

    HashTable populateTable(HashTable table){
        table.linearInsert(1);
        table.linearInsert(10);
        table.linearInsert(100);
        table.linearInsert(1000);
        table.linearInsert(10000);
        return table;
    }
    HashTable populateQuadraticTable(HashTable table){
        table.quadraticInsert(1);
        table.quadraticInsert(10);
        table.quadraticInsert(100);
        table.quadraticInsert(1000);
        table.quadraticInsert(10000);
        return table;
    }
    HashTable populateChainingTable(HashTable table){
        table.chainingInsert(1);
        table.chainingInsert(10);
        table.chainingInsert(100);
        table.chainingInsert(1000);
        table.chainingInsert(10000);
        return table;
    }

    @Test
    public void getNumberOfInsertProbes() {
        hash = new HashTable(5, 1);
        hash = populateTable(hash);
        assertEquals(8,hash.getNumberOfInsertProbes());
    }

    @Test
    public void getTableSize() {
        try{
            hash = new HashTable(5, 2);
            assertEquals(5,hash.getTableSize());
        }catch(AssertionError e){
            System.out.println("Test initializing when passed a prime number");
        }
        try{
            hash = new HashTable(10, 2);
            assertEquals(11,hash.getTableSize());
        }catch(AssertionError e){
            System.out.println("Test initializing when passed an even non-prime number");
        }
        try{
            hash = new HashTable(9, 2);
            assertEquals(11,hash.getTableSize());
        }catch(AssertionError e){
            System.out.println("Test initializing when passed an odd non-prime number");
        }

    }

    @Test
public void linearInsert() {
        try{
            hash = new HashTable(11, 1);
            hash = populateTable(hash);

            assertEquals(1, hash.getFromTable(2));
            assertEquals(10, hash.getFromTable(3));
            assertEquals(100, hash.getFromTable(4));
            assertEquals(1000, hash.getFromTable(9));
            assertEquals(10000, hash.getFromTable(10));

            assertEquals(nil, hash.getFromTable(0));
            assertEquals(nil, hash.getFromTable(1));
            assertEquals(nil, hash.getFromTable(5));
            assertEquals(nil, hash.getFromTable(6));
            assertEquals(nil, hash.getFromTable(7));
            assertEquals(nil, hash.getFromTable(8));
            System.out.println("Passed Linear Probing insertion");
        }catch(AssertionError e){
            System.out.println("Failed Linear Probing insertion");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void quadraticInsert() {
        try{
            hash = new HashTable(11, 2);
            hash = populateQuadraticTable(hash);

            assertEquals(1, hash.getFromTable(2));
            assertEquals(10, hash.getFromTable(9));
            assertEquals(100, hash.getFromTable(3));
            assertEquals(1000, hash.getFromTable(10));
            assertEquals(10000, hash.getFromTable(7));

            assertEquals(nil, hash.getFromTable(0));
            assertEquals(nil, hash.getFromTable(1));
            assertEquals(nil, hash.getFromTable(4));
            assertEquals(nil, hash.getFromTable(5));
            assertEquals(nil, hash.getFromTable(6));
            assertEquals(nil, hash.getFromTable(8));
            System.out.println("Passed Quadratic Probing insertion");
        }catch(AssertionError e){
            System.out.println("Failed Quadratic Probing insertion");
        }
    }

    @Test
    public void chainingInsert() {
        try{
            hash = new HashTable(5, 3);
            hash = populateChainingTable(hash);

            assertEquals((Integer) 1, hash.getChainArray(2).get(0));
            assertEquals((Integer) 10, hash.getChainArray(0).get(0));
            assertEquals((Integer) 100 , hash.getChainArray(0).get(1));
            assertEquals((Integer) 1000, hash.getChainArray(0).get(2));
            assertEquals((Integer) 10000, hash.getChainArray(0).get(3));

            assertNull(hash.getChainArray(1));
            assertNull(hash.getChainArray(3));
            assertNull(hash.getChainArray(4));
            System.out.println("Passed Chaining Probing insertion");
        }catch(AssertionError e){
            System.out.println("Failed Chaining Probing insertion");
        }
    }
}
