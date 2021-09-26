import java.util.Scanner;
import java.util.Random;

/*
 * 			Evaluation of Sorting Algorithms
 * 			CSCI Senior Seminar Program
 * 			By: Robert Atkins
 * 
 * 
 */

public class Driver
{
	
	//We create 3 empty arrays
	int[] unsortedArray1, unsortedArray2, unsortedArray3;
	
	//We declare variables to hold time values
	//Enumerated 1 for Insertion Sort, 2 for Selection Sort, and 3 for Quick Sort.
	static long startTime1, startTime2, startTime3;
	static long endTime1, endTime2, endTime3;
	
	//We declare empty long variables as placeholders to store the sums of the time spent on our experiments
	//Enumerated 1 for Insertion Sort, 2 for Selection Sort, and 3 for Quick Sort.
	static long timeSum1, timeSum2, timeSum3;
	
	//We declare empty long variables as placeholders to store the averages of our experiments on the sorting algorithms
	static long timeAverage1, timeAverage2, timeAverage3;
	
	//We declare an empty integer that will hold the value for the number of elements to put in our arrays.
	static int n;
	
	//We declare an empty integer that will hold the value of the number of times we want to run the experiment.
	static int numberOfExperiments;
	
	//We create a scanner object so we can fill the n and numberOfExperiments variables with user input.
	static Scanner scan = new Scanner(System.in); // 
		
	public static void main(String[] args) 
	{
		
		//We call the function that lets us get our starter array size and number of experiments inputs from the user.
		getStarterInput();
		
		//We create our arrays for experimentation.
		int[] unsortedDefaultArray = new int[n]; //Our unsorted array which will be used to populate all other arrays with default elements.
		
		//We create three arrays which will hold copies of the original unsorted arrays elements.
		int[] insertionSortArray = new int[n];
		int[] selectionSortArray = new int[n];
		int[] quickSortArray = new int[n];
		
		//We run our experiments.
		runExperiments(unsortedDefaultArray, insertionSortArray, selectionSortArray, quickSortArray, n);
		
		
	}
	
	//The main method called to run our experiments with our sorting algorithms.
	static void runExperiments(int[] unsortedDefaultArray, int[] insertionSortArray, int[] selectionSortArray, int[] quickSortArray, int n)
	{
		
		for(int i = 0; i < numberOfExperiments; i++)
		{
			
		runExperiment(unsortedDefaultArray, insertionSortArray, selectionSortArray, quickSortArray, n);
		
		}
		
		//Update the Averages of our experiment results.
		timeAverage1 = timeSum1 / numberOfExperiments;
		timeAverage2 = timeSum2 / numberOfExperiments;
		timeAverage3 = timeSum3 / numberOfExperiments;
		
		//Output the Averages of our experiment results to the screen.
		outputExperimentAverageResults();
		
		System.out.println("[Success] " + numberOfExperiments + " Experiments Completed.");
		
		
	}
	
	//The submethod called by runExperiments() to run our individual experiment on our sorting algorithms.
	static void runExperiment(int[] unsortedDefaultArray, int[] insertionSortArray, int[] selectionSortArray, int[] quickSortArray, int n)
	{
		//We populate our default unsorted array with elements to pass to our sort algorithms
		createDefaultUnsortedArray(unsortedDefaultArray, n);
				
		//We copy the values of our default unsorted array to the placeholder arrays which our sorting algorithms will be applied to.
		copyDefaultUnsortedArray(unsortedDefaultArray, insertionSortArray, selectionSortArray, quickSortArray, n);
		
		//Apply Insertion Sort to our unsorted Array
		startTime1 = System.currentTimeMillis();
		insertionSort(insertionSortArray);
		endTime1 = System.currentTimeMillis();

				
		//Apply Selection Sort to our unsorted Array
		startTime2 = System.currentTimeMillis();
		selectionSort(selectionSortArray);
		endTime2 = System.currentTimeMillis();
				
		//Apply QuickSort to our unsorted Array
				
		startTime3 = System.currentTimeMillis();
		quickSort(quickSortArray, 0, n - 1);
		endTime3 = System.currentTimeMillis();
				
		//Output the time measurements for comparison
		outputExperimentResults();
		
		//We sum our times so that we can take the average in the runExperiments() parent function
		timeSum1 = timeSum1 + (endTime1 - startTime1); 
		timeSum2 = timeSum2 + (endTime2 - startTime2);
		timeSum3 = timeSum3 + (endTime3 - startTime3);
			
	}
	
	
	//The method to create a default array to be passed into all of our sorting algorithms.
	static void createDefaultUnsortedArray(int[] array, int n)
	{
		int min = 0;
		int max = 999999999;
		Random randomInteger = new Random();
		
		
		//Step through each index i in our array and populate it at that point with a random integer between min and max variables.
		for(int i=0; i<n; i++)
		{
			array[i] = randomInteger.nextInt((max - min) + 1) + min;
		}
		
	}
	
