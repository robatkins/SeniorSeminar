import java.util.Random;
import java.util.Scanner;

public class SelectionSort 
{
	//Declare the time holder variables
			static long startTime;
			static long endTime;
			static long duration;
			
	public static void main(String[] args)
	{
		
		//Get number of elements to sort.
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of elements : ");
		int n = scan.nextInt();
		
		//Declare the array with n elements.
		int array[] = new int[n];
		
		getInput(array, n, scan);
		
		startTime = System.currentTimeMillis();
		sort(array);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		
		//outputArray(array, n);
		System.out.println("Selection sort took: " + duration + " Milliseconds");
		
		createDefaultPartiallySortedArray(array, n);
		//We start the counter
		startTime = System.currentTimeMillis();
		//We call the actual method that performs selection sort.
		sort(array);
		//End the counter
		endTime = System.currentTimeMillis();
		//Calculate duration of sort
		duration = endTime - startTime;
		//outputArray(array, n);
		System.out.println("Sorting method took: " + duration + " Milliseconds | Partial Sort");
		
		
		
		
		
		
	}
	
	//This generates input to store in our array, so we may perform a sort function on it.
	public static void getInput(int array[], int n, Scanner scan)
	{
		int min = 0;
		int max = 1000000;
		Random randomInteger = new Random();
		
		for(int i=0; i<n; i++)
		{
			array[i] = randomInteger.nextInt((max - min) + 1) + min;
		}
	}
	
	//The method to output the sorted array to the command line.
	public static void outputArray(int array[], int n)
	{
		for(int i = 0; i < n; i++)
		{
			System.out.println(array[i] + " ");
		}
		System.out.println();
	}
		
	
	
	//The actual implemented algorithm to perform Selection Sort
	public static void sort(int[] array)
	{
		int length = array.length, position, temp;
		
		for(int i = 0; i < length; i++)
		{
			position = i;
			for (int k = i + 1; k < length; k++)
			{
				if(array[k] < array[position])
				{
					
					position = k;
				
				}
				
			}
			
			temp = array[i];
			array[i] = array[position];
			array[position] = temp;
			
		}
		
	}
	
	//Creates the default partially sorted array using the data from the default unsorted array.
	static void createDefaultPartiallySortedArray(int[] array, int n)
	{
				
				int min = 0;
				int max = 99999999;
				Random randomInteger = new Random();
				
				//We populate the first half of the array with integers in a sequential fashion. (Sorted)
				for(int i=0; i < n/2; i++)
				{
					array[i] = i+1;
				}
				
				//We populate the second half of the array with random integers.
				for (int i=(n/2); i < n; i++)
				{
					array[i] = randomInteger.nextInt((max - min) + 1) + min;
				}
				
				
				
	}

}
