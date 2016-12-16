package Calc;

import java.io.*;
import java.util.HashMap;

public class Corpus {
	File french;
	File dutch;
	File english;
	File italian;
	HashMap<String, Integer> hfrench;
	HashMap<String, Integer> hdutch;
	HashMap<String, Integer> hitalian;
	HashMap<String, Integer> henglish;

	public Corpus() {
		french = new File(Language.French.path);
		dutch = new File("bin/Corpus/dutch.txt");
		english = new File("bin/Corpus/english.txt");
		italian = new File("bin/Corpus/italian.txt");

		// System.out.println("Chemin absolu du fichier : " +
		// french.getAbsolutePath());

		hfrench = new HashMap<String, Integer>();
		hdutch = new HashMap<String, Integer>();
		hitalian = new HashMap<String, Integer>();
		henglish = new HashMap<String, Integer>();

	}
	
	
	/**
	 * Converti un fichier corpus en String
	 * @return La hcaine de caractère correspondant au corpus
	 */

	public String CorpusToStr(Language l, double ratio) {
		String chaine = "";
		try {
			InputStream ips = new FileInputStream(l.path);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				chaine += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return chaine.substring(0, (int) (chaine.length()*ratio));
	}
	
	/**
	 * Initialisation
	 */
	
	public void init(double n) {

		for (int i = 0; i < 26; i++) {
			char c = (char) ('a' + i);
			String s = c + "";
			hfrench.put(s, 0);
			hdutch.put(s, 0);
			henglish.put(s, 0);
			hitalian.put(s, 0);
		}

		hfrench.put(" ", 0);
		hdutch.put(" ", 0);
		hitalian.put(" ", 0);
		henglish.put(" ", 0);

		String chaine = CorpusToStr(Language.French,n);

		for (int i = 0; i < chaine.length(); i++) {
			if (((chaine.charAt(i) >= 'a') && (chaine.charAt(i) <= 'z'))
					|| (chaine.charAt(i) == ' ')) {
				hfrench.put((chaine.charAt(i) + ""),
						hfrench.get(chaine.charAt(i) + "") + 1);
			}
		}

		hfrench.put("total", chaine.length() - hfrench.get(" "));
		
		
		chaine = CorpusToStr(Language.Dutch,n);

		for (int i = 0; i < chaine.length(); i++) {
			if (((chaine.charAt(i) >= 'a') && (chaine.charAt(i) <= 'z'))
					|| (chaine.charAt(i) == ' ')) {
				hdutch.put((chaine.charAt(i) + ""),
						hdutch.get(chaine.charAt(i) + "") + 1);
			}
		}

		hdutch.put("total", chaine.length() - hdutch.get(" "));
		
		chaine = CorpusToStr(Language.English,n);

		for (int i = 0; i < chaine.length(); i++) {
			if (((chaine.charAt(i) >= 'a') && (chaine.charAt(i) <= 'z'))
					|| (chaine.charAt(i) == ' ')) {
				henglish.put((chaine.charAt(i) + ""),
						henglish.get(chaine.charAt(i) + "") + 1);
			}
		}

		henglish.put("total", chaine.length() - henglish.get(" "));
		
		
		chaine = CorpusToStr(Language.Italian,n);

		for (int i = 0; i < chaine.length(); i++) {
			if (((chaine.charAt(i) >= 'a') && (chaine.charAt(i) <= 'z'))
					|| (chaine.charAt(i) == ' ')) {
				hitalian.put((chaine.charAt(i) + ""),
						hitalian.get(chaine.charAt(i) + "") + 1);
			}
		}

		hitalian.put("total", chaine.length() - hitalian.get(" "));

	}
	
	
	public String toString(){
		String s ="";
		
		s += "Texte en french : \n" +hfrench.toString()+"\n";
		s += "Texte en dutch : \n" +hdutch.toString()+"\n";
		s += "Texte en english : \n" +henglish.toString()+"\n";
		s += "Texte en italian : \n" +hitalian.toString()+"\n";
		
		return s;
	}
	
	public HashMap<String, Integer> gethfrench(){
		return hfrench;
	}
	
	public HashMap<String, Integer> gethdutch(){
		return hdutch;
	}
	
	public HashMap<String, Integer> gethitalian(){
		return hitalian;
	}
	
	public HashMap<String, Integer> gethenglish(){
		return henglish;
	}
	
	
}