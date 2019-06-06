# -*- coding: utf-8 -*-
"""
Created on Wed Oct 10 15:48:45 2018

@author: Mohammed
"""
"""
• Write a program with a main function that then calls a function called squareNumbers. 
• The function squareNumbersshould ask the user for a number and print out the value of the number squared. 
"""

"""
def squareNumbers():
    sNum = int(input('ENter a number for to be aquare : '))
    #both will work
    #result = sNum ** 2 
    result = sNum * sNum
    print(result)
    

def main():
    squareNumbers()
   
main()   
"""

"""
def main():
    fName = input('Enter your first name please: ')
    lName = input('Enter your last name please: ')
    reversedName(fName, lName)
    
    
def reversedName(fName, lName):
    print('Your reversed name is: ',lName, fName)
    
main()
"""

"""
myValue = 10

def main():
    showValue()
    print(' = ' , myValue)
    
def showValue():
    global myValue
    myValue = 12
    
main()

"""

"""
myList = [2,3,4,5,6,7,8,9]

size = len(myList)-1

print(size)

index = 0

while index < len(myList):
    print(myList[index])
    index += 1

print('\n')

for num in myList:
    print(num)

"""

limit = int(input('Enter upper limit:'))

numbers = list(range(limit+1))
print(numbers)

index = len(numbers)-1

while index >= 0:
    print(numbers[index]),
    index -= 1



    
    
    