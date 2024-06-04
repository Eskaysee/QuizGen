package UnitTests;

import DataStructures.BPTrees;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BPlusTreeTest {

    private BPTrees bpt;

    BPTrees populateBPlusTree(BPTrees bpTree){
        //2019 test 2
        bpTree.insert(23);
        bpTree.insert(31);
        bpTree.insert(43);
        bpTree.insert(13);
        bpTree.insert(47);
        bpTree.insert(17);
        bpTree.insert(29);
        bpTree.insert(37);
        bpTree.insert(41);
        bpTree.insert(19);
        return bpTree;
    }

    @Test
    public void testBPlusTreeInsertion(){
        //Question f.1
        bpt = new BPTrees(4);
        bpt = populateBPlusTree(bpt);
        bpt.insert(48); bpt.insert(16);
        int[][] comp = {{13,16,0},{17,19,0},{23,29,0},{17,23,0},{31,37,41},{43,47,48},{43,0,0},{31,0,0}};
        int k=0;
        for (int[] i : bpt.testKeys()){
            for (int j=0; j<comp[k].length; j++)
                assertEquals(comp[k][j], i[j]);
            ++k;
        }
        System.out.println("Passed insertion test for B+ Tree.");
    }

    @Test
    public void testBPlusTreeDeletion(){
        //Question f.2
        bpt = new BPTrees(4);
        bpt = populateBPlusTree(bpt);
        bpt.delete(23);
        int[][] comp = {{13,17,0},{19,29,0},{19,31,43}};
        int j=0;
        for (int i : bpt.testKeys().get(0))
            assertEquals(comp[0][j++], i);
        j=0;
        for (int i : bpt.testKeys().get(1))
            assertEquals(comp[1][j++], i);
        j=0;
        for (int i : bpt.testKeys().get(4))
            assertEquals(comp[2][j++], i);
        System.out.println("Passed deletion test for B+ Tree.");
    }
}
