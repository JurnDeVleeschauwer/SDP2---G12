package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	private HashMap<String, Exception> errorsMap = new HashMap<String, Exception>();

	
	public static class Builder {

		private String description;
		private String name;
		private List<SdgChild> sdgs = new ArrayList<>();

		private HashMap<String, Exception> errorsMap = new HashMap<String, Exception>();

		
		public Builder description(String description) {
			try {
				this.description = description;
				
			} catch (Exception e) {
				errorsMap.put("description", e);
			}
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

		public Builder sdgs(List<SdgChild> sdgs) {
			try {
				this.sdgs = sdgs;
				
			} catch (Exception e) {
				errorsMap.put("sdgs", e);
			}
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
		this.errorsMap=builder.errorsMap;

	}

	protected SdgComp() {
		this.description = "";
		this.sdgs = null;

	}

	public HashMap<String, Exception> getErrorsMap() {
		return errorsMap;
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
		return "SdgComp [description=" + description + ", sdgs=" + sdgs + ", id=" + id + ", name=" + name
				+ ", getDescription()=" + getDescription() + ", getName()=" + getName() + ", getSdgs()=" + getSdgs()
				+ ", getId()=" + getId() + ", getTarget()=" + getTarget() + ", isBlad()=" + isBlad() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


	/*@Override
	public String toString() {
		String res = "";

		res += String.format("Id: %d, name: %s%n, Category: %s%n", getName());

		for (SdgChild child : sdgs) {
			res += child.toString();
			res += System.lineSeparator();

		}
		return res;
	}*/

}
