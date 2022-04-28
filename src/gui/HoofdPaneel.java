package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.*;

import domain.CategoryController;
import domain.DomeinController;
import domain.MvoGoalController;
import domain.SdgController;
import domain.CategoryController;

/**
 * 
 * @author Jurn
 *
 */
public class HoofdPaneel extends BorderPane {
    private CategoryController categorieController;
    private AanmeldPaneel aanmelden;
    private CategorieenPaneel categoriePaneel;
    private MvoGoalPaneel mvoGoalPaneel;
    private MvoGoalEditPaneel mvoGoalEditPaneel;
    private SdgPaneel sdgPaneel;
    private SdgEditPaneel sdgEditPaneel;
    private Dashboard dashboard;


    /**
     * 
     * @param CategoryController
     */
    public HoofdPaneel() {
        
        createPanelen();
        voegComponentenToe();
    }

    private void voegComponentenToe() {
    	setCenter(mvoGoalPaneel);
    	
    	Button dashboardButton=new Button("Naar dashboard");
    	dashboardButton.setOnAction(e->{toonDashboard();});
    	setTop(dashboardButton);
    	toonDashboard();
    	//toonSdgPaneel(1);
    	//toonCategoriePaneell();
    }

    private void toonDashboard() {
setCenter(dashboard);		
	}

	/**
     * 
     */
    public void createPanelen() {
       this.aanmelden = new AanmeldPaneel( this);

        this.categoriePaneel=new CategorieenPaneel(this, new CategoryController());
        this.mvoGoalPaneel = new MvoGoalPaneel(this, new MvoGoalController());
        this.mvoGoalEditPaneel = new MvoGoalEditPaneel(this, new MvoGoalController());
        this.sdgPaneel = new SdgPaneel(this, new SdgController());
        this.sdgEditPaneel = new SdgEditPaneel(this, new SdgController());
        this.dashboard= new Dashboard(this);
    }
    
    /**
     * toon AanmeldPaneel
     */
    public void toonAanmeldPaneel() {
    	
        setCenter(aanmelden);
    }
    public void toonCategoriePaneell() {
        setCenter(categoriePaneel);
    }
    
    public void toonMvoGoalPaneel(int id) {
    	mvoGoalPaneel.voegComponentenToe(id);
        setCenter(mvoGoalPaneel);
    }
    
    public void toonMvoGoalEditPaneel(int id) {
    	mvoGoalEditPaneel.voegComponentenToe(id);
        setCenter(mvoGoalEditPaneel);
    }
    
    public void toonSdgPaneel(int id) {
    	sdgPaneel.voegComponentenToe(id);
        setCenter(sdgPaneel);
    }
    
    public void toonSdgEditPaneel(int id) {
    	sdgEditPaneel.voegComponentenToe(id);
        setCenter(sdgEditPaneel);
    }
    

}
