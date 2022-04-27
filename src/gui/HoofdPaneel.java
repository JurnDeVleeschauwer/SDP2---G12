package gui;

import javafx.scene.layout.BorderPane;

import java.util.*;

import domain.CategoryController;
import domain.CategoryController;

/**
 * 
 * @author Jurn
 *
 */
public class HoofdPaneel extends BorderPane {
    private final CategoryController categorieController;
    private AanmeldPaneel aanmelden;
    private CategorieenPaneel categoriePaneel;


    /**
     * 
     * @param CategoryController
     */
    public HoofdPaneel(CategoryController categorieController) {
        this.categorieController = categorieController;
        
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
        this.aanmelden = new AanmeldPaneel(categorieController, this);
        this.categoriePaneel=new CategorieenPaneel(this, new CategoryController());
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
