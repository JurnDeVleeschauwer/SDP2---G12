package ui;

import domain.CategoryController;

import domain.DomeinController;
import gui.AanmeldPaneel;
import gui.HoofdPaneel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;
import domain.Datasource;
import domain.DatasourceReader;
import domain.MvoCoordinator;
import domain.MvoCoordinatorController;
import persistence.GenericMapperJpa;
import persistence.PopulateDatabase;
import persistence.PopulateDatabase;

public class StartUp extends Application{

	public static void main(String[] args) {
	        if (args[0].contentEquals("c")) {
	        	DomeinController dc = new DomeinController();
	    		MultiLanguageApp app = new MultiLanguageApp(); 
	    		
	    		int languageChoice = app.chooseLanguage();
	    		
	    		System.out.println(app.translate(languageChoice, "LangTest"));

	        } else {
	            launch(args);
	        }

		
	}
	
	
	@Override
	public void start(Stage stage) {

		PopulateDatabase.populateDatabase();
        HoofdPaneel root = new HoofdPaneel();

        Scene scene = new Scene(root, 800, 800);

        stage.setScene(scene);
        stage.setTitle("Fluvius");
        stage.show();
	}
	
	static void run() {
		
		/*
		 * Vergeet Deze 3 niet voor elke transactie (Was ze zelf vergeten)
		 * GenericMapperJpa.startTransaction();
		 *	GenericMapperJpa.commitTransaction();
		 *	GenericMapperJpa.closePersistency(); 
		 */ 
		 

		 
		

		
		// int languageChoice = app.chooseLanguage();
		


		
	}


}
