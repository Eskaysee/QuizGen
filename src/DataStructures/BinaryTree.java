package DataStructures;

import java.util.ArrayList;

public class BinaryTree<dataType>
{
   BinaryTreeNode<dataType> root;
   ArrayList<dataType> path = new ArrayList<>();
   
   public BinaryTree ()
   {
      root = null;
   }
   
   public int getHeight ()
   {
      return getHeight (root);
   }   
   public int getHeight ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return -1;
      else
         return 1 + Math.max (getHeight (node.getLeft ()), getHeight (node.getRight ()));
   }
   
   public int getSize ()
   {
      return getSize (root);
   }   
   public int getSize ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return 0;
      else
         return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
   }
   
   public void visit ( BinaryTreeNode<dataType> node )
   {
      path.add(node.data);
   }

   public ArrayList<dataType> getLeaves(){
      ArrayList<dataType> leaves = new ArrayList<>();
      setLeaves(root, leaves);
      return leaves;
   }
   public void setLeaves(BinaryTreeNode<dataType> node, ArrayList<dataType> allLeaves){
      if (node != null){
         if (node.right == null && node.left == null) {
            allLeaves.add(node.data);
            node.isLeaf = true;
            return;
         }
         setLeaves(node.left, allLeaves);
         setLeaves(node.right, allLeaves);
         node.isLeaf = false;
      }
   }

   public ArrayList<dataType> preOrder ()
   {
      path.clear();
      preOrder (root);
      return path;
   }
   public void preOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         visit (node);
         preOrder (node.getLeft ());
         preOrder (node.getRight ());
      }
   }

   public ArrayList<dataType> postOrder ()
   {
      path.clear();
      postOrder (root);
      return path;
   }
   public void postOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         postOrder (node.getLeft ());
         postOrder (node.getRight ());
         visit (node);
      }
   }

   public ArrayList<dataType> inOrder ()
   {
      path.clear();
      inOrder (root);
      return path;
   }
   public void inOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         inOrder (node.getLeft ());
         visit (node);
         inOrder (node.getRight ());
      }
   }

   public ArrayList<dataType> levelOrder ()
   {
      path.clear();
      if (root == null)
         return null;
      BTQueue<dataType> q = new BTQueue<dataType> ();
      q.enQueue (root);
      BinaryTreeNode<dataType> node;
      while ((node = q.getNext ()) != null)
      {
         visit (node);
         if (node.getLeft () != null)
            q.enQueue (node.getLeft ());
         if (node.getRight () != null)
            q.enQueue (node.getRight ());
      }
      return path;
   }

   public void clearTree(){root = null;}
   
}
