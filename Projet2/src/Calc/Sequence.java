package Calc;

import java.io.File;

public class Sequence {

	Fasta f;

	/**
	 * Constructeur
	 */
	public Sequence() {
		f = new Fasta(false);
	}

	/**
	 * Obtenir la frequence d'apparition d'un char dans un fichier Fasta.
	 * 
	 * @param c
	 *            Le char recherché.
	 * @param a
	 *            Le fichier à étudié.
	 * @return La valeur du nombre de c sur le nombre de char total.
	 */
	public double FreqApp(char c, Fichier a) {
		File fic = new File("");
		double n = 0;
		double total = 0;

		if (a == Fichier.NC_017626)
			fic = new File("src/FichierCalcul/NC_017626.calc");
		if (a == Fichier.NC_018520)
			fic = new File("src/FichierCalcul/NC_018520.calc");
		if (a == Fichier.NC_019896)
			fic = new File("src/FichierCalcul/NC_019896.calc");

		String s = f.CorpusToStrSansSaut(fic);
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			if ('t' == s.charAt(i)) {
				i += 2;
				while ((s.charAt(i) <= '9') && (s.charAt(i) >= '0')
						&& (s.charAt(i)) != '\0') {
					res += s.charAt(i);
					i++;
				}
				total = (double) Integer.parseInt(res);
				break;
			}
		}

