package gui;




import java.util.ArrayList;

import domain.Category;
import domain.SdgComp;
import domain.SdgController;
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
		public class SdgEditPaneel extends GridPane {
			private final SdgController sdgController;
			private final HoofdPaneel hoofdPaneel;
			private int id;


			public SdgEditPaneel(HoofdPaneel hoofdPaneel, SdgController sdgController) {
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
			

			private void cancelButtonAction(ActionEvent event) {
				hoofdPaneel.toonSdgPaneel(this.id);
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
				
				SdgComp sdg = new SdgComp(1, "gerge", new ArrayList<>(), new Category("rfezrg", "rfezger", true));//(SdgComp) sdgController.getSdg(this.id);
				
				
				add( new Label(Integer.toString(sdg.getId())), 1, 1);
				add(MvoId, 1, 2);
				add( new Label(sdg.getCategory().toString()), 2, 1);
				add(catgoryId, 2, 2);
				add( new Label(sdg.getName()), 3, 1);;
				add(Naam, 3, 2);
				add( new Label(sdg.getSdgs().toString()), 4, 1);
				
				
				
				Button cancelButtonAction = new Button("Cancel");
				cancelButtonAction.setOnAction(this::cancelButtonAction);
				add(cancelButtonAction, 12, 11);

				Button saveButton = new Button("Save");
				saveButton.setOnAction(this::saveButton);
				add(saveButton, 13, 11);

				


			}

		

		}





