package Calc;

import java.io.*;
import java.util.HashMap;

public class Fasta {

	File NC_017626;
	File NC_018520;
	File NC_019896;
	HashMap<String, Integer> hNC_017626;
	HashMap<String, Integer> hNC_018520;
	HashMap<String, Integer> hNC_019896;

	HashMap<String, Double> G1;
	HashMap<String, Double> G2;
	HashMap<String, Double> G3;

	String chaine1;
	String chaine2;
	String chaine3;

	/**
	 * Constructeur
	 * 
	 * @param b
	 *            , boolean permettant de ne pas convertir les fichiers en
	 *            String si besoin car opération tres longue.
	 * 
	 */
	public Fasta(boolean b) {
		NC_017626 = new File(Fichier.NC_017626.path);
		NC_018520 = new File(Fichier.NC_018520.path);
		NC_019896 = new File(Fichier.NC_019896.path);

		hNC_017626 = new HashMap<String, Integer>();
		hNC_018520 = new HashMap<String, Integer>();
		hNC_019896 = new HashMap<String, Integer>();

		G1 = new HashMap<String, Double>();
		G2 = new HashMap<String, Double>();
		G3 = new HashMap<String, Double>();

		if (b) {
			chaine1 = CorpusToStr(NC_017626);
			chaine2 = CorpusToStr(NC_018520);
			chaine3 = CorpusToStr(NC_019896);
		}
	}

	/**
	 * Converti un fichier Fasta en String
	 * 
	 * @param l
	 *            Fichier à convertir en String
	 * @return La chaine de caractère correspondant au fichier
	 */

	public String CorpusToStr(Fichier l) {
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
		return chaine;
	}

	/**
	 * Converti un fichier Fasta en String
	 * 
	 * @param l
	 *            Fichier à convertir en String
	 * @return La chaine de caractère correspondant au fichier
	 */

