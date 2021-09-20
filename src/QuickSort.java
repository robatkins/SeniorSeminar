import java.util.Random;
import java.util.Scanner;

public class QuickSort 
{
	//Declare the time holder variables
	static long startTime;
	static long endTime;
	
	public static void main(String[] args)
	{
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of elements : ");
		
		//Get the number of elemnts to be put in the array.
		int n = scan.nextInt();
		
		//The Array for us to sort
		int array[] = new int[n];
		
		int p, k, upper, lower;
		
		System.out.println("Enter " + n + " elements : " );
		
		//Get the elements to input in the array for sorting.
		getInput(array, n, scan);
		
		//We start the counter
		startTime = System.currentTimeMillis();
		//We call the actual method that performs quick sort.
		sort(array, 0, n - 1);
		//End the counter
		endTime = System.currentTimeMillis();
		//Calculate duration of sort
		long duration = endTime - startTime;
		
		
		//We call this method to output the sorted array to the screen.
		outputArray(array, n);
		
		System.out.println("Sorting method took: " + duration + " Milliseconds");

		
		
	}
	
	//The Actual Quicksort implementation method
	public static void sort(int array[], int lower, int upper)
	{
		if (lower >= upper)
		{
			return;
		}
		
		int p = partition(array, lower, upper);
		sort(array, lower, p - 1);
		sort(array, p + 1, upper);
		
	}
	
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
	
	//The method for partitioning during the Quicksort function.
	//called by the sort() method
	private static int partition(int array[], int lower, int upper)
	{
		int pivot = array[upper];
		int k = lower;
		int temp;
		
		for(int i = lower; i <= upper; i++)
		{
			if(array[i] < pivot)
			{
				temp = array[i];
				array[i] = array[k];
				array[k] = temp;
				k++;
			}
			
		}
		temp = array[upper];
		array[upper] = array[k];
		array[k] = temp;
		
		return k;
	}

}
