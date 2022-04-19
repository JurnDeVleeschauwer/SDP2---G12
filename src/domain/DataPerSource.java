package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DataPerSource<T> {

	private List<String> months = new ArrayList<>(); 
	private List<T> data;
	@Id
	private int id;
	
	@Transient
	private File f; 
	
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
		
		
		data = ReadDatasource.readFile(f);
		
		
	}
	
	
}
