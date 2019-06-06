# -*- coding: utf-8 -*-
"""
Created on Tue Sep 25 10:54:04 2018

@author: Mohammed
"""
"""
Write a function called powerV1. When you call this function 
(from your main function) it should ask the user for a base number and a 
power number. It should then print out the result of raising the base
 number to the power of the second number.  
 """
#Lab3 Question1 (i)
""" 
def main():
    powerV1()
    
def powerV1():
    baseNumber = int(input('Please enter base number: '))
    powerNumber = int(input('Please enter power number: '))
    result = baseNumber**powerNumber
    print(result)
    
main()
"""
#Lab3 Question 1 (ii)
"""
You are going to write a variant of the program described in question (i). Your main function should 
ask the user to input two int values. The first will specify the base number and the second should specify 
power number. You will then called a function called powerV2. This function will take in two int values as 
parameters (the base and power numbers). The function will simply print the value of the base number raised 
to the power value.  
 
"""
"""
def main():
    baseNumber = int(input('Enter the base number: '))
    powerNumber = int(input('Enter the power number: '))
    powerV2(baseNumber, powerNumber)
    
def powerV2(bNum, pNum):
    print('The value of ',bNum, 'raised to the power of ',pNum, 'is :',bNum**pNum)
    
main()
"""


#Lab3 Question 1 (iii)
"""
Write a variant of the function defined in part(ii) called powerV3. This function will take in two int values 
as parameters. The first will specify the base number and the second should specify power number. The function 
will calculate value of the base number raised to the power value and will return this value. Verify the function
 works correctly by storing the result returned from the powerV3 function in a variable and printing out the 
 variable.  
Programming 
"""

def powerV3(bNum, pNum):
    result = bNum**pNum
    return result

def main():
     baseNumber = int(input('Enter the base number: '))
     powerNumber = int(input('Enter the power number: '))
     answer = powerV3(baseNumber, powerNumber)
     print(answer)
    
main()    















