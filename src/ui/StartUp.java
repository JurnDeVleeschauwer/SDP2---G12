package ui;

import domain.CategoryController;
import domain.Datasource;
import domain.DatasourceReader;
import domain.MvoCoordinator;
import domain.MvoCoordinatorController;
import persistence.GenericMapperJpa;

public class StartUp {

	public static void main(String[] args) {
		
		run(); 

		
	}
	
	
	
	static void run() {
		
		/*
		 * Vergeet Deze 3 niet voor elke transactie (Was ze zelf vergeten)
		 * GenericMapperJpa.startTransaction();
		 *	GenericMapperJpa.commitTransaction();
		 *	GenericMapperJpa.closePersistency(); 
		 */ 
		
		MvoCoordinatorController mvoC = new MvoCoordinatorController();
		MultiLanguageApp app = new MultiLanguageApp(); 
		
		DatasourceReader r = new DatasourceReader(); 
		

		
		// int languageChoice = app.chooseLanguage();
		

		
	}

}
