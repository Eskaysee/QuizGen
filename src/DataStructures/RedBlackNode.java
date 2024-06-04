package DataStructures;

/**
 */ // class RedBlackNode
class RedBlackNode<T extends Comparable<? super T>> {

    /** Possible color for this node */
    static final int BLACK = 0;
    /** Possible color for this node */
    static final int RED = 1;
    // the key of each node
    T key;

    /** Parent of node */
    RedBlackNode<T> parent;
    /** Left child */
    RedBlackNode<T> left;
    /** Right child */
    RedBlackNode<T> right;
    // the number of elements to the left of each node
    int numLeft = 0;
    // the number of elements to the right of each node
    int numRight = 0;
    // the color of a node
    int color;
    boolean isLeaf;

    RedBlackNode(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    // Constructor which sets key to the argument.
    RedBlackNode(T key){
        this();
        this.key = key;
    }

    RedBlackNode<T> getLeft () { return left; }
    RedBlackNode<T> getRight () { return right; }
}// end class RedBlackNode

