import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*Driver Class that implements the Plagiarism Test.*/
public class Plagiarism 
{
	/*Execution start at the main function in the code.*/
	public static void main(String[] args) throws FileNotFoundException 
	{
		String path = args[0];						//Accepts Command Line Argument as input.
		File testPath = new File(path);
		
		File[] testFolder_raw = testPath.listFiles();	//Converts the files in the given folder into an array.
		
		int m = testFolder_raw.length;
		
		ArrayList<File> testFolder = new ArrayList<File>();
		
		//Takes all the files of ".txt" format into a separate ArrayList for Plagiarism test. 

		for(int i=0; i<m; i++)
		{
			if(testFolder_raw[i].getName().endsWith(".txt"))
			{
				testFolder.add(testFolder_raw[i]);

			}
		}
		
		int n = testFolder.size();
		/*Calls Bag Of Words Algorithm for calculating the Plagiarism.*/
		double[][] bag_Of_Words = new double[n][n];
		for (int i=0; i < n; i++)
		{
			for (int j=0; j < n; j++)
			{
				if(i != j) 
				{
					File f = testFolder.get(i);
					File g = testFolder.get(j);
					
				
					Bag_Of_Words a = new Bag_Of_Words(f.toString(), g.toString());
					bag_Of_Words[i][j] = a.bow();		//Appends the obtained result into a 2-dimensional array.
				} 
				else if(i == j)
				{
					bag_Of_Words[i][j] = 0.00;			//Appends 0 as result when both the files are same.
				}
			}
		}
		
		/*Calls the Matrix Printing Function.*/
		System.out.println("\t\t\t\tPlagiarism using Bag Of Words Algorithm");
		matrixPrinting(testFolder,bag_Of_Words);
		System.out.println("*********************************************************************************************************");
		
		/*Calling the Longest Common Substring Algorithm to find the Plagiarism*/
		double[][] lcSubstring = new double[n][n];	//Initializing the 2-d array for storing the results after LCS Algorithm.
		for (int i=0; i < n; i++)
		{
			for (int j=0; j < n; j++)
			{
				if(i != j) 
				{
					File f = testFolder.get(i);
					File g = testFolder.get(j);
					
				
					Longest_Common_Substring b = new Longest_Common_Substring(f.toString(), g.toString());
					lcSubstring[i][j] = b.LCS();		//Appends the obtained result into a 2-dimensional array.
				} 
				else if(i == j)
				{
					lcSubstring[i][j] = 0.00;			//Appends 0 as result when both the files are same.
				}
			}
		}
		
		System.out.println("\t\t\tPlagiarism using Longest Common Substring Algorithm");
		matrixPrinting(testFolder,lcSubstring);
		System.out.println("*********************************************************************************************************");
		
		double[][] finPrint = new double[n][n];
		
		for (int i=0; i < n; i++)
		{
			for (int j=0; j < n; j++)
			{
				if(i != j) 
				{
					File f = testFolder.get(i);
					File g = testFolder.get(j);
					
				
					Finger_Printing c = new Finger_Printing(f.toString(), g.toString());
					finPrint[i][j] = c.fingerPrinting();		//Appends the obtained result into a 2-dimensional array.
				} 
				else if(i == j)
				{
					finPrint[i][j] = 0.00;						//Appends 0 as result when both the files are same.
				}
			}
		}
		
		System.out.println("\t\t\tPlagiarism using Finger Printing Algorithm");
		matrixPrinting(testFolder,finPrint);
		System.out.println("*********************************************************************************************************");
		
	}
	
		/*Function to print a two dimensional array in a matrix format.*/
		public static void matrixPrinting(ArrayList<File> comparisionFolder, double[][] percentage_Array) 
		{
			System.out.println("\t\t");
			for (int i = 0; i < comparisionFolder.size(); i++)
			{			
				System.out.print("\t    "+comparisionFolder.get(i).getName());
			}
			
			System.out.println("");
			
			for (int i = 0; i < comparisionFolder.size(); i++)
			{
				System.out.print(comparisionFolder.get(i).getName()+"\t");
				for (int j = 0; j < comparisionFolder.size(); j++)
				{
					System.out.printf("%.2f \t\t", percentage_Array[i][j]);
				}
				System.out.println("");
		}
		
	}
}
