package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.MvoGoalComp;
import domain.MvoGoalController;
import domain.SdgComp;
import domain.SdgController;
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
	public class SdgPaneel extends GridPane {
		private final SdgController sdgController;
		private final HoofdPaneel hoofdPaneel;
		private int id;


		public SdgPaneel(HoofdPaneel hoofdPaneel, SdgController sdgController) {
			this.hoofdPaneel = hoofdPaneel;
			this.sdgController = sdgController;

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
			//sdgController.deleteMvoGoal(sdgController.getMvoGoal(this.id));
			//hoofdPaneel.toonCategoriePaneell();
		}

		private void editButton(ActionEvent event) {
			//sdgController.edit();
			hoofdPaneel.toonSdgEditPaneel(this.id);
		}
		
		private void maakGrid() {
			getChildren().clear();
			
			SdgComp sdg = new SdgComp(1, "gerge", new ArrayList<>(), new Category("rfezrg", "rfezger", true));//(SdgComp) sdgController.getSdg(this.id);
			
			Label id = new Label(Integer.toString(sdg.getId()));
			add(id, 1, 1);
			Label categoryid = new Label(sdg.getCategory().toString());
			add(categoryid, 2, 1);
			Label naam = new Label(sdg.getName());
			add(naam, 3, 1);
			Label sdgs = new Label(sdg.getSdgs().toString());
			add(sdgs, 4, 1);
			
			
			
			Button deleteButtonAction = new Button("Delete");
			deleteButtonAction.setOnAction(this::deleteButtonAction);
			add(deleteButtonAction, 12, 11);

			Button editButton = new Button("Edit");
			editButton.setOnAction(this::editButton);
			add(editButton, 13, 11);

			


		}

	

	}



