# -*- coding: utf-8 -*-
"""
Created on Tue Oct  9 12:50:21 2018

@author: Mohammed
"""

"""
There are three seating categories at a stadium. For a football games, 
Class A seat’s cost €25, Class B seat’s cost €20 and Class C seat’s 
cost €30. Write a program that asks how many tickets for each class of 
seats were sold, and then display the amount of income generated from 
ticket sales. 
"""

seatACost = 25.00
seatBCost = 20.00
seatCCost = 30.00

resultA = int(input('How many tickets sold class A : '))
resultA *= seatACost 

resultB = int(input('How many tickets sold class B : '))
resultB *= seatBCost 

resultC = int(input('How many tickets sold class C : '))
resultC *= seatCCost 

print('\nTotal revenue generated from ticket sales')

totalRevenue = resultA + resultB + resultC

print(totalRevenue, 'euros.')