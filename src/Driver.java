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
	
	//We create 6 empty arrays
	int[] unsortedArray1, unsortedArray2, unsortedArray3;
	static int[] partiallySortedArray1, partiallySortedArray2, partiallySortedArray3;
	
	//We declare variables to hold time values
	//Enumerated 1 for Insertion Sort, 2 for Selection Sort, and 3 for Quick Sort. 4,5,6 are the same but for the partially sorted versions.
	static long startTime1, startTime2, startTime3, startTime4, startTime5, startTime6;
	static long endTime1, endTime2, endTime3, endTime4, endTime5, endTime6;
		
	//We declare empty long variables as placeholders to store the sums of the time spent on our experiments
	//Enumerated 1 for Insertion Sort, 2 for Selection Sort, and 3 for Quick Sort. 4,5,6 are the same but for the partially sorted versions
	static long timeSum1, timeSum2, timeSum3, timeSum4, timeSum5, timeSum6;
	
	//We declare empty long variables as placeholders to store the averages of our experiments on the sorting algorithms
	static long timeAverage1, timeAverage2, timeAverage3, timeAverage4, timeAverage5, timeAverage6;
	
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
		int[] partiallySortedDefaultArray = new int[n]; //Our partially sorted array which will be used to populate all other partially sorted arrays with default elements.
		
		//We create six arrays which will hold copies of the original unsorted array elements
		int[] insertionSortArray = new int[n];
		int[] selectionSortArray = new int[n];
		int[] quickSortArray = new int[n];
		//These three arrays only hold copies past n/2
		int[] partiallySortedSelectionSortArray = new int[n];
		int[] partiallySortedQuickSortArray = new int[n];
		int[] partiallySortedInsertionSortArray = new int[n];		
		
		//We run our experiments.
		runExperiments(unsortedDefaultArray, insertionSortArray, selectionSortArray, quickSortArray, n, partiallySortedDefaultArray, partiallySortedInsertionSortArray, partiallySortedSelectionSortArray, partiallySortedQuickSortArray);
		
	}
	
	//The main method called to run our experiments with our sorting algorithms.
	static void runExperiments(int[] unsortedDefaultArray, int[] insertionSortArray, int[] selectionSortArray, int[] quickSortArray, int n, int[] partiallySortedDefaultArray, int[] partiallySortedInsertionSortArray, int[] partiallySortedSelectionSortArray, int[] partiallySortedQuickSortArray)
	{
		
		for(int i = 0; i < numberOfExperiments; i++)
		{
			
		runExperiment(unsortedDefaultArray, insertionSortArray, selectionSortArray, quickSortArray, n, partiallySortedDefaultArray, partiallySortedInsertionSortArray, partiallySortedSelectionSortArray, partiallySortedQuickSortArray);
		
		}
		
		//Update the Averages of our experiment results.
		timeAverage1 = timeSum1 / numberOfExperiments;
		timeAverage2 = timeSum2 / numberOfExperiments;
		timeAverage3 = timeSum3 / numberOfExperiments;
		timeAverage4 = timeSum4 / numberOfExperiments;
		timeAverage5 = timeSum5 / numberOfExperiments;
		timeAverage6 = timeSum6 / numberOfExperiments;
		
		//Output the Averages of our experiment results to the screen.
		outputExperimentAverageResults();
		outputExperimentAverageResultsPS();
		
		System.out.println("[Success] " + numberOfExperiments * 2 + " Experiments Completed.");
		
		
	}
	

	
	//The submethod called by runExperiments() to run our individual experiment on our sorting algorithms.
	static void runExperiment(int[] unsortedDefaultArray, int[] insertionSortArray, int[] selectionSortArray, int[] quickSortArray, int n, int[] partiallySortedDefaultArray, int[] partiallySortedInsertionSortArray, int[] partiallySortedSelectionSortArray, int[] partiallySortedQuickSortArray)
	{
		//We populate our default unsorted array with elements to pass to our sort algorithms
		createDefaultUnsortedArray(unsortedDefaultArray, partiallySortedDefaultArray, n);
				
		//We copy the values of our default unsorted array to the placeholder arrays which our sorting algorithms will be applied to.
		copyDefaultUnsortedArray(unsortedDefaultArray, insertionSortArray, selectionSortArray, quickSortArray, n);
		//This function does the same thing but to a partially sorted array.
		copyDefaultPartiallySortedArray(partiallySortedDefaultArray, partiallySortedInsertionSortArray, partiallySortedSelectionSortArray, partiallySortedQuickSortArray, n);
		
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
		
		//outputArray(quickSortArray, n);
		//Output the time measurements for comparison
		
		
		//We sum our times so that we can take the average in the runExperiments() parent function
		timeSum1 = timeSum1 + (endTime1 - startTime1); 
		timeSum2 = timeSum2 + (endTime2 - startTime2);
		timeSum3 = timeSum3 + (endTime3 - startTime3);
		
		//Apply Insertion Sort to our partially sorted Array.
		startTime4 = System.currentTimeMillis();
		insertionSort(partiallySortedInsertionSortArray);
		endTime4 = System.currentTimeMillis();

						
		//Apply Selection Sort to our partially sorted Array.
		startTime5 = System.currentTimeMillis();
		selectionSort(partiallySortedSelectionSortArray);
		endTime5 = System.currentTimeMillis();
						
		//Apply QuickSort to our partially sorted Array.
						
		//Becomes unstable past 19000 elements and crashes program. no known fix yet. Runs fine when isolated in the QuickSort class. Problem seems to have something to do with the integer range. Possibly using too much memory?
		startTime6 = System.currentTimeMillis();
		if(n< 9999999)
		{
			quickSort(partiallySortedQuickSortArray, 0, n-1);
		}
		endTime6 = System.currentTimeMillis();
		
		//We sum our times so that we can take the average in the runExperiments() parent function
		timeSum4 = timeSum4 + (endTime4 - startTime4); 
		timeSum5 = timeSum5 + (endTime5 - startTime5);
		timeSum6 = timeSum6 + (endTime6 - startTime6);
			
		
	}
	

	
	//The method to create a default array to be passed into all of our sorting algorithms.
	static void createDefaultUnsortedArray(int[] array,int[]arrayb , int n)
	{
		int min = 0;
		int max = 9999999;
		Random randomInteger = new Random();
		
		
		//Step through each index i in our array and populate it at that point with a random integer between min and max variables.
		for(int i=0; i<n; i++)
		{
			array[i] = randomInteger.nextInt((max - min) + 1) + min;
		}
		
		createDefaultPartiallySortedArray(array, arrayb, n); //we pass our values into the creation of the partially sorted array so there is consistency in data. only half of them actually get stored in this array.
		
		
		
	}
	
	//Creates the default partially sorted array using the data from the default unsorted array.
	static void createDefaultPartiallySortedArray(int[] array, int[] arrayb, int n)
	{
		
		int min = 0;
		int max = 9999999;
		Random randomInteger = new Random();
		
		//We populate the first half of the array with integers in a sequential fashion. (Sorted)
		for(int i=0; i < n/2; i++)
		{
			arrayb[i] = i+1;
		}
		
		//We populate the second half of the array with random integers.
		for (int i=(n/2); i < n; i++)
		{
			arrayb[i] = array[i];
		}
		
		
		
	}
	
	//We copy our default partially sorted array into the other array values to test on for partial sort.
	public static void copyDefaultPartiallySortedArray(int[] defaultUnsortedArray, int[] insertionSortArray, int[] selectionSortArray, int[] quickSortArray,  int n)
	{
		
		//We assign the elements of our default unsorted array to the placeholder arrays to apply our various sorting algorithms to for consistency across tests.
		for(int i=0; i < n; i++)
		{
			
			insertionSortArray[i] = defaultUnsortedArray[i];
			selectionSortArray[i] = defaultUnsortedArray[i];
			quickSortArray[i] = defaultUnsortedArray[i];
			
		}
		
	}
	
	//We copy our default unsorted array into the other arrays which we wish to perform our sorting algorithms on that will not be partially sorted.
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
		
		// We declare and initialize our variables.
		int length = array.length, temp, k;
		
		//We loop through the array for the sorting process.
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
		// We declare and initialize our variables.
		int length = array.length, position, temp;
		
		//We loop through the array for the sorting process.
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
		// We declare and initialize our variables.
		int length = array.length, temp;
		boolean flag;
		
		// We loop through the array for our sorting process.
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
		// We declare and initialize our variables.
		int pivot = array[upper];
		int k = lower;
		int temp;
			
		//We loop through the array for the quicksort sorting processd.
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
	
	//The method to output an array to the command line. Used for testing that sorting works.
	public static void outputArray(int array[], int n)
	{
		for(int i = 0; i < n; i++)
		{
			System.out.println(array[i] + " ");
		}
			System.out.println();
	}
	
	
	//The method to output the average of the results of our experiments to the screen after all of the experiments are ran.
	public static void outputExperimentAverageResults()
	{
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Not Partially Sorted Results");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Number of Experiments Ran: " + numberOfExperiments + "   |");
		System.out.print("Insertion Sort Average Time: " + timeAverage1 + " Milliseconds | ");
		System.out.print("Selection Sort Average Time: " + timeAverage2 + " Milliseconds | ");
		System.out.println("QuickSort Average Time: " + timeAverage3 + " Milliseconds");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}
	
	//The method to output the average of the results of our experiments to the screen after all of the experiments are ran. FOR PARTIALLY SORTED ARRAYS
	public static void outputExperimentAverageResultsPS()
	{
		
		System.out.println("Partially Sorted Results");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Number of Experiments Ran: " + numberOfExperiments + "   |");
		System.out.print("Partially Sorted Array Insertion Sort Average Time: " + timeAverage4 + " Milliseconds | ");
		System.out.print("Partially Sorted Array Selection Sort Average Time: " + timeAverage5 + " Milliseconds | ");
		System.out.println("Partially Sorted Array QuickSort Average Time: " + timeAverage6 + " Milliseconds");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}
	
	//The method to output the results of our experiments to the screen.
	public static void outputExperimentResults()
	{
		
		System.out.println();
		System.out.println("Not Partially Sorted Results");
		System.out.println("Number of Array Elements : " + n);
		System.out.print("Insertion Sort Time : " + (endTime1 - startTime1) + " Milliseconds |");
		System.out.print("Selection Sort Time : " + (endTime2 - startTime2) + " Milliseconds |");
		System.out.println("QuickSort Time : " + (endTime3 - startTime3) + " Milliseconds");
		
	}
	//The method to output the results of our experiments to the screen. FOR PARTIALLY SORTED ARRAYS
	public static void outputExperimentResultsPS()
	{
		
		System.out.println();
		System.out.println("Partially Sorted Results");
		System.out.println("Number of Array Elements : " + n);
		System.out.print("Insertion Sort Time : " + (endTime4 - startTime4) + " Milliseconds |");
		System.out.print("Selection Sort Time : " + (endTime5 - startTime5) + " Milliseconds |");
		System.out.println("QuickSort Time : " + (endTime6 - startTime6) + " Milliseconds");
		
	}
	
	//The method to output the starter prompt to get the user to populate the starter variables.
	public static void getStarterInput()
	{
		
		//We get the number of elements we want to sort as well as the number of experiments we want to perform with the given number of elements.
		System.out.print("Enter the number of elements : ");
		n = scan.nextInt();
		System.out.println();
		System.out.print("Enter the number of times to run the experiment: ");
		numberOfExperiments = scan.nextInt();
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");

	}
	
}
