package domain;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DataPerSource<T> {

	@Transient
	private List<String> months = new ArrayList<>(); 
	
	@Transient
	private List<T> data;
	
	@Transient
	private File f; 
	
	@Id
	private int id;
	private Map<String, Integer> dataSet = new HashMap<>(); 
	

	
	public DataPerSource() {
		populateMonths(months);
		
	}


	private void populateMonths(List<String> months) {
		months.add(Months.JAN.toString().toLowerCase());
		months.add(Months.FEB.toString().toLowerCase());
		months.add(Months.MARCH.toString().toLowerCase());
		months.add(Months.APRIL.toString().toLowerCase());
		months.add(Months.MAY.toString().toLowerCase());
		months.add(Months.JUNE.toString().toLowerCase());
		months.add(Months.JULY.toString().toLowerCase());
		months.add(Months.AUGUST.toString().toLowerCase());		
		months.add(Months.SEPTEMBER.toString().toLowerCase());
		months.add(Months.OKTOBER.toString().toLowerCase());
		months.add(Months.NOVEMBER.toString().toLowerCase());
		months.add(Months.DECEMBER.toString().toLowerCase());
		
		
	}
	
	public void populateData() throws FileNotFoundException {
		
		
		data = DatasourceReader.readFile(f);
		
		
	}
	
	
	public void populateDataSet(List<String> months, List<T> data)  {
		
		
	}
	
	
}
