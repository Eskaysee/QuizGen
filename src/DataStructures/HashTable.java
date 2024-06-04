package DataStructures;

import java.util.ArrayList;

public class HashTable {

    private int tableSize;
    private int numberOfInsertProbes = 0;
    private int key;
    int[] table;

    ArrayList<Integer>[] chainingTable;

    /**
     * Constructor
     * @param tableSize size of table
     * @param resolutionScheme to be chosen
     */
    public HashTable(int tableSize, int resolutionScheme) {
        setTableSize(tableSize, resolutionScheme);
    }
    /**
     * get the number of insert probes
     * @return numberOfInsertProbes
     */
    public int getNumberOfInsertProbes() {
        return numberOfInsertProbes;
    }
    /**
     * set number of insert probes to input number
     * @param probes new probes count
     */
    private void setNumberOfInsertProbes(int probes) {
        this.numberOfInsertProbes = probes;
    }

    /**
     * get the size of the table
     * @return current table's size
     */
    public int getTableSize() {
        return this.tableSize;
    }

    /**
     * Set the size of the hash table
     * @param tableSize new table size
     */
    private void setTableSize(int tableSize, int resolutionScheme) {
        while(!(isPrime(tableSize))) {
            tableSize++;
        }
        this.tableSize = tableSize;
        createTable(tableSize,resolutionScheme);
    }

    /**
     * isPrime code by Derek Banas,Java Hash Tables 2, March 22,2013
     * @param number number being tested
     * @return true if number is prime or false if it is not prime
     */
    private boolean isPrime(int number) {

        //Eliminate the need to check versus even numbers
        if(number % 2 == 0) { return false; }

        //Check against all odd numbers
        for(int i = 3; i*i <= number; i += 2) {
            if(number % i == 0) { return false; }
        }

        return true;
    }

    /**
     * Populate the table with nulls, It cannot be null so instead it is MIN_VALUE.
     * But the MIN_VALUES will be treated as nulls
     * @param table hash table to be filled
     */
    private void fillTable(int[] table){
        for(int i = 0; i < table.length; i++){
            table[i] = Integer.MIN_VALUE;
        }
    }

    /**
     * creates the appropriate table, array of String or LinkedList,
     * depending on the resolution scheme
     * @param tableSize size of table to be created
     * @param resolutionScheme chosen resolution scheme
     */
    @SuppressWarnings("unchecked")
    private void createTable(int tableSize, int resolutionScheme) {
        if (resolutionScheme == 1 || resolutionScheme == 2 ) {
            table = new int[tableSize];
            fillTable(table);
        }
        else {
            chainingTable = new ArrayList[tableSize];
        }
    }

    /**
     * function to create the key from a String
     * @param item date/time to be converted
     * @return the key
     */
    private int hashFunction(int item) {
        return (2* + Math.abs(item)) % tableSize;
    }

    /**
     * insert method that uses linear probing to insert the data
     * @param item of data
     */
    public void linearInsert(int item) {

        key = hashFunction(item);

        while (table[key] != Integer.MIN_VALUE) {
            numberOfInsertProbes++;
            key = (key+1)%tableSize;
        }

        table[key] = item;
    }

    /**
     * insert method that probes quadratically
     * @param item of data
     */
    public void quadraticInsert(int item) {

        int i = 1;
        key = hashFunction(item);
        while (table[key] != Integer.MIN_VALUE) {
            numberOfInsertProbes++;
            key = (key+(i*i))%tableSize;
            if(key<0) {
                key = Math.abs(key);
            }
            i++;
        }

        table[key] = item;
    }

    /**
     * insert method used when resolution scheme is chaining
     * @param item number to inserted
     *
     */
    public void chainingInsert(int item) {

        key = hashFunction(item);

        if (chainingTable[key] != null) {
            setNumberOfInsertProbes(numberOfInsertProbes+chainingTable[key].size());
            chainingTable[key].add(item);
        }
        else if(chainingTable[key] == null){
            chainingTable[key] = new ArrayList<>();
            chainingTable[key].add(item);

        }

    }

    public int getFromTable(int index){
        int i = table[index];
        return i;
    }
    public ArrayList<Integer> getChainArray(int index){
        ArrayList<Integer> chainT =  chainingTable[index];
        return chainT;
    }
}