	public String CorpusToStr(File l) {
		String chaine = "";
		try {
			InputStream ips = new FileInputStream(l.getAbsoluteFile());
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			br.readLine(); // Pour ne pas prendre la première ligne
			while ((ligne = br.readLine()) != null) {
				chaine += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return chaine;
	}

	/**
	 * Converti un fichier Fasta en String sans sauter la première ligne
	 * 
	 * @param l
	 *            Fichier à convertir en String
	 * @return La chaine de caractère correspondant au fichier
	 */
	public String CorpusToStrSansSaut(File l) {
		String chaine = "";
		try {
			InputStream ips = new FileInputStream(l.getAbsoluteFile());
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
		return chaine;
	}

	/**
	 * Initialisation sur chaque lettre de chaque Fichier Fasta Chaque lettre
	 * est stockée dans un HashMap puis écris dans un fichier Les fichiers sont
	 * situés dans /src/FichierCalcul
	 */

	public void init() {

		hNC_017626.put("A", 0);
		hNC_017626.put("T", 0);
		hNC_017626.put("C", 0);
		hNC_017626.put("G", 0);
		hNC_017626.put("U", 0);
		hNC_017626.put("N", 0);

		hNC_018520.put("A", 0);
		hNC_018520.put("T", 0);
		hNC_018520.put("C", 0);
		hNC_018520.put("G", 0);
		hNC_018520.put("U", 0);
		hNC_018520.put("N", 0);

		hNC_019896.put("A", 0);
		hNC_019896.put("T", 0);
		hNC_019896.put("C", 0);
		hNC_019896.put("G", 0);
		hNC_019896.put("U", 0);
		hNC_019896.put("N", 0);

		for (int i = 0; i < chaine1.length(); i++) {
			if (chaine1.charAt(i) == 'A' || chaine1.charAt(i) == 'T'
					|| chaine1.charAt(i) == 'C' || chaine1.charAt(i) == 'G'
					|| chaine1.charAt(i) == 'U' || chaine1.charAt(i) == 'N')
				hNC_017626.put((chaine1.charAt(i) + ""),
						hNC_017626.get(chaine1.charAt(i) + "") + 1);
		}

		hNC_017626.put("t", chaine1.length() - (chaine1.length() / 60));

		for (int i = 0; i < chaine2.length(); i++) {
			if (chaine2.charAt(i) == 'A' || chaine2.charAt(i) == 'T'
					|| chaine2.charAt(i) == 'C' || chaine2.charAt(i) == 'G'
					|| chaine2.charAt(i) == 'U' || chaine2.charAt(i) == 'N')
				hNC_018520.put((chaine2.charAt(i) + ""),
						hNC_018520.get(chaine2.charAt(i) + "") + 1);
		}

		hNC_018520.put("t", chaine2.length() - (chaine2.length() / 60));

		for (int i = 0; i < chaine3.length(); i++) {
			if (chaine3.charAt(i) == 'A' || chaine3.charAt(i) == 'T'
					|| chaine3.charAt(i) == 'C' || chaine3.charAt(i) == 'G'
					|| chaine3.charAt(i) == 'U' || chaine3.charAt(i) == 'N')
				hNC_019896.put((chaine3.charAt(i) + ""),
						hNC_019896.get(chaine3.charAt(i) + "") + 1);
		}

		hNC_019896.put("t", chaine3.length() - (chaine3.length() / 60));

		File f1 = new File("src/FichierCalcul/NC_017626.calc");
		File f2 = new File("src/FichierCalcul/NC_018520.calc");
		File f3 = new File("src/FichierCalcul/NC_019896.calc");

		try {
			FileWriter fw = new FileWriter(f1);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(hNC_017626.toString());
			output.flush();
			output.close();

			fw = new FileWriter(f2);
			output = new BufferedWriter(fw);
			output.write(hNC_018520.toString());
			output.flush();
			output.close();

			fw = new FileWriter(f3);
			output = new BufferedWriter(fw);
			output.write(hNC_019896.toString());
			output.flush();
			output.close();

		} catch (IOException ioe) {
			System.out.print("Erreur durant l'écriture");
		}

		System.out
				.println("init() terminé\nRésulats stockés dans /src/FichierCalcul");

	}

	/**
	 * Initialisation sur chaque codon de chaque Fichier Fasta Chaque codon est
	 * stocké dans un HashMap puis écris dans un fichier Les fichiers sont
	 * situés dans /src/FichierCalcul
	 */

	public void init2() {

		char[] tab = { 'A', 'T', 'C', 'G' };

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				for (int k = 0; k < tab.length; k++) {
					G1.put("" + tab[i] + tab[j] + tab[k], 0.0);
					G2.put("" + tab[i] + tab[j] + tab[k], 0.0);
					G3.put("" + tab[i] + tab[j] + tab[k], 0.0);
				}
			}
		}

		// String chaine1 = "GTTG\nAC\nTT\nT\nT\nT\n";

		String buf = chaine1.substring(0, 3);
		int imax = chaine1.length();
		int NbSaut = 0;

		for (int i = 3; i < chaine1.length(); i += 3) {
			G1.put(buf, G1.get(buf) + 1.0);

			if (chaine1.charAt(i) == '\n') {
				i++;
				NbSaut++;
			}
			if (i >= imax)
				break;

			buf += chaine1.charAt(i);

			if (chaine1.charAt(i + 1) == '\n') {
				i++;
				NbSaut++;
			}
			if (i + 1 >= imax)
				break;

			buf += chaine1.charAt(i + 1);

			if (chaine1.charAt(i + 2) == '\n') {
				i++;
				NbSaut++;
			}
			if (i + 2 >= imax)
				break;

			buf += chaine1.charAt(i + 2);
			buf = buf.substring(3, 6);

		}

		G1.put("tot", (double) ((chaine1.length() / 3) - NbSaut));

		NbSaut = 0;
		buf = chaine2.substring(0, 3);
		imax = chaine2.length();

		for (int i = 3; i < chaine2.length(); i += 3) {
			G2.put(buf, G2.get(buf) + 1.0);

			if (chaine2.charAt(i) == '\n')
				i++;
			if (i >= imax)
				break;

			buf += chaine2.charAt(i);

			if (chaine2.charAt(i + 1) == '\n')
				i++;
			if (i + 1 >= imax)
				break;

			buf += chaine2.charAt(i + 1);

			if (chaine2.charAt(i + 2) == '\n')
				i++;
			if (i + 2 >= imax)
				break;

			buf += chaine2.charAt(i + 2);
			buf = buf.substring(3, 6);

		}

		G2.put("tot", (double) ((chaine2.length() / 3) - NbSaut));

		NbSaut = 0;
		buf = chaine3.substring(0, 3);
		imax = chaine3.length();

		for (int i = 3; i < chaine3.length(); i += 3) {

			G3.put(buf, G3.get(buf) + 1.0);

			if (chaine3.charAt(i) == '\n')
				i++;
			if (i >= imax)
				break;

			buf += chaine3.charAt(i);

			if (chaine3.charAt(i + 1) == '\n')
				i++;
			if (i + 1 >= imax)
				break;

			buf += chaine3.charAt(i + 1);

			if (chaine3.charAt(i + 2) == '\n')
				i++;
			if (i + 2 >= imax)
				break;

			buf += chaine3.charAt(i + 2);
			buf = buf.substring(3, 6);

		}

		G3.put("tot", (double) ((chaine3.length() / 3) - NbSaut));

		File f1 = new File("src/FichierCalcul/NC_017626.calc");
		File f2 = new File("src/FichierCalcul/NC_018520.calc");
		File f3 = new File("src/FichierCalcul/NC_019896.calc");

		try {
			FileWriter fw = new FileWriter(f1, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write("\n");
			output.write(G1.toString());
			output.flush();
			output.close();

			fw = new FileWriter(f2, true);
			output = new BufferedWriter(fw);
			output.write("\n");
			output.write(G2.toString());
			output.flush();
			output.close();

			fw = new FileWriter(f3, true);
			output = new BufferedWriter(fw);
			output.write("\n");
			output.write(G3.toString());
			output.flush();
			output.close();

		} catch (IOException ioe) {
			System.out.print("Erreur durant l'écriture init2()");
		}

		System.out
				.println("init2() terminé\nRésulats stockés dans /src/FichierCalcul");
	}

	/**
	 * Initialisation sur chaque gène de chaque Fichier Fasta Chaque gène est
	 * stocké dans un HashMap puis écris dans un fichier Les fichiers sont
	 * situés dans /src/FichierCalcul
	 */

	public void init3() {

		File f1 = new File("src/FichierCalcul/NC_017626.gene");
		File f2 = new File("src/FichierCalcul/NC_018520.gene");
		File f3 = new File("src/FichierCalcul/NC_019896.gene");

		// chaine1="GTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGCTTCTGAACTGGTTACCTGCCGTGAGTAAATTAAAATTTTATTGACTTAGGTCACTAAATACTTTAACCAATATAGGCATAGCGCACAGACAGATAAAAATTACAGAGTACACAACATCCATGAAACGCATTAGCACCACCATTACCACCACCATCACCATTACCACAGGTAACGGTGCGGGCTGACGCGTACAGGAAACACAGAAAAAAGCCCGCACCTGACAGTGCGGGCTTTTTTTTCGACCAAAGGTAACGAGGTAACAACCATGCGAGTGTTGAAGTTCGGCGGTACATCAGTGGCAAATGCAGAACGTTTTCTGCGGGTTGCCGATATTCTGGAAAGCAATGCCAGGCAGGGGCAGGTGGCCACCGTCCTCTCTGCCCCCGCCAAAATCACCAACCACCTGGTGGCGATGATTGAAAAAACCATTAGCGGCCAGGATGCTTTACCCAATATCAGCGATGCTGAACGTATTTTTGCCGAACTTCTGACGGGACTCGCCGCCGCCCAGCCGGGATTCCCGCTGGCGCAATTGAAAACTTTCGTCGACCAGGAATTTGCTCAAATAAAACATGTCCTGCATGGCATTAGTTTGTTAGGGCAGTGCCCGGATAGCATCAACGCTGCGCTGATTTGCCGTGGCGAGAAAATGTCGATCGCCATTATGGCCGGCGTGTTAGAAGCGCGTGGTCACAACGTTACCGTTATCGATCCGGTCGAAAAACTACTGGCAGTGGGGCATTACCTCGAATCTACCGTCGATATTGCTGAGTCCACCCGCCGTATTGCGGCAAGTCGTATTCCGGCTGATCACATGGTGCTGATGGCAGGTTTCACCGCCGGTAATGAAAAAGGCGAACTGGTGGTGCTTGGACGTAACGGTTCCGACTACTCCGCTGCGGTGCTGGCTGCCTGTTTACGCGCCGATTGTTGCGAGATTTGGACGGACGTTGACGGGGTTTATACCTGCGACCCGCGTCAGGTGCCCGATGCGAGGTTGTTGAAGTCGATGTCCTACCAGGAAGCGATGGAGCTTTCCTACTTCGGCGCTAAAGTTCTTCACCCCCGCACCATTACCCCCATCGCCCAGTTCCAGATCCCTTGCCTGATTAAAAATACCGGAAATCCTCAAGCACCAGGTACGCTCATTGGTGCCAGCCGTGATGAAGACGAATTACCGGTCAAGGGCATTTCCAATCTGAATAACATGGCAATGTTCAGCGTTTCCGGCCCGGGGATGAAAGGAATGGTCGGCATGGCGGCGCGCGTCTTTGCTGCAATGTCACGCGCCCGTATTTCCGTGGTGCTGATTACGCAATCATCTTCCGAATACAGTATCAGTTTCTGCGTTCCGCAAAGCGACTGTGTGCGAGCTGAACGGGCAATGCAGGAAGAGTTCTACCTGGAACTGAAAGAAGGCTTACTGGAGCCGCTGGCGGTGACGGAACGGCTGGCCATTATCTCGGTGGTAGGTGATGGTATGCGCACCTTGCGTGGGATCTCGGCGAAATTCTTTGCCGCGCTGGCCCGCGCCAATATCAACATTGTCGCCATTGCTCAGGGATCTTCTGAACGCTCAATCTCTGTCGTGGTAAATAACGATGATGCGACCACTGGCGTGCGCGTTACTCATCAGATGCTGTTCAATACCGATCAGGTTATCGAAGTGTTTGTGATTGGCGTCGGTGGCGTTGGCGGTGCGCTGCTGGAGCAACTGAAGCGTCAACAAAGCTGGCTGAAGAATAAACATATCGACTTACGTGTCTGCGGCGTTGCCAACTCGAAGGCTCTGCTTACCAATGTGCATGGCCTAAACCTGGAAAACTGGCAGGAAGAACTGGCGCAAGCCAAAGAGCCGTTTAATCTCGGGCGCTTAATTCGCCTCGTGAAAGAATATCATCTGCTAAACCCGGTCATTGTTGACTGCACCTCCAGCCAGGCAGTGGCGGATCAATATGCCGACTTCTTGCGCGAAGGTTTCCACGTTGTCACGCCGAACAAAAAGGCCAACACCTCGTCGATGGATTACTACCATCTGTTGCGTCATGCGGCGGAAAAATCGCGGCGTAAATTCCTCTATGACACCAACGTTGGGGCTGGATTACCGGTTATTGAGAACCTGCAAAATCTGCTCAATGCTGGTGATGAATTGATGAAGTTCTCCGGCATTCTTTCAGGTTCGCTTTCTTATATCTTCGGCAAGTTAGACGAAGGCATGAGTTTCTCCGAGGCGACTACTCTGGCGCGGGAAATGGGTTATACCGAACCGGATCCGCGAGATGATCTTTCTGGTATGGATGTAGCGCGTAAGCTATTGATTCTCGCTCGTGAAACGGGACGTGAACTGGAGCTGGTGGATATTGAAATTGAACCTGTGCTGCCCGCAGAGTTTAACGCTGAGGGTGATGTTGCCGCTTTTATGGCGAATCTGTCACAGCTCGACGATCTCTTTGCCGCGCGCGTGGCGAAGGCCCGTGATGAAGGAAAAGTTTTGCGCTATGTTGGCAATATTGATGAAGATGGTGCCTGCCGCGTGAAGATTGCCGAAGTGGATGGTAATGATCCGCTGTTCAAAGTGAAAAATGGCGAAAACGCCCTGGCCTTTTATAGCCACTATTATCAGCCGCTGCCGTTGGTCCTGCGCGGATACGGTGCGGGTAATGACGTTACAGCTGCCGGTGTCTTTGCCGATCTGCTACGCACCCTCTCATGGAAGTTAGGAGTCTAAAATGGTTAAGGTTTATGCCCCGGCTTCCAGTGCCAATATGAGCGTCGGGTTTGATGTGCTCGGGGCGGCGGTGACACCCGTTGATGGTGCATTGCTCGGAGATGTAGTCACGGTTGAGGCGGCAGAGACATTCAGTCTCAACAACCTCGGACGCTTTGCCGATAAGCTGCCGTCAGAACCACGGGAAAATATCGTTTATCAGTGCTGGGAGCGTTTTTGCCAGGAGCTGGGCAAGCAAATTCCAGTGGCGATGACTCTGGAAAAGAATATGCCGATCGGCTCGGGCTTAGGCTCCAGCGCCTGTTCGGTGGTCGCGGCGCTGATGGCGATGAATGAACACTGCGGCAAGCCACTTAATGACACCCGTTTGCTGGCTTTGATGGGCGAGCTGGAAGGGCGTATCTCCGGCAGCATTCATTACGACAACGTGGCGCCGTGTTTTCTTGGTGGTATGCAGTTGATGATCGAAGAAAACGACATCATCAGCCAGCAAGTGCCAGGGTTTGATGAGTGGCTGTGGGTGCTGGCGTATCCGGGGATTAAAGTCTCGACGGCAGAAGCCAGGGCTATTTTACCGGCGCAGTATCGCCGCCAGGATTGCATTGCGCACGGGCGACATCTGGCTGGCTTCATTCACGCCTGCTATTCCCGTCAGCCTGAGCTTGCCGCGAAGCTGATGAAAGATGTTATCGCTGAACCCTACCGTGAACGGTTACTGCCTGGCTTCCGGCAGGCGCGGCAGGCGGTTGCGGAAATCGGCGCGGTAGCGAGCGGTATCTCCGGCTCCGGCCCGACCTTGTTCGCTCTGTGTGACAAGCCGGATACCGCCCAGCGCGTTGCCGACTGGTTGGGTAAGAACTACCTGCAAAATCAGGAAGGTTTTGTTCATATTTGCCGGCTGGATACGGCGGGCGCACGAGTACTGGAAAACTAAATGAAACTCTACAATCTGAAAGATCACAATGAGCAGGTCAGCTTTGCGCAAGCCGTAACCCAGGGGTTGGGCAAAAATCAGGGGCTGTTTTTTCCGCACGACCTGCCGGAATTCAGCCTGACTGAAATTGATGAGATGCTGAAGCTGGATTTTGTCACCCGCAGTGCGAAGATCCTCTCGGCGTTTATTGGTGATGAAATCCCGCAGGAAATCCTGGAAGAGCGCGTGCGCGCGGCGTTTGCCTTCCCGGCTCCGGTCGCCAATGTTGAAAGCGATGTCGGTTGTCTGGAATTGTTCCACGGGCCAACGCTGGCATTTAAAGATTTCGGCGGTCGCTTTATGGCACAAATGCTGACCCATATTGCGGGCGATAAGCCAGTGACCATTCTGACCGCGACCTCCGGTGATACCGGAGCGGCAGTGGCTCATGCTTTCTACGGTTTACCGAATGTGAAAGTGGTTATCCTCTATCCACGAGGCAAAATCAGTCCACTGCAAGAAAAACTGTTCTGTACATTGGGCGGCAATATCGAAACTGTTGCCATCGACGGCGATTTCGATGCCTGTCAGGCGCTGGTGAAGCAGGCGTTTGATGATGAAGAGCTGAAAGTGGCGCTGGGGTTAAACTCAGCTAACTCGATTAACATCAGCCGTTTGCTGGCGCAGATTTGCTACTACTTTGAAGCAGTTGCGCAGCTGCCGCAGGAAGCGCGCAACCAGCTGGTTGTCTCGGTGCCAAGCGGAAACTTCGGCGATTTGACGGCGGGTCTGCTGGCGAAGTCACTCGGTCTGCCGGTGAAACGTTTTATTGCTGCGACCAACGTGAACGATACCGTGCCACGTTTCCTGCACGACGGTCAGTGGTCACCCAAAGCGACTCAGGCGACGTTATCCAACGCGATGGACGTGAGTCAGCCGAACAACTGGCCGCGTGTGGAAGAGTTGTTCCGCCGCAAAATCTGGCAACTGAAAGAGCTGGGTTATGCAGCCGTGGATGATGAAACCACGCAACAGACAATGCGTGAGTTAAAAGAACTGGGCTACACCTCGGAGCCGCACGCTGCCGTAGCGTATCGTGCGCTGCGTGACCAGTTGAATCCAGGCGAATATGGCTTGTTCCTCGGCACCGCGCATCCGGCGAAATTTAAAGAGAGCGTGGAAGCGATTCTCGGTGAAACGTTGGATCTGCCAAAAGAGCTGGCAGAACGTGCTGATTTACCCTTGCTTTCACAT\n";

		char[] tab = { 'A', 'T', 'C', 'G' };
		double total = 0;
		boolean b = false;
		String buf = chaine1.substring(0, 3);
		int imax = chaine1.length();
		long NbGene = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				for (int k = 0; k < tab.length; k++) {
					G1.put("" + tab[i] + tab[j] + tab[k], 0.0);
				}
			}
		}

		for (int i = 3; i < chaine1.length(); i += 3) {

			if ((buf.equals("TAA") || (buf.equals("TAG") || buf.equals("TGA"))))
				b = false;

			if (buf.equals("ATG")) {
				b = true;
				NbGene++;
			}

			if (b) {
				G1.put(buf, G1.get(buf) + 1.0);
			}

			if (i >= imax)
				break;
			if (chaine1.charAt(i) == '\n') {
				i++;
			}

			if (i >= imax)
				break;

			buf += chaine1.charAt(i);

			if (i + 1 >= imax)
				break;

			if (chaine1.charAt(i + 1) == '\n') {
				i++;
			}

			if (i + 1 >= imax)
				break;

			buf += chaine1.charAt(i + 1);

			if (i + 2 >= imax)
				break;

			if (chaine1.charAt(i + 2) == '\n') {
				i++;
			}

			if (i + 2 >= imax)
				break;

			buf += chaine1.charAt(i + 2);
			buf = buf.substring(3, 6);
		}


		total = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				for (int k = 0; k < tab.length; k++) {
					total += G1.get("" + tab[i] + tab[j] + tab[k]);
				}
			}
		}
		
		G1.put("tot", total);

		try {
			FileWriter fw = new FileWriter(f1);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(G1.toString());
			output.write("\n");
			output.flush();
			output.close();

		} catch (IOException ioe) {
			System.out.print("Erreur durant l'écriture init3()");
		}

		System.out
				.println("init3() terminé pour la première séquence\n"
						+ NbGene
						+ " Gènes ont été trouvé, résulats stockés dans /src/FichierCalcul");

		b = false;
		buf = chaine2.substring(0, 3);
		imax = chaine2.length();
		NbGene = 0;
		
		
		
		for (int i = 3; i < chaine2.length(); i += 3) {

			if ((buf.equals("TAA") || (buf.equals("TAG") || buf.equals("TGA"))))
				b = false;

			if (buf.equals("ATG")) {
				b = true;
				NbGene++;
			}

			if (b) {
				G1.put(buf, G1.get(buf) + 1.0);
			}

			if (i >= imax)
				break;
			if (chaine2.charAt(i) == '\n') {
				i++;
			}
			if (i >= imax)
				break;

			buf += chaine2.charAt(i);

			if (i + 1 >= imax)
				break;

			if (chaine2.charAt(i + 1) == '\n') {
				i++;
			}
			if (i + 1 >= imax)
				break;

			buf += chaine2.charAt(i + 1);

			if (i + 2 >= imax)
				break;

			if (chaine2.charAt(i + 2) == '\n') {
				i++;
			}
			if (i + 2 >= imax)
				break;

			buf += chaine2.charAt(i + 2);
			buf = buf.substring(3, 6);
		}
		
		total = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				for (int k = 0; k < tab.length; k++) {
					total += G1.get("" + tab[i] + tab[j] + tab[k]);
				}
			}
		}
		
		G1.put("tot", total);
		
		try {
			FileWriter fw = new FileWriter(f2);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(G1.toString());
			output.write("\n");
			output.flush();
			output.close();

		} catch (IOException ioe) {
			System.out.print("Erreur durant l'écriture init3()");
		}

		System.out
				.println("init3() terminé pour la deuxième séquence\n"
						+ NbGene
						+ " Gènes ont été trouvé, résulats stockés dans /src/FichierCalcul");

		b = false;
		buf = chaine3.substring(0, 3);
		imax = chaine3.length();
		NbGene = 0;
		
		
		for (int i = 3; i < chaine3.length(); i += 3) {

			if ((buf.equals("TAA") || (buf.equals("TAG") || buf.equals("TGA"))))
				b = false;

			if (buf.equals("ATG")) {
				b = true;
				NbGene++;
			}

			if (b) {
				G1.put(buf, G1.get(buf) + 1.0);
			}

			if (i >= imax)
				break;
			if (chaine3.charAt(i) == '\n') {
				i++;
			}
			if (i >= imax)
				break;

			buf += chaine3.charAt(i);

			if (i + 1 >= imax)
				break;

			if (chaine3.charAt(i + 1) == '\n') {
				i++;
			}
			if (i + 1 >= imax)
				break;

			buf += chaine3.charAt(i + 1);

			if (i + 2 >= imax)
				break;

			if (chaine3.charAt(i + 2) == '\n') {
				i++;
			}
			if (i + 2 >= imax)
				break;

			buf += chaine3.charAt(i + 2);
			buf = buf.substring(3, 6);
		}
		
		total=0;
		
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				for (int k = 0; k < tab.length; k++) {
					total += G1.get("" + tab[i] + tab[j] + tab[k]);
				}
			}
		}

		G1.put("tot", total);

		try {
			FileWriter fw = new FileWriter(f3);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(G1.toString());
			output.write("\n");
			output.flush();
			output.close();

		} catch (IOException ioe) {
			System.out.print("Erreur durant l'écriture init3()");
		}

		System.out
				.println("init3() terminé pour la troisième séquence\n"
						+ NbGene
						+ " Gènes ont été trouvé, résulats stockés dans /src/FichierCalcul");

		System.out.println("init3() terminé");

	}

	public String toString() {
		String s = "";
		s += "Genome1 : \n" + hNC_017626.toString() + "\n";
		s += "Genome2 : \n" + hNC_018520.toString() + "\n";
		s += "Genome3 : \n" + hNC_019896.toString() + "\n";

		return s;
	}

	public HashMap<String, Integer> gethNC_017626() {
		return hNC_017626;
	}

	public HashMap<String, Integer> gethNC_018520() {
		return hNC_018520;
	}

	public HashMap<String, Integer> gethNC_019896() {
		return hNC_019896;
	}

}
