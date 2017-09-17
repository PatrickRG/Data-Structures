//Program 4
//Patrick Goddard
//Data Structures and Algorithms in Java

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class MergeSort{

	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

	    System.out.print("Please specify an batch input file:");
	    String file = scan.nextLine();
	    StringTokenizer st = null;
	    try{
	      //Create a new File object
	      File f = new File(file);
	      //Pass the file into the FileInputStream
	      InputStream stream = new FileInputStream(f);
	      int size = stream.available();
	      String holder = "";

	      System.out.print("Your input file reads:\n\n");
	          //Loop through what's avaliable in the stream
	          for(int i = 0; i < size; i++) {
	            char x = (char)stream.read();
	              System.out.print(x);
	              //Add the values in a basic String so it is easier to parse
	              holder += x;
	          }
	          //Split the string into tokens based on spaces
	          st = new StringTokenizer(holder," ");
	          System.out.println();
	          System.out.println();

	    } catch(Exception ex){
	      System.out.println("Error encountered. Please run program following input directions. Exiting...");
	      System.exit(0);
	    }
		try{
			int[] array = new int[st.countTokens() + 1];
			for(int i = 0; i < array.length - 1; i++){
				array[i] = Integer.parseInt(st.nextToken().trim());
			}
			//System.out.println("Highest: " + highest);

			MergeSort lol = new MergeSort();
			//Store start time in nano seconds
			long startTime = System.nanoTime();
		    array = merge(array, 0, array.length - 1);
			long endTime = System.nanoTime();
			System.out.println("Sorted array: \n");
			for(int r = 1; r < array.length; r++)
				System.out.print(array[r] + " ");
			long runTime = endTime - startTime;
			System.out.println("\n\nRun Time in Nano Seconds: " + runTime);

		}catch(Exception ex){ ex.printStackTrace(); System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }

	}
	private static int[] merge(int[] array, int lower, int upper) {
		if (lower < upper) {
			int mid = lower + (upper - lower) / 2;
			merge(array, lower, mid);
			merge(array, mid + 1, upper);
			combine(array, lower, mid, upper);
		}
		return array;
	}

	private static int[] combine(int[] array, int lower, int mid, int upper) {

		int p = lower;
		int q = mid + 1;
		int r = lower;

		int[] temp = new int[array.length];
		for (int i = lower; i <= upper; i++) {
			temp[i] = array[i];
		}

		while (p <= mid  && q <= upper){
			if (temp[p] <= temp[q]){
				array[r] = temp[p];
				p++;
			}
			else{
				array[r] = temp[q];
				q++;
			}
			r++;
		}
		while (p <= mid ){
			array[r] = temp[p];
			r++;
			p++;
		}
		return array;
	}

}
