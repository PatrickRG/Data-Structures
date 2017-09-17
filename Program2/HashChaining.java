import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;
public class HashChaining{

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

    HashChaining lol = new HashChaining(); //Create an instance variable to call the non-static methods below
    lol.Chain(st);

    }

  	public void Chain(StringTokenizer st){
        //set hash size to the first integer in the list
        int hashSize = Integer.parseInt(st.nextToken());
        HashNode[] hash = new HashNode[hashSize];
        String secondNumber = st.nextToken();

        for(int i = 0; i < hashSize; i++)
            hash[i] = new HashNode(-1, null);

        while(st.countTokens() > 0)
    		{
    			String current = st.nextToken();
    			StringTokenizer c = new StringTokenizer(current, ".");
    			StringTokenizer temp = null;
                System.out.println();

    			if(current.contains("in")){
    				int numberToInsert = Integer.parseInt(c.nextToken());
                    HashNode nodeToInsert = new HashNode(numberToInsert, null);
                    int location = numberToInsert % hashSize;
    				System.out.println("inserting " + numberToInsert + " at " + location);

                    if(hash[location].value == -1){
                        hash[location] = nodeToInsert;
                    }
                    else{
                        hash[location].addNext(nodeToInsert);
                    }
    			}

    			else if(current.contains("del")){
    				int numberToDelete = Integer.parseInt(c.nextToken());
    				//int location = list.indexOf(numberToDelete);
                    Boolean deleted = false;
                    for(int i = 0; i < hashSize; i++){
                        if(hash[i].value == numberToDelete){
                            hash[i] = hash[i].getNext();
                            deleted = true;
                        }
                        HashNode tempNode = hash[i];
                        try{
                            while(true){
                                if(tempNode.getNext().value == numberToDelete){
                                    tempNode.deleteNodeAfter();
                                    deleted = true;
                                }
                                tempNode = tempNode.getNext();
                            }
                        } catch(Exception ex){}

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
                        HashNode tempNode = hash[i];
                        try{
                            while(true){
                                if(tempNode.getNext().value == numberToFind){
                                    found = true;
                                }
                                tempNode = tempNode.getNext();
                            }
                        } catch(Exception ex){}
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
                        System.out.print(i + ": " + hash[i].value + " ");
                        hash[i].printChain();
                    }

                }
        }

    }

	public class HashNode{
		private int value;
		private HashNode next;
		public HashNode(int val, HashNode n) {
			value = val;
			next = n;
		}

		public int getValue() { return value; }
		public HashNode getNext() { return next; }
		public void setNext(HashNode n) { next = n; }
        public void deleteNodeAfter() {
            if(getNext().getNext() != null){
			        HashNode tempNode = getNext().getNext();
			        setNext(tempNode);
            }
            else
                setNext(null);
		}
        public void addNext(HashNode s) {
            if(getNext() != null){
			        getNext().addNext(s);
            }
            else
                setNext(s);
		}
        public void printChain(){
            try{
                HashNode tempNode = getNext();
                while(true){
                    System.out.print(tempNode.value + " ");
                    tempNode = tempNode.getNext();
                }
            } catch(Exception f){System.out.println();}
        }
	}
}