		String res1 = "";
		for (int i = 0; i < s.length(); i++) {
			if (c == s.charAt(i)) {
				i += 2;
				while ((s.charAt(i) <= '9') && (s.charAt(i) >= '0')
						&& (s.charAt(i)) != '\0') {
					res1 += s.charAt(i);
					i++;
				}
				n = (double) Integer.parseInt(res1);
				break;

			}
		}
		return n / total;
	}

	/**
	 * Obtenir la proba d'apparition d'une sequence dans un fichier Fasta selon
	 * la formule donnée dans le sujet.
	 * 
	 * @param seq
	 *            La sequence recherchée.
	 * @param a
	 *            Le fichier à étudié.
	 * @return Muliplication des FreqApp sur charque char de la sequence.
	 */

	public double ProbaApp(String seq, Fichier a) {

		double n = 1;
		for (int i = 0; i < seq.length(); i++) {
			n *= FreqApp(seq.charAt(i), a);
		}
		return n;
	}

	/**
	 * Predit le fichier le plus probable à la sequence.
	 * 
	 * @param seq
	 *            La sequence étudiée
	 * 
	 * @return Le fichier ou la valeur de ProbaApp est la plus grande.
	 */
	public Fichier Prediction(String seq) {

		Fichier a = Fichier.NC_017626;

		double max = ProbaApp(seq, Fichier.NC_017626);
		if (ProbaApp(seq, Fichier.NC_018520) > max) {
			max = ProbaApp(seq, Fichier.NC_018520);
			a = Fichier.NC_018520;
		}
		if (ProbaApp(seq, Fichier.NC_019896) > max) {
			max = ProbaApp(seq, Fichier.NC_019896);
			a = Fichier.NC_019896;
		}

		return a;
	}

	/**
	 * Obtenir la frequence d'apparition d'un codon dans un fichier Fasta.
	 * 
	 * @param codon
	 *            Le cdon recherché.
	 * @param a
	 *            Le fichier à étudié.
	 * @return La valeur du nombre de codon sur le nombre de char total.
	 */

	public double FreqAppCodon(String codon, Fichier a) {

		for (int i = 0; i < codon.length(); i++) {
			if (codon.charAt(i) != 'A' && codon.charAt(i) != 'T'
					&& codon.charAt(i) != 'G' && codon.charAt(i) != 'C') {
				System.out.println("Erreur la chaine doit être un codon ");
				return 0.0;
			}
		}

		if (codon.length() != 3) {
			System.out.println("Erreur la chaine doit être un codon");
			return 0.0;
		}
		File fic = new File("");
		double n = 0;
		double total = 0;

		if (a == Fichier.NC_017626)
			fic = new File("src/FichierCalcul/NC_017626.calc");
		if (a == Fichier.NC_018520)
			fic = new File("src/FichierCalcul/NC_018520.calc");
		if (a == Fichier.NC_019896)
			fic = new File("src/FichierCalcul/NC_019896.calc");

		String s = f.CorpusToStrSansSaut(fic);

		String res = "";
		String buf = s.substring(0, 3);

		for (int i = 0; i < s.length(); i++) {
			if ("tot".equals(buf) == true) {
				i++;
				while ((s.charAt(i) <= '9') && (s.charAt(i) >= '0')
						&& (s.charAt(i)) != '\0') {
					res += s.charAt(i);
					i++;
				}
				total = (double) Integer.parseInt(res);

				break;
			}

			buf += s.charAt(i);
			buf = buf.substring(1, 4);

		}

		buf = s.substring(0, 3);
		String res1 = "";
		for (int i = 0; i < s.length(); i++) {
			if (codon.equals(buf) == true) {
				i++;
				while ((s.charAt(i) <= '9') && (s.charAt(i) >= '0')
						&& (s.charAt(i)) != '\0') {
					res1 += s.charAt(i);
					i++;
				}
				n = (double) Integer.parseInt(res1);
				break;
			}//
			buf += s.charAt(i);
			buf = buf.substring(1, 4);
		}

		return n / total;
	}

	/**
	 * Obtenir la proba d'apparition d'une sequence dans un fichier Fasta selon
	 * la formule donnée dans le sujet.
	 * 
	 * @param seq
	 *            La sequence recherchée.
	 * @param a
	 *            Le fichier à étudié.
	 * @return Muliplication des FreqApp sur charque codon de la sequence.
	 */

	public double ProbaAppSequence(String seq, Fichier a) {

		double n = 1;
		String buf = seq.substring(0, 3);

		for (int i = 0; i < seq.length(); i++) {
			buf += seq.charAt(i);

			if (i % 3 == 0) {
				buf = buf.substring(1, 4);

				n *= FreqAppCodon(buf, a);
			}
		}
		return n;
	}

	/**
	 * Obtenir la frequence d'apparition d'un codon dans un fichier .gene ayant
	 * récupérer les codons dans les gènes.
	 * 
	 * @param codon
	 *            Le cdon recherché.
	 * @param a
	 *            Le fichier à étudié.
	 * @return La valeur du nombre de codon sur le nombre de char total.
	 */

	public double FreqAppCodonGene(String codon, Fichier a) {

		for (int i = 0; i < codon.length(); i++) {
			if (codon.charAt(i) != 'A' && codon.charAt(i) != 'T'
					&& codon.charAt(i) != 'G' && codon.charAt(i) != 'C') {
				System.out.println("Erreur la chaine doit être un codon ");
				return 0.0;
			}
		}

		if (codon.length() != 3) {
			System.out.println("Erreur la chaine doit être un codon");
			return 0.0;
		}
		File fic = new File("");
		double n = 0;
		double total = 0;

		if (a == Fichier.NC_017626)
			fic = new File("src/FichierCalcul/NC_017626.gene");
		if (a == Fichier.NC_018520)
			fic = new File("src/FichierCalcul/NC_018520.gene");
		if (a == Fichier.NC_019896)
			fic = new File("src/FichierCalcul/NC_019896.gene");

		String s = f.CorpusToStrSansSaut(fic);

		String res = "";
		String buf = s.substring(0, 3);

		for (int i = 0; i < s.length(); i++) {
			if ("tot".equals(buf) == true) {
				i++;
				while ((s.charAt(i) <= '9') && (s.charAt(i) >= '0')
						&& (s.charAt(i)) != '\0') {
					res += s.charAt(i);
					i++;
				}
				total = (double) Integer.parseInt(res);

				break;
			}

			buf += s.charAt(i);
			buf = buf.substring(1, 4);

		}

		buf = s.substring(0, 3);

		String res1 = "";
		for (int i = 0; i < s.length(); i++) {
			if (codon.equals(buf) == true) {
				i++;
				while ((s.charAt(i) <= '9') && (s.charAt(i) >= '0')
						&& (s.charAt(i)) != '\0') {
					res1 += s.charAt(i);
					i++;
				}
				n = (double) Integer.parseInt(res1);
				break;
			}
			buf += s.charAt(i);
			buf = buf.substring(1, 4);

		}

		return n / total;
	}

	/**
	 * Obtenir la proba d'apparition d'une sequence dans un fichier .gene
	 * 
	 * @param seq
	 *            La sequence recherchée.
	 * @param a
	 *            Le fichier à étudié.
	 * @return Muliplication des FreqApp sur charque codon de la sequence.
	 */

	public double ProbaAppSequenceGene(String seq, Fichier a) {

		double n = 1;
		String buf = seq.substring(0, 3);

		for (int i = 0; i < seq.length(); i++) {
			buf += seq.charAt(i);

			if (i % 3 == 0) {
				buf = buf.substring(1, 4);

				n *= FreqAppCodonGene(buf, a);
			}
		}
		return n;
	}

	/**
	 * Permet de prédire si la sequence donnée en paramètre est ou non un gène.
	 * * @param seq La sequence recherchée.
	 * 
	 * @return true si la fonction prédit un gène, false sinon.
	 */

	public boolean ProbaGene(String seq) {

		String buf = "";
		String buf2 = seq.substring(0, 3);
		for (int i = 3; i < seq.length() - 2; i += 3) {
			buf += buf2;

			if ((buf2.equals("TAA") || (buf2.equals("TAG") || buf2
					.equals("TGA")))) {
				buf = buf.substring(0, buf.length() - 3);
				break;
			}

			buf2 += seq.charAt(i);
			buf2 += seq.charAt(i + 1);
			buf2 += seq.charAt(i + 2);
			buf2 = buf2.substring(3, 6);
		}
		

		@SuppressWarnings("unused")
		Fichier a;
		boolean gene = false;
		double max;

		if (ProbaAppSequence(buf, Fichier.NC_017626) > ProbaAppSequenceGene(
				buf, Fichier.NC_017626)) {
			max = ProbaAppSequence(buf, Fichier.NC_017626);
			a = Fichier.NC_017626;
			gene = false;
		} else {
			max = ProbaAppSequenceGene(buf, Fichier.NC_017626);
			a = Fichier.NC_017626;
			gene = true;
		}

		if (ProbaAppSequence(buf, Fichier.NC_018520) > max) {
			max = ProbaAppSequence(buf, Fichier.NC_018520);
			a = Fichier.NC_018520;
			gene = false;
		} else {
			if (ProbaAppSequenceGene(buf, Fichier.NC_018520) > max) {
				max = ProbaAppSequenceGene(buf, Fichier.NC_018520);
				a = Fichier.NC_018520;
				gene = true;
			}
		}

		if (ProbaAppSequence(buf, Fichier.NC_019896) > max) {
			max = ProbaAppSequence(buf, Fichier.NC_019896);
			a = Fichier.NC_019896;
			gene = false;
		} else {
			if (ProbaAppSequenceGene(buf, Fichier.NC_019896) > max) {
				max = ProbaAppSequenceGene(buf, Fichier.NC_019896);
				a = Fichier.NC_019896;
				gene = true;
			}
		}

		if (gene){
			System.out.println("C'est un gène");
			return true;
		}
		else{
			System.out.println("Ce n'est pas un gène");
			return false;
		}
			
	}

	/**
	 * Permet de prédire si la sequence de longueur l donnée en paramètre est ou
	 * non un gène.
	 * 
	 * @param seq
	 *            La sequence recherchée.
	 * 
	 * @param longueur
	 *            Longueur.
	 */

	public void ProbaGeneLongueur(String seq, int longueur) {
		String buf = "";

		if (seq.length() > longueur) {
			buf = seq.substring(0, longueur);
		} else
			buf = seq;

		ProbaGene(buf);
	}

}
