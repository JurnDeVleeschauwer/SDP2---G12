
package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.MvoGoalChild;
import domain.MvoGoalComp;
import domain.MvoGoalController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
	public class MvoGoalPaneel extends GridPane {
		private final MvoGoalController mvoGoalController;
		private final HoofdPaneel hoofdPaneel;
		private int id;
		private TableView<MvoGoalChild> tableView;


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
			/*List<String> resultaat=MvoGoalWijzigenPopup.display();
			if(resultaat!=null) {
				
				mvoGoalController.updateMvoGoal();
				update();
			}*/
		}
		
		private void createButton(ActionEvent event) {
			/*
				List<String> resultaat=MvoGoalAanmakenPopup.display();
				if(!resultaat.isEmpty()) {
					mvoGoalController.addMvoGoal(resultaat.get(0),resultaat.get(1), null, true);
				
			};*/
		}
		
		
		private void maakGrid() {
			getChildren().clear();
			
			Label title = new Label("MVOGoal");
			title.setFont(new Font("Arial", 30));
			add( title, 5, 0);
			
			ArrayList<MvoGoalChild> listMVoChild = new ArrayList<MvoGoalChild>();
			listMVoChild.add(new MvoGoalChild(1, 1, null, null, "null", "Blad"));
			
			MvoGoalComp mvoGoal = new MvoGoalComp(1, "Boom", listMVoChild, new Category("Natuur", "Natuur", true));
			//MvoGoalComp mvoGoal = (MvoGoalComp) mvoGoalController.getMvoGoal(this.id);
			Label id = new Label("ID:");
			id.setFont(new Font("Arial", 15));
			add( id, 1, 1);
			add( new Label(Integer.toString(mvoGoal.getId())), 2, 1);
			Label category = new Label("Category:");
			category.setFont(new Font("Arial", 15));
			add( category, 1, 2);
			add( new Label(mvoGoal.getCategory().getName()), 2, 2);
			Label name = new Label("Name:");
			name.setFont(new Font("Arial", 15));
			add( name, 1, 3);
			add( new Label(mvoGoal.getName()), 2, 3);;
			Label SDGs = new Label("SDGs:");
			SDGs.setFont(new Font("Arial", 15));
			add( SDGs, 1, 4);
			maakTableView(mvoGoal);

			
			
			
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

		private void maakTableView(MvoGoalComp mvoGoal) {
			//getChildren().clear();
		    tableView = new TableView<MvoGoalChild>();
		        
		    TableColumn<MvoGoalChild, String> column1 = new TableColumn<>("id");
		    column1.setCellValueFactory(new PropertyValueFactory<>("id"));


		    TableColumn<MvoGoalChild, String> column2 = new TableColumn<>("value");
		    column2.setCellValueFactory(new PropertyValueFactory<>("value"));

	        TableColumn<MvoGoalChild, String> column3 = new TableColumn<>("mvoName");
	        column3.setCellValueFactory(new PropertyValueFactory<>("mvoName"));

	        tableView.getColumns().add(column1);
	        tableView.getColumns().add(column2);
	        tableView.getColumns().add(column3);
	        
	        for(MvoGoalChild mvoGoalChild : mvoGoal.getSdgs()) {
	        	tableView.getItems().add(mvoGoalChild);
	        }

	        add(tableView, 1, 5);
	        

		}

			
		

	

	}

