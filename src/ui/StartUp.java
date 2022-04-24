package ui;

import domain.CategoryController;
import domain.DomeinController;
import gui.HoofdPaneel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

public class StartUp extends Application{

	public static void main(String[] args) {
	        if (args[0].contentEquals("c")) {
	        	CategoryController dc = new CategoryController();
	    		MultiLanguageApp app = new MultiLanguageApp(); 
	    		
	    		int languageChoice = app.chooseLanguage();
	    		
	    		System.out.println(app.translate(languageChoice, "LangTest"));

	        } else {
	            launch(args);
	        }

		
	}
	
	
	@Override
	public void start(Stage stage) {
    	DomeinController dc = new DomeinController();
        

        HoofdPaneel root = new HoofdPaneel(dc);

        Scene scene = new Scene(root, 800, 800);

        stage.setScene(scene);
        stage.setTitle("Fluvius");
        stage.show();
		
	}


}
