import UnitTests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({BPlusTreeTest.class, HashTableTest.class, GraphTest.class, BinarySearchTreeTest.class})

public class DataStructuresTest {
}
