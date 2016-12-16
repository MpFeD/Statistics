import java.util.ArrayList;

public class Node {
	private double r0;
	private int id;
	private ArrayList<Arc> entrants;
	private ArrayList<Arc> sortants;

	public Node(int id) {
		this.id=id;
		entrants = new ArrayList<Arc>();
		sortants = new ArrayList<Arc>();
	}
	
	
	public Node(int id, ArrayList<Arc> entrants, ArrayList<Arc> sortants) {
		this.id=id;
		entrants = new ArrayList<Arc>();
		sortants = new ArrayList<Arc>();
		this.entrants=entrants;
		this.sortants=sortants;
	}
	
	public int getId(){
		return id;
	}
	
	public void addEntrants(Arc a){
		entrants.add(a);
	}
	
	public void addSortants(Arc a){
		sortants.add(a);
	}
	
	public ArrayList<Arc> getEntrants(){
		return entrants;
	}
	//----------
	public double getScore(){
		if(sortants.size()==0){
			return 0;
		}else{
			return (1.0/(double)sortants.size())*r0;
		}
	}
	
	public String toString(){
		return id +" "+entrants.toString() +"\n"+ sortants.toString();
	}
}