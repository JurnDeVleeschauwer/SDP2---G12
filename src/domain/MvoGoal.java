package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import persistence.MvoMapper;

@Entity
public class MvoGoal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private int value;
	private int sdgID;
	private int datasourceID;
	/*private int categoryID;*/
	private String icon;
	private String mvoName;
	

	public MvoGoal(int id, int value, int sdgId, int datasourceID, String icon, String mvoName) {
		
		setId(id);
		setDatasourceID(datasourceID);
		setMvoName(mvoName);
		setIcon(icon);
		setValue(value);
		setSdgID(sdgId);
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getSdgID() {
		return sdgID;
	}

	public void setSdgID(int sdgID) {
		this.sdgID = sdgID;
	}

	public int getDatasourceID() {
		return datasourceID;
	}

	public void setDatasourceID(int datasourceID) {
		this.datasourceID = datasourceID;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMvoName() {
		return mvoName;
	}

	public void setMvoName(String mvoName) {
		this.mvoName = mvoName;
	}
	
	public void addMvoGoal(MvoGoal g) {
		
		MvoMapper.add(g); 
	}
	

}
