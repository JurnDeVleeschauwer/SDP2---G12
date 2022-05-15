package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import exceptions.MvoGoalException;
import exceptions.SdgException;
import persistence.GenericMapperJpa;

@Entity
public class MvoGoalComp extends MvoGoalAbstract implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private final GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper;

	private final String name;

	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
	private final List<MvoGoalChild> mvoGoals;

	public static class Builder {
		private GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper = new GenericMapperJpa<>(MvoGoalAbstract.class);
		private String name;
		private List<MvoGoalChild> mvoGoals = new ArrayList<>();

		public Builder mvoGoalMapper(GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper) {
			this.mvoGoalMapper = mvoGoalMapper;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder mvoGoals(List<MvoGoalChild> mvoGoals) {
			this.mvoGoals = mvoGoals;
			return this;
		}

		public Builder setMvoGoalMapper(GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper) {
			this.mvoGoalMapper = mvoGoalMapper;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setMvoGoals(List<MvoGoalChild> mvoGoals) {
			this.mvoGoals = mvoGoals;
			return this;
		}

		public MvoGoalComp build() {
			return new MvoGoalComp(Builder.this);

		}
	}

	public MvoGoalComp(Builder builder) {
		this.mvoGoalMapper = builder.mvoGoalMapper;
		this.mvoGoals = builder.mvoGoals;
		this.name = builder.name;

	}

	protected MvoGoalComp() {
		this.mvoGoalMapper = null;
		this.name = "";
		this.mvoGoals = null;

	}

	@Override
	public void add(MvoGoalAbstract mvoGoal) throws MvoGoalException {
		mvoGoals.add((MvoGoalChild) mvoGoal);

	}

	@Override
	public void remove(MvoGoalAbstract mvoGoal) throws MvoGoalException {

		mvoGoals.remove(mvoGoal);

	}

	@Override
	public MvoGoalChild getChild(int mvoGoalChildId) throws MvoGoalException {

		return mvoGoals.stream().filter((currentMvo) -> currentMvo.getCounter() == mvoGoalChildId)
				.collect(Collectors.toList()).get(0);

	}

	public String getName() {
		return name;
	}

	public List<MvoGoalChild> getSdgs() {
		return mvoGoals;
	}

	public GenericMapperJpa<MvoGoalAbstract> getMvoGoalMapper() {
		return mvoGoalMapper;
	}

	public List<MvoGoalChild> getMvoGoals() {
		return mvoGoals;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mvoGoals, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MvoGoalComp other = (MvoGoalComp) obj;
		return Objects.equals(mvoGoals, other.mvoGoals) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		String res = "";

		res += String.format(" Name: %s%n", getName());

		for (MvoGoalChild child : mvoGoals) {
			res += child.toString();

		}

		return res;

	}

}
