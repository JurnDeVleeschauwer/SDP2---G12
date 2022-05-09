package gui;

import domain.DomeinController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Dashboard extends GridPane{
	
    private final HoofdPaneel hoofdPaneel;

	  public Dashboard (HoofdPaneel hoofdPaneel)
	    {
	    
	        this.hoofdPaneel = hoofdPaneel;	

	        
	        configureerGrid();
	        voegComponentenToe();
	    }

	
    private void voegComponentenToe() {
    	Button buttonCategoryPaneel = new Button("Categories");
    		buttonCategoryPaneel.setOnAction(e->{
    			hoofdPaneel.toonCategoriePaneell();
    		});
    		
    		add(buttonCategoryPaneel,0,0);
    	
    	Button buttonSdg = new Button("Sdg 1");
    	buttonSdg.setOnAction(e->{
    		hoofdPaneel.toonSdgPaneel(1);
    	});
    	
    	add(buttonSdg,1,0);
    	
    	Button buttonMvogoal = new Button("MvoGoal 1");
    		buttonMvogoal.setOnAction(e->{
    			hoofdPaneel.toonMvoGoalPaneel(0);
    		});
    	add(buttonMvogoal,2,0);
    		
    		Button buttonAanmelden = new Button("Aanmelden");
		buttonAanmelden.setOnAction(e->{
			hoofdPaneel.toonAanmeldPaneel();
		});
    	add(buttonAanmelden,3,0);
		
		Button buttonListMVO = new Button("List Mvo");
		buttonListMVO.setOnAction(e->{
		hoofdPaneel.toonListMvoGoalPaneel();
	});
	add(buttonListMVO,4,0);
	
		
	}


	private void configureerGrid()
    {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        
        getColumnConstraints().addAll(col1, col2);
    }

	

}
