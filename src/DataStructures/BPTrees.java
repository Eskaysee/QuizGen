package DataStructures;

import java.util.ArrayList;

public class BPTrees{
    private int order;
    private bpNode root;
    private ArrayList<int[]> nodeKeys = new ArrayList<>();

    public BPTrees(int branches){
        order = branches;
        root = null;
    }

////////////////////////////////////////////////////////////////
    private class bpNode {
        boolean isLeaf, isRoot;
        int[] keys;
        bpNode[] pointers;
        int ofOrder;
        int totKeys;
        bpNode parent;
        private bpNode(int order, boolean isLeaf) {
            ofOrder = order;
            this.isLeaf = isLeaf;
            totKeys = 0;
            pointers = new bpNode[ofOrder];
            keys = new int[ofOrder - 1];
        }

        // A function to traverse all nodes in a subtree rooted with this node
        public void printNodes(){
            int j = 0;
            if (this.isLeaf)
                System.out.print("Leaf");
            else if (this == root)
                System.out.print("Root");
            else
                System.out.print("Non-Leaf");
            System.out.println(" with total Keys:" + totKeys);
            for (int i=0; i < totKeys; i++){
                System.out.print("|");
                System.out.print(keys[i] + "|");
            }
            System.out.println();
            for (; j < totKeys; j++){
                if (!this.isLeaf)
                    pointers[j].printNodes();
            }
            if (!this.isLeaf)
                pointers[j].printNodes();
        }

    public void testKeys(){
        int j = 0;
        for (; j < totKeys; j++){
            if (!this.isLeaf)
                pointers[j].testKeys();
        }
        if (!this.isLeaf)
            pointers[j].testKeys();
        nodeKeys.add(this.keys);
    }

        public void Drop(int k){
            int i=0;
            for (; i<totKeys; i++){
                if (keys[i] == k){
                    keys[i] = 0;
                    pointers[i+1] = null;
                    break;
                }
            }
            for (; i+1<totKeys; i++) {
                keys[i] = keys[i + 1];
                pointers[i+1] = pointers[i+2];
                keys[i+1] = 0; pointers[i+2] = null;
            }
            --totKeys;
        }

        public boolean fullEnough(){
            float minKeysLeaf = (float) (order-1)/2;
            float minPointersLeaf = (float) order/2;
            if (isLeaf && totKeys >= minKeysLeaf) return true;
            else if (isRoot && countPointers()>=2 && totKeys>=1) return true;
            else if ((!isLeaf && !isRoot) && countPointers() >= minPointersLeaf) return true;
            else return false;
        }
        public boolean moreThanHalfFull(){
            float minKeysLeaf = (float) (order-1)/2;
            float minPointersLeaf = (float) order/2;
            if (isLeaf && totKeys-1 >= minKeysLeaf) return true;
            else if (!isLeaf && countPointers()-1 >= minPointersLeaf) return true;
            else return false;
        }

        public int countPointers(){
            int i = 0;
            for (; i<order; i++)
                if (pointers[i] == null) break;
            return i;
        }
        public int findChild(bpNode child){
            int i = 0;
            for (; i<countPointers(); i++){
                if (pointers[i]==child)
                    break;
            }
            return i;
        }
        public void setRoot(){
            if (parent == null) isRoot = true;
            else isRoot = false;
        }
    }
///////////////////////////////////////////////////////////

    public bpNode find(int k) {
        bpNode n = root;
        while (!n.isLeaf) {
            int it = 0;
            while (it < n.totKeys && k >= n.keys[it])
                it++;
            n = n.pointers[it];
        }
        return n;
    }

    public void insert(int data) {
        if (root == null) {
            root = new bpNode(order, true);
            root.keys[root.totKeys] = data;
            root.totKeys++;
        }
        else{
            bpNode left = find(data);
            if (left.totKeys < left.ofOrder-1){
                int pos = left.totKeys-1;
                for (; pos>=0; pos--){
                    if (left.keys[pos]>data)
                        left.keys[pos+1] = left.keys[pos];
                    else break;
                }
                left.keys[pos+1] = data;
                left.totKeys++;
            }
            else{
                bpNode right = new bpNode(order, left.isLeaf);
                split(left,right,data);
                putInParent(left, right);
            }
        }
    }

