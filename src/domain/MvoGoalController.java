package domain;

import java.util.List;

public class MvoGoalController {

	private MvoGoalManager mvoGoalManager;

	public MvoGoalController() {
		mvoGoalManager = new MvoGoalManager();
	}

	public void addMvoGoalChild(int value, Datasource datasource, String icon, String mvoName) {
		MvoGoalChild goal = new MvoGoalChild.Builder().value(value).datasource(datasource).icon(icon).mvoName(mvoName)
				.build();

		// System.out.println(goal.toString());
		mvoGoalManager.addMvoGoal(goal);
	}

	public void addMvoGoalComp(String name) {

		MvoGoalAbstract goal = new MvoGoalComp.Builder().setName(name).build();
		// System.out.println(goal.getId());
		mvoGoalManager.addMvoGoal(goal);
	}

	public void updateMvoGoal(MvoGoalAbstract MvoGoalToUpdate) {

		mvoGoalManager.updateMvoGoal(MvoGoalToUpdate);
	}

	public MvoGoalAbstract getMvoGoal(int mvoGoalId) {

		return mvoGoalManager.getMvoGoal(mvoGoalId);
	}

	public void deleteMvoGoal(MvoGoalAbstract mvoGoal) {
		mvoGoalManager.deleteMvoGoal(mvoGoal);
	}

	public List<MvoGoalAbstract> getAll() {
		return mvoGoalManager.getAll();
	}

	public void addSubMvoGoal(int mvoGoalCompId, int value, Datasource datasource, String icon, String mvoName) {
		MvoGoalChild mvoGoalChild = new MvoGoalChild.Builder().value(value).datasource(datasource).icon(icon)
				.mvoName(mvoName).build();

		mvoGoalManager.addSubMvoGoal(mvoGoalChild, mvoGoalCompId);
		mvoGoalManager.updateMvoGoal(mvoGoalManager.getMvoGoal(mvoGoalCompId));
	}

}
