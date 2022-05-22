package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import exceptions.MvoGoalException;
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
	private HashMap<String, Exception> errorsMap = new HashMap<String, Exception>();

	
	public static class Builder {
		private GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper = new GenericMapperJpa<>(MvoGoalAbstract.class);
		private String name;
		private List<MvoGoalChild> mvoGoals = new ArrayList<>();
		private HashMap<String, Exception> errorsMap = new HashMap<String, Exception>();

		public Builder mvoGoalMapper(GenericMapperJpa<MvoGoalAbstract> mvoGoalMapper) {
			this.mvoGoalMapper = mvoGoalMapper;
			return this;
		}

		public Builder name(String name) {

			try {
				this.name = name;

			} catch (Exception e) {
				errorsMap.put("name", e);
			}
			return this;
		}

		public Builder mvoGoals(List<MvoGoalChild> mvoGoals) {
			try {

				this.mvoGoals = mvoGoals;
			} catch (Exception e) {
				errorsMap.put("mvoGoals", e);
			}

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
		this.errorsMap=builder.errorsMap;

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

	public Integer getValue() {
		return null;
	}

	public List<MvoGoalChild> getSdgs() {
		return mvoGoals;
	}

	public HashMap<String, Exception> getErrorsMap() {
		return errorsMap;
	}

	public GenericMapperJpa<MvoGoalAbstract> getMvoGoalMapper() {
		return mvoGoalMapper;
	}

	public List<MvoGoalChild> getMvoGoals() {
		return mvoGoals;
	}

	public String getMvoName() {
		return null;
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
