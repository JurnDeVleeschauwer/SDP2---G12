package ui;

import domain.CategoryController;
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
		
		  GenericMapperJpa.startTransaction();
		  
			MvoCoordinator coord = mvoC.login("YorbEen", "123123"); 
			System.out.println(coord.getUsername() + "" + coord.getPassword());

		  
		 GenericMapperJpa.commitTransaction();
		 GenericMapperJpa.closePersistency(); 
		

		
		// int languageChoice = app.chooseLanguage();
		

		
	}

}
