//Program 3
//Patrick Goddard
//Data Structures and Algorithms in Java

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class RedBlackTree{
	private static RBNode nullNode;
	/* static initializer for nullNode */
	static
	{
		nullNode = new RBNode(0);
		nullNode.left = nullNode;
		nullNode.right = nullNode;
	}

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
      System.out.print("Your input file reads:     ");
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
    //Since my methods are not static, I have to create an instance variable to call them
    RedBlackTree lol = new RedBlackTree();
    lol.Execute(st);
	}


	public void Execute(StringTokenizer st){

		RBTree tree = new RBTree(-1);
		try{
			while(st.countTokens() > 0){


				String currentNode = st.nextToken();
				StringTokenizer c = new StringTokenizer(currentNode, ".");
				StringTokenizer temp = null;

				if(currentNode.contains("in")){
					int numberToInsert = Integer.parseInt(c.nextToken());
					System.out.print("inserting " + numberToInsert);
					if(tree.search(numberToInsert))
						System.out.print("\nCannot insert " + numberToInsert + " : DUPLICATE\n\n");
					else
						tree.insert(numberToInsert);
				}
				else if(currentNode.contains("del")){
					int numberToDelete = Integer.parseInt(c.nextToken());
					System.out.print("deleting " + numberToDelete);
					tree.delete(numberToDelete);
				}
				else if(currentNode.contains("sch")){
					int numberToFind = Integer.parseInt(c.nextToken());

					if(tree.search(numberToFind))
						System.out.print(numberToFind + " was FOUND\n\n");
					else
						System.out.print(numberToFind + " was  NOT FOUND\n\n");
				}
				else
					System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting...");

					tree.toStringIn();
					System.out.println("\n");

			}
				//System.out.println("Final Red Black Tree:\n   ");
				//tree.toStringIn();
				System.out.println();

			}catch(Exception ex) { System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }
	}

	static class RBNode{

	    RBNode left;
		RBNode right;
		RBNode parent;
	    int value;
	    int color;

	    public RBNode(int value){
			this.left = nullNode;
	 	    this.right = nullNode;
	 	    this.value = value;
			this.parent = nullNode;
	 	    color = 1;
	    }

	    public RBNode(int value, RBNode left, RBNode right, RBNode parent){
		    this.left = left;
		    this.right = right;
		    this.value = value;
			this.parent = parent;
		    color = 1;
	    }
	}

	static class RBTree{
		private RBNode currentNode;
		private RBNode parentNode;
		private RBNode grandNode;
		private RBNode greatNode;
		private RBNode rootPointer;


		static final int BLACK = 1;
		static final int RED   = 0;

		public RBTree(int holder){
			rootPointer = new RBNode(holder);
			rootPointer.left = nullNode;
			rootPointer.right = nullNode;
		}

		public void insert(int val ){
			currentNode = parentNode = grandNode = rootPointer;
			nullNode.value = val;
			while (currentNode.value != val){
				greatNode = grandNode;
				grandNode = parentNode;
				parentNode = currentNode;
				if(val < currentNode.value)
					currentNode = currentNode.left;
				else
					currentNode = currentNode.right;

				if (currentNode.left.color == RED && currentNode.right.color == RED)
					handleReorient( val );
			}

			if (currentNode != nullNode)
				return;
			currentNode = new RBNode(val, nullNode, nullNode, nullNode);

			if (val < parentNode.value){
				parentNode.left = currentNode;
				currentNode.parent = parentNode;
			}
			else{
				parentNode.right = currentNode;
				currentNode.parent = parentNode;
			}

			//System.out.println(currentNode.value + "'s parent is " + parentNode.value);
			handleReorient(val);
		}

		private void destroy(RBNode r){

			if(currentNode.left == nullNode && currentNode.right == nullNode){
				System.out.println("Case 1");
				if(currentNode.parent.left == currentNode)
					currentNode.parent.left = nullNode;
				else
					currentNode.parent.right = nullNode;
			}

			else if (currentNode.left != nullNode && currentNode.right != nullNode) {

	            RBNode replacement = leftRightLast(currentNode.left);
	            currentNode.value = replacement.value;
				replacement.parent.right = replacement.left;
				replacement.left.parent = replacement.parent;
				replacement.left = nullNode;
				replacement.right = nullNode;
	        }

			else if (currentNode.left != nullNode && currentNode.right == nullNode) {
				System.out.println("Case 4");
				if(currentNode.parent.left.value == currentNode.value)
					currentNode.parent.left = currentNode.left;
				else
					currentNode.parent.left = currentNode.left;
	   		}

			else if (currentNode.left == nullNode && currentNode.right != nullNode) {
				System.out.println("Case 4");
				if(currentNode.parent.left.value == currentNode.value)
					currentNode.parent.left = currentNode.right;
				else
					currentNode.parent.left = currentNode.right;
	   		}
			else
				System.out.println("failed detection");

		}

		private void delete(int val){
			currentNode = rootPointer.right;
			try{
				while(currentNode.value != val){
					if(val < currentNode.value)
						currentNode = currentNode.left;
					else
						currentNode = currentNode.right;
				}
			}catch(Exception lol){}
			//System.out.println("Parent of " + currentNode.parent.value);
			destroy(currentNode);
			handleReorient(currentNode.value);
			handleReorient(currentNode.right.value);
			handleReorient(currentNode.left.value);
			handleReorient(currentNode.parent.value);

		}

		private void handleReorient(int value){
			currentNode.color = RED;
			currentNode.left.color = BLACK;
			currentNode.right.color = BLACK;

			if (parentNode.color == RED){
				grandNode.color = RED;
				if (value < grandNode.value && grandNode.value != value && value < parentNode.value)
					parentNode = rotate(value, grandNode);
				currentNode = rotate(value, greatNode);
				currentNode.color = BLACK;
			}
			rootPointer.right.color = BLACK;
		}

		private RBNode rotate(int val, RBNode parentNode)
		{
			if(val < parentNode.value){
				if(val < parentNode.left.value){
					parentNode.left = rotateLeft(parentNode.left);
					return parentNode.left;
				}
				else{
					parentNode.left = rotateRight(parentNode.left);
					return parentNode.left;
				}
			}
			else{
				if(val < parentNode.right.value){
					parentNode.right = rotateLeft(parentNode.right);
					return parentNode.right;
				}
				else{
					parentNode.right = rotateRight(parentNode.right);
					return parentNode.right;
				}
			}
		}

		private RBNode rotateLeft(RBNode current){
			RBNode leftChild = current.left;
			current.left = leftChild.right;
			leftChild.right = current;
			return leftChild;
		}

		private RBNode rotateRight(RBNode current){
			RBNode rightChild = current.right;
			current.right = rightChild.left;
			rightChild.left = current;
			return rightChild;
		}

		public boolean search(int val){
			return search(rootPointer.right, val);
		}

		private boolean search(RBNode node, int val)
		{
			boolean found = false;
			while ((node != nullNode) && !found)
			{
				int nodeVal = node.value;
				if (val < nodeVal)
					node = node.left;
				else if (val > nodeVal)
					node = node.right;
				else
				{
					found = true;
					break;
				}
				found = search(node, val);
			}
			return found;
		}

		public void toStringIn(){
			System.out.println("   [Root: " + rootPointer.right.value + " ]");
			inorder(rootPointer.right);
	    }

		private void inorder(RBNode r){
			if (r != nullNode){
				inorder(r.left);

				String colorHolder = " Black";
				if (r.color == 0)
					colorHolder = " Red";
				System.out.print(r.value + "" + colorHolder + ", ");

				inorder(r.right);
			}
		}

		private RBNode leftRightLast(RBNode r){
			if(r.right == nullNode) return r;
			return leftRightLast(r.right);
		}
	}
}
