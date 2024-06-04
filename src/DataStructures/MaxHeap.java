package DataStructures;

import java.util.Vector;

/**
 * Avhusaho Ramalala
 * Class for implementing max Priority Queue
 */
public class MaxHeap<T extends Comparable<? super T>> extends BinaryHeap<T>{

    /**
     * Constructor: use default initial capacity of vector
     */
    public MaxHeap(){
        heap = new Vector<>();
    }

    /**
     * constructor: set custom initial capacity for vector
     * @param capacity: initial capacity of queue
     */
    MaxHeap(int capacity) {
        heap = new Vector<>(capacity);
    }

    /**
     * Recursive Heapify-down procedure. Here the node at index i
     * and its two direct children violates the heap property
     * @param i element
     */
    void heapifyDown(int i){
        // get left and right child of node at index i
        int left = leftChild(i);
        int right = rightChild(i);

        int largest = i;

        // compare heap.get(i) with its left and right child
        // and find largest value
        if (left < size() && heap.get(left).item.compareTo(heap.get(i).item) > 0) {
            largest = left;
        }

        if (right < size() && heap.get(right).item.compareTo(heap.get(largest).item) > 0) {
            largest = right;
        }

        if (largest != i)
        {
            // swap with child having greater value
            swap(i, largest);

            // call heapify-down on the child
            heapifyDown(largest);
        }
    }

    /**
     * Recursive Heapify-up procedure
     * @param i item
     */
    void heapifyUp(int i){
        // check if node at index i and its parent violates
        // the heap property
        if (i > 0 && heap.get(parent(i)).item.compareTo(heap.get(i).item) < 0)
        {
            // swap the two if heap property is violated
            swap(i, parent(i));

            // call Heapify-up on the parent
            heapifyUp(parent(i));
        }
    }

    /**
     * @return Name of tree
     */
    @Override
    public String toString() {
        return "Max Heap";
    }
}