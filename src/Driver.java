import DataStructures.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/**
 * Author: All team members
 * Driver creates UI and also accepts input from user before creating the pool text file.
 * @param <T> class is generic to allow for insertion of numbers, strings or characters into data structures
 */

public class Driver<T extends Comparable<? super T>>{

    private char[] charLib = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
            'p','q','r','s','t','u','v','w','x','y','z'};
    private String[] stringLib = {"apple","banana","care","down","edible","fly","garage","hello","ink","jump","kick","lie",
            "make","no","over","principle","quit","rest","sit","tumble","up","veer","wait","xenophobia","you","zit"};
    private ArrayList<T> allInserts;
    private ArrayList<T> travel;  //for traversals
    private T type;
    private BinarySearchTree<T> bst;
    private AVLTree<T> avl;
    private RedBlackTree<T> rbt;
    private BinaryHeap<T> bheap;
    private BPTrees bpt;
    private Graph digraph;
    private static int heapType = 0;
    private static final String[] resolutionSchemes = {"Linear Probing", "Quadratic Probing", "Chaining resolution scheme"};

    /**
     * Constructor
     * @param init: type of items to be inserted (i.e. Integers, Characters or String)
     */
    @SuppressWarnings("unchecked")
    private Driver(T init){
        allInserts = new ArrayList<>();
        type = init;
        travel = new ArrayList<>();
        //Data Structures
        bst = new BinarySearchTree<>();
        avl = new AVLTree<>();
        rbt = new RedBlackTree();
        digraph = new Graph();
    }

    private void poolType(int format, int type, int poolSize, String filename){
        if (filename.substring(0, filename.indexOf("/")).equals("BinarySearchTree")){
            if (format == 1)
                treeMCQ(poolSize, bst, filename, type);
            if (format == 2)
                treeTrueFalse(poolSize, bst, filename, type);
            /*if (format == 3)
                fillIn(poolSize, bst, filename, type);*/
        }
        else if (filename.substring(0, filename.indexOf("/")).equals("AVLTree")){
            if (format == 1)
                treeMCQ(poolSize, avl, filename, type);
            if (format == 2)
                treeTrueFalse(poolSize, avl, filename, type);
            /*if (format == 3)
                fillIn(poolSize, avl, filename, type);*/
        }
        else if (filename.substring(0, filename.indexOf("/")).equals("RedBlackTree")){
            if (format == 1)
                treeMCQ(poolSize, rbt, filename, type);
            if (format == 2)
                treeTrueFalse(poolSize, rbt, filename, type);
            /*if (format == 3)
                fillIn(poolSize, rbt, filename, type);*/
        }
        else if (filename.substring(0, filename.indexOf("/")).equals("BinaryHeap")){
            switch(heapType){
                case 0:
                    bheap = new MinHeap<>();
                    break;
                case 1:
                    bheap = new MaxHeap<>();
                    break;
                default:
                    System.out.println("Invalid heap type: " + heapType);

            }
            if (format == 1)
                treeMCQ(poolSize, bheap, filename, type);
            if (format == 2)
                treeTrueFalse(poolSize, bheap, filename, type);
            //if (format == 3);
        }
    }

    /**
     * Author: Group 2
     * Get the inOrder reading of the nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printAllNodes(Object object){
        if (object instanceof BinarySearchTree)
            travel = bst.inOrder();

        else if(object instanceof AVLTree)
            travel = avl.inOrder();

        else if(object instanceof RedBlackTree)
            travel = rbt.inOrder();

        else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap)
                bheap = (MinHeap<T>) object;
            else if(object instanceof MaxHeap)
                bheap = (MaxHeap<T>) object;
            travel = bheap.inOrder();
        }
    }

    /**
     * Author: Group 2
     * Get preOrder formation of the nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printPreOrder(Object object){
        if (object instanceof BinarySearchTree)
            travel = bst.preOrder();

        else if(object instanceof AVLTree)
            travel = avl.preOrder();

        else if(object instanceof RedBlackTree)
            travel = rbt.preOrder();

        else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap)
                bheap = (MinHeap<T>) object;
            else if(object instanceof MaxHeap)
                bheap = (MaxHeap<T>) object;
            travel = bheap.preOrder();
        }
    }

    /**
     * Author: Group 2
     * get post order formation of nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printPostOrder(Object object){
        if (object instanceof BinarySearchTree)
            travel = bst.postOrder();

        else if(object instanceof AVLTree)
            travel = avl.postOrder();

        else if(object instanceof RedBlackTree)
            travel = rbt.postOrder();

        else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap)
                bheap = (MinHeap<T>) object;
            else if(object instanceof MaxHeap)
                bheap = (MaxHeap<T>) object;
            travel = bheap.postOrder();
        }
    }

    /**
     * Author: Group 2
     * Get level ordering of nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printLevelOrder(Object object){
        if (object instanceof BinarySearchTree)
            travel = bst.levelOrder();

        else if(object instanceof AVLTree)
            travel = avl.levelOrder();

        else if(object instanceof RedBlackTree)
            travel = rbt.levelOrder();

        else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap)
                bheap = (MinHeap<T>) object;
            else if(object instanceof MaxHeap)
                bheap = (MaxHeap<T>) object;
            travel = bheap.levelOrder();
        }
    }

    @SuppressWarnings("unchecked")
    private int height(Object obj){
        if (obj instanceof BinarySearchTree) {
            bst = (BinarySearchTree<T>) obj;
            return bst.getHeight();
        }
        else if(obj instanceof AVLTree){
            avl = (AVLTree<T>) obj;
            return avl.getHeight();
        }
        else if(obj instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) obj;
            return rbt.getHeight();
        }
        else if(obj instanceof BinaryHeap){
            if(obj instanceof MinHeap)
                bheap = (MinHeap<T>) obj;
            else if(obj instanceof MaxHeap)
                bheap = (MaxHeap<T>) obj;
            return bheap.getHeight();
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    private int size(Object obj){
        if (obj instanceof BinarySearchTree) {
            bst = (BinarySearchTree<T>) obj;
            return bst.getSize();
        }
        else if(obj instanceof AVLTree){
            avl = (AVLTree<T>) obj;
            return avl.getSize();
        }
        else if(obj instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) obj;
            return rbt.getSize();
        }
        else if(obj instanceof BinaryHeap){
            if(obj instanceof MinHeap)
                bheap = (MinHeap<T>) obj;
            else if(obj instanceof MaxHeap)
                bheap = (MaxHeap<T>) obj;
            return bheap.getSize();
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<T> treeLeaves(Object obj){
        if (obj instanceof BinarySearchTree)
            return bst.getLeaves();

        else if(obj instanceof AVLTree)
            return avl.getLeaves();

        else if(obj instanceof RedBlackTree)
            return rbt.getLeaves();

        else if(obj instanceof BinaryHeap){
            if(obj instanceof MinHeap)
                bheap = (MinHeap<T>) obj;
            else if(obj instanceof MaxHeap)
                bheap = (MaxHeap<T>) obj;
            return bheap.getLeaves();
        }
        return null;
    }

    /**
     * Author: Group 2
     * insert all items in the array list into the passed in data structure.
     * @param list: Array List of items of any type.
     * @param obj: Passed in data structure.
     * @return data structure with items inserted.
     */
    @SuppressWarnings("unchecked")
    private Object insert(ArrayList<T> list, Object obj){
        if(obj instanceof BinarySearchTree){
            for(T i: list){
                bst.insert(i);
            }
        }
        else if (obj instanceof AVLTree){
            for(T i: list){
                avl.insert(i);
            }
        }
        else if (obj instanceof RedBlackTree){
            for(T i: list){
                rbt.insert(i);
            }
        }
		else if ( obj instanceof BinaryHeap){
            if(obj instanceof MinHeap){
                try{
                    ((MinHeap) obj).clear();
                    bheap = (MinHeap) obj;
                }catch(Exception e){
                    System.out.println(""+e);
                }
                for(T i: list){
                    bheap.insert(i);
                }
            }
            else if(obj instanceof MaxHeap){
                try{
                    ((MaxHeap) obj).clear();
                    bheap = (MaxHeap) obj;
                }catch(Exception e){
                    System.out.println(""+e);
                }
                for(T i: list){
                    bheap.insert(i);
                }
            }
        }
        return obj;
    }

    /**
     * Author: Group 2
     * This should be the insert for every data structure except graph and hashtables
     * @param b: number of items to be inserted
     * @param obj: data structure to which items will be implemented
     */
    @SuppressWarnings("unchecked")
    private void insert(int b, Object obj){  //b is the number of inserts
	/*
        Items to be inserted could be Strings, Characters or Numbers therefore the type of
        random items must be checked before being generated
       */
        /*
        Storing the generated inputs into an ArrayList makes easier to ensure that no duplicates are created
        by checking if the allInserts ArrayList contains the number.
         */
        for (int i=0; i<b; ++i) {
            if (type instanceof String) {
                int index = (int) (Math.random()*26);
                T str = (T) stringLib[index];
                if (!allInserts.contains(str)) {
                    allInserts.add(str);
                }
                else i--;
            }
            if (type instanceof Character){
                int index = (int) (Math.random()*26);
                Character c = charLib[index];
                T chr = (T) c;
                if (!allInserts.contains(chr)) {
                    allInserts.add(chr);
                }
                else i--;
            }
            else if (type instanceof Number) {
                Number h = Math.random()*100;
                if (type instanceof Integer)  //special case
                    h = (int) (Math.random()*100);
                else if (type instanceof Float) h = (float) (Math.random()*100);
                T num = (T) h;
                if (!allInserts.contains(num)) {
                    allInserts.add(num);
                }
                else i--;
            }
        }
        insert(allInserts, obj);
    }

    /**
     * Author: Group 2
     * @param item to be deleted.
     * @param obj: data structure from which item is supposed to ebe deleted
     */
    @SuppressWarnings("unchecked")
    private void delete(T item, Object obj){
		/*
        Each Object is checked to see what data structure it is into to called the appropriate method.
         */
        if(obj instanceof BinarySearchTree){
            bst.delete(item);
        }
        else if (obj instanceof AVLTree){
            avl.delete(item);
        }
        else if (obj instanceof RedBlackTree){
            rbt.delete(item);
        }
		else if (obj instanceof BinaryHeap){
            try{
                if(obj instanceof MinHeap){
                    bheap = (MinHeap) obj;
                    bheap.delete();
                }
                else if(obj instanceof MaxHeap){
                    bheap = (MaxHeap) obj;
                    bheap.delete();
                }
            }catch(Exception e){
                System.out.println("Error while deleting binary heap\n"+e);
            }
        }

        /*
         * Implement delete for other data structures
         */
    }

     /**
     * Author: Group 2
     * Generate Multiple Choice Questions about insertion or deletion for the specified data structure
     * @param total: number of questions in a pool to be created (i.e. pool size)
     * @param obj: data structure
     * @param fileName: output text file name
     * @param type: insertion, deletion
     */
    private void treeMCQ(int total, Object obj, String fileName, int type){
        fileName = createNewMCQFile(fileName);
        try {
            PrintWriter file = new PrintWriter(new FileWriter("MultipleChoice/" + fileName + ".txt", true));   //windows forward slash
            String[] traverse = {"preOrder", "postOrder", "inOrder", "levelOrder"};
            String[] option = {"A.  ", "B.  ", "C.  ", "D.  "};
            emptyTree(obj);
            insert((int)(Math.random()*3+6), obj);
            for (int i = 0; i < total; i++) {
                ArrayList<Integer> heightsize = new ArrayList<>(Arrays.asList(height(obj)-1, size(obj)-1, height(obj), size(obj), size(obj)+1, height(obj)+1));
                file.println("Question " + (i+1) + " (2 points)");
                int index = (int) (Math.random() * 4);

                //insertion
                if (type == 1) {
					/*
                    Generate insert MultipleChoice of the specified pool size for the given data structure
                     */
                    file.write("If the following " + allInserts + " is inserted inside a "+obj.toString()+
                            " then in which order will the nodes be visited during " + traverse[index] + " traversal?\n");
                    for (int j = 0; j < option.length; ++j) {
                        if (j == 0) {
                            printPreOrder(obj);
                        }else if (j == 1) {
                            printPostOrder(obj);
                        }else if (j == 2) {
                            printAllNodes(obj);
                        }else {
                            printLevelOrder(obj);
                        }
                        if (j == index) {
                            file.print("*");
                        }
                        file.print(option[j] + travel.toString() + "\n");
                    }
                }

                //deletion
                else if(type == 2){
					/*
                    Generate delete MultipleChoice of the specified pool size for the given data structure
                     */
                    int randomElement = (int)(Math.random()*allInserts.size());
                    file.write("If we delete " + allInserts.get(randomElement) + " from a "+obj.toString()+" containing " +allInserts +
                            ", then the new tree in " + traverse[index] + " traversal will be:\n");
                    delete(allInserts.get(randomElement), obj);
                    for (int j = 0; j < option.length; ++j) {
                        if (j == 0)
                            printPreOrder(obj);
                        else if (j == 1)
                            printPostOrder(obj);
                        else if (j == 2)
                            printAllNodes(obj);
                        else
                            printLevelOrder(obj);
                        if (j == index)
                            file.print("*");
                        file.print(option[j] + travel.toString() + "\n");
                    }
                }

                //root
                else if (type == 3){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the root is:\n");
                    printLevelOrder(obj); T answer = travel.get(0); allInserts.remove(answer);
                    for (int j = 0; j < option.length; ++j) {
                        if (j == index)
                            file.print("*"+option[j] + answer + "\n");
                        else{
                            int rand = (int) (Math.random() * allInserts.size());
                            T wrong = allInserts.remove(rand);
                            file.print(option[j] + wrong + "\n");
                        }
                    }
                }

                //leaf
                else if (type == 4){
                    file.write("The following " + allInserts + " is inserted into a "+obj.toString()+
                            ". Which of the following is a leaf  is:\n");
                    ArrayList<T> leafNodes = treeLeaves(obj);
                    for (T leaf : leafNodes)
                        allInserts.remove(leaf);
                    T randAns = leafNodes.remove((int)(Math.random()*leafNodes.size()));
                    for (int j = 0; j < option.length; ++j) {
                        if (j == index)
                            file.print("*"+option[j] + randAns + "\n");
                        else{
                            int rand = (int) (Math.random() * allInserts.size());
                            T wrong = allInserts.remove(rand);
                            file.print(option[j] + wrong + "\n");
                        }
                    }
                }

                //height
                else if (type == 5){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the height of the tree is:\n");
                    heightsize.remove(2);
                    for (int j = 0; j < option.length; ++j) {
                        if (j == index)
                            file.print("*"+option[j] + height(obj) + "\n");
                        else{
                            int rand = (int) (Math.random() * heightsize.size());
                            file.print(option[j] + heightsize.remove(rand) + "\n");
                        }
                    }
                }

                //size
                else if (type == 6){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the size of the tree is:\n");
                    heightsize.remove(3);
                    for (int j = 0; j < option.length; ++j) {
                        if (j == index)
                            file.print("*"+option[j] + size(obj) + "\n");
                        else{
                            int rand = (int) (Math.random() * heightsize.size());
                            file.print(option[j] + heightsize.remove(rand) + "\n");
                        }
                    }
                }
                emptyTree(obj);
                insert((int)(Math.random()*3+6), obj);
                file.println("E.  None of the answers are correct.\n#randomize\n");
                file.flush();
            }
            System.out.println("Done!\n==========================================\n");
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Try Again.");
            File label = new File("MultipleChoice/", fileName + ".txt");
            label.delete();
        }
    }

    /**
     *
     * @param obj Tree
     */
    @SuppressWarnings("unchecked")
    private void emptyTree(Object obj){
        if (obj instanceof BinarySearchTree)
            bst.clearTree();
        else if(obj instanceof AVLTree)
            avl.clearTree();
        else if(obj instanceof RedBlackTree)
            rbt.clearTree();
        else if(obj instanceof BinaryHeap){
            if (obj instanceof MaxHeap)
                bheap = (MaxHeap<T>) obj;
            else if (obj instanceof MinHeap)
                bheap = (MinHeap<T>) obj;
            bheap.clearTree();
        }
        allInserts.clear();
    }

    private void treeTrueFalse(int total, Object obj, String fileName, int type){
        fileName = createNewToFFile(fileName);
        try {
            PrintWriter file = new PrintWriter(new FileWriter("TrueFalse/" + fileName + ".txt", true));   //windows forward slash
            emptyTree(obj);
            String[] traversal = {"pre-order", "post-order"};
            insert((int)(Math.random()*3+6), obj);
            for (int i = 0; i < total; i++) {
                file.println("Question " + (i+1) + " (2 points)");
                int TorF = (int) (Math.random() * 2);
                int[] sh = {height(obj)-1, size(obj)-1, height(obj), size(obj), size(obj)+1, height(obj)+1};
                int randi = (int)(Math.random()*6);
                if (TorF == 0)
                    printAllNodes(obj);
                else printLevelOrder(obj);
                if (type == 1) {
                    int traversalArrPos = (int)(Math.random()*2);
                    if (TorF == 0) printPreOrder(obj);
                    else printPostOrder(obj);
                    file.write("If the following " + allInserts + " is inserted inside a "+obj.toString()+
                            " then the " + traversal[traversalArrPos] + " traversal will be "+travel+"\n");
                    if (TorF == traversalArrPos){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{file.print("True\n");
                        file.println("*False\n");}
                }
                else if (type == 2) {
                    int traversalArrPos = (int)(Math.random()*2);
                    int randomElement = (int)(Math.random()*allInserts.size());
                    delete(allInserts.get(randomElement), obj);
                    if (TorF == 0) printPreOrder(obj);
                    else printPostOrder(obj);
                    file.write("If we delete " + allInserts.get(randomElement) + " from a "+obj.toString()+" containing " +allInserts +
                        ", then the new tree in " + traversal[traversalArrPos] + " traversal is: "+ travel +"\n");
                    if (TorF == traversalArrPos){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{file.print("True\n");
                        file.println("*False\n");}
                }
                else if (type == 3) {
					/*
                    Generate insert TrueFalse of the specified pool size for the given data structure
                     */
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the root is " + travel.get(0) + ".\n");
                    if (TorF==0){
                        file.print("True\n");
                        file.println("*False\n");
                    }
                    else {
                        file.print("*True\n");
                        file.println("False\n");
                    }
                }
                else if(type == 4){
					/*
                    Generate delete MultipleChoice of the specified pool size for the given data structure
                     */
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then " + travel.get(0) + " is a leaf.\n");
                    if (TorF==1){
                        file.print("True\n");
                        file.println("*False\n");
                    }
                    else {
                        file.print("*True\n");
                        file.println("False\n");
                    }
                }
                else if (type==5){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the height of the tree is " + sh[randi] + ".\n");
                    if (sh[randi] == height(obj)){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{
                        file.print("True\n");
                        file.println("*False\n");
                    }
                }
                else if (type==6){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the height of the tree is " + sh[randi] + ".\n");
                    if (sh[randi] == size(obj)){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{
                        file.print("True\n");
                        file.println("*False\n");
                    }
                }
                emptyTree(obj);
                insert((int)(Math.random()*3+6), obj);
                file.flush();
            }
            System.out.println("Done!\n=======================================\n");
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Try Again.");
            File label = new File("TrueFalse/", fileName + ".txt");
            label.delete();
        }
    }

    private void graphPool(int format, int type, String name, int path_or_cost){
        ArrayList<String> otherNodes;
        if (type == 1){
            digraph.emptyGraphMap();
            digraph.insert("v0","v1", Graph.infinity);
            digraph.insert("v0","v3", Graph.infinity);
            digraph.insert("v1","v3", Graph.infinity);
            digraph.insert("v1","v4", Graph.infinity);
            digraph.insert("v2","v0", Graph.infinity);
            digraph.insert("v2","v5", Graph.infinity);
            digraph.insert("v3", "v2", Graph.infinity);
            digraph.insert("v3", "v4", Graph.infinity);
            digraph.insert("v3","v5", Graph.infinity);
            digraph.insert("v3","v6", Graph.infinity);
            digraph.insert("v4","v6", Graph.infinity);
            digraph.insert("v6","v5", Graph.infinity);
            otherNodes = new ArrayList<>(Arrays.asList("v0", "v1", "v3", "v4", "v5"));
            digraph.type = "unweighted";
            if (format == 1)
                graphMCQ(path_or_cost, name, digraph, otherNodes);
            //else if (format  == 2)
            //else if (format == 3)
        }
        else if(type == 2){
            digraph.emptyGraphMap();
            digraph.insert("Algeria", "Belgium", 7.0);
            digraph.insert("Algeria", "Chile", 3.0);
            digraph.insert("Algeria", "Denmark", 4.0);
            digraph.insert("Belgium", "Greece", 3.0);
            digraph.insert("Chile", "France", 4.0);
            digraph.insert("Denmark", "France", 2.0);
            digraph.insert("Denmark", "Ethiopia", 7.0);
            digraph.insert("France", "Greece", 5.0);
            digraph.insert("Ethiopia", "Greece", 2.0);
            otherNodes = new ArrayList<>(Arrays.asList("Belgium", "Chile", "Greece", "Denmark", "Ethiopia"));
            digraph.type = "weighted";
            if (format == 1)
                graphMCQ(path_or_cost, name, digraph, otherNodes);
            //else if (format  == 2)
            //else if (format == 3)
        }
        else if(type == 3){
            digraph.emptyGraphMap();
            digraph.insert("Jan", "Feb", -5.00);
            digraph.insert("Jan", "Apr", 7.00);
            digraph.insert("Jan", "May", 2.00);
            digraph.insert("Feb", "Mar", 5.00);
            digraph.insert("Mar", "Jan", 1.00);
            digraph.insert("Mar", "Apr", -4.00);
            digraph.insert("Apr", "May", 3.00);
            digraph.insert("May", "Mar", 1.00);
            digraph.insert("May", "Feb", 6.00);
            otherNodes = new ArrayList<>(Arrays.asList("Feb", "Mar", "May"));
            digraph.type = "negative";
            if (format == 1)
                graphMCQ(path_or_cost, name, digraph, otherNodes);
            //else if (format  == 2)
            //else if (format == 3)
        }
        else if(type == 4){
            digraph.emptyGraphMap();
            digraph.insert("A", "B", 4);
            digraph.insert("A", "C", 8);
            digraph.insert("B", "C", 9);
            digraph.insert("B", "D", 10);
            digraph.insert("C", "D", 7);
            otherNodes = new ArrayList<>(Arrays.asList("B", "C"));
            digraph.type = "acyclic";
            if (format == 1)
                graphMCQ(path_or_cost, name, digraph, otherNodes);
            //else if (format  == 2)
            //else if (format == 3)
        }
        else if(type == 5){
            graphPool(format, 1, name, path_or_cost);
            graphPool(format, 2, name, path_or_cost);
            graphPool(format, 3, name, path_or_cost);
            graphPool(format, 4, name, path_or_cost);
        }
        else{
            System.out.println("Enter a valid Question Format from the Menu.");
        }
    }

    private String createNewMCQFile(String textFile){
        try {
            File label = new File("MultipleChoice/", textFile + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(textFile + " has been created");
            else{
                System.out.println(textFile + " already exists.");
                String dataStruc = textFile.substring(0, textFile.indexOf("/"));
                String dir = "MultipleChoice/"+ dataStruc;
                textFile = dataStruc + "/" + changeFileName(dir, textFile.substring(textFile.indexOf("/")+1));
                label = new File("MultipleChoice/", textFile + ".txt");
                label.createNewFile();
                System.out.println(textFile.substring(textFile.indexOf("/")+1) + " has been created instead.");
            }
            return textFile;
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
        return textFile;
    }
    private String createNewFillInFile(String textFile){
        try {
            File label = new File("MultipleChoice/", textFile + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(textFile + " has been created");
            else{
                System.out.println(textFile + " already exists.");
                String dataStruc = textFile.substring(0, textFile.indexOf("/"));
                String dir = "MultipleChoice/"+ dataStruc;
                textFile = dataStruc + "/" + changeFileName(dir, textFile.substring(textFile.indexOf("/")+1));
                label = new File("MultipleChoice/", textFile + ".txt");
                label.createNewFile();
                System.out.println(textFile.substring(textFile.indexOf("/")+1) + " has been created instead.");
            }
            return textFile;
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
        return textFile;
    }

    private String createNewToFFile(String textFile){
        try {
            File label = new File("TrueFalse/", textFile + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(textFile + " has been created");
            else{
                System.out.println(textFile + " already exists.");
                String dataStruc = textFile.substring(0, textFile.indexOf("/"));
                String dir = "TrueFalse/"+dataStruc;
                textFile = dataStruc + "/" +changeFileName(dir, textFile);
                label = new File("TrueFalse/", textFile + ".txt");
                label.createNewFile();
                System.out.println(textFile + " has been created instead.");
            }
            return textFile;
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
        return textFile;
    }

    private void graphMCQ(int poc, String fname, Graph dg, ArrayList<String> others) {
        fname = createNewMCQFile(fname);
        try {
            PrintWriter file = new PrintWriter(new FileWriter("MultipleChoice/"+fname+".txt", true));   //windows forward slash
            String[] option = {"A.  ", "B.  ", "C.  ", "D.  "};
            int index = Math.min(others.size(), (int) (Math.random()*4));
            if (poc == 1){
                if (dg.type.equals("unweighted")) dg.bfs(dg.startPoint());
                else if (dg.type.equals("weighted")) dg.dijkstra(dg.startPoint());
                else if (dg.type.equals("negative")) dg.bellmanFord(dg.startPoint());
                else if (dg.type.equals("acyclic")) dg.topological(dg.startPoint());
                file.write("What is the cost of the path, from "+dg.startPoint()+" to "+dg.endPoint()+", in the displayed graph?\n");
                int minIterations = Math.min(others.size()+1, option.length);
                for (int i=0; i<minIterations; i++){
                    int randInt = (int)(Math.random()*others.size());
                    //System.out.println("RANDINT = "+randInt+" & ITERATOR IS "+i+" + ARRAY SIZE: "+others.size());
                    if (i == index)
                        file.println("*"+option[i]+dg.getNodeValue(dg.endPoint()));
                    else
                        file.println(option[i]+dg.getNodeValue(others.remove(randInt)));
                }
            }
            else if (poc == 2){
                file.write("What is the shortest path (using the most suitable algorithm) from "+dg.startPoint()+" to "+dg.endPoint()+" in the graph show?\n");
                for (int i=0; i<others.size()+1; i++){
                    //Wont work. need new strategy
                    //consider negative edges or graphs with cycles
                    /*if (i==0)
                        dg.bfs(dg.startPoint());
                    else if (i==1)
                        dg.dijkstra(dg.startPoint());
                    else if (i==2)
                        dg.bellmanFord(dg.startPoint());
                    else if (i==3)
                        dg.topological(dg.startPoint());
                    if (i == index)
                        file.print("*");
                    file.println(option[i]+ dg.printPath(dg.endPoint()));*/
                }
            }
            file.println("#randomize\n");
            file.flush();
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Try Again.");
            File label = new File("MultipleChoice/", fname + ".txt");
            label.delete();
        }
    }

    private static void printchoice(int a,int b , int c, int d, int e, int f){
        int dataStructure = a, format = b;
        switch (dataStructure) {
            case 0:
                break;
            case 1:
                System.out.println("1. BinarySearchTree");
                break;
            case 2:
                System.out.println("1. AVLTree");
                break;
            case 3:
                System.out.println("1. RedBlackTree");
                break;
            case 4:
                System.out.print("1. Binary ");
                int minOrMax = f;
                if (minOrMax == 1) {
                    System.out.println("MinHeap");
                } else {
                    System.out.println("MaxHeap");
                }
                break;
            case 5:
                System.out.println("1. Graph");
                break;
            case 6:
                System.out.println("2. HashTable");
                break;
        }
        switch(format){
            case 1:
                System.out.println("2. MCQ");
                break;

            case 2:
                System.out.println("2. True/False");
                break;

            case 3:
                System.out.println("2. Fill In Numeric");
                break;
        }

        if (dataStructure < 5) {
            int type = c, poolSize = d, dataStored = e;
            switch (type) {
                case 0:
                    break;
                case 1:
                    System.out.println("3. Insertion");
                    break;
                case 2:
                    System.out.println("3. Deletion");
                    break;
                case 3:
                    System.out.println("3. Root");
                    break;
                case 4:
                    System.out.println("3. Leaf");
                    break;
                case 5:
                    System.out.println("3. Height");
                    break;
                case 6:
                    System.out.println("3. Size");
                    break;
            }
            System.out.println("4. PoolSize: " + poolSize);
            switch (dataStored) {
                case 0:
                    break;
                case 1:
                    System.out.println("5. Numbers");
                    break;
                case 2:
                    System.out.println("5. Words");
                    break;
                case 3:
                    System.out.println("5. Decimals");

                    break;
                case 4:
                    System.out.println("5. Letters");
                    break;
            }
        }
        else if (dataStructure == 5){
            int graphType = c, costOrPath = d;
            switch (graphType) {
                case 0:
                    break;
                case 1:
                    System.out.println("3. UnweightedDigraph");
                    break;
                case 2:
                    System.out.println("3. WeightedDigraph");
                    break;
                case 3:
                    System.out.println("3. NegativeDigraph");
                    break;
                case 4:
                    System.out.println("3. AcyclicDigraph");
                    break;
                case 5:
                    System.out.println("3. EachDigraph");
                    break;
            }
            if (costOrPath == 1)
                System.out.println("4. Path Cost");
            else System.out.println("4. Path Followed");
        }
        else if (dataStructure == 6){
            int hashType = c, poolSize = d, resolution = e;
            if (format == 1) {
                if (hashType == 1)
                    System.out.println("3. Insertion");
                else
                    System.out.println("3. Collisions");
                if (resolution==1) System.out.println("4. Linear Probing");
                else if (resolution==2) System.out.println("4. Quadratic Probing");
                else System.out.println("4. Chaining");
                System.out.println("5. PoolSize: " + poolSize);
            }
            else if (format == 3) {
                if (resolution==1) System.out.println("3. Linear Probing");
                else if (resolution==2) System.out.println("3. Quadratic Probing");
                else System.out.println("3. Chaining");
                System.out.println("4. PoolSize: " + poolSize);
            }
            else System.out.println("3. PoolSize: " + poolSize);
        }
    }

    private static void theone(int a,int b , int c, int d, int e, int f) {
        Driver<Integer> intTree = new Driver<Integer>(0);
        Driver<String> strTree = new Driver<String>("");
        Driver<Float> fltTree = new Driver<Float>((float) 0.0);
        Driver<Character> chrTree = new Driver<Character>(' ');
        String filename = "";
        int dataStructure = a, format = b;
        if (dataStructure < 5) {
            int type = c, poolSize = d, dataStored = e;
            switch (dataStructure) {
                case 0:
                    break;
                case 1:
                    filename = "BinarySearchTree/";
                    break;
                case 2:
                    filename = "AVLTree/";
                    break;
                case 3:
                    filename = "RedBlackTree/";
                    break;
                case 4:
                    filename = "BinaryHeap/";
                    int minOrMax = f;
                    if (minOrMax == 1) {
                        heapType = 0;
                        filename += "MinHeap/";
                    } else {
                        heapType = 1;
                        filename += "MaxHeap/";
                    }
                    break;
            }
            switch (type) {
                case 0:
                    break;
                case 1:
                    filename += "Insertion_";
                    break;
                case 2:
                    filename += "Deletion_";
                    break;
                case 3:
                    filename += "Root_";
                    break;
                case 4:
                    filename += "Leaf_";
                    break;
                case 5:
                    filename += "Height_";
                    break;
                case 6:
                    filename += "Size_";
                    break;
            }
            switch (dataStored) {
                case 0:
                    break;
                case 1:
                    filename += "Numbers_" + poolSize + "Qs";
                    intTree.poolType(format, type, poolSize, filename);
                    break;
                case 2:
                    filename += "Words_" + poolSize + "Qs";
                    strTree.poolType(format, type, poolSize, filename);
                    break;
                case 3:
                    filename += "Decimals_" + poolSize + "Qs";

                    fltTree.poolType(format, type, poolSize, filename);
                    break;
                case 4:
                    filename += "Letters_" + poolSize + "Qs";
                    chrTree.poolType(format, type, poolSize, filename);
                    break;
            }
        }
        else if (dataStructure == 5){
            filename = "Graph/";
            int graphType = c, costOrPath = d;
            switch (graphType) {
                case 0:
                    break;
                case 1:
                    filename += "UnweightedDigraph";
                    intTree.graphPool(format, graphType, filename, costOrPath);
                    break;
                case 2:
                    filename += "WeightedDigraph";
                    intTree.graphPool(format, graphType, filename, costOrPath);
                    break;
                case 3:
                    filename += "NegativeDigraph";
                    intTree.graphPool(format, graphType, filename, costOrPath);
                    break;
                case 4:
                    filename += "AcyclicDigraph";
                    intTree.graphPool(format, graphType, filename, costOrPath);
                    break;
                case 5:
                    filename += "EachDigraph";
                    intTree.graphPool(format, graphType, filename, costOrPath);
                    break;
            }
            System.out.println("Done!\n======================================\n");
        }
        else if (dataStructure == 6){
            filename = "HashTable/";
            int hashType = c, poolSize = d, resolution = e;
            if (format == 1) {
                if (hashType == 1)
                    filename += "Insertion";
                else
                    filename += "Collisions";
            }
            else if (format == 2) {
                resolution = 2;
                hashType = 2;
            } else hashType = 3;
            if (format != 2) {
                filename += "_" + resolutionSchemes[resolution - 1];
                filename += "_" + poolSize + "Qs";
            } else filename += "Loadfactor_" + poolSize + "Qs";
            switch (format){
                case 1:
                    filename = intTree.createNewMCQFile(filename);
                    break;
                case 2:
                    filename = intTree.createNewToFFile(filename);
                    break;
                case 3:
                    filename = intTree.createNewFillInFile(filename);
                    break;
            }
            new HashTableDriver(poolSize, format, resolution, hashType, filename);
            System.out.println("Done!\n==========================================\n");
        }
    }

    public static void main (String [] args) {
        Scanner in = new Scanner(System.in);
        int choice=0,format=0, dataStructure=0;

        do{
            try {
                int graphType = 0, type = 0, poolSize = 0, hashType = 0;
                int costOrPath = 0, dataStored = 0, minOrMax = 0, resolution = 0;
                System.out.println("Enter a number to select.\n" +
                        "1. New Question Pool\n" +
                        "2. Choose from textFile\n" +
                        "3. Exit");
                choice = in.nextInt();

                if (choice == 1) {
                    System.out.println("\nChoose a Data Structure (Enter a number):\n" +
                            "1. Binary Search Tree\n" +
                            "2. AVL Tree\n" +
                            "3. Red Black Tree\n" +
                            "4. Binary Heap\n" +
                            "5. Graph\n" +
                            "6. Hash Table");
                    dataStructure = in.nextInt();

                    System.out.println("\nChoose Question format. (Enter a number)\n" +
                            "1. MCQ\n" +
                            "2. True/False\n" +
                            "3. Fill In Numeric");
                    format = in.nextInt();

                    if (dataStructure == 5) {
                        System.out.println("Which directed graph type (which algorithm) would you like?\n" +
                                "1. Unweighted (Breadth First Search)\n" +
                                "2. Weighted (Dijkstra)\n" +
                                "3. Negative Weights (Bellman-Ford)\n" +
                                "4. Acyclic (Topological Sort)\n" +
                                "5. All of the above\n");
                        graphType = in.nextInt();
                        System.out.println("Do you want questions on...\n" +
                                "1. Path Cost\n" +
                                "2. Path Sequence\n");
                        costOrPath = in.nextInt();
                        theone(dataStructure,format,graphType,costOrPath,0,0);
                    } else if (dataStructure == 6) {
                        if (format == 1) {
                            System.out.println("Select one:\n1. Insertion\n2. Collisions");
                            hashType = in.nextInt();
                            System.out.println("choose resolution scheme\n1. Linear Probing\n2. Quadratic Probing\n3. Chaining");
                            resolution = in.nextInt();
                            System.out.println("Enter number of questions (pool size).");
                            poolSize = in.nextInt();
                        } else if (format == 2) {
                            System.out.println("Enter number of questions (pool size).");
                            poolSize = in.nextInt();
                        } else {
                            System.out.println("choose resolution scheme\n1. Linear Probing\n2. Quadratic Probing\n3. Chaining");
                            resolution = in.nextInt();
                            System.out.println("Enter number of questions (pool size).");
                            poolSize = in.nextInt();
                        }
                        theone(dataStructure,format,hashType, poolSize, resolution,0);
                    } else {
                        if (dataStructure == 4) {
                            System.out.println("Enter a number:\n1. Binary Min Heap\n2. Binary Max Heap");
                            minOrMax = in.nextInt();
                        }
                        System.out.println("Choose Question. (Enter a number)\n" +
                                "1. Insertion - Traversal\n" +
                                "2. Deletion - Traversal\n" +
                                "3. Question about the root\n" +
                                "4. Question about the leaf\n" +
                                "5. Question about the tree height\n" +
                                "6. Question about the tree size");
                        type = in.nextInt();

                        System.out.println("Enter number of questions (pool size).");
                        poolSize = in.nextInt();

                        System.out.println("Select the type of data you wish to store:\n" +
                                "1. Integer\n" +
                                "2. String\n" +
                                "3. Float\n" +
                                "4. Char\n");
                        dataStored = in.nextInt();
                        theone(dataStructure,format,type,poolSize,dataStored,minOrMax);
                    }
                } else if (choice == 2) {
                    System.out.println();
                    System.out.println("Enter filename");
                    in.nextLine();
                    System.out.println(System.getProperty("user.dir")); // to print the default directory so you know where to place the file
                    String file_name = in.nextLine();
                    System.out.println();

                    try {
                        Scanner file = new Scanner(new File(file_name));
                        dataStructure = file.nextInt();
                        format = file.nextInt();
                        if (dataStructure == 5) {
                            graphType = file.nextInt();
                            costOrPath = file.nextInt();
                            printchoice(dataStructure,format,graphType,costOrPath,0,0);
                        } else if (dataStructure == 6) {
                            if (format == 1) {
                                hashType = file.nextInt();
                                resolution = file.nextInt();
                                poolSize = file.nextInt();
                            } else if (format == 2) {
                                poolSize = file.nextInt();
                            } else {
                                resolution = file.nextInt();
                                poolSize = file.nextInt();
                            }
                            printchoice(dataStructure,format,hashType, poolSize, resolution,0);
                        } else {
                            if (dataStructure == 4) minOrMax = file.nextInt();
                            type = file.nextInt();
                            poolSize = file.nextInt();
                            dataStored = file.nextInt();
                            printchoice(dataStructure,format,type,poolSize,dataStored,minOrMax);
                        }

                        System.out.println("enter yes to continue or no to change the values entered");
                        String confirmation = in.nextLine();
                        if (confirmation.equals("yes")||confirmation.equals("Yes")||confirmation.equals("YES")||confirmation.equals("y")||confirmation.equals("Y")){
                            if (dataStructure==5)// to print the graph
                                theone(dataStructure,format,graphType,costOrPath,0,0);
                            else if(dataStructure==6) // to print the hash table
                                theone(dataStructure,format,hashType, poolSize, resolution,0);
                            else
                                theone(dataStructure,format,type,poolSize,dataStored,minOrMax);// sends the file data to a folder to then be created need to make a nother method that just prints out the methodd
                        }
                        else if(confirmation.equals("no")||confirmation.equals("No")||confirmation.equals("NO")||confirmation.equals("n")||confirmation.equals("N")){
                            System.out.println();
                            continue;
                        }
                        else{
                            System.out.println("you have entered an invalid argument try yes or no only");
                        }
                        file.close();
                    }

                    catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                    }
                } else if (choice == 3) break;
                else {
                    System.out.println("Invalid choice, Choose again");
                    continue;
                }
            }catch (InputMismatchException e){
                System.out.println("Enter a valid number from the menu!");
                main(new String[]{});
                return;
            }
        }
        while (choice != 3);
        System.out.println("\n!!!BYE!!!\n");
        in.close();
    }




    /**
     * Creates a new file name if a duplicate is detected
     * @param filename: duplicate filename
     * @return new filename
     */
    private String changeFileName (String dir, String filename){
        File folder = new File(dir);
        File[] files = folder.listFiles();
        String old; int num=0;
        for (File file: files){
            old = file.getName();
            if(old.contains(filename) && old.contains("(")){
                int temp = Integer.parseInt(old.substring(old.indexOf("(")+1, old.indexOf(")")));
                if (num<temp)
                    num = temp;
            }
        }
        filename += " (" + (++num) + ")";
        return filename;
    }
}
