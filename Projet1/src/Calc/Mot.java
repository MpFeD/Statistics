package Calc;

public class Mot {
	private Corpus c;
	
	
	public Mot(){
		c=new Corpus();
		c.init(1);
	}
		
	public Mot(double n){
		c=new Corpus();
		c.init(n);
	}
	
	/**
	 * Calcule la fréquance d'apparition d'une lettre selon la formule (1)
	 * 
	 * @param langue
	 *            Language utilisé
	 * @param l
	 *            Texte utilisé
	 * @return la fréquence d'apparition d'une lettre
	 */
	
	
	public double FreqApp(String l, String langue){
		
		double n=0;
		if (langue == "dutch"){
			n=(double)c.gethdutch().get(l)/c.gethdutch().get("total");
		}
		
		if (langue == "english"){
			n=(double)c.gethenglish().get(l)/c.gethenglish().get("total");
		}
		
		if (langue == "french"){
			n=(double)c.gethfrench().get(l)/c.gethfrench().get("total");
		}
		
		if (langue == "italian"){
			n=(double)c.gethitalian().get(l)/c.gethitalian().get("total");
		}
		
		return n;
	}
	
	/**
	 * Calcule la probabilité d'apparition d'une lettre
	 * 
	 * @param mot
	 *            mot étudié
	 * @param lange
	 *            langue choisie
	 * @return la probabilité d'apparition d'une lettre
	 */
	
	public double ProbaAppLettre(String mot, String langue){
	
	
		double n =1;
		for(int i = 0; i<mot.length();i++){
			n *=FreqApp(mot.charAt(i)+"" , langue);
		}
		return n; 
	}
	
	public Corpus getc(){
		return c;
	}
}
