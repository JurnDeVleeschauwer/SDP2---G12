package gui;

import javafx.scene.layout.BorderPane;

import java.util.*;

import domain.CategoryController;
import domain.DomeinController;

/**
 * 
 * @author Jurn
 *
 */
public class HoofdPaneel extends BorderPane {
    private final DomeinController dc;
    private AanmeldPaneel aanmelden;
    private CategorieenPaneel categoriePaneel;


    /**
     * 
     * @param CategoryController
     */
    public HoofdPaneel(DomeinController dc) {
        this.dc = dc;
        
        createPanelen();
        voegComponentenToe();
    }

    private void voegComponentenToe() {
    	setCenter(categoriePaneel);
    }

    /**
     * 
     */
    public void createPanelen() {
        this.aanmelden = new AanmeldPaneel(dc, this);
        this.categoriePaneel=new CategorieenPaneel(this, new DomeinController());
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
 

}
