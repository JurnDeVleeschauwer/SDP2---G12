package ui;

import domain.CategoryController;

import ui.MultiLanguageApp;
import domain.DomeinController;
import gui.AanmeldPaneel;
import gui.HoofdPaneel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.*;
import domain.Datasource;
import domain.DatasourceController;
import domain.DatasourceReader;
import domain.MvoCoordinator;
import domain.MvoCoordinatorController;
import domain.MvoGoalController;
import domain.SdgController;
import persistence.GenericMapperJpa;
import persistence.PopulateDatabase;
import persistence.PopulateDatabase;

public class StartUp extends Application {

	public static void main(String[] args) throws FileNotFoundException {
		if (args[0].contentEquals("c")) {
			DatasourceController dc = new DatasourceController();
		//	MultiLanguageApp app = new MultiLanguageApp();

		//	int languageChoice = app.chooseLanguage();
			MvoCoordinatorController mcc = new MvoCoordinatorController();
			MvoGoalController mgc = new MvoGoalController();
			SdgController sc = new SdgController();
			CategoryController cg = new CategoryController(sc.getSdgManager());

			PopulateDatabase.populateDatabase(cg, dc, mcc, mgc, sc);
		//	System.out.println(app.translate(languageChoice, "LangTest"));
		//	GenericMapperJpa.commitTransactionAndClose();
		//	GenericMapperJpa.closePersistency();
			System.exit(0);
		} else {
			launch(args);
		}

	}


	
	@Override
	public void start(Stage stage) throws FileNotFoundException {

		DatasourceController dc = new DatasourceController();
		MvoCoordinatorController mcc = new MvoCoordinatorController();
		MvoGoalController mgc = new MvoGoalController();
		SdgController sc = new SdgController();
		CategoryController cg = new CategoryController(sc.getSdgManager());

		PopulateDatabase.populateDatabase(cg, dc, mcc, mgc, sc);
		HoofdPaneel root = new HoofdPaneel(cg, dc, mcc, mgc, sc);

		Scene scene = new Scene(root, 800, 800);

		stage.setScene(scene);
		stage.setTitle("Fluvius");
		stage.show();
	}

	static void run() {

		/*
		 * Vergeet Deze 3 niet voor elke transactie (Was ze zelf vergeten)
		 * GenericMapperJpa.startTransaction(); GenericMapperJpa.commitTransaction();
		 * GenericMapperJpa.closePersistency();
		 */

		// int languageChoice = app.chooseLanguage();

	}

}
