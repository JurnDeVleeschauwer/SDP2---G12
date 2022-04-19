package ui;

import domain.DomainController;

public class StartUp {

	public static void main(String[] args) {
		
		run(); 

		
	}
	
	
	
	static void run() {
		DomainController dc = new DomainController();
		MultiLanguageApp app = new MultiLanguageApp(); 
		
		int languageChoice = app.chooseLanguage();
		
		System.out.println(app.translate(languageChoice, "LangTest"));
		
	}

}
