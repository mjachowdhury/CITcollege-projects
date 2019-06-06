# -*- coding: utf-8 -*-
"""
Created on Tue Oct  9 12:24:35 2018

@author: Mohammed
"""
"""
Write a program to calculate and display a person’s body mass index (BMI).
 A persons BMI is calculated with the following formula: 
 
• BMI = (weight/ height2  ) * 703  
 
Where weight in in pounds and height in in inches. Your program should 
ask the user for their weight (in pounds) and height (in inches).  
 
"""
weight = int(input('Enter your weight in pounds: '))
height = int(input('Enter your height: '))

#BMI = (weight / height * height) * 703
bmi = (weight*703)/(height*height)

print('Your BMI is : ', bmi)