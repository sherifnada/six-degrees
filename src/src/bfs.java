package src;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

public class bfs {
	
	//Prints the path and returns distance
	public static long simpleShortestPath(Node src, Node dst){
		if(src.equals(dst))
			return 0L; 
		
		if (src.getNeighbors().size() == 0){
			System.out.println("Source node has no neighbors");
			return Long.MIN_VALUE;
		}
		
		LinkedList<Node> q = new LinkedList<Node>();
		Hashtable<Node, Integer> distanceFromSrc = new Hashtable<Node, Integer>();
		Hashtable<Node, Node> parents = new Hashtable<Node, Node>();
		
		q.add(src);
		distanceFromSrc.put(src, 0);
		
		while (! q.isEmpty()){
			Node current = q.pop();
			if(current.equals(dst)){
				//print the path and return the distance
				Node n = current;
				String path = n.getValue();
				while(!src.equals(n)){
					n = parents.get(n);
					path = n.getValue() + " -> " + path;
				}
				System.out.println("Shortest Path from " + src.getValue() + " to " + dst.getValue() + ": " + path);
				return distanceFromSrc.get(current);	
			}
			else{
				for (Node n : current.getNeighbors()){
					if(! distanceFromSrc.containsKey(n)){
						//if we haven't seen this node before, add it to the distances
						//and make its distance 1 more than its parent's
						q.add(n);
						distanceFromSrc.put(n, distanceFromSrc.get(current) + 1);
						parents.put(n, current);
					}
				}
			}
		}
		//we have traversed the entire graph and haven't found the node. Doesn't exist
		return Long.MIN_VALUE;
	}
	
	//more memory, but simpler idea
	public static long simpleShortestDistance(Node src, Node dst){
		if(src.equals(dst))
			return 0L; 
		
		if (src.getNeighbors().size() == 0){
			System.out.println("Source node has no neighbors");
			return Long.MIN_VALUE;
		}
		
		LinkedList<Node> q = new LinkedList<Node>();
		Hashtable<Node, Integer> distanceFromSrc = new Hashtable<Node, Integer>();
		
		q.add(src);
		distanceFromSrc.put(src, 0);
		
		while (! q.isEmpty()){
			Node current = q.pop();
			if(current.equals(dst))
				return distanceFromSrc.get(current);
			else{
				for (Node n : current.getNeighbors()){
					if(! distanceFromSrc.containsKey(n)){
						//if we haven't seen this node before, add it to the distances
						//and make its distance 1 more than its parent's
						q.add(n);
						distanceFromSrc.put(n, distanceFromSrc.get(current) + 1);
					}
				}
			}
		}
		//we have traversed the entire graph and haven't found the node. Doesn't exist
		return Long.MIN_VALUE;
	}
	
	
	//Takes less memory, but code is more complex
	public static long BFSShortestDistance(Node src, Node dst){
		LinkedList<Node> q = new LinkedList<Node>();
		LinkedList<Integer> distances = new LinkedList<Integer>();
		HashSet<String> nodesVisited = new HashSet<String>();
		
		if(src.equals(dst))
			return 0L; 
		
		if (src.getNeighbors().size() == 0){
			System.out.println("Source node has no neighbors");
			return Long.MIN_VALUE;
		}
		
		nodesVisited.add(src.getValue());
		long curDistance = 1;
		q.addAll(src.getNeighbors());
		distances.addFirst(src.getNeighbors().size());
		distances.add(0);
		
		while (! q.isEmpty()){
			Node n = q.pop();
			
			
			if (!nodesVisited.contains(n.getValue())){
				nodesVisited.add(n.getValue());
				
				if( n.equals(dst))
					return curDistance;			
				else{
					int additionalNodes = 0;
					for(Node node: n.getNeighbors()){
						if(! nodesVisited.contains(node.getValue())){
							additionalNodes++;
							q.add(node);
						}
					}
					
					//increase the number of nodes at the upcoming distance
					int newNodesAtDistance = distances.removeLast() + additionalNodes;
					distances.addLast(newNodesAtDistance);
			
				}
			}
			
			//if we have looked at all nodes distance x away, increase our current distance
			//and advance to the number of nodes in the next distance category
			distances.addFirst(distances.removeFirst() - 1);
			if( distances.getFirst() == 0){
				distances.removeFirst();
				distances.add(0);
				curDistance++;
			}
		}
		
		//we have traversed the entire graph and haven't found the node. Doesn't exist
		return Long.MIN_VALUE;
	}

}
