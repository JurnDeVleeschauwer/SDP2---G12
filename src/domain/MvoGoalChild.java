package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import domain.MvoGoalComp.Builder;
import exceptions.MvoGoalException;
import persistence.GenericMapperJpa;

@Entity
public class MvoGoalChild extends MvoGoalAbstract implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int value;
	@OneToOne(cascade = CascadeType.PERSIST)
	private final Datasource datasource;

	@Transient
	private final int counter ;

	private final String icon;
	private final String mvoName;



	public static class Builder {
		private int value;
		private Datasource datasource;

		private int counter = 1;

		private String icon;
		private String mvoName;


		public Builder value(int value) {
			this.value=value;
			return this;
		}

		public Builder datasource(Datasource datasource) {
			this.datasource=datasource;
			return this;
		}

		public Builder counter(int counter) {
			this.counter=counter;
			return this;
		}
		public Builder icon (String icon) {
			this.icon=icon;
					return this;
		}
		public Builder mvoName(String mvoName) {
			this.mvoName=mvoName;
			return this;
		}

		public void setValue(int value) {
			this.value = value;
		}
		public void setDatasource(Datasource datasource) {
			this.datasource = datasource;
		}
		public void setCounter(int counter) {
			this.counter = counter;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public void setMvoName(String mvoName) {
			this.mvoName = mvoName;
		}
		public MvoGoalChild build() {
			return new MvoGoalChild(Builder.this);

		}


	}




	protected MvoGoalChild() {
		this.value = 0;
		this.datasource = new Datasource();
		this.counter = 1;
		this.icon = "";
		this.mvoName = "";

	}




	public MvoGoalChild(Builder builder) {
		this.counter=builder.counter;
		this.datasource=builder.datasource;
		this.icon=builder.icon;
		this.mvoName=builder.mvoName;
		this.value=builder.value;
	}




	public int getId() {
		return id;
	}


	/*@Override
	public void setId(int id) {
		 String childId = Integer.toString(super.id) + Integer.toString(id);

		this.id = Integer.parseInt(childId);
	} */

	public int getCounter() {
		return counter;
	}


	public int getValue() {
		return value;
	}


	public Datasource getDatasource() {
		return datasource;
	}



	public String getIcon() {
		return icon;
	}

	public String getMvoName() {
		return mvoName;
	}

	public String getName() {
		return null;
	}
	
	@Override
	public boolean isBlad() {
		return true;
	}





	@Override
	public int hashCode() {
		return Objects.hash(datasource, icon, mvoName, value);
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
		return Objects.equals(datasource, other.datasource) && Objects.equals(icon, other.icon)
				&& Objects.equals(mvoName, other.mvoName)
				&& value == other.value;
	}


	@Override
	public String toString() {

		//int id, int value, SdgComp sdgComp, Datasource datasource, String icon, String mvoName
		return String.format("id: %d, value: %d%n,  Datasource: %s, Icon: %s, MvoName: %s",
				getId(), getValue(), getDatasource().toString(), getIcon(), getMvoName() );
	}


	public void get() {
		throw new UnsupportedOperationException();

	}

	public void getChild() {
		throw new UnsupportedOperationException();

	}

	public void add(MvoGoalAbstract sdg) throws MvoGoalException {
		throw new UnsupportedOperationException();


	}
	public void remove(MvoGoalAbstract sdg) throws MvoGoalException {
		throw new UnsupportedOperationException();

	}


	public MvoGoalChild getChild(MvoGoalAbstract sdg) throws MvoGoalException {
		throw new UnsupportedOperationException();
	}

	public void addMvoGoalJpa(MvoGoalAbstract comp) {
		throw new UnsupportedOperationException();

	}

	public void removeMvoGoalJpa(MvoGoalComp comp) {
		throw new UnsupportedOperationException();

	}

	public MvoGoalAbstract getMvoGoalJpa(int id) {
		throw new UnsupportedOperationException();

	}

	public void updateMvoGoal(MvoGoalComp mvoGoalToUpdate) {
		throw new UnsupportedOperationException();

	}
}
