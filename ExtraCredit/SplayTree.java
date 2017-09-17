//Extra Credit
//Patrick Goddard
//Data Structures and Algorithms in Java

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class SplayTree{
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
    SplayTree lol = new SplayTree();
    lol.Execute(st);
	}


	public void Execute(StringTokenizer st){

		Tree tree = new Tree();
		try{
			while(st.countTokens() > 0){


				String currentNode = st.nextToken();
				StringTokenizer c = new StringTokenizer(currentNode, ".");
				StringTokenizer temp = null;

				if(currentNode.contains("in")){
					int numberToInsert = Integer.parseInt(c.nextToken());
					System.out.print("\n\ninserting " + numberToInsert + "\n\n");
					if(tree.search(numberToInsert))
						System.out.print("Cannot insert " + numberToInsert + " : DUPLICATE\n\n");
					else
						tree.insert(numberToInsert);
				}
				else if(currentNode.contains("del")){
					int numberToDelete = Integer.parseInt(c.nextToken());
					System.out.print("\n\ndeleting " + numberToDelete + "\n\n");
					tree.remove(numberToDelete);
				}
				else if(currentNode.contains("sch")){
					int numberToFind = Integer.parseInt(c.nextToken());

					if(tree.search(numberToFind))
						System.out.print("\n\n" + numberToFind + " was FOUND\n\n");
					else
						System.out.print("\n\n" + numberToFind + " was  NOT FOUND\n\n");
				}
				else
					System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting...");

					tree.inOrder();
					System.out.println("\n");

			}
				//System.out.println("Final Red Black Tree:\n   ");
				//tree.toStringIn();
				System.out.println();

			}catch(Exception ex) { System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }
	}

	static class SplayNode{
		SplayNode left;
		SplayNode right;
		SplayNode parent;
		int value;

		public SplayNode(int value, SplayNode left, SplayNode right, SplayNode parent){
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.value = value;
		}

		public SplayNode(){
			this.left = null;
			this.right = null;
			this.parent = null;
			this.value = -1;
		}
	}

	static class Tree{

		SplayNode rootNode;

		public Tree(){
			rootNode = null;
		}

		private void Splay(SplayNode node){

			while (node.parent != null){
				if (node.parent.parent == null){

				 	if (node == node.parent.left)
				     	rotateLeft(node, node.parent);
				 	else
				     	rotateRight(node, node.parent);
				}

		     	else{
		         	if (node == node.parent.left){
						if (node.parent == node.parent.parent.left){
						rotateLeft(node.parent, node.parent.parent);
						rotateLeft(node, node.parent);
						}

			             else{
			                rotateLeft(node, node.parent);
			                rotateRight(node, node.parent);
			            }
		         	}
		         	else{

						if (node.parent == node.parent.parent.left){
						 	rotateRight(node, node.parent);
						 	rotateLeft(node, node.parent);
						}
						else{
							rotateRight(node.parent, node.parent.parent);
						 	rotateRight(node, node.parent);
						}
		         	}
		     	}
		 	}

		 	rootNode = node;
		}
		public void rotateLeft(SplayNode node, SplayNode parentNode){

			if (parentNode.parent != null){
			 	if (parentNode == parentNode.parent.left)
			     	parentNode.parent.left = node;
			 	else
			     	parentNode.parent.right = node;
			}

		 	if (node.right != null)
		     	node.right.parent = parentNode;

			node.parent = parentNode.parent;
			parentNode.parent = node;
			parentNode.left = node.right;
			node.right = parentNode;
		}

		public void rotateRight(SplayNode node, SplayNode parentNode){

			if (parentNode.parent != null){
			 	if (parentNode == parentNode.parent.left)
			     	parentNode.parent.left = node;
			 	else
			     	parentNode.parent.right = node;
			}
		 	if (node.left != null)
		     	node.left.parent = parentNode;

			node.parent = parentNode.parent;
			parentNode.parent = node;
			parentNode.right = node.left;
			node.left = parentNode;
		}

		public void remove(int elementToRemove){
		 	SplayNode node = searchForNode(elementToRemove);
			remove(node);
		}

		private void remove(SplayNode node){

		 	if (node == null)
		     	return;

		 	Splay(node);

			if(node.left != null && node.right !=null){
				while(node.left.right != null)
				node.left = node.left.right;

				node.left.right = node.right;
				node.right.parent = node.left;
				node.left.parent = null;
				rootNode = node.left;
			}

			else if( node.left != null){
				node.left.parent = null;
				rootNode = node.left;
			}

			else if (node.right != null){
				node.right.parent = null;
				rootNode = node.right;
			}



			else{
				rootNode = null;
			}

			node.left = null;
			node.right = null;
			node.parent = null;
			node = null;
		}

		public void insert(int elementToInsert){

			SplayNode holder = rootNode;
			SplayNode temp = null;
			while (holder != null){

		     	temp = holder;
		     	if (elementToInsert < temp.value)
		         	holder = holder.right;
		     	else
		         	holder = holder.left;
			}

			holder = new SplayNode();

			holder.value = elementToInsert;
			holder.parent = temp;

			if (temp == null)
		     	rootNode = holder;
			else if (temp.value > elementToInsert)
		     	temp.right = holder;
			else
		     	temp.left = holder;

			Splay(holder);
		}

		public boolean search(int value){
			SplayNode current = rootNode;

			while (current != null){
				if (value > current.value)
				 	current = current.left;
				else if (value < current.value)
					current = current.right;
				else
				 	return true;
			}
			return false;
		}

		public SplayNode searchForNode(int value){

			SplayNode current = rootNode;

			while (current != null){
				if (value > current.value)
				 	current = current.left;
				else if (value < current.value)
					current = current.right;
				else
				 	return current;
			}
			return null;
		}

		public void inOrder(){
			System.out.println("Root: " + rootNode.value + "\n");
		 	inOrder(rootNode);
		}

		public void inOrder(SplayNode current){

		 	if (current != null){
				inOrder(current.left);
				System.out.print(current.value + " ");
				inOrder(current.right);
		 	}
		}
	}
}
