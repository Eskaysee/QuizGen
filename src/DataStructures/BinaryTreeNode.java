package DataStructures;

public class BinaryTreeNode<dataType>
{
   dataType data;
   int color;
   BinaryTreeNode<dataType> left;
   BinaryTreeNode<dataType> right;
   int height;
   BinaryTreeNode<dataType> parent;
   boolean isLeaf;
   
   public BinaryTreeNode ( dataType d, BinaryTreeNode<dataType> l, BinaryTreeNode<dataType> r )
   {
      data = d;
      left = l;
      right = r;
      height = 0;
      color = 1;
      parent = null;
   }
   
   BinaryTreeNode<dataType> getLeft () { return left; }
   BinaryTreeNode<dataType> getRight () { return right; }
}
