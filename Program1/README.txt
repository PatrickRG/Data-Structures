NOTE: OPEN THIS README WITHIN 'Notepad++' OR A PROPER TEXT EDITOR

READ ME for PROGRAM 1 by Patrick Goddard

######################################################################################################################################################

COMPILE the file with 'javac Program1.java'
RUN the file with 'java Program1'

######################################################################################################################################################

After you run the file, specify an structures input file '<name>.bat'

######################################################################################################################################################

BATCH FILE FORMATTING:


Doubly-Linked List: 

        <INTEGER.in>           - inserts the integer at the head of the list
        <INTEGER.in_LOCATION>  - inserts the integer at the location integer
        <INTEGER.del>          - deletes the first value of the integer provided in the list
        <INTEGER.sch>          - prints if the integer is found in the list or not

        Duplicate values are allowed, 'DEL' will just delete the first value in the list

        Example Input File (ListTest.bat): 1.in 3.in 5.in 3.del 7.in 9.in 11.in 12.in 2.in 1.sch 15.in_7 2.in


Stack: 

        The first value needs to be an integer, this specifies the maximum size of the stack
  
        <INTEGER.push>         - pushes the integer to the top of the stack if the stack is not at maximum size
        <pop>                  - pops the top integer off of the stack

         Example Input File (StackTest.bat): 10 1.push 3.push pop 7.push 9.push 11.push 12.push pop pop 15.push


Circular Queue:

        Operates like a list, but the TAIL points to the HEAD
      
        <INTEGER.in>           - inserts the integer at the head of the list
        <INTEGER.in_LOCATION>  - inserts the integer at the location integer
        <INTEGER.del>          - deletes the first value of the integer provided in the list
        <INTEGER.sch>          - prints if the integer is found in the list or not

        Duplicate values are allowed, 'DEL' will just delete the first value in the circular queue

        Example Input File (test.bat): 1.in 3.in 5.in 3.del 7.in 9.in 11.in 12.in 2.in 1.sch 15.in_7 2.in


Max Heap:

         The first value needs to be one of the following strings: { pre | post | in}
         This specifies the printing algorithm of the maximum heap

        <INTEGER.in>           - inserts the integer at the next node within the heap (child right or child left), 
                                 the maximum heap is then sorted
        <INTEGER.del>          - deletes the first value of the integer in the heap
                                 the maximum heap is then sorted
        <INTEGER.sch>          - prints if the integer is found in the heap or not

        Example Input File (HeapTest.bat): pre 1.in 3.in 5.in 3.del 2.in 1.sch


######################################################################################################################################################