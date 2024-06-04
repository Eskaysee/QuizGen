package UnitTests;

import DataStructures.Graph;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GraphTest {
    private Graph digraph;

    Graph unweighted(Graph dg){
        dg.emptyGraphMap();
        dg.insert("v0","v1", Graph.infinity);
        dg.insert("v0","v3", Graph.infinity);
        dg.insert("v1","v3", Graph.infinity);
        dg.insert("v1","v4", Graph.infinity);
        dg.insert("v2","v0", Graph.infinity);
        dg.insert("v2","v5", Graph.infinity);
        dg.insert("v3", "v2", Graph.infinity);
        dg.insert("v3", "v4", Graph.infinity);
        dg.insert("v3","v5", Graph.infinity);
        dg.insert("v3","v6", Graph.infinity);
        dg.insert("v4","v6", Graph.infinity);
        dg.insert("v6","v5", Graph.infinity);
        dg.type = "unweighted";
        return dg;
    }
    Graph weighted(Graph dg){
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
        digraph.type = "weighted";
        return dg;
    }
    Graph negative(Graph dg){
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
        digraph.type = "negative";
        return dg;
    }
    Graph acyclic(Graph dg){
        digraph.emptyGraphMap();
        digraph.insert("A", "B", 4);
        digraph.insert("A", "C", 8);
        digraph.insert("B", "C", 9);
        digraph.insert("B", "D", 10);
        digraph.insert("C", "D", 7);
        digraph.type = "acyclic";
        return dg;
    }

    //Testing the actual paths followed
    
    @Test
    public void testBFS(){
        try {
            digraph = new Graph();
            digraph = unweighted(digraph);
            digraph.bfs(digraph.startPoint());
            assertEquals("v2 --> v0 --> v3 --> v6\n",digraph.printPath(digraph.endPoint()));
            System.out.println("Passed Breadth First Search algorithm test for unweighted directed graph.");
        }catch (AssertionError e){
            System.out.println("Failed Breadth First Search algorithm test for unweighted directed graph.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDijkstra(){
        try {
            digraph = new Graph();
            digraph = weighted(digraph);
            digraph.dijkstra(digraph.startPoint());
            assertEquals("Algeria --> Denmark --> France\n",digraph.printPath(digraph.endPoint()));
            System.out.println("Passed Dijkstra algorithm test for weighted directed graph.");
        }catch (AssertionError e){
            System.out.println("Failed Dijkstra algorithm test for weighted directed graph.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testBellmanFord(){
        try {
            digraph = new Graph();
            digraph = negative(digraph);
            digraph.bellmanFord(digraph.startPoint());
            assertEquals("Jan --> Feb --> Mar --> Apr\n",digraph.printPath(digraph.endPoint()));
            System.out.println("Passed Bellman-Ford algorithm test for negatively weighted directed graph.");
        }catch (AssertionError e){
            System.out.println("Failed Bellman-Ford algorithm test for negatively weighted directed graph.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testTopological(){
        try {
            digraph = new Graph();
            digraph = acyclic(digraph);
            digraph.topological(digraph.startPoint());
            assertEquals("A --> B --> D\n",digraph.printPath(digraph.endPoint()));
            System.out.println("Passed Topological algorithm test for acyclic directed graph.");
        }catch (AssertionError e){
            System.out.println("Failed Topological algorithm test for acyclic directed graph.");
            System.out.println(e.getMessage());
        }
    }
}
