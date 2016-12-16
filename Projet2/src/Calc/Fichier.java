package Calc;

public enum Fichier {
	
	NC_017626("NC_017626", "src/Fasta/NC_017626.fna"),
	NC_018520("NC_018520", "src/Fasta/NC_018520.fna"),
	NC_019896("NC_019896", "src/Fasta/NC_019896.fna"),
	
	NC_017626calc("NC_017626", "src/FichierCalcul/NC_017626.calc"),
	NC_018520calc("NC_018520", "src/FichierCalcul/NC_017626.calc"),
	NC_019896calc("NC_019896", "src/FichierCalcul/NC_017626.calc");
	
	public String name ="";
	public String path ="";
	
	
	Fichier(String name, String path){
		this.name=name;
		this.path=path;
	}

}
