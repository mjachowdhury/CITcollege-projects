# -*- coding: utf-8 -*-
"""
Created on Wed Oct 10 16:38:51 2018

@author: Mohammed
"""

"""
Implement a function called printNumTriangle. The function should ask the user
 to enter a single integer. It should then print a triangle of that size 
 specified by the integer so that each row in the triangle is made up of the 
 integer displayed.  
 
The following is a sample output 
 
Please enter an integer for triangle size: 5 
1 
22 
333 
4444 
55555  
"""

def printNumTriangle():
    triangleRange = int(input('Please enter an integer for triangle size: '))
    for i in range(1, triangleRange):
        print(str(i)*i) # need to understand this line here
        
printNumTriangle()