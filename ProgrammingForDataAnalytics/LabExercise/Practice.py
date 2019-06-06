# -*- coding: utf-8 -*-
"""
Created on Tue Oct  9 12:03:13 2018

@author: Mohammed
"""
"""
By default the execution of a print command will move the cursor on to the next line • It can sometimes be useful to suspend this behaviour so that the cursor stays on the same line. • You can do this by placing a comma at the end of the print line

"""
#print('Welcome to ', end='')
#print('Analytical Programming')

"""
books = int(input('How many books do you want to read: '))
months = int(input('How many months will take to finish to read: '))

perMonth = books / months

print('You will read ', perMonth, 'books per month.')

"""

"""
name = input('Enter you full name: ')
rest = ' \n goes to college at \n\tCIT '
print (name + rest)
"""

"""
Write a revised and extended version of the loan approval program using 
logical operators (use a single if statement). • This version of the 
program should tell the user if they are approved or not for the loan.  
– A user will be approved for a loan if at least one of A or B below 
are met. 
• A) User has a salary greater than 30,000 and they have been 
working for more than two years 
• B) User has a bank account balance 
of greater than 70,000
"""
salary = int(input('Enter your anual salary: '))
yearsWorking = int(input('Enter number of years working: '))
bankBalance = int(input('Enter current balance: '))

if(salary >= 30000 and yearsWorking > 2 ) or bankBalance > 70000:
    print('Loan Approved')
else:
    print('Loan Declined')    