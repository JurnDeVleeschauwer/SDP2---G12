package persistence;

import domain.CategoryController;
import domain.Datasource;
import domain.DatasourceController;
import domain.MvoCoordinatorController;
import domain.MvoGoalAbstract;
import domain.MvoGoalComp;
import domain.MvoGoalController;
import domain.SdgComp;
import domain.SdgController;

public class PopulateDatabase {
	

	
	public static CategoryController populateDatabase(CategoryController cc,DatasourceController dc, MvoCoordinatorController mcc,MvoGoalController mgc, SdgController sc) {

		

		cc.addCategory("Electricity", "Electricity.png");

		mcc.insertMvoCoordinator("Yorben", "123456789");
		dc.addDatasource("Electricity Consuption", "Watt", "Month", 2022, 50); 
		dc.addDatasource("Tree Consuption", "Watt", "Month", 2022, 35);
		mgc.addMvoGoalComp("Eradicate poverty by 2030 everywhere");
		mgc.addSubMvoGoal(0, 60, dc.getDatasource(1), "electricity.png", "Poverty");
		sc.addSdg("No Poverty", "End poverty in all its forms everywhere");
		sc.addSubSdg("Eradicate Extreme Poverty", "sdgGoal1.png", mgc.getMvoGoal(0), sc.getSdg(0) , 60,  0);


		cc.addSdgToCategory(0, sc.getSdg(0).getChild(0).getId());
		return cc;


	}
}
