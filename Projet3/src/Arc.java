
public class Arc {
	private Node tail;
	private Node head;
	private double proba;


	public Arc(Node tail, Node head, double proba){
		this.tail=tail;
		this.head=head;
		this.proba=proba;
	}
	
	public Node getTail(){
		return tail;
	}
	
	public Node getHead(){
		return head;
	}
	
	public String toString(){
		//return "("+tail.toString()+","+head.toString()+")";
		return tail.getId()+ " "+ head.getId();
		//return "";
	}
}