    public void delete(int data) {
        bpNode n = find(data);
        n.Drop(data);
        bpNode p = n.parent;
        if (n.fullEnough()) return;
        else if (couldTakeFromSibling(p, n)) return;
        else{
            bpNode l = getLeftSibling(p, n);
            bpNode r = getRightSibling(p, n);
            if (l != null) merge(l, n);
            else merge(n, r);
        }
    }

    private void putInParent(bpNode l, bpNode r){
        if (l == root){
            root = new bpNode(order, false);
            root.pointers[0] = l;
            root.pointers[1] = r;
            l.parent = root; r.parent = root;
            while (!r.isLeaf)
                r = r.pointers[0];
            root.keys[0] = r.keys[0];
            root.totKeys++;
            root.setRoot(); l.setRoot(); r.setRoot();
        }
        else{
            bpNode leftP = l.parent;
            if (leftP.totKeys < order - 1) {
                int pos = leftP.totKeys - 1;
                for (; pos >= 0; pos--) {
                    if (leftP.keys[pos] > r.keys[0]) {
                        leftP.keys[pos + 1] = leftP.keys[pos];
                        leftP.pointers[pos+2] = leftP.pointers[pos+1];
                    }
                    else break;
                }
                leftP.keys[pos + 1] = r.keys[0];
                leftP.pointers[pos + 2] = r;
                r.parent = leftP;
                leftP.totKeys++;
            }
            else {
                bpNode u = new bpNode(order, false);
                u.pointers[0] = r;  //place holder for the new leaf node from the split in the insertion method
                split(leftP, u, r.keys[0]);
                putInParent(leftP, u);
            }
        }
    }

    private void split(bpNode l, bpNode r, int d){
        //Get the keys in the correct order and empty left node
        int[] tempK = new int[order];
        bpNode[] tempP = new bpNode[order+1];
        for (int i=0; i<l.totKeys; i++){
            tempK[i] = l.keys[i];
            tempP[i] = l.pointers[i];
            l.keys[i]=0; l.pointers[i]=null;
        }
        tempP[order-1] = l.pointers[order-1];
        l.pointers[order-1] = null;
        int pos = l.totKeys-1; l.totKeys = 0;
        for (; pos>=0; pos--){
            if (tempK[pos]>d) {
                tempK[pos+1] = tempK[pos];
                if(!l.isLeaf) tempP[pos+2] = tempP[pos+1];
            }
            else break;
        }
        tempK[pos+1] =  d;
        tempP[pos+2] = r.pointers[0];

        //Now to do the actual splitting
        int num = order/2;
        if (l.isLeaf) {
            for (int j=0; j<num; j++){
                l.keys[j] = tempK[j];
                l.totKeys++;
            }
            for (int i = 0; num < order; num++) {
                r.keys[i] = tempK[num];
                ++r.totKeys;
                ++i;
            }
            r.pointers[order-1] = tempP[order-1];
            l.pointers[order-1] = r;
        }
        else{
            for (int j=0; j<num; j++){
                l.keys[j] = tempK[j];
                l.pointers[j] = tempP[j];
                tempP[j].parent = l;
                l.totKeys++;
            }
            l.pointers[num] = tempP[num];
            tempP[num].parent = l;
            ++num; int i=0;
            for (; num < order; num++) {
                r.pointers[i] = tempP[num];
                tempP[num].parent = r;
                r.keys[0] = tempK[num];
                ++r.totKeys;
                ++i;
            }
            r.pointers[i] = tempP[num];
            tempP[num].parent = r;
        }
    }

    protected bpNode getRightSibling(bpNode p, bpNode n){
        int i = p.findChild(n);
        if (++i < order)
            return p.pointers[i];
        else return null;
    }
    protected bpNode getLeftSibling(bpNode p, bpNode n){
        int i = p.findChild(n);
        if (i == 0)
            return null;
        else return p.pointers[--i];
    }

