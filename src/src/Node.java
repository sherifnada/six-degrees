package src;

import java.util.ArrayList;

public class Node{
	private String value = new String(); 
	private ArrayList<Node> neighbors = new ArrayList<Node>();
	
	public Node(String value){
		this.value = value;
	}
	
	public Node(String value, ArrayList<Node> neighbors){
		this.value = value;
		this.neighbors = neighbors;
	}
	
	public ArrayList<Node> getNeighbors(){
		return this.neighbors;
	}
	
	public void setNeighbors(ArrayList<Node> neighbors){
		this.neighbors = neighbors;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	@Override
	public boolean equals(Object o){
		if (! (o instanceof Node))
			return false;
		
		Node n = (Node) o;
		return this.value.equals(n.getValue());
	}
}