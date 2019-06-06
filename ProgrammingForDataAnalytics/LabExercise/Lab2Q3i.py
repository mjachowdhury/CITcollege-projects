# -*- coding: utf-8 -*-
"""
Created on Wed Oct 10 15:23:38 2018

@author: Mohammed
"""

"""
You should write a function that will print out the ‘times’ table for a 
number up to a specific limit. The function should take in two parameters.
The first value, num, is the number that we will multiply by 0, 1, 2, 3, etc.
The second number, limit, is the number at which we will stop multiplying.
   
So if the user enters 3 as the value of num and 5 as the value of limit 
then the program will output the 3 times table from 0 to 5  as shown below.  
• 3*0 • 3*1 • 3*2 • 3*3 • 3*4 • 3*5 
 
The following is a sample output: 
Please enter time tables for printing: 6 Please enter upper limit for 
multiplication: 4 6*0 = 0 6*1 = 6 6*2 = 12 6*3 = 18 6*4 = 24 
 
"""
 
num = int(input('Enter time tables for printing: '))
upperLimit = int(input('Enter upper limit: '))

#here 0 menat it will start from 0 and upperLimit + 1 menas it will multiply up to the upperlimt number
# say for exaple if i put 6 upperlimt it will print till 6 and if i put 3 it will start from 0 to 3 * 6
def printTable(num, upperLimit):   
    for i in range(0, upperLimit+1):
        print(num, 'x', i, '=', num * i)

def main():
    printTable(num, upperLimit)
   
main()    