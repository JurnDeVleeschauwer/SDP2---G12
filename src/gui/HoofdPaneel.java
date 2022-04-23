package gui;

import javafx.scene.layout.BorderPane;

import java.util.*;

import domain.CategoryController;

/**
 * 
 * @author Jurn
 *
 */
public class HoofdPaneel extends BorderPane {
    private CategoryController dc;
    private AanmeldPaneel aanmelden;
    private CategorieenPaneel categoriePaneel;


    /**
     * 
     * @param CategoryController
     */
    public HoofdPaneel(CategoryController dc) {
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
        this.categoriePaneel=new CategorieenPaneel(this);
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