	public static void copyDefaultUnsortedArray(int[] defaultUnsortedArray, int[] insertionSortArray, int[] selectionSortArray, int[] quickSortArray,  int n)
	{
		
		//We assign the elements of our default unsorted array to the placeholder arrays to apply our various sorting algorithms to for consistency across tests.
		for(int i=0; i < n; i++)
		{
			
			insertionSortArray[i] = defaultUnsortedArray[i];
			selectionSortArray[i] = defaultUnsortedArray[i];
			quickSortArray[i] = defaultUnsortedArray[i];
			
		}
		
	}
	
	
	//The implementation of our Insertion Sort Algorithm.
	public static void insertionSort(int[] array)
	{
		
		int length = array.length, temp, k;
		
		for(int i = 1; i < length; i++)
		{
			temp = array[i];
			
			for(k = i; k > 0; k--)
			{
				
				if (array[k - 1] > temp)
				{
					
					array[k] = array[k - 1];
					
				}
				else
				{
					break;
				}
				
			}
			
			array[k] = temp;

			
		}
		
		
	}
	
	//The implementation of our Selection Sort Algorithm
	public static void selectionSort(int[] array)
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
	
	//The implementation of our Bubble Sort Algorithm
	public static void bubbleSort(int[] array)
	{
		
		int length = array.length, temp;
		boolean flag;
		
		for(int i = 0; i< length; i++)
		{
			
			flag = false;
			for(int j = 0; j < length - i - 1; j++)
			{
				
				if(array[j] > array[j + 1])
				{
					
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					flag = true;
				
				}
				
			}
			
		}
		
	}
	
	//The implementation of our QuickSort Algorithm.
	public static void quickSort(int array[], int lower, int upper)
	{
		if (lower >= upper)
		{
			return;
		}
			
		int p = partition(array, lower, upper);
		quickSort(array, lower, p - 1);
		quickSort(array, p + 1, upper);
			
	}
	
	//The method for partitioning during the quickSort function.
	//called by the quickSort() method
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
	
	//The method to output the sorted array to the command line.
	public static void outputArray(int array[], int n)
	{
		for(int i = 0; i < n; i++)
		{
			System.out.println(array[i] + " ");
		}
			System.out.println();
	}
	
	
	//The method to output the average of the results of our experiments to the screen.
	public static void outputExperimentAverageResults()
	{
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Number of Experiments Ran: " + numberOfExperiments);
		System.out.print("Insertion Sort Average Time: " + timeAverage1 + " Milliseconds | ");
		System.out.print("Selection Sort Average Time: " + timeAverage2 + " Milliseconds | ");
		System.out.println("QuickSort Average Time: " + timeAverage3 + " Milliseconds");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}
	
	//The method to output the results of our experiments to the screen.
	public static void outputExperimentResults()
	{
		
		System.out.println();
		System.out.println("Number of Array Elements : " + n);
		System.out.print("Insertion Sort Time : " + (endTime1 - startTime1) + " Milliseconds |");
		System.out.print("Selection Sort Time : " + (endTime2 - startTime2) + " Milliseconds |");
		System.out.println("QuickSort Time : " + (endTime3 - startTime3) + " Milliseconds");
		
	}
	
	//The method to output the starter prompt to get the user to populate the starter variables.
	public static void getStarterInput()
	{
		
		System.out.print("Enter the number of elements : ");
		n = scan.nextInt();
		System.out.println();
		System.out.print("Enter the number of times to run the experiment: ");
		numberOfExperiments = scan.nextInt();
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");

	}
	
}
