package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
		public static CategoryController populateDatabase(CategoryController cc,DatasourceController dc, MvoCoordinatorController mcc,MvoGoalController mgc, SdgController sc) throws FileNotFoundException {
		
		cc.addCategory("Electricity", "Electricity.png");
		cc.addCategory("Climate", "Climate.png");

		ArrayList<String> rolesYorben = new ArrayList<>();
		rolesYorben.add("MVO coördinator");
		rolesYorben.add("User");
		mcc.insertMvoCoordinator("Yorben", "123456789", rolesYorben);

		ArrayList<String> rolesEddy = new ArrayList<>();
		rolesEddy.add("User");
		mcc.insertMvoCoordinator("Eddy", "123456789", rolesEddy);
		
		dc.addDatasource("Electricity Consuption", "Watt", "Month", 2022, 50, new File("src/maandenTest.txt")); 

		dc.addDatasource("Tree Consumption", "Watt", "Month", 2022, 35, new File("src/gemiddeldeTest.txt"));
		mgc.addMvoGoalComp("Eradicate poverty by 2030 everywhere");
		mgc.addSubMvoGoal(0, 60, dc.getDatasource(1), "electricity.png", "Poverty");

		
		sc.addSdg("No Poverty", "End poverty in all its forms everywhere");
		sc.addSubSdg("Eradicate Extreme Poverty", "sdgGoal1.png", mgc.getMvoGoal(0), sc.getSdg(1) , 60,  1);
		sc.addSubSdg("Ensure equal income", "sdgGoal1.png", mgc.getMvoGoal(0), sc.getSdg(1) , 60,  1);

		sc.addSdg("Zero hunger", "Eliminate food insecurity globally");
		sc.addSubSdg("Ensure food supply access", "sdgGoal2.png", mgc.getMvoGoal(0), sc.getSdg(5) , 60,  5);
		sc.addSubSdg("Increase agricultural production", "sdgGoal2.png", mgc.getMvoGoal(0), sc.getSdg(5) , 60,  5);

		cc.addSdgToCategory(0, 1);
		cc.addSdgToCategory(0, 5);

		return cc;


	}
}
