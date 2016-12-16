package Calc;

public enum Language {
	
	French("french", "bin/Corpus/french.txt"),
	Dutch("dutch", "bin/Corpus/dutch.txt"),
	English("english", "bin/Corpus/english.txt"),
	Italian("italian", "bin/Corpus/italian.txt");
	
	public String name ="";
	public String path ="";
	
	
	Language(String name, String path){
		this.name=name;
		this.path=path;
	}

}
