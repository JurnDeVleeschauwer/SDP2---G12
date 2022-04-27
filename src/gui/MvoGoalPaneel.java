
package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.MvoGoalComp;
import domain.MvoGoalController;
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
	public class MvoGoalPaneel extends GridPane {
		private final MvoGoalController mvoGoalController;
		private final HoofdPaneel hoofdPaneel;
		private int id;


		public MvoGoalPaneel(HoofdPaneel hoofdPaneel, MvoGoalController mvoGoalController) {
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
		
		private void deleteButtonAction(ActionEvent event) {
			//MvoGoalController.deleteMvoGoal(mvoGoalController.getMvoGoal(this.id));
			//hoofdPaneel.toonCategoriePaneell();
		}

		private void editButton(ActionEvent event) {
			//MvoGoalController.edit();
			hoofdPaneel.toonMvoGoalEditPaneel(this.id);
		}
		
		private void maakGrid() {
			getChildren().clear();
			
			MvoGoalComp mvoGoal = new MvoGoalComp(1, "gerge", new ArrayList<>(), new Category("rfezrg", "rfezger", 2, true));//(MvoGoalComp) mvoGoalController.getMvoGoal(this.id);
			
			Label id = new Label(Integer.toString(mvoGoal.getId()));
			add(id, 1, 1);
			Label categoryid = new Label(Integer.toString(mvoGoal.getCategoryID()));
			add(categoryid, 2, 1);
			Label naam = new Label(mvoGoal.getName());
			add(naam, 3, 1);
			Label sdgs = new Label(mvoGoal.getSdgs().toString());
			add(sdgs, 4, 1);
			
			
			
			Button deleteButtonAction = new Button("Delete");
			deleteButtonAction.setOnAction(this::deleteButtonAction);
			add(deleteButtonAction, 12, 11);

			Button editButton = new Button("Edit");
			editButton.setOnAction(this::editButton);
			add(editButton, 13, 11);

			


		}

	

	}

