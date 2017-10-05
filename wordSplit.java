package dataDriven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class wordSplit
{
	public static void main(String a[])
	{
		final String fileName = "Y:\\Selenium\\Contacts.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			
			String stringLine;
			while ((stringLine = br.readLine()) != null)
			{
				//Splitting a String by taking ',' as delimiter
				String[] tokens = stringLine.split(",");
				int i = 1;
				for(String s:tokens)
				{
					System.out.println("Word" +i+ " : "+s);
					i++;
				}
				System.out.println("\n");
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		/*
		String str = "Yuga,Challagolla,7074 Majors Rd, ,Cumming,GA,30040";
		String[] tokens = str.split(",");
		int i = 0;
		for(String s:tokens)
		{
			System.out.println("Word" +i+ " : "+s);
			i++;
		}
		*/
	}
	
}
