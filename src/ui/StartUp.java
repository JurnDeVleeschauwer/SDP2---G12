package ui;

import domain.CategoryController;
import domain.Datasource;
import domain.DatasourceReader;
import domain.MvoCoordinator;
import domain.MvoCoordinatorController;
import persistence.GenericMapperJpa;
import persistence.PopulateDatabase;

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
		 
			PopulateDatabase pd = new PopulateDatabase(); 
		 
		

		
		// int languageChoice = app.chooseLanguage();
		

		
	}

}
