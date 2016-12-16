package Calc;

import java.util.HashMap;

public class RegionsPromoteurs {
	HashMap<String, Integer> H;

	public RegionsPromoteurs() {
		H=new HashMap<String,Integer>();
	}

	/**
	 * Simuler une sequence
	 * 
	 * @param l
	 *            Longueur l du mot.
	 * @param x
	 *            Nombre de fois que qu'un mot sera produit.
	 * @param mot
	 *            Le mot donné.
	 * @param nsim
	 *            Nombre de simulation.
	 * @param N
	 *            Nombre éstimé de comptage.
	 * @return Probabilité empirique.
	 */

	public int SimulerSequenceEquiprobable(int l, int x, String mot, int nsim,
			int N) {
		int ni = 0;
		if (mot.length() != l) {
			System.out.println("Erreur");
			return -1;
		}
		String res = "";

		int ret = 0;
		double n;

		for (int j = 0; j < nsim; j++) {
			for (int i = 0; i < l * x; i++) {
				n = Math.random();
				if (n < 0.25)
					res += 'A';
				else if (n < 0.5)
					res += 'T';
				else if (n < 0.75)
					res += 'G';
				else
					res += 'C';
			}

			for (int i = 0; i < res.length(); i += l) {
				String buf = res.substring(i, l + i);
				if (buf.equals(mot) == true) {
					ni++;
				}
			}
			if (ni >= N)
				ret++;
		}

		return ret;
	}

	/**
	 * Simuler une sequence
	 * 
	 * @param l
	 *            Longueur l du mot.
	 * @param x
	 *            Nombre de fois que qu'un mot sera produit.
	 * @param mot
	 *            Le mot donné.
	 * @param nsim
	 *            Nombre de simulation.
	 * @param N
	 *            Nombre éstimé de comptage.
	 * @return Probabilité empirique.
	 */

	public int SimulerSequenceModele(int l, int x, String mot, int nsim, int N) {
		int ni = 0;
		if (mot.length() != l) {
			System.out.println("Erreur");
			return -1;
		}
		String res = "";

		int ret = 0;
		double n;

		for (int j = 0; j < nsim; j++) {
			for (int i = 0; i < l * x; i++) {
				n = Math.random();
				if (n < 0.4)
					res += 'A';
				else if (n < 8)
					res += 'T';
				else if (n < 0.9)
					res += 'G';
				else
					res += 'C';
			}

			for (int i = 0; i < res.length(); i += l) {
				String buf = res.substring(i, l + i);
				System.out.println(buf);
				if (buf.equals(mot) == true) {
					ni++;
				}
			}
			if (ni >= N)
				ret++;
		}

		return ret;
	}

	/**
	 * Compte le nombre de mot dans un texte sans qu'ils se recouvrent
	 * 
	 * @param mot
	 *            Le mot donné.
	 * @param texte
	 *            Texte étudié.
	 * @return Mots comptés.
	 */

	public int ComptageMot(String mot, String texte) {
		int res = 0;

		if (mot.length() < 2) {
			System.out.println("Erreur");
			return 0;
		}

		if (texte.length() < mot.length()) {
			System.out.println("Erreur");
			return 0;
		}
		String buf = texte.substring(0, mot.length());

		for (int i = mot.length(); i <= texte.length(); i++) {
			
			//System.out.println(buf);
			if (buf.equals(mot) == true) {
				res++;
				i += mot.length()-2;
			}
			if (i >= texte.length())
				break;
			buf += texte.charAt(i);
			if (buf.length() == 1)
				break;

			buf = buf.substring(1, buf.length());
			
		}
		return res;
	}
	
	/**
	 * Affiche le nombre de mot dans un texte selon une longueur donnée
	 * 
	 * @param longuer
	 *            La longueur.
	 * @param texte
	 *            Texte étudié.
	 */

	public void ComptageMotLongueur(int longueur, String texte) {
		int res = 0;

		if (longueur > texte.length()) {
			System.out.println("Erreur");
			return;
		}

		String mot = "";
		for (int i = 0; i < longueur; i++) {
			mot += texte.charAt(i);
		}
		
		for (int j = 0; j < texte.length(); j++) {
			res=0;			
			res += ComptageMot(mot, texte);
			H.put(mot,res);
			mot += texte.charAt(j);
			mot=mot.substring(1,3);
		}
		
		
		System.out.println(H.toString());

	}

}
