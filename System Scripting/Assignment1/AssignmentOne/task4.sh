#Task 4
#Write an interactive bash script with three functions that accepts two integer parameters. The
#script should request a user to input two numbers. The first function should implement a
#multiplication. The second function should implement an addition. In the third function, when
#the second parameter is a multiple of 5, a multiplication operation should be executed otherwise
#the addition operation. The script should terminate after four interactions. Properly comment
#your code.


				#################### REPORT ##########################
#For this script first I will take two integer user input. then based on hte logic it will show the result. If user put second input 5 then it will do the multiplication otherwise it will do the addition function.
 

#!/bin/bash

# Script Name: task4.sh

#main script

#Getting user input for two parameters

clear

echo "If the second parameter is number 5 then it will do the multiplication otherwise it will do addition."
echo
echo -n "Please Enter First Number :"
	read first #waiting to get first number
echo -n "Please Enter Second Number :"
	read second # waiting to get second number 
	

# Multiplication function

function mul()
{
	x=$1 #first argument
	y=$2 #second argument
	
	echo -e "\nNumber entered by you for multiplication:
			 \nFirst number is : $x 
		 	 \nSecond number is : $y"
		 	 
	echo "" #For extra space
		 	
	echo "Multiplication of $1 and $2 is = `expr $x \* $y` "
	
} #end of mul function


# Addition function

function add()
{
	x=$1 #first argument
	y=$2 #second argument
	
	echo -e "\nNumber entered by you for addition:
			 \nFirst number is : $x 
		 	\nSecond number is : $y"
		 	
	echo "" #For extra space
		 	
	echo "Sum of $1 and $2 is = `expr $x + $y` "
} #end of add function


# Logic if second parameter is 5 then it will call mul function
# otherwise it will call addition function

function third()
{
	if [ "$second" = 5 ]; then mul $first $second
	else add $first $second
	fi
}


#calling function
third 
echo
 

