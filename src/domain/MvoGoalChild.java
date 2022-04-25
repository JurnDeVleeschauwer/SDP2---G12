package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import persistence.GenericMapperJpa;

@Entity
@Table
public class MvoGoalChild extends MvoGoalAbstract implements Serializable {
	/**
	 * int => Object 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int value;
	@OneToOne(targetEntity = Datasource.class,  cascade = CascadeType.ALL)
	private int datasourceID;
	
	@OneToMany(targetEntity = SdgComp.class, cascade = CascadeType.ALL)
	private int sdgID;
	private String icon;
	private String mvoName;
	@ManyToOne(targetEntity = Category.class, cascade = CascadeType.PERSIST)
	private int categoryID;
	@Transient
	private GenericMapperJpa<MvoGoalChild> mvoGoalMapper = new GenericMapperJpa<MvoGoalChild>(MvoGoalChild.class);

	public MvoGoalChild(int id, int value, SdgComp sdgComp, Datasource datasource, String icon, String mvoName) {
		
		setId(id);
		setDatasourceID(datasource);
		setMvoName(mvoName);
		setIcon(icon);
		setValue(value);
		setSdgID(sdgComp);
		
	}
	
	protected MvoGoalChild() {
		
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

	public void setSdgID(SdgComp sdgComp) {
		this.sdgID = sdgComp.getId();
	}

	public int getDatasourceID() {
		return datasourceID;
	}

	public void setDatasourceID(Datasource datasource) {
		this.datasourceID = datasource.getId();
		
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
	

	@Override
	public int hashCode() {
		return Objects.hash(datasourceID, icon, mvoName, sdgID, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MvoGoalChild other = (MvoGoalChild) obj;
		return datasourceID == other.datasourceID && Objects.equals(icon, other.icon)
				&& Objects.equals(mvoName, other.mvoName) && sdgID == other.sdgID && value == other.value;
	}
	
	
	public void addMvoGoal(MvoGoalChild g) {
		
		mvoGoalMapper.insert(g); 
	}
	
	public void removeMvoGoal(MvoGoalChild g) {
		mvoGoalMapper.delete(g);
	}
	
	public MvoGoalChild getMvoGoal(int mvoGoalId) {
		return mvoGoalMapper.get(mvoGoalId); 
	}
	
	public void updateMvoGoal(MvoGoalChild g) {
		mvoGoalMapper.update(g); 
	}

	public void deleteMvoGoal(MvoGoalChild mvoGoal) {
		mvoGoalMapper.delete(mvoGoal);
		
	}
	

}
