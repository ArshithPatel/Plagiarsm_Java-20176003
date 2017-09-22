import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*Class implementation for Longest Common Substring Algorithm.*/
public class Longest_Common_Substring {
	
	String file1, file2;
	/*Longest Common Substring Contructor.*/
	public Longest_Common_Substring(String file1, String file2)
	{
		this.file1 = file1;
		this.file2 = file2;
	}

	/*LCS method that returns the LCS percent of two files.*/
	public double LCS() throws FileNotFoundException
	{
		//Access two files.
		File f = new File(file1);
		File g = new File(file2);
		
		double perc = 0.00;
		
		//Calling the charArrayFile Function.
		char[] array1 = charArrayFile(f);
		char[] array2 = charArrayFile(g);
//		int i = 0;
//		while (array1[i] != '\0')
//		{
//			//System.out.print(array1[i]);
//			i++;
//		}
		//System.out.println(" ");
		
		perc = lcs(array1,array2);
//		System.out.println(perc);
		
		return perc;
		

	}
	
	//Function that converts the data in the file into a character array. 
	public char[] charArrayFile(File file) throws FileNotFoundException
	{
		String text = "";
		
		Scanner t =new Scanner(file);
		
		while(t.hasNextLine())
		{
			text = text + t.next();
		}
		text = text.replaceAll("[^a-z0-9A-Z_ ]+", "");	//Removes all the special characters from the data except '_'.
		text += '\0';
		text.toLowerCase();
		t.close();
		int n = text.length();
		
		//Converts the text into a character array.
		char arr[] = new char[n];
		
		int i=0;
		
		while (text.charAt(i) != '\0')
		{
			arr[i] = text.charAt(i);
			i++;
		}
		//System.out.println(text);
		return arr;
	}
	
	/*Function to calculate the Plagiarism Percentage. Returns a double value.*/
	public double lcs(char X[], char Y[])
	{
	    int m = X.length, n = Y.length;
	    int i;
	    
	    double d;
	    d = m+n-2;
	    //System.out.println(d);
	    
	    int  x=0;
	    int [][] L = new int [m][n];
	   
	   //Calculates the length of longest common substring.
	    for (i=0; i< m; i++)
	    {
	        for (int j=0; j< n; j++)
	        {
	            if (i == 0 || j == 0)
	            {
	                L[i][j] = 0;
	            }

	            else
	            {
	                if (X[i] == Y[j])				//Compares the character array to get the length of common substring.
	                {   
	                    
	                    L[i][j] = L[i-1][j-1] + 1;

	                    if  (x < L[i][j])
	                    {
	                        x = L[i][j];
	                    }

	                }
	                else
	                {
	                    L[i][j] = 0;   
	                }
	            }    
	        }    
	    }
	    
	    //System.out.println(x);

	    //Calculates the Plagiarism percent from the length of longest common substring calculated above.
	    
	    double lcs_perc;
	    lcs_perc = ((2*x)/(d))*100;
	    return lcs_perc;
	}

}
