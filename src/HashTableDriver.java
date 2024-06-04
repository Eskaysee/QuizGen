import DataStructures.HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

public class HashTableDriver {

    private int poolSize;
    private int questionType;
    private int resolutionScheme;
    private int mcqType;
    private PrintStream stream;
    private PrintStream originalOut = System.out;
    private final int[][] RANGES = {{100,500}, {1000,2000}, {4000,5000}, {50,100}};
    private static final String[] option = {"A.  ", "B.  ", "C.  ", "D.  ","E.  "};
    private static final String[] resolutionSchemes = {"Linear Probing", "Quadratic Probing", "Chaining resolution scheme"};

    /**
     * Constructor
     */
    public HashTableDriver(int poolSize, int questionType, int resolutionScheme, int mcqType, String filename){
        this.poolSize = poolSize;
        this.questionType = questionType;
        this.resolutionScheme = resolutionScheme-1;
        this.mcqType = mcqType;

        setStream(filename, questionType);
        createTable();
        System.setOut(originalOut);
    }

    private void setStream(String filename, int format){
        File file;
        switch(format){
            case 1:
                file = new File("MultipleChoice/"+filename+".txt");
                break;
            case 2:
                file = new File("TrueFalse/"+filename+".txt");
                break;
            default:
                file = new File("FillIn/"+filename+".txt");
        }

        try {
            stream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets user input about the table characteristics and how many hash tables to create
     */
    private void createTable(){

        switch(questionType){
            case 1:
                hashTableMCQ(mcqType);
                break;
            case 2:
                hashTableTrueFalse();
                break;
            case 3:
                hashTableMCQ(mcqType);
                break;
            default:
                System.out.println("Invalid Choice");
        }

    }

    private void hashTableTrueFalse(){

        System.setOut(stream);

        for(int q = 0; q < poolSize; q++){
            float tableSize = (int)(50 + (Math.random() * (101 - 50)));
            float numberOfInserts = (int)(1 + (Math.random() * (tableSize - 1)));
            int choice = (int)(1 + (Math.random() * (3 - 1)));
            float ans = tableSize/numberOfInserts;

            if(choice == 1){
                System.out.printf("Question " + (q + 1) + " (2 points)\nAssume a perfect hash function. A hash table of size " + tableSize + " containing " +
                        numberOfInserts + " items has a Load Factor of %.3f\n" , ans);
                System.out.println("*1. True\n2. False\n");
            }else{
                ans = numberOfInserts/tableSize;
                System.out.printf("Question " + (q + 1) + " (2 points)\nAssume a perfect hash function. A hash table of size " + tableSize + " containing " +
                        numberOfInserts + " items has a Load Factor of %.2f\n" , ans);
                System.out.println("1. True\n*2. False\n");
            }
        }

        System.setOut(System.out);
    }

    private void hashTableMCQ(int mcqType){
        int tableSize = (int)(7 + (Math.random() * (8 - 7)));

        System.setOut(stream);
        for(int q = 0; q < poolSize; q++) {///////////////must be pool size
            HashTable table = new HashTable(tableSize, resolutionScheme+1);
            int[] range = getRange();

            ArrayList<Integer> insertedNums = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                int num = (int) (range[0] + (Math.random() * (range[1] - range[0])));
                insertedNums.add(num);

                if (resolutionScheme == 0) {
                    table.linearInsert(num);
                } else if (resolutionScheme == 1) {
                    table.quadraticInsert(num);
                } else {
                    table.chainingInsert(num);
                }
            }

            ArrayList<String> tableArray;

            if (resolutionScheme == 0 || resolutionScheme == 1) {
                tableArray = getTable(table);
            } else {
                tableArray = getChainingTable(table);
            }
            ArrayList<String> tableCopy = copyArray(tableArray);
            int randomNum = (int) (Math.random() * 4);

            if (mcqType == 1) {

                System.out.println("Question " + (q + 1) + " (2 points)\nGiven the following inputs " + insertedNums + " and a hash function: (2 * item) % " + tableSize + " using " + resolutionSchemes[resolutionScheme] + ". Which of the resulting tables below is correct? Assume table size will be checked to be prime, if not adjust to the nearest following prime");
                for (int i = 0; i < 5; i++) {
                    if (i == randomNum) {
                        System.out.println("*" + option[i] + tableCopy);
                        continue;
                    }
                    Collections.shuffle(tableArray);
                    System.out.println(option[i] + tableArray);
                }
                System.out.println("#randomize\n");

            } else if(mcqType == 2){

                System.out.println("Question " + (q + 1) + " (2 points)\nGiven the following inputs " + insertedNums + " and a hash function: (2 * item) % " + tableSize + " using " + resolutionSchemes[resolutionScheme] + ". How many collisions would occur? Assume table size will be checked to be prime, if not adjust to the nearest following prime");
                for (int i = 0; i < 5; i++) {
                    if (i == randomNum) {
                        System.out.println("*" + option[i] + table.getNumberOfInsertProbes());
                    }
                    else if (i == 0) {
                        if (table.getNumberOfInsertProbes()>2)
                            System.out.println(option[i] + (table.getNumberOfInsertProbes()-3));
                        else
                            System.out.println(option[i] + (table.getNumberOfInsertProbes()+3));
                    }else if (i == 1) {
                        if(table.getNumberOfInsertProbes()>1)
                            System.out.println( option[i] + (table.getNumberOfInsertProbes()-2));
                        else
                            System.out.println(option[i] + "None of the answers");
                    }else if (i == 2) {
                        if (table.getNumberOfInsertProbes()>0)
                            System.out.println(option[i] + (table.getNumberOfInsertProbes()-1));
                        else
                            System.out.println(option[i] + (table.getNumberOfInsertProbes()+4));
                    }else if (i == 3) {
                        System.out.println(option[i] + (table.getNumberOfInsertProbes()+2));
                    }else {
                        System.out.println(option[i] + (table.getNumberOfInsertProbes()+1));
                    }
                }
                System.out.println("#randomize\n");

                //System.setOut(originalOut);

            }
            else if(mcqType == 3){
                int choice = (int)(Math.random() * insertedNums.size());
                System.out.println("Question " + (q + 1) + " (2 points)\n Given the following inputs " + insertedNums + " and a hash function: (2 * item) % " + tableSize + " using " + resolutionSchemes[resolutionScheme] +
                        ". " + insertedNums.get(choice) + " Will be inserted in index position _?\n*{"+choice+"}\n");
            }
        }

    }

    private ArrayList<String> getTable(HashTable t){
        final int NIL = Integer.MIN_VALUE;
        ArrayList<String> tempArray = new ArrayList<>();
        for(int i = 0; i < t.getTableSize(); i++){

            if(t.getFromTable(i)==NIL)
                tempArray.add("___");
            else
                tempArray.add(Integer.toString(t.getFromTable(i)));

        }
        return tempArray;
    }

    private ArrayList<String> getChainingTable(HashTable t) {
        ArrayList<String> tempArray = new ArrayList<>();
        StringBuilder innerTempArray;
        for(int i = 0; i < t.getTableSize(); i++){
            if(t.getChainArray(i) == null) {
                tempArray.add("___");
            }
            else {
                innerTempArray = new StringBuilder();
                for(int insert: t.getChainArray(i)){
                    if(innerTempArray.length()==0){
                        innerTempArray.append(insert);
                    }
                    else{
                        String s = "->" +insert;
                        innerTempArray.append(s);
                    }
                }
                tempArray.add(innerTempArray.toString());
            }
        }
        return tempArray;
    }

    private int[] getRange(){
        int randomNum = (int)(Math.random() *3);
        return RANGES[randomNum];
    }

    private ArrayList<String> copyArray(ArrayList<String> arr){
        return new ArrayList<>(arr);
    }

}
