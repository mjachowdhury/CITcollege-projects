#Task 1
#Write a bash script to inform of user login activities. If a username or ID is provided to the
#script, it should output:
#- the real name of the user,
#- the number of times the user is logged in,
#- time and date of their last login.
#If no username is given, it should output in a tabular form:
#- all logged in users,
#- time and date of their last login.
#To test your solution, create multiple users on your system. Properly comment your code for
#easy understanding.

							#################### REPORT ##########################

# This script is about to get the user name and how many times they logged in and their time and date when they logged in last time. For this first it will create a file in the tmp folder and the name  which is -- task1File and from their I will get all the user details about their activities
 
 
#!/bin/bash

# Script Name: task1.sh

#creating a file name as task1File in the tmp folder
	
	clear
	w > /tmp/task1File

#Getting and finding out the total unique user logged in the system currently

	echo "Total number of unique users logged in the system currently"
	cat /tmp/task1File|  sed '1,2d' | awk '{print $1}' | uniq | wc -l

	echo #Creating extra space

#Getting and finding out the total unique user name logged in the system currently

	echo "List of unique users name logged in the system currently"
	cat /tmp/task1File | sed '1,2d'|  awk '{print $1}' | uniq

	echo #Creating extra space

#Getting user activiteis and what they are doing 
	echo "List of users logged in and what they are doing"
	cat /tmp/task1File

	echo #Creating extra space

#Getting last login user name and time and date 
	
	NOWDATE=$(date +"%Y-%m-%d")
	NOWTIME=$(date +"%T")
	
	echo "Last login user name date and time:"
	echo "$NOWDATE""  ""$NOWTIME " 
	
	last | grep ^username | grep 'logged in' | wc -l
	
	cat /tmp/task1File
	
	echo
