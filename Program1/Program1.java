//Program 1
//Patrick Goddard
//Data Structures and Algorithms in Java

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Program1{



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

		System.out.println("Enter '1' for doubly-linked list");
		System.out.println("Enter '2' for stack");
		System.out.println("Enter '3' for circular queue");
		System.out.println("Enter '4' for max-heap");
		System.out.println("Enter number: ");
		String input = scan.nextLine();

		//Create an instance variable to call the non-static methods below
		Program1 inst = new Program1();

		if(input.equals("1")){
			lol.doublyLinkedList(st);
		}
		else if(input.equals("2")){
			lol.stackImplementation(st);
		}
		else if(input.equals("3")){
			lol.circularQueue(st);
		}
		else if(input.equals("4")){
			lol.maxHeap(st);
		}
		else
			System.out.println("Error encountered. Please run program following input directions. Exiting...");
	}


	public void doublyLinkedList(StringTokenizer st){

		DList list = new DList();
		try{
			while(st.countTokens() > 0)
			{

				System.out.println("\n" + list + "\n");

				String current = st.nextToken();
				StringTokenizer c = new StringTokenizer(current, ".");
				StringTokenizer temp = null;

				//Parse which function is taken in
				if(current.contains("in") && current.contains("_")){
					int numberToInsert = Integer.parseInt(c.nextToken());
					String holder = c.nextToken();
					String[] split = holder.split("_");
					int locationToInsert = Integer.parseInt(split[1]);
					System.out.print("inserting " + numberToInsert + " at position " + locationToInsert);

					DList.Node bef = list.getNodeInPosition(locationToInsert);
					DList.Node aft = bef.getNext();
					list.addBetween(numberToInsert, bef, aft);

				}
				else if(current.contains("in")){
					int numberToInsert = Integer.parseInt(c.nextToken());
					System.out.print("inserting " + numberToInsert + " at head");

					list.addFirst(numberToInsert);
				}
				else if(current.contains("del")){
					int numberToDelete = Integer.parseInt(c.nextToken());
					//int location = list.indexOf(numberToDelete);
					System.out.print("deleting " + numberToDelete);
					list.remove(list.findFirstNodeWithVal(numberToDelete));
				}
				else if(current.contains("sch")){
					int numberToFind = Integer.parseInt(c.nextToken());
					//Call the function findFirstNodeWithVal to see if the value is in the list
					if(list.findFirstNodeWithVal(numberToFind) != null)
						System.out.println(numberToFind + " was FOUND");
					else if(list.findFirstNodeWithVal(numberToFind) == null)
						System.out.println(numberToFind + " was  NOT FOUND");
				}
				else
					System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting...");
			}
			System.out.println("\n\nFinalList: " + list + "\n");
		} catch(Exception ex) { System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }
	}

	public void stackImplementation(StringTokenizer st){

		StackItUp stack = new StackItUp();
		try{
			int stackSize = Integer.parseInt(st.nextToken());
			stack.setMaxSize(stackSize);

			while(st.countTokens() > 0)
			{

				System.out.println("\n" + stack + "\n");

				String current = st.nextToken();
				StringTokenizer c = new StringTokenizer(current, ".");
				StringTokenizer temp = null;

				//Parse which function is taken in
				if(current.contains("push")){
					int numberToInsert = Integer.parseInt(c.nextToken());
					System.out.print("pushing " + numberToInsert + " to front");

					stack.push(numberToInsert);
					//current.previousNode = prev;
				}
				else if(current.contains("pop")){
					//int location = list.indexOf(numberToDelete);
					System.out.print("popping");
					stack.pop();
				}
				else
					System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting...");

				//for(int i = 0; i < list.size(); i++)
					//System.out.print( + list.get(i) + " - ");
			}
			System.out.println("\n\nFinal Stack: " + stack + "\n");
		} catch(Exception ex) { System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }
	}

	public void circularQueue(StringTokenizer st){

		CQueue q = new CQueue();
		try{
			while(st.countTokens() > 0)
			{

				System.out.println("\n" + q + "\n");

				String current = st.nextToken();
				StringTokenizer c = new StringTokenizer(current, ".");
				StringTokenizer temp = null;

				//Parse which function is taken in
				if(current.contains("in") && current.contains("_")){
					int numberToInsert = Integer.parseInt(c.nextToken());
					String holder = c.nextToken();
					String[] split = holder.split("_");
					int locationToInsert = Integer.parseInt(split[1]);
					System.out.print("inserting " + numberToInsert + " at position " + locationToInsert);
					//if(list.contains(numberToInsert))
					//	System.out.println(" -- DUPLICATE KEY");
					//else
					//	System.out.println();
					//current = new Node(numberToInsert);
					CQueue.Node bef = q.getNodeInPosition(locationToInsert);
					CQueue.Node aft = bef.getNext();
					q.addBetween(numberToInsert, bef, aft);

				}
				else if(current.contains("in")){
					int numberToInsert = Integer.parseInt(c.nextToken());
					System.out.print("inserting " + numberToInsert + " at head");

					//if(list.contains(numberToInsert))
					//	System.out.println(" -- DUPLICATE KEY");
					//else
					//	System.out.println();
					q.addFirst(numberToInsert);
					//current.previousNode = prev;
				}
				else if(current.contains("del")){
					int numberToDelete = Integer.parseInt(c.nextToken());
					//int location = list.indexOf(numberToDelete);
					System.out.print("deleting " + numberToDelete);
					q.remove(q.findFirstNodeWithVal(numberToDelete));
				}
				else if(current.contains("sch")){
					int numberToFind = Integer.parseInt(c.nextToken());

					if(q.findFirstNodeWithVal(numberToFind) != null)
						System.out.println(numberToFind + " was FOUND");
					else if(q.findFirstNodeWithVal(numberToFind) == null)
						System.out.println(numberToFind + " was  NOT FOUND");
				}
				else
					System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting...");

				//for(int i = 0; i < list.size(); i++)
					//System.out.print( + list.get(i) + " - ");
			}
			System.out.println("\n\nFinal CircularQueue: " + q + "\n");
		} catch(Exception ex) { System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }
	}

	public void maxHeap(StringTokenizer st){

		MHeap heap = new MHeap();
		try{
			String prePostIn = st.nextToken();
			System.out.println("\n\nPrint method: " + prePostIn);
			while(st.countTokens() > 0){

				//Parse which function is taken in
				if(prePostIn.equalsIgnoreCase("pre")){
					System.out.println();
					heap.toStringPre(0);
					System.out.println("\n");
				}
				else if(prePostIn.equalsIgnoreCase("post")){
					System.out.println();
					heap.toStringPost(0);
					System.out.println("\n");
				}
				else if(prePostIn.equalsIgnoreCase("in")){
					System.out.println();
					heap.toStringIn(0);
					System.out.println("\n");
				}
				else{
					System.out.println("You have not specified a PRE/POST/IN printing method in the input file. See README.");
				}

				String current = st.nextToken();
				StringTokenizer c = new StringTokenizer(current, ".");
				StringTokenizer temp = null;

				if(current.contains("in")){
					int numberToInsert = Integer.parseInt(c.nextToken());
					System.out.print("inserting " + numberToInsert);
					heap.add(numberToInsert);
				}
				else if(current.contains("del")){
					int numberToDelete = Integer.parseInt(c.nextToken());
					System.out.print("deleting " + numberToDelete);
					heap.remove(numberToDelete);
				}
				else if(current.contains("sch")){
					int numberToFind = Integer.parseInt(c.nextToken());

					if(heap.findFirstNodeWithVal(numberToFind) != -1)
						System.out.println(numberToFind + " was FOUND");
					else if(heap.findFirstNodeWithVal(numberToFind) == -1)
						System.out.println(numberToFind + " was  NOT FOUND");
				}
				else
					System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting...");
			}
			if(prePostIn.equalsIgnoreCase("pre")){
				System.out.print("\nFinal Heap: ");
				heap.toStringPre(0);
				System.out.println("\n");
			}
			else if(prePostIn.equalsIgnoreCase("post")){
				System.out.print("\nFinal Heap: ");
				heap.toStringPost(0);
				System.out.println("\n");
			}
			else if(prePostIn.equalsIgnoreCase("in")){
				System.out.print("\nFinal Heap: ");
				heap.toStringIn(0);
				System.out.println("\n");
			}
			else{
				System.out.println("You have not specified a PRE/POST/IN printing method in the input file. See README.");
			}
		}catch(Exception ex) { System.out.println("There was a problem reading your input file. Please use the respective format listed in the README. Exiting..."); }
	}

	public class DList{

		public Node header;
		public Node tail;
		public int size = 0;

		//Default constructor; The header is behind the head node; the tail is one past the last node; these hold psuedo-pointers
		public DList() {
			header = new Node(-1, null, null);
			tail = new Node(-1, header, null);
			header.setNext(tail);
		}

		//Node class that holds values and exists within DList
		public class Node{
			private int value;
			private Node prev;
			private Node next;
			public Node(int val, Node p, Node n) {
				value = val;
				prev = p;
				next = n;
			}

			public int getValue() { return value; }
			public Node getPrev() { return prev; }
			public Node getNext() { return next; }
			public void setPrev(Node p) { prev = p; }
			public void setNext(Node n) { next = n; }
		}

		public int size() { return size; }

		public boolean isEmpty() { return size == 0; }

		public Node first() {
			if (isEmpty()) return null;
		 	return header.getNext();
		}

		public Node last() {
		 	if (isEmpty()) return null;
		 	return tail.getPrev();
		}

		public void addFirst(int e) {
			addBetween(e, header, header.getNext());
		}

		public void addLast(int e) {
			addBetween(e, tail.getPrev(), tail);
		}

		public void removeFirst() {
			if (!isEmpty())
				remove(header.getNext()); // first element is beyond header
		}

		public void removeLast() {
			if (isEmpty())
				remove(tail.getPrev()); // last element is before tail
		}

		//Add between two elements
		public void addBetween(int e, Node predecessor, Node successor) {
			Node newest = new Node(e, predecessor, successor);
			predecessor.setNext(newest);
			successor.setPrev(newest);
			size++;
		}

		//remove the first node with the value that is passed in
		public void remove(Node node) {
			if(node.getValue() == last().getValue())
				removeLast();
			else{
				Node predecessor = node.getPrev();
				Node successor = node.getNext();
				predecessor.setNext(successor);
				successor.setPrev(predecessor);
			}
		}

		public Node findFirstNodeWithVal(int e){
			Node temp = header.getNext();
			for(int i = 0; i < size(); i++){
				if(temp.getValue() == e)
					return temp;
				else
					temp = temp.getNext();
			}
			return null;
		}

		//override toString method to print
		public String toString(){
			String s = "";
			Node temp = header.getNext();
			for(int i = 0; i < size(); i++){
				if(temp.getValue() == -1)
					continue;
				else
					s += temp.getValue() + " ";
				temp = temp.getNext();
			}
			return s;
		}

		//return node at a certain location
		public Node getNodeInPosition(int pos){
			Node temp = header.getNext();
			for(int i = 0; i < pos -1; i++){
				temp = temp.getNext();
			}
			return temp;
		}
	}

	public class StackItUp{

		public Node first;
		public Node nextNode;
		public int size = 0;
		public int maxSize = 10;

		//We only need a next pointer since this is a Stack and not a double list
		public StackItUp() {
			first = new Node(-1, null);
			nextNode = new Node(-1, null);
			first.setNext(nextNode);
		}

		//Use nodes and point them to the next node; LIFO
		public class Node{
			private int value;
			private Node next;
			public Node(int val, Node n) {
				value = val;
				next = n;
			}

			public int getValue() { return value; }
			public Node getNext() { return next; }
			public void setNext(Node n) { next = n; }
		}

		public void setMaxSize(int e){
			System.out.println("\n\nSetting maximum size to " + e);
			maxSize = e;
		}

		public int size() { return size; }

		public boolean isEmpty() { return size == 0; }

		public Node top() {
			if (isEmpty())
				return null;
		 	return first.getNext();
		}

		//Push value to top of stack
		public void push(int e) {
			Node newest = new Node(e, first.getNext());
			first.setNext(newest);
			size++;
		}

		//pop the first value off of the stack
		public void pop() {
				Node tempNode = first.getNext();
				first.setNext(tempNode.getNext());
				size--;
		}

		//override the toString method of Object class
		public String toString(){
			String s = "";
			Node temp = first.getNext();
			for(int i = 0; i < size(); i++){
				if(temp.getValue() == -1)
					continue;
				else
					s += temp.getValue() + " ";
				temp = temp.getNext();
			}
			return s;
		}
	}

	public class CQueue{
		public Node header;
		public Node tail;
		public int size = 0;

		//CQueue operates like a DList but with the tail pointing at the head
		public CQueue() {
			header = new Node(-1, null, null);
			tail = new Node(-1, header, null);
			header.setNext(tail);
			tail.setNext(header);
		}

		public class Node{
			private int value;
			private Node prev;
			private Node next;
			public Node(int val, Node p, Node n) {
				value = val;
				prev = p;
				next = n;
			}

			public int getValue() { return value; }
			public Node getPrev() { return prev; }
			public Node getNext() { return next; }
			public void setPrev(Node p) { prev = p; }
			public void setNext(Node n) { next = n; }
		}

		public int size() { return size; }

		public boolean isEmpty() { return size == 0; }

		public Node first() {
			if (isEmpty()) return null;
		 	return header.getNext();
		}

		public Node last() {
		 	if (isEmpty()) return null;
		 	return tail.getPrev();
		}

		public void addFirst(int e) {
			addBetween(e, header, header.getNext());
		}

		public void addLast(int e) {
			addBetween(e, tail.getPrev(), tail);
		}

		public void removeFirst() {
			if (!isEmpty())
				remove(header.getNext());
		}

		public void removeLast() {
			if (isEmpty())
				remove(tail.getPrev());
		}

		//add between two nodes
		public void addBetween(int e, Node predecessor, Node successor) {
			Node newest = new Node(e, predecessor, successor);
			predecessor.setNext(newest);
			successor.setPrev(newest);
			size++;
		}

		//remove a node
		public void remove(Node node) {
			if(node.getValue() == last().getValue())
				removeLast();
			else{
				Node predecessor = node.getPrev();
				Node successor = node.getNext();
				predecessor.setNext(successor);
				successor.setPrev(predecessor);
			}
		}

		//find the first node with value e that is passed in
		public Node findFirstNodeWithVal(int e){
			Node temp = header.getNext();
			for(int i = 0; i < size(); i++){
				if(temp.getValue() == e)
					return temp;
				else
					temp = temp.getNext();
			}
			return null;
		}

		//return node that is in a certain location
		public Node getNodeInPosition(int pos){
			Node temp = header.getNext();
			for(int i = 0; i < pos -1; i++){
				temp = temp.getNext();
			}
			return temp;
		}

		//override Object toString method
		public String toString(){
			String s = "";
			Node temp = header.getNext();
			for(int i = 0; i < size(); i++){
				if(temp.getValue() == -1)
					continue;
				else
					s += temp.getValue() + " ";
				temp = temp.getNext();
			}
			s += "[<--points to " + temp.getNext().getNext().getValue() + "]"; //Show that the list is truly circular
			return s;
		}
	}

	public class MHeap{
		private int[] heap = new int[0];
		private int size = 0;

		public boolean isEmpty() { return size == 0; }

		public int getSize(){ return size;}

		//If the heap need to be increased
		public void increaseSize(){
			int[] temp = new  int[heap.length+1];
		    for(int i = 0; i < temp.length; i++){
		    	if(i == temp.length - 1)
		        	temp[i]= -1;
		        else
		        	temp[i] = heap[i];
		    }
		    size++;
		    heap = temp;
		}


		public void remove(int e){
			int location = findLocation(e);
			//-1 acts as null in my heap
			if(location != -1){
				//if the value exists, swap it with the last node to prepare for deletion
				int temp = heap[location];
				heap[location] = heap[size - 1];
				heap[size -1] = temp;
				//create new int array to hold decremented list
				int[] tempA = new  int[heap.length-1];
				//fill the new list with all values except the last value that needs to be deleted
			    for(int i = 0; i < tempA.length; i++){
			        	tempA[i] = heap[i];
			    }
			    //decrement size
			    size--;
			    //set heap to the new decremented array I created
			    heap = tempA;
			    //sort the heap to turn it into a max heap
			    sort();
			}
		}


		public void add(int e){
			//if the heap is at size, increase size
			if(heap.length == size)
				increaseSize();
			//add value to last element
			heap[size-1] = e;
			//sort the heap
			sort();
		}

		public void sort(){

			int leftChild;
			int rightChild;
			int parent;
			for(int i = 0; i < size; i++)
			{
				parent = heap[i];
				//make sure we aren't calling out of the bounds of the array
				if((2*i + 1) < size)
					leftChild  = heap[2*i + 1];
				else
					leftChild = -1;

				//make sure we aren't calling out of the bounds of the array
				if((2*i + 2) < size)
					rightChild = heap[2*i + 2];
				else
					rightChild = -1;
				//compare left child to parent node and right child to see if they need to be switched
				//if they are switched, re-call sort to start again
				if(leftChild > parent && leftChild > rightChild){
			 		int temp = heap[i];
			 		heap[i] = heap[2*i + 1];
			 		heap[2*i + 1] = temp;
			 		sort();
			 	}

			 	//compare right child to parent node and left child to see if they need to be switched
				//if they are switched, re-call sort to start again
			 	else if(rightChild > parent && rightChild > leftChild){
			 		int temp = heap[i];
			 		heap[i] = heap[2*i + 2];
			 		heap[2*i + 2] = temp;
			 		sort();
			 	}
			}
		}

		//return the first location of value e
		public int findLocation(int e){
			for(int i = 0; i < heap.length; i++){
				if(heap[i] == e)
					return i;
			}
			return -1;
		}

		//Find the first node with value e
		public int findFirstNodeWithVal(int e){
			for(int i = 0; i < heap.length; i++){
				if(heap[i] == e)
					return heap[i];
			}
			return -1;
		}

		//Pre output method
		public void toStringPre(int root){
			if(root >= size)
				return;
			//Print PRE both calls
			System.out.print(heap[root] + " ");
			//recursively call left child
			toStringPre(2 * root + 1);
			//recursively call right child
			toStringPre(2 * root + 2);
		}

		//Post output method
		public void toStringPost(int root){
			if(root >= size)
				return;
			//recursively call left child
			toStringPost(2 * root + 1);
			//recursively call right child
			toStringPost(2 * root + 2);
			//Print POST both calls
			System.out.print(heap[root] + " ");
		}

		//In output method
		public void toStringIn(int root){
			if(root >= size)
				return;
			//recursively call left child
			toStringIn(2 * root + 1);
			//Print IN between both calls
			System.out.print(heap[root] + " ");
			//recursively call right child
			toStringIn(2 * root + 2);

		}
	}
}
