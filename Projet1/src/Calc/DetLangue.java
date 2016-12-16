package Calc;

import java.util.HashMap;

public class DetLangue {
	private String[] s = { "fatta", "ora", "che", "dato", "volta", "by",
			"other", "mean", "statistics", "chacolate", "president", "thanks",
			"patatoes", "constitutionellement", "peter", "pomme", "daar" };
	private String[] langue = { "italian", "italian", "italian", "italian",
			"italian", "english", "english", "english", "english", "english",
			"english", "english", "english", "french", "english", "french",
			"dutch" };

	private Mot m;

	public DetLangue() {
		m = new Mot();
	}

	/**
	 * Question 5 : determine la langue d'un mot
	 * 
	 * @return le lmax le plus grand selon la fonction ProbaAppLettre appliqu�e
	 *         � chaque langue
	 */
	public String Determination(String mot) {
		double max = m.ProbaAppLettre(mot, "dutch");
		String lmax = "dutch";

		if (m.ProbaAppLettre(mot, "french") > max) {
			max = m.ProbaAppLettre(mot, "french");
			lmax = "french";
		}
		if (m.ProbaAppLettre(mot, "english") > max) {
			max = m.ProbaAppLettre(mot, "english");
			lmax = "english";
		}
		if (m.ProbaAppLettre(mot, "italian") > max) {
			max = m.ProbaAppLettre(mot, "italian");
			lmax = "italian";
		}

		return lmax;
	}

	/**
	 * Calcule la performance selon la formule de la question 6
	 * 
	 * @return 1 - nb r�ponses fausses / nb r�ponses
	 */

	public double Performances() {
		int cpt = 0;
		for (int i = 0; i < s.length; i++) {
			if (Determination(s[i]) != langue[i]) {
				cpt++;
			}
		}
		return 1 - ((double) cpt / s.length);
	}

	/**
	 * Estime la langue
	 * 
	 * @param mot
	 *            Mot recherch�
	 * @param l
	 *            Chemin du corpus
	 * @return nb mot total / nb mot "mot"
	 */
	public double EstimationLangue(String mot, Language l) {
		int n;

		// on suppose que le nombre de mot d'un texte est �gal au nombre
		// d'�space contenu dans celui ci +1
		try {
			n = NbMot(mot, l) / (m.getc().gethitalian().get(" ") + 1);

			if (l.name.equals(Language.French))
				n = NbMot(mot, l) / (m.getc().gethfrench().get(" ") + 1);

			if (l.name.equals(Language.Dutch))
				n = NbMot(mot, l) / (m.getc().gethdutch().get(" ") + 1);

			if (l.name.equals(Language.English))
				n = NbMot(mot, l) / (m.getc().gethenglish().get(" ") + 1);
		} catch (Exception e) {
			n = 0;
		}
		return n;
	}

	/**
	 * Calcule le Nombre de mot d'un corpus
	 * 
	 * @param mot
	 *            Mot recherch�
	 * @param l
	 *            Chemin du corpus
	 * @return Le nombre de fois que le mot "mot" apparait dans le corpus de
	 *         chemin "path"
	 */

	public int NbMot(String mot, Language l) {
		int n = 0;
		String chaine = m.getc().CorpusToStr(l, 1);
		String recherche = "";
		for (int i = 0; i < chaine.length(); i++) {
			if ((chaine.charAt(i)) == ' ') {
				if (recherche.compareTo(mot) == 0)
					n++;
				recherche = "";
			} else {
				recherche += chaine.charAt(i);
			}
		}
		return n;
	}

	/**
	 * Calcule pl selon la formule avant la question 8
	 * 
	 * @param l
	 *            Language utilisé
	 * @return nb mot écrit dans la langue l / nb mot dans la base
	 */

	public double pl(Language l) {
		int total = (m.getc().gethenglish().get(" ") + 1)
				+ (m.getc().gethdutch().get(" ") + 1)
				+ (m.getc().gethfrench().get(" ") + 1)
				+ (m.getc().gethitalian().get(" ") + 1);
		double tot = (double) total;
		if (l.name.equals(Language.French))
			return (m.getc().gethfrench().get(" ") + 1) / tot;

		if (l.name.equals(Language.Dutch))
			return (m.getc().gethdutch().get(" ") + 1) / tot;

		if (l.name.equals(Language.Italian))
			return (m.getc().gethitalian().get(" ") + 1) / tot;

		return (m.getc().gethenglish().get(" ") + 1) / tot;
	}

	/**
	 * Calcule pl
	 * 
	 * @param mot
	 *            motutilisé
	 * @return determination selon pl
	 */

