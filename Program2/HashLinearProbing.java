import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;
public class HashLinearProbing{

  public static void main(String args[]){
    Scanner scan = new Scanner(System.in);

    System.out.print("Please specify an batch input file: ");
    String file = scan.nextLine();
    StringTokenizer st = null;

    try{
      File f = new File(file);
      InputStream stream = new FileInputStream(f);
      int size = stream.available();
      String holder = "";
      System.out.print("Your input file reads:     ");

          for(int i = 0; i < size; i++) {
            char x = (char)stream.read();
              System.out.print(x);
              holder += x;
          }

          st = new StringTokenizer(holder," ");
          System.out.println();
          System.out.println();

    } catch(Exception ex){
      System.out.println("Error encountered. Please run program following input directions. Exiting...");
      System.exit(0);
    }

    HashLinearProbing lol = new HashLinearProbing(); //Create an instance variable to call the non-static methods below
    lol.LinearProbing(st);

    }

  	public void LinearProbing(StringTokenizer st){
        //set hash size to the first integer in the list
        int hashSize = Integer.parseInt(st.nextToken());
        HashNode[] hash = new HashNode[hashSize];
        String secondNumber = st.nextToken();

        for(int i = 0; i < hashSize; i++)
            hash[i] = new HashNode(-1);

        while(st.countTokens() > 0)
    		{
    			String current = st.nextToken();
    			StringTokenizer c = new StringTokenizer(current, ".");
    			StringTokenizer temp = null;
                System.out.println();

    			if(current.contains("in")){
    				int numberToInsert = Integer.parseInt(c.nextToken());
                    HashNode nodeToInsert = new HashNode(numberToInsert);
                    int location = numberToInsert % hashSize;

                    if(hash[location].value == -1){
                        System.out.println("inserting " + numberToInsert + " at " + location);
                        hash[location] = nodeToInsert;
                    }
                    else{
                        Boolean alreadyInserted = false;
                        for(int i = location; i < hashSize; i++){
                            if(hash[i].value == -1){
                                hash[i] = nodeToInsert;
                                System.out.println("inserting " + numberToInsert + " at " + i + "-- USING PROBING");
                                alreadyInserted = true;
                                break;
                            }
                        }
                        if(!alreadyInserted){
                            for(int i = 0; i < location; i++){
                                if(hash[i].value == -1){
                                    hash[i] = nodeToInsert;
                                    System.out.println("inserting " + numberToInsert + " at " + i + "-- USING PROBING");
                                    break;
                                }
                                if(i == (location - 1))
                                    System.out.println(numberToInsert + " could NOT be inserted");
                            }
                        }
                    }
    			}

    			else if(current.contains("del")){
    				int numberToDelete = Integer.parseInt(c.nextToken());
    				//int location = list.indexOf(numberToDelete);
                    Boolean deleted = false;
                    for(int i = 0; i < hashSize; i++){
                        if(hash[i].value == numberToDelete){
                            hash[i].value = -1;
                            deleted = true;
                        }

                    }
                    if(deleted)
    				    System.out.println("deleting " + numberToDelete);
                    else
                        System.out.println(numberToDelete + " was not found");

    			}
    			else if(current.contains("sch")){
    				int numberToFind = Integer.parseInt(c.nextToken());
                    Boolean found = false;
                    for(int i = 0; i < hashSize; i++){
                        if(hash[i].value == numberToFind){
                            found = true;
                        }
                    }

                    if(found)
                        System.out.println("\n" + numberToFind + " was FOUND\n");
                    else
                        System.out.println("\n" + numberToFind + " was NOT FOUND");

    			}
    			else
    				System.out.println("There was a problem reading your input file. Please use the respective format listed in the assignment description. Exiting...");

                for(int i = 0; i < hashSize; i++){
                    if(hash[i].value != -1){
                        System.out.println(i + ": " + hash[i].value + " ");
                    }

                }
        }

    }

	public class HashNode{
		private int value;
		public HashNode(int val) {
			value = val;
		}

		public int getValue() { return value; }
	}
}
