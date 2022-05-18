package domain;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class DataPerSource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Transient
	private List<String> dataRaw;
	
	@Transient
	private File f; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private Map<String, Integer> dataSet = new LinkedHashMap<>(); 
	

	
	public DataPerSource(File f, boolean sum, boolean avg) throws FileNotFoundException {
		setF(f); 
		populateData(f, sum, avg);
	}

	protected DataPerSource() {

		
		
	}
	

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	



	public List<String> getData() {
		return dataRaw;
	}


	public void setData(List<String> data) {
		this.dataRaw = data;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Map<String, Integer> getDataSet() {
		return dataSet;
	}


	public void setDataSet(Map<String, Integer> dataSet) {
		this.dataSet = dataSet;
	}
	
	
	
	public Map<String, Integer>populateData(File f, boolean sum, boolean average) throws FileNotFoundException {
		
		
		dataRaw = DatasourceReader.readFile(f);
		
		dataSet = processData(dataRaw, sum, average);
		
	
		return dataSet; 
	}
	
	
	
	
	public Map<String, Integer> processData(List<String> data, boolean sum, boolean avg) {

		Map<String, Integer> monthValueMap = new LinkedHashMap<>(); 
		List<String> daysOfWeek = new ArrayList<>();
		List<String> months = new ArrayList<String>(); 
		String langMatchMonths; 
		String langMatchDays; 
		boolean dayCheck = false; 
		boolean monthCheck = false; 
		
			if(data.get(0).equals("nederlands") || data.get(0).contains("ne")) {
				langMatchDays = "\\b(?:zo.*?|ma.*?|di.*?|woe.*?|do.*?|vrij.*?|za.*?)"; 
				langMatchMonths =  "\\b(?:jan.*?|feb.*?|maart.*?|apr.*?|mei.*?|juni.*?|juli.*?|aug.*?|sept.*?|okt.*?|nov.*?|dec.*?)";
				String[] days = {"ma", "di", "woe", "do", "vrij", "za", "zo"}; 
				String[] monthsOfYear = {"jan", "feb", "maart", "april", "mei", "juni", "juli", "aug", "sept", "okt", "nov", "dec"};
				months.addAll(Arrays.asList(monthsOfYear)); 
				daysOfWeek.addAll(Arrays.asList(days));

			} else {
				langMatchDays = "(?:sun.*?|mon.*?|tue.*?|wed.*?|thu.*?|fri.*?|sat.*?)";
				langMatchMonths = "\\b(?:jan.*?|feb.*?|march.*?|apr.*?|may.*?|june.*?|july.*?|aug.*?|sept.*?|okt.*?|nov.*?|dec.*?)";
				String[] days = {"mon", "tue", "wed", "thur", "fri", "sat", "sun"};
				String[] monthsOfYear = {"jan", "feb", "mar", "april", "may", "june", "july", "aug", "sept", "okt", "nov", "dec"};
				months.addAll(Arrays.asList(monthsOfYear)); 
				daysOfWeek.addAll(Arrays.asList(days));

			}
			
			int monthCount = 0; 
			
			for(String line : data) {
				if(line.matches(langMatchDays)) {
						dayCheck = true; 
						break;
				} 
				
				
				if(line.matches(langMatchMonths)) {
					monthCount++;
					
					//if(monthCount >= 2) {
					//	monthCheck = true;
					//	break; 
					//}
					
					
				}
				
				
			}
			
			
			
				
				if(dayCheck == true) {
					System.out.println("DAYCHECK");
					monthValueMap = dayFunc(data, langMatchDays, langMatchMonths, daysOfWeek, months, avg); 
					
				} /*else if(monthCheck == true)  {
					System.out.println("MONTHCHECK");
					monthValueMap = monthFunc(data, langMatchMonths, daysOfWeek, months, true);
					
				}*/else if(dayCheck == false && monthCheck == false) {
					System.out.println("WEEKCHECK");
					monthValueMap = weekFunc(data, langMatchMonths, daysOfWeek, months, avg); 
					
				} 
				
				
			
			
		
		
			monthValueMap.forEach((k,v) -> {
				System.out.println("Key:" + k  + " V " + v);
			});
			
		return monthValueMap; 
	}
	
	private Map<String, Integer> monthFunc(List<String> data, String langMatchMonths, List<String> daysOfWeek, List<String> months, boolean avg) {
		

		String currentMonth = ""; 
		String prevMonth = ""; 
		Map<String, Integer> monthValueMap = new LinkedHashMap<>(); 

		
		for(int i = 0; i < data.size(); i++) {
					if(data.get(i).matches(langMatchMonths)) {
						currentMonth = data.get(i).replaceAll(" ", "").split(":")[0];
						
						int monthValue = Integer.parseInt(data.get(i).replaceAll(" ", "").split(":")[1]); 
						
						if(avg == true) {
							System.out.println("MONTHAVG");
							for(int iMonth = 0; i < months.size(); i++) {
								if(currentMonth == months.get(iMonth)) {
									
									
									if(iMonth == 0) {
										monthValue /= 31; 
									}else if(iMonth == 1) {
										monthValue /= 29; 
									} else if(iMonth == 7 ) {
										monthValue /= 31; 
									} else if(iMonth % 2 == 0) {
										monthValue /= 31;
									} else if(iMonth % 2 != 0) {
										monthValue /= 30;
									}
									
									
								}
							
								
								
								
							}
							
							
							
							
						}
						
						monthValueMap.put(currentMonth, monthValue );
				}
		}
	
		
		
	return monthValueMap; 
	
	}
	
		
	private Map<String, Integer> weekFunc(List<String> data, String langMatchMonths, List<String> daysOfWeek, List<String> months, boolean avg) {
		
		String currentMonth = ""; 
		String prevMonth = ""; 
		int weeksPerMonth = 0;
		Map<String, Integer> monthValueMap = new LinkedHashMap<>(); 
		List<Integer> valueOfWeeks = new ArrayList<>(); 
		int monthsPerYear = 0; 
		
		for(int i = 0; i < data.size(); i++) {
				if(monthsPerYear < 12 ) {
					if(data.get(i).matches(langMatchMonths)) {
						currentMonth = data.get(i); 
						prevMonth = currentMonth;
					} else {
						if(data.get(i).contains("week")) {
							if(valueOfWeeks.size() < 4) {
								if(!prevMonth.equals(currentMonth) && !currentMonth.isBlank()) {
									int monthValue = valueOfWeeks.stream().reduce((subtotal, element)-> subtotal + element).get(); 
									monthValueMap.put(prevMonth, monthValue ); 
									
									prevMonth = currentMonth; 
									weeksPerMonth = 0;
									valueOfWeeks.clear();
								}
								
								valueOfWeeks.add(Integer.parseInt(data.get(i).replaceAll(" ", "").split(":")[1]));
								
								if(valueOfWeeks.size() > 3) {
									
									int monthValue = valueOfWeeks.stream().reduce((subtotal, element)-> subtotal + element).orElse(0); 
									System.out.println(monthValue);
									if(currentMonth.isEmpty() && prevMonth.isEmpty()) {
										currentMonth = months.get(monthsPerYear);
										prevMonth = currentMonth; 
									} 
									
									if(currentMonth.isEmpty() && !prevMonth.isEmpty()) {
										
										for(int monthCheck = 0; monthCheck < months.size(); monthCheck++) {
											if(months.get(monthCheck).contains(prevMonth)) {
												
												if(monthCheck == months.size() - 1) {
													
													currentMonth = months.get(0); 
												
												} else {
													monthsPerYear = monthCheck; 
													currentMonth = months.get(monthCheck + 1);	
													}
												}
											}
									
										}
									
									if(avg == true) {
										System.out.println("AVG found");
										monthValue /= valueOfWeeks.size(); 
									}
									
									monthValueMap.put(currentMonth, monthValue);
									currentMonth = "";  
									weeksPerMonth = 0;
									valueOfWeeks.clear();
									}
								}
							}
					} 	
				}	
		}	
		monthValueMap.forEach((k, v) -> {
			System.out.println(k + " " + v);
		});
		return monthValueMap;
		
		
	
	}

	private Map<String, Integer> dayFunc(List<String> data, String langMatchDays, String langMatchMonths, List<String> daysOfWeek, List<String> months, boolean avg ) {

		
		String currentMonth = ""; 
		String prevMonth = ""; 
		int weeksPerMonth = 0;
		Map<String, Integer> monthValueMap = new LinkedHashMap<>(); 

		List<Integer> valueOfDay = new ArrayList<>(); 
		List<Integer> valueOfWeeks = new ArrayList<>(); 
		List<Boolean> isInList = new ArrayList<>(); 
		int tempDay = -1; 
		int currentDay = 0; 
		int monthsPerYear = 0; 
		
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).matches(langMatchDays) || data.get(i).matches(langMatchMonths)) {
				if(monthsPerYear < 12 ) {
					if(data.get(i).matches(langMatchMonths)) {
						currentMonth = data.get(i); 
						prevMonth = currentMonth;
					} else {
						
						if(weeksPerMonth < 4) {
							if(!prevMonth.equals(currentMonth) && !currentMonth.isBlank()) {
								int monthValue = valueOfDay.stream().reduce((subtotal, element)-> subtotal + element).get(); 
								monthValueMap.put(prevMonth, monthValue ); 
								
								prevMonth = currentMonth; 
								currentDay = 0;
								weeksPerMonth = 0;
								isInList.clear();
								valueOfDay.clear();
								valueOfWeeks.clear();
							} 
							if(currentDay < 7) {
									
									if(data.get(i).contains(daysOfWeek.get(currentDay))) {
										isInList.add(true); 
										valueOfDay.add(Integer.parseInt(data.get(i).replace(" ", "").split(":")[1]));
										
									} else {
										isInList.add(false); 
									}
									
									currentDay++; 
								} 
							
								if(isInList.size() > 6) {	
									
									int weekValue = valueOfDay.stream().reduce((subtotal, element)-> subtotal + element).get(); 
									valueOfWeeks.add(weekValue); 
									weeksPerMonth++; 
									
									currentDay=0;
									isInList.clear();
									valueOfDay.clear();
									}

									if(weeksPerMonth > 3) {
										monthsPerYear++; 
										int monthValue = valueOfWeeks.stream().reduce((subtotal, element)-> subtotal + element).orElse(0); 
										
										if(currentMonth.isEmpty() && prevMonth.isEmpty()) {
											currentMonth = months.get(monthsPerYear);
										} 
										
										if(currentMonth.isEmpty() && !prevMonth.isEmpty()) {
											
											for(int monthCheck = 0; monthCheck < months.size(); monthCheck++) {
												if(months.get(monthCheck).contains(prevMonth)) {
													
													if(monthCheck == months.size() - 1) {
														
														currentMonth = months.get(0); 
													
													} else {
														monthsPerYear = monthCheck; 
														currentMonth = months.get(monthCheck + 1);	
													}
												}
											}
										
										}
										
										if(avg == true) {
											System.out.println("AVG FOUND");
											monthValue /= valueOfWeeks.size();
										}

										monthValueMap.put(currentMonth, monthValue);
										currentMonth = "";  
										currentDay = 0;
										weeksPerMonth = 0;
										isInList.clear();
										valueOfDay.clear();
										valueOfWeeks.clear();
									}
							}
						} 	
					}	
				}
		}	
		return monthValueMap;
		
	}
	
	public void populateDataSet(List<String> months, List<String> data)  {
		
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataRaw, dataSet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataPerSource other = (DataPerSource) obj;
		return Objects.equals(dataRaw, other.dataRaw) && Objects.equals(dataSet, other.dataSet);
	}



}
