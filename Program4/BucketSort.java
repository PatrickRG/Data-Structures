//Program 4
//Patrick Goddard
//Data Structures and Algorithms in Java

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class BucketSort{

	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

	    System.out.print("Please specify an batch input file: ");
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
			int highest = 0;
			for(int i = 0; i < array.length -1; i++){
				array[i] = Integer.parseInt(st.nextToken().trim());
				if(array[i] > highest)
					highest = array[i];
			}

			//System.out.println("Highest: " + highest);
			int[][] buckets = new int[highest + 1][array.length];
			for(int i = 0; i < buckets.length; i++){
				for(int j = 0; j <  buckets[i].length; j++)
					buckets[i][j] = -1;
			}

			BucketSort lol = new BucketSort();
			long startTime = System.nanoTime();

		    lol.Bucket(array, buckets, startTime);

		}catch(Exception ex){ System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }

	}
	public void Bucket(int[] array, int[][] buckets, long startTime){
		try{
			for(int i = 0; i < array.length - 1; i++){
					int verIndex = 0;
					while(buckets[array[i]][verIndex] != -1)
						verIndex++;
					buckets[array[i]][verIndex] = array[i];
			}
			int counter = 0;
			for(int i = 0; i < buckets.length; i++){
				for(int j = 0; j < buckets[i].length; j++){
					if(buckets[i][j] != -1){
						array[counter] = buckets[i][j];
						counter++;
					}
				}
			}

			System.out.println("Sorted array: ");
			for(int i = 0; i < array.length - 1; i++)
				System.out.print(array[i] + " ");

			long endTime = System.nanoTime();
			long runTime = endTime - startTime;
			System.out.println("\n\nRun Time in Nano Seconds: " + runTime);

			}catch(Exception ex) { ex.printStackTrace(); System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }
	}

}
