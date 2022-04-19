package ui;

import domain.CategoryController;

public class StartUp {

	public static void main(String[] args) {
		
		run(); 

		
	}
	
	
	
	static void run() {
		CategoryController dc = new CategoryController();
		MultiLanguageApp app = new MultiLanguageApp(); 
		
		int languageChoice = app.chooseLanguage();
		
		System.out.println(app.translate(languageChoice, "LangTest"));
		
	}

}
