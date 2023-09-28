package tst;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import src.Node;
import src.bfs;

public class bfsTest {
	Node graph[] = new Node[26];
	
	@Before
	public void initGraph(){
		char alphabet = 'a';
		for (int i = 0; i < graph.length; alphabet++, i++)
			graph[i] = new Node(Character.toString(alphabet));
	}
	
	@Test
	public void testEdgeCases(){
		Assert.assertEquals("Distance from node to itself should be 0", bfs.BFSShortestDistance(graph[0], graph[0]), 0);
		Assert.assertEquals( "Neighborless node should have return Long.MIN_VALUE",bfs.BFSShortestDistance(graph[0], graph[5]), Long.MIN_VALUE);
	}
	
	@Test
	public void myBFSTest1(){
		/*
		 Finds shortest distance between A and E (Distance is 3) and B and E (2)
		 A----B----C
		      |    |
		 F----D----+
		 	  |	   |
		 	  +----E	 
		 */
		
		//initialize the list of neighbors
		ArrayList<Node> a = new ArrayList<Node>();
		//set node 1 as a neighbor
		a.add(graph[1]);
		//add that list of neighbors of node 0 to the graph
		graph[0].setNeighbors(a);
		
		ArrayList<Node> b = new ArrayList<Node>();
		b.add(graph[0]);
		b.add(graph[2]);
		b.add(graph[3]);
		graph[1].setNeighbors(b);
		
		ArrayList<Node> c = new ArrayList<Node>();
		c.add(graph[1]);
		c.add(graph[3]);
		c.add(graph[4]);
		graph[2].setNeighbors(c);
		
		ArrayList<Node> d = new ArrayList<Node>();
		d.add(graph[2]);
		d.add(graph[4]);
		d.add(graph[5]);
		graph[3].setNeighbors(d);
		
		ArrayList<Node> e = new ArrayList<Node>();
		e.add(graph[2]);
		e.add(graph[3]);
		graph[4].setNeighbors(e);
		
		ArrayList<Node> f = new ArrayList<Node>();
		f.add(graph[3]);
		graph[5].setNeighbors(f);
		
		Assert.assertEquals(bfs.BFSShortestDistance(graph[0], graph[4]), 3);
		Assert.assertEquals(bfs.BFSShortestDistance(graph[1], graph[4]), 2);
		Assert.assertEquals(bfs.simpleShortestDistance(graph[0], graph[4]), 3);
		Assert.assertEquals(bfs.simpleShortestDistance(graph[1], graph[4]), 2);
		Assert.assertEquals(bfs.simpleShortestPath(graph[0], graph[4]), 3);
		Assert.assertEquals(bfs.simpleShortestPath(graph[1], graph[4]), 2);
	}
	
	@Test
	public void myBFSTest2(){
		/*
		 Finds shortest distance between A and E (Distance is 4)
		 and between B and E (3)
		 and between D and C (1)
		 A----B----C
		      |    |
		 F----D----+
		 |	  	   
		 E	 
		 */
		
		//initialize the list of neighbors
		ArrayList<Node> a = new ArrayList<Node>();
		//set node 1 as a neighbor
		a.add(graph[1]);
		//add that list of neighbors of node 0 to the graph
		graph[0].setNeighbors(a);
		
		ArrayList<Node> b = new ArrayList<Node>();
		b.add(graph[0]);
		b.add(graph[2]);
		b.add(graph[3]);
		graph[1].setNeighbors(b);
		
		ArrayList<Node> c = new ArrayList<Node>();
		c.add(graph[1]);
		c.add(graph[3]);
		graph[2].setNeighbors(c);
		
		ArrayList<Node> d = new ArrayList<Node>();
		d.add(graph[2]);
		
		d.add(graph[5]);
		graph[3].setNeighbors(d);
		
		ArrayList<Node> e = new ArrayList<Node>();
		e.add(graph[2]);
		e.add(graph[3]);
		graph[4].setNeighbors(e);
		
		ArrayList<Node> f = new ArrayList<Node>();
		f.add(graph[3]);
		f.add(graph[4]);
		graph[5].setNeighbors(f);
		
		Assert.assertEquals(bfs.BFSShortestDistance(graph[0], graph[4]), 4);
		Assert.assertEquals(bfs.BFSShortestDistance(graph[1], graph[4]), 3);
		Assert.assertEquals(bfs.simpleShortestDistance(graph[0], graph[4]), 4);
		Assert.assertEquals(bfs.simpleShortestDistance(graph[1], graph[4]), 3);
		Assert.assertEquals(bfs.simpleShortestPath(graph[0], graph[4]), 4);
		Assert.assertEquals(bfs.simpleShortestPath(graph[1], graph[4]), 3);
	}
}

