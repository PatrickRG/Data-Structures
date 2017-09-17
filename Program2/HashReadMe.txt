READ ME for PROGRAM 2 by Patrick Goddard

#######################################################################################################################

COMPILE the files with
    'javac HashLinearProbing.java'
    'javac HashChaining.java'
    'javac HashDouble.java'

RUN the files with
    'java HashLinearProbing'
    'java HashChaining'
    'java HashDouble'

#######################################################################################################################

After you run the program, specify an input file <name.bat>

#######################################################################################################################


FILE FORMATTING:


Linear Probing:

      [n] The FIRST  entry, which has to be an INTEGER, in the input file sets the SIZE OF THE HASH MAP
      [N] The SECOND entry, which has to be an INTEGER, in the input file is how many ENTRIES ARE TO BE PERFORMED

      <INTEGER.in>           - inserts the integer at location at [INTEGER % n], if this fails we increment until the first open slot
      <INTEGER.del>          - deletes the first value of the integer provided in the list
      <INTEGER.sch>          - prints if the integer is found in the list or not

      Example Input File (test.bat): 100 10 1.in 3.in 5.in 203.in 103.in 103.del 7.in 9.in 11.in 12.in 109.in 2.in 1.sch 15.in

Hash Chaining:

      [n] The FIRST  entry in the input file sets the SIZE OF THE HASH MAP
      [N] The SECOND entry in the input file is how many ENTRIES are to be performed

      <INTEGER.in>           - inserts the integer at location at [INTEGER % n], and adds it to the end of the chain at location [INTEGER % n]
      <INTEGER.del>          - deletes the first value of the integer provided in the list
      <INTEGER.sch>          - prints if the integer is found in the list or not

      Example Input File (test.bat): 100 10 1.in 3.in 5.in 203.in 103.in 103.del 7.in 9.in 11.in 12.in 109.in 2.in 1.sch 15.in

Double Hashing:

    [n] The FIRST  entry in the input file sets the SIZE OF THE HASH MAP
    [N] The SECOND entry in the input file is how many ENTRIES are to be performed

      <INTEGER.in>           - inserts the integer at location at [INTEGER % n], if this fails the double hashing method will keep being called until
                               ([INTEGER % n] + ([0,1,...,n-1] * ([largestPrimeNumber below N] - (INTEGER % [largestPrimeNumber below N]) % n;
      <INTEGER.del>          - deletes the first value of the integer provided in the list
      <INTEGER.sch>          - prints if the integer is found in the list or not

      Example Input File (DoubleTest.bat): 13 8 18.in 41.in 22.in 44.in 59.in 32.in 31.in 73.in 59.sch 4.sch
      //This input file is the visual input example within the lecture slides, and my program returns the same results

#######################################################################################################################
