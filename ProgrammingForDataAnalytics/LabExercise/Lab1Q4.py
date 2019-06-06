# -*- coding: utf-8 -*-
"""
Created on Tue Oct  9 12:15:01 2018

@author: Mohammed
"""
"""
 Write a program that will ask a student for their first name and then for their surname. 
 It should then ask the student to enter the int numerical grade they received in each of their 
 three subjects.  
The program should then print out the full name of the student along with their average numerical 
grade (Use only a single print statement) 
  
"""


fName = input('Enter your first name please: ')
sName = input('Enter your Surname please: ')

gradeSubA = int(input('Grade for Subject A :'))
gradeSubB = int(input('Grade for Subject B :'))
gradeSubC = int(input('Grade for Subject C :'))

print ('Student Full Name ',fName, ' ' + sName, 'Three subject grade are ', gradeSubA, gradeSubB, gradeSubC)