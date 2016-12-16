package Calc;


public class Main {

	public static void main(String[] args) {
		DetLangue d = new DetLangue();
		System.out.println("Performance selon la base de test et la première fonction determination : ");
		System.out.println(d.Performances());
		System.out.println("Performance selon la base de test et la deuxième fonction determination : ");
		System.out.println(d.Performances2());
		System.out.println("Performance selon la base de test et la troisième fonction determination : ");
		System.out.println(d.Performances3());
	}

}
