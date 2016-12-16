import java.util.ArrayList;

public class SimpleWeb {

	private ArrayList<Node> liste;

	public SimpleWeb() {
		liste = new ArrayList<Node>();
	}

	public SimpleWeb(int maxNode) {
		liste = new ArrayList<Node>(maxNode);
		for (int i = 0; i < maxNode; i++) {
			liste.add(new Node(i));
		}
	}

	public SimpleWeb(ArrayList<Node> liste) {
		liste = liste;
	}

	public void addArc(int a, int b) {
		
		for (Node n1 : liste){
			if (n1.getId()==a){
				for (Node n2 : liste){
					if (n2.getId()==b){
						n1.addEntrants(new Arc(n1,n2,0.0));
						n2.addSortants(new Arc(n2,n1,0.0));
					}
				}
				
			}
		}
	}
	
	public double updateProbas(){
		return 0.0;
		
	}
	
	public void showTransitionTable(){
		String res = "";
		for(int i=0;i<liste.size();i++){
			res +="[";
			for(int j=0;j<liste.size();i++){
				liste.get(i).getEntrants().get(j);
			}
		}
	}
	
	
	public String toString(){
		String res ="";
		for (Node n1 : liste){
			res += n1.toString() +"\n";
		}
		return res;
	}
}
