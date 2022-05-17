package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import domain.MvoGoalComp.Builder;
import exceptions.SdgException;

@Entity
@Table(name = "sdg")
public class SdgComp extends SdgAbstract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String description;

	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
	private final List<SdgChild> sdgs;

	public static class Builder {

		private String description;
		private String name;
		private List<SdgChild> sdgs = new ArrayList<>();

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder sdgs(List<SdgChild> sdgs) {
			this.sdgs = sdgs;
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

		public Builder setSdgs(List<SdgChild> sdgs) {
			this.sdgs = sdgs;
			return this;
		}
		public SdgComp build() {
			return new SdgComp(Builder.this);

		}
	}

	public String getDescription() {
		return description;
	}

	public SdgComp(Builder builder) {
		this.description = builder.description;
		this.name = builder.name;
		this.sdgs = builder.sdgs;

	}

	protected SdgComp() {
		this.description = "";
		this.sdgs = null;

	}

	@Override
	public void add(SdgAbstract sdg) throws SdgException {
		sdgs.add((SdgChild) sdg);

	}

	@Override
	public void remove(SdgAbstract sdg) throws SdgException {

		sdgs.remove(sdg);

	}

	@Override
	public SdgChild getChild(int sdgId) throws SdgException {

		return sdgs.stream().filter((currentSdg) -> currentSdg.getId() == sdgId).collect(Collectors.toList()).get(0);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SdgChild> getSdgs() {
		return sdgs;
	}

	@Override
	public String toString() {
		String res = "";

		res += String.format("Id: %d, name: %s%n, Category: %s%n", getName());

		for (SdgChild child : sdgs) {
			res += child.toString();
			res += System.lineSeparator();

		}
		return res;
	}

}
