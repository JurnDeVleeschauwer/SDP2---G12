package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.*;

import domain.CategoryController;
import domain.Datasource;
import domain.DatasourceController;
import domain.DatasourceReader;
import domain.DomeinController;
import domain.MvoCoordinatorController;
import domain.MvoGoalAbstract;
import domain.MvoGoalChild;
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
	private CategoryController cc;
	private DatasourceController dc;
	private MvoCoordinatorController mcc;
	private MvoGoalController mgc;
	private SdgController sc;
	private ListSdgPaneel listSdgPaneel;

	
	/**
	 * 
	 * @param categoryController
	 * @param sc
	 * @param mgc
	 * @param mcc
	 * @param dc
	 * @param CategoryController
	 */

	public HoofdPaneel(CategoryController categoryController, DatasourceController dc, MvoCoordinatorController mcc,
			MvoGoalController mgc, SdgController sc) {
		this.cc = categoryController;
		this.dc = dc;
		this.mcc = mcc;
		this.mgc = mgc;
		this.sc = sc;
		this.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
		this.setId("hoofdpanneel_id");
		

		createPanelen();
		voegComponentenToe();
	}

	private void voegComponentenToe() {
		setCenter(mvoGoalPaneel);
		

	toonAanmeldPaneel();
//		toonDatasource();
	}

	public void toonDashboard() {
		setTop(dashboard);
	}
	public void enableDashboard() {
		setTop(dashboard);
	}
	/**
	 * @param categoryController
	 * 
	 */
	public void createPanelen() {
		this.aanmelden = new AanmeldPaneel(this, mcc);

		this.categoriePaneel = new CategorieenPaneel(this, cc,sc);
		this.mvoGoalPaneel = new MvoGoalPaneel(this, mgc, dc);
		this.sdgPaneel = new SdgPaneel(this, sc, mgc);
		this.dashboard = new Dashboard(this,mcc);
		this.listMvoGoalPaneel = new ListMvoGoalPaneel(this, mgc, dc);
		this.listSdgPaneel = new ListSdgPaneel(this, sc, mgc);

	}

	/**
	 * toon AanmeldPaneel
	 */
	public void toonAanmeldPaneel() {
		setCenter(aanmelden);
	}
	public void toonNewAanmeldPaneel() {
		aanmelden = new AanmeldPaneel(this, mcc);
		setCenter(aanmelden);
		setTop(null);
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
		listMvoGoalPaneel.voegComponentenToe();
		setCenter(listMvoGoalPaneel);
	}
	
	public void toonListSdgPaneel() {
		listSdgPaneel.voegComponentenToe();
		setCenter(listSdgPaneel);
	}

	public void toonDatasource(MvoGoalChild mvoGoal,Datasource datasource,int mvoId) {
		setCenter(new DataSourceView(mgc,mvoGoal,this, dc,datasource,mvoId));
	}

}
