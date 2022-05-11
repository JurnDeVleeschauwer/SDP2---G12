package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.MvoGoalChild;
import domain.MvoGoalComp;
import domain.MvoGoalController;
import domain.SdgComp;
import domain.SdgController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

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
			/*List<String> resultaat=SdgWijzigenPopup.display();
			if(resultaat!=null) {
				
				sdgController.updateSdg();
				update();
			}*/
		}
		
		
		private void createButton(ActionEvent event) {
			/*
				List<String> resultaat= SdgAanmakenPopup.display();
				if(!resultaat.isEmpty()) {
					sdgController.addMvoGoal(resultaat.get(0),resultaat.get(1), null, true);
				
			};*/
		}
		
		private void maakGrid() {
			this.gridLinesVisibleProperty().set(true);
			
			getChildren().clear();
			
			Label title = new Label("SDG");
			title.setFont(new Font("Arial", 30));
			add( title, 5, 0);
			
			
			SdgComp sdg = new SdgComp( "naamSdgComp", "DescriptieSdgComp",new ArrayList<>());//(SdgComp) sdgController.getSdg(this.id);
			
			
			Label id = new Label("ID:");
			id.setFont(new Font("Arial", 15));
			add( id, 1, 1);
			add(new Label(Integer.toString(sdg.getId())), 2, 1);
			Label category = new Label("Category:");
			category.setFont(new Font("Arial", 15));
			add( category, 1, 2);
			Label name = new Label("Name:");
			name.setFont(new Font("Arial", 15));
			add( name, 1, 3);
			add(new Label(sdg.getName()), 2, 3);
			
			
			
			Button deleteButtonAction = new Button("Verwijderen");
			deleteButtonAction.setOnAction(this::deleteButtonAction);
			add(deleteButtonAction, 12, 11);

			Button editButton = new Button("Wijzigen");
			editButton.setOnAction(this::editButton);
			add(editButton, 13, 11);

			Button createButton = new Button("Aanmaken");
			createButton.setOnAction(this::createButton);
			add(createButton, 14, 11);
			


		}
		
	

	}



