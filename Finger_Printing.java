import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*This Class implements the Finger Printing Algorithm for Plagiarism Test.*/

public class Finger_Printing {
	String file1;
	String file2;
	
	/*This class accepts two File arguments.
	 Constructor for the Finger Printing Algorithm class.*/
	public Finger_Printing(String file1, String file2)
	{
		this.file1 = file1;
		this.file2 = file2;
	}
	
	//Function that returns the Plagiarism percentage to the object.
	public double fingerPrinting() throws FileNotFoundException
	{
		//Accessing the files from the arguments.
		File f = new File(file1);
		File g = new File(file2);
		
		double perce = 0.00;
		
		//Converts the data in the file to a character array.
		char[] array1 = this.charArrayFile(f);
		char[] array2 = this.charArrayFile(g);
		
		//COndition for checking empty files and files with only special characters.
		if(array1.length > 2 && array2.length > 2)
		{
			//Gets the size of the data present in the file excluding special characters.
			int n1 = array1.length;
			int n2 = array2.length;
			
			//Function to create the hash table of the file data.
			long[] hash1 = this.hash_Table(array1, n1);
			long[] hash2 = this.hash_Table(array2, n2);
			
			//calling the Finger Printing Plagiarism percentage.
			perce = this.fingerPercent(hash1, hash2, n1, n2);
			return perce;
		}
		else
		{
			perce = 0.0;			//Returns  when an empty file or a file with only special characters is encountered.
			return perce;
		}
		
	}
	
	//Function that converts the data in the file into an array.
	public char[] charArrayFile(File file) throws FileNotFoundException
	{
		String text = "";
		Scanner t =new Scanner(file);
		
		while(t.hasNextLine())
		{
			text = text + t.next();
		}
		text = text.replaceAll("[^a-z0-9A-Z_]+", "");		//Removes all the special characters in the data excluding '_'.
		text += '\0';
		t.close();
		int n = text.length();
		char arr[] = new char[n];
		
		int i=0;											//Converts the test String into a character array.
		while (text.charAt(i) != '\0')
		{
			arr[i] = text.charAt(i);
			i++;
		}
		//System.out.println(text);
		return arr;
	}
	
	//Function to create the hash table of the given data.
	public long[] hash_Table(char arr[], int m)
	{
		int n = 0;
		long[] array = new long[m];

		while(arr[n+4] != '\0')
		{
			for (int i = n; i < n+5; i++)				//Loop calculates the hash value at the weight of  5.
			{
				array[n] += (int)arr[i] * (Math.pow(5,i-n)); 
			}
			array[n] = array[n] % 1103;
			n++;
		}
		array[n] = 0;
		return array;
	}
	
	//Function to return the percentage of plagiarism using Finger Printing algorithm.
	public double fingerPercent(long array1[], long array2[], int m, int n)
	{
		int  count=0;
		for(int i=0; i<m; i++)
		{
			for(int j=0; j<n; j++)
			{
				if(array1[i] == array2[j])		//Compares the hash values to get Plagiarism.
				{
					count += 1;	
					array2[j] = 0;
					break;
				}				
			}				
		}

		double x = m;
		double y = n;

		double finperc = ((2*count)/(x + y)) * 100;
		return finperc;
	}

}
