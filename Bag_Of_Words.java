import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.lang.Math;

/*Class implementation of Bag Of Words algorithm.*/
public class Bag_Of_Words {
	
	String file1;
	String file2;
	
	/*Bag of Words Constructor.*/
	public Bag_Of_Words(String file1, String file2)
	{
		this.file1 = file1;
		this.file2 = file2;
	}

	/*Method that returns the plagiarism percent of two files.*/
	public double bow() throws FileNotFoundException 
	{
		// TODO Auto-generated method stub
		double percent_Of_Plagiarism = 0.00;
		try
		{
		//Opening the files passed as arguments.	
		File f = new File(file1);
		File g = new File(file2);
		
		//Converting the file to a single String Object.
		String text1 = this.stringFile(f);
		String text2 = this.stringFile(g);
		
		//Gets the frequency of words in the file.
		HashMap<String, Integer> word_Frequency1 = this.wordFrequency(text1);
		HashMap<String, Integer> word_Frequency2 = this.wordFrequency(text2);
		
		//Calling the dot-product function.
		int d = this.dotProduct(word_Frequency1,word_Frequency2);
		
		//Calling the Euclidean Norm Funtion.
		double eNorm1 = this.euclideanNorm(word_Frequency1);
		double eNorm2 = this.euclideanNorm(word_Frequency2);
		
		//Calling the percentage function.
		percent_Of_Plagiarism = this.bowPercent(d, eNorm1, eNorm2);
		}
		//Exception Handling for file handling.
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
		return percent_Of_Plagiarism;
		
	}

	//This function returns the data as a single string from the given file
	public String stringFile(File file) throws FileNotFoundException
	{
		String text = "";
		
			Scanner t =new Scanner(file);
			while(t.hasNextLine())
			{
				text = text + t.next() + " ";
			}
			t.close();

		return text.toLowerCase();	//Returns the file in complete lower case.
	}
	
	//Function that calculates the frequency of all the words in the file.
	public HashMap<String, Integer> wordFrequency(String text)
	{
		HashMap<String, Integer> word_frequency = new HashMap<String, Integer>();
		String[] words = text.split(" ");				//Converts the text string into a array of words.
		
		int n  = words.length;
		int c = 0;
		for (int i=0; i<n; i++)
		{
			c = 0;
			for (int j=0; j<n; j++)
			{
				if (words[i].equals(words[j]))	//Compares the words.
				{
					c+=1;						//Increments the counter when the above condition is true.
				}
			}
			word_frequency.put(words[i], c);
			
		}
		return word_frequency;		//Returns the word_frequency hashmap.
	}
	
	//Function that calculates the dot product of two files.
	public int dotProduct(HashMap<String, Integer> a, HashMap<String, Integer> b)
	{
		int dot_Product = 0;					//Intializing the dot product variable. 
		Set<String> a_keys = a.keySet();		//Gets the key from the first hashmap.
		Set<String> b_keys = b.keySet();		//Gets the key from the second hashmap.
		for(String key1: a_keys)
		{
			for(String key2: b_keys)
			{
				if(key1.equals(key2))			//Compares the words over iteration.
				{
					dot_Product+= ( a.get(key1) * b.get(key2));		//Increments the dot product.
				}
			}
		}
		return dot_Product;
	}
	
	//Function to calculate the Euclidean Norm of a file.
	public double euclideanNorm(HashMap<String, Integer> a)
	{
		double eNorm;
		int e = 0;
		Set<String> a_keys = a.keySet();
		for(String key1: a_keys)					//Iterates over the words list and calculates the Euclidean norm.
		{
			e += (Math.pow(a.get(key1),2));
		}
		eNorm = Math.sqrt(e);
		return eNorm;
	}
	
	//Function to calculate the Plagiarism Percentage of two files using the dotProduct and Eulidean norms.
	public double bowPercent(int d, double eNorm1, double eNorm2)
	{
		double plagiarismPercent = (d/(eNorm1*eNorm2))*100;
		return plagiarismPercent;
	}

}
 