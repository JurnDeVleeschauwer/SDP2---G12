package ui;


import java.util.InputMismatchException;
import java.util.Locale; 
import java.util.ResourceBundle;
import java.util.Scanner;

public class MultiLanguageApp   {

	Scanner s = new Scanner(System.in);
	private ResourceBundle talen; 

	public int chooseLanguage()
	{
		 int choice = 0; 
		 boolean exception = true; 
		 do {
			 try
			 {
				 do
				 {
				 //Menu 
				 System.out.println("1. English");
				 System.out.println("2. Nederlands");
				 
				 choice = s.nextInt();
				  
				 
				 }
				 while(choice != 2 && choice != 1);  
				 exception = false; 
				 return choice;  
				
				 
			 }
			 catch(InputMismatchException mismatch)
			 {
				
				 System.err.println("Give in a number between 1 and 3.");
				 System.err.println("Geef een getal tussen 1 en 3."); 
				 s.nextLine();
			 }
		 }
		 while(exception == true); 
		return choice; 
	}
	
	
	
	public String translate(int choice, String key)
	{
		System.out.println(choice);
		
		Locale l;
		switch(choice){
		  case 1:
		    l = new Locale("en");
		    break;
		  default:
		    l = new Locale("");
		    break;
		}
		System.out.println(choice);
		System.out.println(key);
		System.out.println(l);
		talen = ResourceBundle.getBundle("translations.translations", l);
		return talen.getString(key); 
		
	}

}	

	
