package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.*;

import domain.CategoryController;
import domain.DatasourceController;
import domain.DomeinController;
import domain.MvoCoordinatorController;
import domain.MvoGoalController;
import domain.SdgController;
import domain.CategoryController;

/**
 * 
 * @author Jurn
 *
 */
public class HoofdPaneel extends BorderPane {
    private AanmeldPaneel aanmelden;
    private CategorieenPaneel categoriePaneel;
    private MvoGoalPaneel mvoGoalPaneel;
    private SdgPaneel sdgPaneel;
    private Dashboard dashboard;
    private ListMvoGoalPaneel listMvoGoalPaneel;


    /**
     * 
     * @param categoryController 
     * @param CategoryController
     */
    public HoofdPaneel(CategoryController categoryController) {
        
        createPanelen(categoryController);
        voegComponentenToe();
    }

    private void voegComponentenToe() {
    	setCenter(mvoGoalPaneel);
    	
    	Button dashboardButton=new Button("Naar dashboard");
    	dashboardButton.setOnAction(e->{toonDashboard();});
    	setBottom(dashboardButton);
    	toonDashboard();
    	//toonSdgPaneel(1);
    	//toonCategoriePaneell();
    }

    private void toonDashboard() {
setCenter(dashboard);		
	}

	/**
	 * @param categoryController 
     * 
     */
    public void createPanelen(CategoryController categoryController) {
       this.aanmelden = new AanmeldPaneel(this,new MvoCoordinatorController());

        this.categoriePaneel=new CategorieenPaneel(this, categoryController);
        this.mvoGoalPaneel = new MvoGoalPaneel(this, new MvoGoalController(), new DatasourceController());
        this.sdgPaneel = new SdgPaneel(this, new SdgController());
        this.dashboard= new Dashboard(this);
        this.listMvoGoalPaneel = new ListMvoGoalPaneel(this, new MvoGoalController(), new DatasourceController());
        
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
    
    
    public void toonSdgPaneel(int id) {
    	sdgPaneel.voegComponentenToe(id);
        setCenter(sdgPaneel);
    }
    
    public void toonListMvoGoalPaneel() {
        setCenter(listMvoGoalPaneel);
    }
    
    

}
