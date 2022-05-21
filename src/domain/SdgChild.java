package domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import exceptions.SdgException;

@Entity
public class SdgChild extends SdgAbstract implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private final String description;
	private final String icon;

	private final int target;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private final MvoGoalAbstract mvoGoal;

	public static class Builder {

		private String description;
		private String name;
		private String icon;
		private int target;
		private MvoGoalAbstract mvoGoal;
		private SdgComp sdgComp;

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder icon(String icon) {
			this.icon = icon;
			return this;
		}

		public Builder target(int target) {
			this.target = target;
			return this;
		}

		public Builder mvoGoal(MvoGoalAbstract mvoGoal) {
			this.mvoGoal = mvoGoal;
			return this;
		}

		public Builder sdgComp(SdgComp sdgComp) {
			this.sdgComp = sdgComp;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setIcon(String icon) {
			this.icon = icon;
			return this;
		}

		public Builder setTarget(int target) {
			this.target = target;
			return this;

		}

		public Builder setMvoGoal(MvoGoalAbstract mvoGoal) {
			this.mvoGoal = mvoGoal;
			return this;

		}

		public Builder setSdgComp(SdgComp sdgComp) {
			this.sdgComp = sdgComp;
			return this;
		}

		public SdgChild build() {
			return new SdgChild(Builder.this);

		}
	}

	protected SdgChild() {
		this.description = "";
		this.icon = "";
		this.target = 0;
		this.mvoGoal = new MvoGoalComp();

	}

	public SdgChild(Builder builder) {
		this.description = builder.description;
		this.name = builder.name;
		this.icon = builder.icon;
		this.target = 0;
		this.mvoGoal = new MvoGoalComp();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public String getIcon() {
		return icon;
	}

	public int getTarget() {
		return target;
	}

	@Override
	public String toString() {
		return String.format("id: %s, icon: %s, description: %s", getId(), getIcon(), getDescription());
	}

	public void get() {
		throw new UnsupportedOperationException();

	}

	public void getChild() {
		throw new UnsupportedOperationException();

	}

	public void add(SdgAbstract sdg) throws SdgException {
		throw new UnsupportedOperationException();

	}

	public void remove(SdgAbstract sdg) throws SdgException {
		throw new UnsupportedOperationException();

	}

	public SdgChild getChild(SdgAbstract sdg) throws SdgException {
		throw new UnsupportedOperationException();
	}

	public void addSdgJpa(SdgAbstract comp) {
		throw new UnsupportedOperationException();

	}

	public void removeSdgJpa(SdgComp comp) {
		throw new UnsupportedOperationException();

	}

	public SdgComp getSdgJpa(int id) {
		throw new UnsupportedOperationException();

	}

	public void updateSdg(SdgComp sdgCompToUpdate) {
		throw new UnsupportedOperationException();
	}

}
