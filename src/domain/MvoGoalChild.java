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
	@OneToOne(cascade = CascadeType.PERSIST)
	private Datasource datasource; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sdgcomp")
	private SdgComp sdgComp;
	private String icon;
	private String mvoName;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;
	@Transient
	private GenericMapperJpa<MvoGoalChild> mvoGoalMapper = new GenericMapperJpa<MvoGoalChild>(MvoGoalChild.class);

	public MvoGoalChild(int id, int value, SdgComp sdgComp, Datasource datasource, String icon, String mvoName) {
		
		setId(id);
		setDatasource(datasource);
		setMvoName(mvoName);
		setIcon(icon);
		setValue(value);
		setSdg(sdgComp);
		
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

	public SdgComp getSdg() {
		return sdgComp;
	}

	public void setSdg(SdgComp sdgComp) {
		this.sdgComp = sdgComp;
	}

	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
		
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
		return Objects.hash(datasource, icon, mvoName, sdgComp, value);
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
		return datasource == other.datasource && Objects.equals(icon, other.icon)
				&& Objects.equals(mvoName, other.mvoName) && sdgComp == other.sdgComp && value == other.value;
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
	
	@Override
	public String toString() {
		
		//int id, int value, SdgComp sdgComp, Datasource datasource, String icon, String mvoName
		return String.format("id: %d, value: %d%n, SdgCompId: %s%n, Datasource: %s, Icon: %s, MvoName: %s",
				getId(), getValue(), getSdg().toString(), getDatasource().toString(), getIcon(), getMvoName() ); 
	}
}
