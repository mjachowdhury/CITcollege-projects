# -*- coding: utf-8 -*-
"""
Created on Tue Sep 25 09:36:54 2018

@author: Mohammed
"""
""""
The area of a rectangle is the length multiplied by width. Write a program that asks for the
length and width of two rectangles. The program should tell the user which rectangle has the
greater area or if the areas are the same. 
"""

lengthOne = int(input('Enter first lengthOne:'))
widthOne = int(input('Enter first widthOne:'))
lengthTwo = int(input('Enter first lengthTwo:'))
widthTwo = int(input('Enter first widthTwo:'))

areaOne = lengthOne * widthOne
areaTwo = lengthTwo * widthTwo

if areaOne > areaTwo:
    print("Rectange 1 is greater than rectagne 2")
elif areaOne < areaTwo:
    print ("REctange 2 is greater than rectange 1")
else:
    print('Area of R1 is the same of R2')
    