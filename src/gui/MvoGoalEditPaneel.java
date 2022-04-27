package gui;




import java.util.ArrayList;

import domain.Category;
import domain.Datasource;
import domain.MvoGoalComp;
import domain.MvoGoalController;
import domain.SdgComp;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

	/**
	 * 
	 * @author Jurn
	 *
	 */
	public class MvoGoalEditPaneel extends GridPane {
		private final MvoGoalController mvoGoalController;
		private final HoofdPaneel hoofdPaneel;
		private int id;


		public MvoGoalEditPaneel(HoofdPaneel hoofdPaneel, MvoGoalController mvoGoalController) {
			this.hoofdPaneel = hoofdPaneel;
			this.mvoGoalController = mvoGoalController;

			configureerGrid();
		}

		private void configureerGrid() {
			setPadding(new Insets(10));
			setHgap(2);
			setVgap(2);

		}

		public void voegComponentenToe(int id) {
			this.id = id;
			maakGrid();
		}
		

		private void cancelButtonAction(ActionEvent event) {
			hoofdPaneel.toonMvoGoalPaneel(this.id);
		}

		private void saveButton(ActionEvent event) {
			//MvoGoalController.addMvoGoal(Integer.valueOf(MvoId.getText()), value, mvoGoal.getSdgs(),  datasource,  icon,  mvoName);
			maakGrid();

		}
		
	    private final TextField MvoId = new TextField();
	    private final TextField catgoryId = new TextField();
	    private final TextField Naam = new TextField();

		
		private void maakGrid() {
			getChildren().clear();
			
			MvoGoalComp mvoGoal = new MvoGoalComp(1, "gerge", new ArrayList<>(), new Category("rfezrg", "rfezger", 2, true));//(MvoGoalComp) mvoGoalController.getMvoGoal(this.id);
			
			add( new Label(Integer.toString(mvoGoal.getId())), 1, 1);
			add(MvoId, 1, 2);
			add( new Label(Integer.toString(mvoGoal.getCategoryID())), 2, 1);
			add(catgoryId, 2, 2);
			add( new Label(mvoGoal.getName()), 3, 1);;
			add(Naam, 3, 2);
			add( new Label(mvoGoal.getSdgs().toString()), 4, 1);
			
			
			
			Button cancelButtonAction = new Button("Cancel");
			cancelButtonAction.setOnAction(this::cancelButtonAction);
			add(cancelButtonAction, 12, 11);

			Button saveButton = new Button("Save");
			saveButton.setOnAction(this::saveButton);
			add(saveButton, 13, 11);

			


		}

	

	}



