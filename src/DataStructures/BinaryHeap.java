package DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public abstract class BinaryHeap<T extends Comparable<? super T>> extends BinaryTree<T>{

    Vector<BinaryHeapNode<T>> heap; // vector to store heap elements
    private ArrayList<T> path = new ArrayList<>();

    class BinaryHeapNode<T>{
        T item;

        BinaryHeapNode(T item){
            this.item = item;
        }

        @Override
        public String toString(){
            return item.toString();
        }

    }

    /**
     * @param i item position
     * @return parent of item at specified index
     */
    int parent(int i){
        // if i is already a root node
        if (i == 0)
            return 0;

        return (i - 1) / 2;
    }

    /**
     * @param index element position
     * @return left child of item at index
     */
    int leftChild(int index){
        return (2 * index + 1);
    }

    /**
     * @param index element position
     * @return right child of item at index
     */
    int rightChild(int index){
        return (2 * index + 2);
    }

    /**
     * swap values at two indexes
     * @param x index
     * @param y index
     */
    void swap(int x, int y){
        // swap with child having greater value
        BinaryHeapNode<T> temp = heap.get(x);
        heap.setElementAt(heap.get(y), x);
        heap.setElementAt(temp, y);
    }

    /**
     * @return size of the heap
     */
    int size() { return heap.size(); }

    abstract void heapifyDown(int i);

    abstract void heapifyUp(int i);

    public void insert(T item){
        insert(new BinaryHeapNode<>(item));
    }
    /**
     * insert specified key into the heap
     * @param key to be inserted
     */
    private void insert(BinaryHeapNode<T> key){
        // insert the new element to the end of the vector
        heap.addElement(key);

        // get element index and call heapify-up procedure
        int index = size() - 1;
        heapifyUp(index);
    }

    /**
     * function to remove and return element with highest priority
     * (present at root). It returns null if queue is empty
     * @return removed item
     * @throws Exception if null is found
     */
    public BinaryHeapNode<T> delete() throws Exception {
        // if heap is empty, throw an exception
        if (size() == 0) {
            throw new Exception("Index is out of range (Heap underflow)");
        }

        // element with highest priority
        BinaryHeapNode<T> root = heap.firstElement();	// or heap.get(0);

        // replace the root of the heap with the last element of the vector
        heap.setElementAt(heap.lastElement(), 0);
        heap.remove(size()-1);

        // call heapify-down on root node
        heapifyDown(0);

        // return root element
        return root;
    }

    /**
     * function to return, but does not remove, element with highest priority
     * (present at root). It returns null if queue is empty
     * @return item
     * @throws Exception if null is found
     */
    private BinaryHeapNode<T> peek() throws Exception {
        // if heap has no elements, throw an exception
        if (size() == 0) {
            throw new Exception("Index out of range (Heap underflow)");
        }

        // else return the top (first) element
        return heap.firstElement();	// or heap.get(0);
    }

    /**
     * function to remove all elements from priority queue
     * @throws Exception if found null
     */
    public void clear()throws Exception {
        System.out.print("Emptying queue: ");
        while (!heap.isEmpty()) {
            System.out.print(delete() + " ");
        }
        System.out.println();
    }

    /**
     * Add node to ArrayList
     * @param node: node to be added
     */
    private void visit(BinaryHeapNode<T> node) {
        path.add(node.item);
    }

    /**
     * @return ArrayList of node keys in preOrder
     */
    public ArrayList<T> preOrder() {
        path.clear();
        try{
            preOrder (peek());
        }catch(Exception e){
            System.out.println("Pre Order error" + e);
        }
        return path;
    }
    /**
     * Get the preOrder formation of nodes recursively
     * @param node: root node
     */
    private void preOrder(BinaryHeapNode<T> node)
    {

        if (node != null)
        {
            visit (node);
            BinaryHeapNode<T> lnode;
            if(leftChild(heap.indexOf(node)) >= heap.size()){
                lnode = null;
            }else{
                lnode = heap.get(leftChild(heap.indexOf(node)));
            }
            preOrder (lnode);

            if(rightChild(heap.indexOf(node)) >= heap.size()){
                lnode = null;
            }else{
                lnode = heap.get(rightChild(heap.indexOf(node)));
            }
            preOrder (lnode);
        }
    }

    /**
     * @return ArrayList of node keys in postOrder
     */
    public ArrayList<T> postOrder()
    {
        path.clear();
        try{
            postOrder (peek());
        }catch(Exception e){
            System.out.println("Error in Binary Heap post order\n"+e);
        }
        return path;
    }
    /**
     * Get the postOrder formation of nodes recursively
     * @param node: root node
     */
    private void postOrder(BinaryHeapNode<T> node)
    {
        if (node != null)
        {
            BinaryHeapNode<T> lnode;
            if(leftChild(heap.indexOf(node)) >= heap.size()){
                lnode = null;
            }else{
                lnode = heap.get(leftChild(heap.indexOf(node)));
            }
            postOrder (lnode);

            if(rightChild(heap.indexOf(node)) >= heap.size()){
                lnode = null;
            }else{
                lnode = heap.get(rightChild(heap.indexOf(node)));
            }
            postOrder (lnode);
            visit (node);
        }
    }

    /**
     * @return ArrayList of node keys in inOrder
     */
    public ArrayList<T> inOrder ()
    {
        path.clear();
        try{
            inOrder (peek());
        }catch(Exception e){
            System.out.println("Error in Binary Heap pre order\n"+e);
        }
        return path;
    }
    /**
     * Get the inOrder formation of nodes recursively
     * @param node: root node
     */
    private void inOrder(BinaryHeapNode<T> node)
    {
        if (node != null)
        {
            BinaryHeapNode<T> lnode;
            if(leftChild(heap.indexOf(node)) >= heap.size()){
                lnode = null;
            }else{
                lnode = heap.get(leftChild(heap.indexOf(node)));
            }
            inOrder (lnode);

            visit (node);

            if(rightChild(heap.indexOf(node)) >= heap.size()){
                lnode = null;
            }else{
                lnode = heap.get(rightChild(heap.indexOf(node)));
            }
            inOrder (lnode);
        }
    }

    /**
     * @return ArrayList of node keys in levelOrder
     */
    public ArrayList<T> levelOrder(){
        path.clear();
        try{
            levelOrder (peek());
        }catch(Exception e){
            System.out.println("Error in Binary Heap pre order\n"+e);
        }
        return path;
    }
    /**
     * Get the levelOrder formation of nodes recursively
     * @param startNode: root node
     */
    private ArrayList<T> levelOrder (BinaryHeapNode<T> startNode)
    {
        path.clear();
        Queue<BinaryHeapNode<T>> queue=new LinkedList<>();
        queue.add(startNode);
        while(!queue.isEmpty())
        {
            BinaryHeapNode<T> tempNode=queue.poll();

            BinaryHeapNode<T> lnode;
            if(leftChild(heap.indexOf(tempNode)) >= heap.size()){
                lnode = null;
            }else{
                lnode = heap.get(leftChild(heap.indexOf(tempNode)));
            }

            BinaryHeapNode<T> rnode;
            if(rightChild(heap.indexOf(tempNode)) >= heap.size()){
                rnode = null;
            }else{
                rnode = heap.get(rightChild(heap.indexOf(tempNode)));
            }

            path.add(tempNode.item);
            if(lnode!=null)
                queue.add(lnode);
            if(rnode!=null)
                queue.add(rnode);
        }
        return path;
    }

}
