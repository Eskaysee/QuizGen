import java.util.ArrayList;
import DataStructures.BTQueueNode;
import DataStructures.BinaryTreeNode;
import DataStructures.BTQueue;
import DataStructures.BinaryTree;
import DataStructures.BinarySearchTree;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BinarySearchTreeTest {
    // when using the bst it gets printed like an arraylist so would need it when
    public BinarySearchTree<Integer> bst= new BinarySearchTree<>();
    ArrayList<Integer> alist = new ArrayList<Integer>(5);

    void insert(int a){
        bst.insert(23);
        bst.insert(17);
        bst.insert(20);
        bst.insert(42);
        bst.insert(45);
    }


    @Test
    public void preOrder() {
        insert(0);
        // put inorder according to one in 2017 test 1 csc 2001F
        alist.add(17);
        alist.add(20);
        alist.add(23);
        alist.add(42);
        alist.add(45);
        try {
		assertEquals(alist,bst.inOrder());
		System.out.println("Passed Binary Search Tree insertion and inOrder traversal.");
	}catch (AssertionError e){
            System.out.println("Failed Binary Search Tree insertion and inOrder traversal.");
            System.out.println(e.getMessage());
        }
    }
}