package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.DataSource;

import persistence.GenericMapperJpa;

@Entity
@Table
public class Datasource implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	private String name; 
	private int expectedGoal;
	private int year;
	private String yAxis;
	private String xAxis;
	
	
	public Datasource(String name, String yAxis, String xAxis, int year, int expectedGoal) {
		
		setName(name);
		setyAxis(yAxis);
		setxAxis(xAxis);
		setYear(year);
		setExpectedGoal(expectedGoal);
		
	}
	
	protected Datasource() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExpectedGoal() {
		return expectedGoal;
	}

	public void setExpectedGoal(int expectedGoal) {
		this.expectedGoal = expectedGoal;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getyAxis() {
		return yAxis;
	}

	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expectedGoal, name, xAxis, yAxis, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Datasource other = (Datasource) obj;
		return expectedGoal == other.expectedGoal && Objects.equals(name, other.name)
				&& Objects.equals(xAxis, other.xAxis) && Objects.equals(yAxis, other.yAxis) && year == other.year;
	}

	
	@Override
	public String toString() {
		return String.format("name: %s, yAxis: %s, xAxis: %s, Year: %d, Expectedgoal: %d", getName(), getyAxis(), getxAxis(), getYear(), getExpectedGoal()); 
	}

	
	
	

}
