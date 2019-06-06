# -*- coding: utf-8 -*-
"""
Created on Tue Oct  2 10:02:43 2018

@author: Mohammed
"""

"""
Write a calculator. The program should first ask the user for two separate numerical values. It should then give the 
user an option to perform one of four operations:  addition, subtraction, division or multiplication. Therefore, 
if the user selects multiplication then your program should print out the product of the two values.  
You should write a different function for performing each operation. For example the sum function will 
take in two int numerical parameters and return the result. The following is sample output from this program. 
"""

# Lab3 Question 2 (1)
"""
def userChoice():
    print('Simple Calculator Program\n')
    print('1. Addition\n')
    print('2. Substruction\n')
    print('2. Multiplication\n')
    print('4. Division\n')
    
    
    

def addition(numberOne, numberTwo):
    return numberOne + numberTwo

def substruction(numberOne, numberTwo):
    return numberOne - numberTwo

def multiplication(numberOne, numberTwo):
    return numberOne * numberTwo

def division(numberOne, numberTwo):
    return numberOne / numberTwo


def calculator():
    num1 = int(input('Enter first number: '))
    num2 = int(input('Enter second number: '))
    
    userChoice()
    
    option = int(input('Please enter your choice:\n'))
    
    exitCal = False
    
    while exitCal == False:
        if option == 1:
            result = addition(num1, num2)
            print(result) 
        elif option == 2:
            result = substruction(num1,num2)
            print(result) 
        elif option == 3:
            result = multiplication(num1, num2)
            print(result) 
        elif option == 4:
            result = division(num1, num2)
            print(result) 
        else:
            print('Invalid choice')
           
            exitCal == True
        
def main():
    calculator()
  
main()    
"""   

""" 
def addition(numberOne, numberTwo):
    return numberOne + numberTwo

def substruction(numberOne, numberTwo):
    return numberOne - numberTwo

def multiplication(numberOne, numberTwo):
    return numberOne * numberTwo

def division(numberOne, numberTwo):
    return numberOne / numberTwo

def main():
    exitCal = False
    while exitCal == False:
        num1 = int (input('Enter number One : '))
        num2 = int (input('Enter number Two: '))
        
        option = int (input('Please choose one of the Option.\n 1. Add\n 2. Sub\n 3. Mul\n 4. Div : ')) 
        if option == 1:
            result = addition(num1, num2)
        elif option == 2:
            result = substruction(num1 , num2)
        elif option == 3:
            result = multiplication(num1, num2)
        elif option == 4:
            result = division(num1, num2)
        else:
            print('Invalid choice')
        print(result)    
    

main()  
"""
"""
# Program make a simple calculator that can add, subtract, multiply and divide using functions

# This function adds two numbers 
def add(x, y):
   return x + y

# This function subtracts two numbers 
def subtract(x, y):
   return x - y

# This function multiplies two numbers
def multiply(x, y):
   return x * y

# This function divides two numbers
def divide(x, y):
   return x / y

print("Select operation.")
print("1.Add")
print("2.Subtract")
print("3.Multiply")
print("4.Divide")

# Take input from the user 
choice = input("Enter choice(1/2/3/4):")

num1 = int(input("Enter first number: "))
num2 = int(input("Enter second number: "))

if choice == '1':
   print(num1,"+",num2,"=", add(num1,num2))

elif choice == '2':
   print(num1,"-",num2,"=", subtract(num1,num2))

elif choice == '3':
   print(num1,"*",num2,"=", multiply(num1,num2))

elif choice == '4':
   print(num1,"/",num2,"=", divide(num1,num2))
else:
   print("Invalid input")

"""

def calculate():
    operation = input('''
Please type in the math operation you would like to complete:
+ for addition
- for subtraction
* for multiplication
/ for division
''')

    number_1 = int(input('Please enter the first number: '))
    number_2 = int(input('Please enter the second number: '))

    if operation == '+':
        print('{} + {} = '.format(number_1, number_2))
        print(number_1 + number_2)

    elif operation == '-':
        print('{} - {} = '.format(number_1, number_2))
        print(number_1 - number_2)

    elif operation == '*':
        print('{} * {} = '.format(number_1, number_2))
        print(number_1 * number_2)

    elif operation == '/':
        print('{} / {} = '.format(number_1, number_2))
        print(number_1 / number_2)

    else:
        print('You have not typed a valid operator, please run the program again.')

    # Add again() function to calculate() function
    again()

def again():
    calc_again = input('''
Do you want to calculate again?
Please type Y for YES or N for NO.
''')

    if calc_again.upper() == 'Y':
        calculate()
    elif calc_again.upper() == 'N':
        print('Thank you for using our program.')
        print('See you later.')
    else:
        again()
        
        
def welcome():
    print('''
Welcome to Calculator
''')

        
welcome()
calculate()

    
    
    
    
    