    private boolean couldTakeFromSibling(bpNode parent, bpNode node){
        bpNode left = getLeftSibling(parent, node);
        bpNode right = getRightSibling(parent, node);
        int movedK = 0; bpNode movedP;
        if (left != null) {
            if (left.moreThanHalfFull()) {
                movedK = left.keys[left.totKeys - 1];
                movedP = left.pointers[left.totKeys];
                if (node.isLeaf)
                    moveKey(node, movedK);
                else
                    movePointer(node, movedP, "left");
                left.Drop(movedK);
                int i = parent.findChild(node)-1;
                parent.keys[i] = movedK;
                return true;
            }
        }
        if (right == null) return false;
        else if (right.moreThanHalfFull()){
            movedK = right.keys[0];
            movedP = right.pointers[0];
            if (node.isLeaf)
                moveKey(node, movedK);
            else
                movePointer(node, movedP, "right");
            popFromRHS(right);
            int i = parent.findChild(right)-1;
            parent.keys[i] = right.keys[0];
            return true;
        }
        return false;
    }

    /*can take from sibling supporting methods*/
    private void moveKey(bpNode node, int data){
        int pos = node.totKeys - 1;
        for (; pos >= 0; pos--) {
            if (node.keys[pos] > data)
                node.keys[pos + 1] = node.keys[pos];
            else break;
        }
        node.keys[pos + 1] = data;
        node.totKeys++;
    }
    private void movePointer(bpNode node, bpNode child, String fromSib){
        if (fromSib.equals("right")){
            int index = node.totKeys+1;
            node.pointers[index] = child;
        }
        else if (fromSib.equals("left")){
            int i=node.countPointers();
            for (; i-1>=0 ; i--)
                node.pointers[i] = node.pointers[i-1];
            node.pointers[0] = child;
        }
    }
    private void popFromRHS(bpNode rNode){
        int i=1;
        for (; i < order-1; ++i){
            rNode.keys[i-1] = rNode.keys[i];
            rNode.pointers[i-1] = rNode.pointers[i];
            rNode.keys[i] = 0; rNode.pointers[i]= null;
        }
        rNode.totKeys--;
    }

    private void merge(bpNode left, bpNode right){
        int j = left.totKeys; int i=0;
        for (; i<right.totKeys; i++){
            left.keys[j] = right.keys[i];
            left.pointers[j] = right.pointers[i];
            if (!left.isLeaf)
                right.pointers[i].parent = left;
            ++j; left.totKeys++;
        }
        if (!left.isLeaf){
            left.pointers[j] = right.pointers[i];
            right.pointers[i].parent = left;
        }
        else left.pointers[order-1] = right.pointers[order-1];
        left.parent.Drop(right.keys[0]);
        j = left.parent.findChild(left) - 1;
        if (j >= 0) left.parent.keys[j] = left.keys[0];

        if (root == left.parent){
            if (!root.fullEnough()) {
                left.parent = null; left.setRoot(); root = left; return;
            }
        }
        else{
            bpNode p = left.parent; j = p.parent.findChild(p);
            if (p.totKeys == 0 && j > 0) moveKey(p, p.parent.keys[j-1]);
            else if (p.totKeys == 0 && j >= 0) moveKey(p, p.parent.keys[j]);
        }

        if (left.parent.fullEnough());
        else if (couldTakeFromSibling(left.parent.parent, left.parent));
        else{
            bpNode l = getLeftSibling(left.parent.parent, left.parent);
            bpNode r = getRightSibling(left.parent.parent, left.parent);
            if (l != null) merge(l, left.parent);
            else merge(left.parent, r);
        }
    }

    public void printNodes(){
        if (root != null)
            root.printNodes();
        System.out.println();
    }
    public ArrayList<int[]> testKeys(){
        if (root != null)
            root.testKeys();
        return nodeKeys;
    }

    public static void main(String[] args){
        BPTrees bpt = new BPTrees(4);
        //2019 b+ tree notes
        bpt.insert(20);
        bpt.insert(1);
        bpt.insert(4);
        bpt.insert(16);
        bpt.insert(25);
        bpt.insert(9);
        bpt.insert(13);
        bpt.insert(15);
        bpt.insert(10);
        bpt.insert(11);
        bpt.insert(12);
        System.out.println("INSERTED: 20,1,4,16,25,9,13,15,10,11,12");
        bpt.printNodes();

        System.out.println();
        System.out.println("===========================================");
        System.out.println();

        bpt.delete(13);
        bpt.delete(15);     //merge
        bpt.delete(1);
        System.out.println("DELETED: 13,15,1");
        bpt.printNodes();
    }
}