	public String Determination2(String mot) {
		double max = m.ProbaAppLettre(mot, "dutch") * pl(Language.Dutch);
		String lmax = "dutch";

		if (m.ProbaAppLettre(mot, "french") > max) {
			max = m.ProbaAppLettre(mot, "french") * pl(Language.French);
			lmax = "french";
		}
		if (m.ProbaAppLettre(mot, "english") > max) {
			max = m.ProbaAppLettre(mot, "english") * pl(Language.English);
			lmax = "english";
		}
		if (m.ProbaAppLettre(mot, "italian") > max) {
			max = m.ProbaAppLettre(mot, "italian") * pl(Language.Italian);
			lmax = "italian";
		}

		return lmax;
	}

	/**
	 * Calcule pl
	 * 
	 * @return performances selon pl
	 */

	public double Performances2() {

		int cpt = 0;
		for (int i = 0; i < s.length; i++) {
			if (Determination2(s[i]) != langue[i]) {
				cpt++;
			}
		}
		return 1 - ((double) cpt / s.length);
	}

	/**
	 * Evolution de la performance selon la question 8. Modifie les hashmap de
	 * la fonction init() dans la classe Corpus en envoyant une partie du texte
	 * (ratio). Affiche la fonction Performances() tout les 10% du texte.
	 */

	public void EvolutionPerformance() {
		m.getc().init(0.1);
		System.out.println(Performances());
		m.getc().init(0.2);
		System.out.println(Performances());
		m.getc().init(0.3);
		System.out.println(Performances());
		m.getc().init(0.4);
		System.out.println(Performances());
		m.getc().init(0.5);
		System.out.println(Performances());
		m.getc().init(0.6);
		System.out.println(Performances());
		m.getc().init(0.7);
		System.out.println(Performances());
		m.getc().init(0.8);
		System.out.println(Performances());
		m.getc().init(0.9);
		System.out.println(Performances());
		m.getc().init(1);
		System.out.println(Performances());
	}
	
	/**
	 * Determine le mot sachant la langue
	 * 
	 * @param l
	 *            language
	 * @param mot
	 *            mot à analysé
	 * @return la probabilité du mot sachant la langue
	 */

	public double DeterminationAmeliore(Language l, String mot) {
		double res = 1.0;
		String c = "";
		String chaine = "";
		HashMap<String, Double> duble = new HashMap<String, Double>();

		for (char i = 'a'; i <= 'z'; i++) {
			for (char j = 'a'; j <= 'z'; j++) {
				duble.put("" + (char) i + (char) j, 0.0);
			}
		}
		chaine = m.getc().CorpusToStr(l, 1);

		for (int i = 0; i < chaine.length(); i++) {
			if ((chaine.charAt(i) >= 'a' && chaine.charAt(i) <= 'z')
					&& (chaine.charAt(i + 1) >= 'a' && chaine.charAt(i + 1) <= 'z'))
				duble.put(
						"" + chaine.charAt(i) + chaine.charAt(i + 1),
						duble.get("" + chaine.charAt(i) + chaine.charAt(i + 1)) + 1);
		}
		for (char j = 'a'; j <= 'z'; j++) {
			int total = 0;
			for (char i = 'a'; i <= 'z'; i++) {
				total += duble.get("" + j + i);
			}
			for (char i = 'a'; i <= 'z'; i++) {
				duble.put("" + j + i, duble.get("" + j + i) / (double) total);
			}
		}

		for (int i = 0; i < mot.length() - 1; i++) {
			c = "" + mot.charAt(i) + mot.charAt(i + 1);
			res *= duble.get(c);
		}

		
		return res;
	}
	
	
	/**
	 * Determine le mot 
	 * 
	 * @param mot
	 *            mot à analysé
	 * @return la meilleure langue possible
	 */

	public String Test(String mot) {
		double max = DeterminationAmeliore(Language.French, mot);
		String lmax = "french";

		if (max < DeterminationAmeliore(Language.Dutch, mot)) {
			max = DeterminationAmeliore(Language.Dutch, mot);
			lmax = "dutch";
		}

		if (max < DeterminationAmeliore(Language.English, mot)) {
			max = DeterminationAmeliore(Language.English, mot);
			lmax = "english";
		}

		if (max < DeterminationAmeliore(Language.Italian, mot)) {
			max = DeterminationAmeliore(Language.Italian, mot);
			lmax = "italian";
		}
		return lmax;
	}
	
	
	/**
	 * performances pour DeterminationAmeliore
	 * 
	 * @param mot
	 *            mot à analysé
	 * @return la meilleure langue possible
	 */
	public double Performances3() {
		int cpt = 0;
		for (int i = 0; i < s.length; i++) {
			if (Test(s[i]) != langue[i]) {
				cpt++;
			}
		}
		return 1 - ((double) cpt / s.length);
	}

}
