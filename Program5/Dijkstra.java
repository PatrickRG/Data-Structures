//Program 5
//Patrick Goddard
//Data Structures and Algorithms in Java

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Dijkstra{

	public static void main(String args[]){
	Scanner scan = new Scanner(System.in);

    System.out.print("Please specify an batch input file: ");
    String file = scan.nextLine();
    StringTokenizer st = null;

    try{
		//Create a new File object
		File f = new File(file);
		//Pass the file into the FileInputStream
		Scanner counter = new Scanner(f);
		Scanner in = new Scanner(f);
		System.out.print("Your input file reads:     \n");

		String lineHolder = "";
		int rowNum = 0;
		while (counter.hasNextLine()) {
		  	lineHolder = counter.nextLine();
		  	rowNum++;
		}

		int[][] matrix = new int[rowNum][rowNum];
		in.useDelimiter("[/n]");

		String line = "";
		int lineCount = 0;
		while (in.hasNextLine()) {
		    line = in.nextLine();
		    Scanner lineIn = new Scanner(line);
		    lineIn.useDelimiter(" ");

		    for (int i = 0; i < rowNum; i++) {
				String temp = lineIn.next().trim();
				if(temp.equals("inf"))
					matrix[lineCount][i] = -1;
				else
		        	matrix[lineCount][i] = Integer.parseInt(temp);
		    }
		    lineCount++;
		}

		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				if(matrix[i][j] == -1)
					System.out.print("inf ");
				else
					System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("\n");

		//Since my methods are static, create instance variable to call
		Dijkstra lol = new Dijkstra();
		lol.traverse(matrix, matrix.length);


    }catch(Exception ex){
			System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting...");
			System.exit(0);
  		}

    }


	public void traverse(int graph[][], int size)
	{
		//path array to hold shortest current path
		int[][] path = new int[size][size];

		//Boolean array to hold whether a vertex is in the shortest path
		Boolean[] isIncluded = new Boolean[size];

		//fill path diagonal with max value since it will need to be compared to hold shortest path
		for (int i = 0; i < size; i++){
			path[i][i] = Integer.MAX_VALUE;
			isIncluded[i] = false;
		}

		//distance from source node to itself is always zero
		path[0][0] = 0;

		for (int count = 0; count < size-1; count++){

			//pick minimum distance
			int min = Integer.MAX_VALUE;
			int lowerBound = min;

			for (int iter = 0; iter < size; iter++){
			 	if (isIncluded[iter] == false && path[iter][iter] <= min){
			     	min = path[iter][iter];
				 	lowerBound = iter;
				}

			}

			int q = lowerBound;
			isIncluded[q] = true;

			for (int r = 0; r < size; r++){

				//check if there is a shorter path
				if (path[q][q] != Integer.MAX_VALUE && graph[q][r] != -1 && path[r][r] > path[q][q] + graph[q][r] && !isIncluded[r]){

					//clear path array to get rid of old path values
					for (int i = 0; i < size; i++){
						path[i][r] = 0;
					}

					//store shortest value
					path[r][r] = path[q][q] + graph[q][r];
					//add individual paths
					path[q][r] = graph[q][r];

				}
			}
		}

		System.out.println("Shortest path matrix: \n");
		for (int i = 0; i < size; i++){
			for(int j = 0; j < size; j++)
				System.out.print(path[i][j] + " ");
		System.out.println();
		}
	}
}
