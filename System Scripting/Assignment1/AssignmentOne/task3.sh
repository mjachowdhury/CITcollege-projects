#Task 3
#Write a bash script with a function that accepts a file as parameter. The function should
#double space the lines in the file and write the output to a new file as well as to the standard
#output. Ensure in the body of the script that it is being called with the correct number of
#arguments. Also ensure that the file is readable before attempting to double space. Properly
#comment your code.


				#################### REPORT ##########################
#For this script first I have created a text file in the directory name rteNews.txt. When the program will run it will show the original content in the terminal. Then it will be double spaced and will be shown in the terminal as well. Then program will create a new file with updated content and it will be saved in the same directory.


#!/bin/bash

# Script Name: task3.sh

clear

# Here I am creating original file and showing them in the terminal first
echo "Original file"
echo "============="
echo ""
cat rteNews.txt

 
# Creating the function for Double space and saving them to a new file and it will be in the same directory

echo "Here is the Double space from the original file."

function DoubleSpace()
{
	echo "================ Double Space=============="
	echo ""
	
	#Here I am creating the double space
	sed G rteNews.txt	
	
	echo "********************"
	echo "After creating the double space then Creating the new file with updated content and saving the file in the directory" 
	echo 
	echo -n "Please look into the directory for a new file. FILE NAME IS - newFile.txt"
	
	
	# Here I am transfering origina content to a new file with the double space
	
	#sed G rteNews.txt >> newFile.txt	
	sed G rteNews.txt &>> newFile.txt
	echo 	
	
} #end of function

#calling the function

DoubleSpace

echo

