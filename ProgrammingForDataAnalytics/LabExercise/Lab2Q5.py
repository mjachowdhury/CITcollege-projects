# -*- coding: utf-8 -*-
"""
Created on Wed Oct 10 17:53:53 2018

@author: Mohammed
"""

"""
The Fibonacci numbers are the numbers in the following integer sequence: 
 
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ... 
 
By definition, the first two numbers in the Fibonacci sequence are 0 and 1, and each subsequent 
number is the sum of the previous two. 
 
Create a program that creates a list and will populate it with the first 40 Fibonacci numbers 
(this can be done with just three lines of code).  
 
The program should then ask the user to enter an integer value between 1 and 40 to indicate which
number in the Fibonacci series they would like to see and the application should display that number. 
For example, if the user enters 13, the 13th number is 144. 
 
 
"""

def userInput():
    number = int(input('Please enter the number between 1 - 40 to find out the fibonacci :'))
    return number

def findFibonacci(number):
    if number == 0:
        return 0
    elif number == 1:
        return 1
    else:
        return findFibonacci(number - 1) + findFibonacci (number - 2)
    
    
def main():
    userNumber = userInput()
    print(findFibonacci(userNumber))
    
main()    
    