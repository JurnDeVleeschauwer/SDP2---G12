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


    /**
     * 
     * @param CategoryController
     */
    public HoofdPaneel(CategoryController dc) {
        this.dc = dc;

        voegComponentenToe();
    }

    private void voegComponentenToe() {
    }

    /**
     * 
     */
    public void createPanelen() {

    }

 

}
