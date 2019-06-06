# -*- coding: utf-8 -*-
"""
Created on Wed Oct 10 16:54:14 2018

@author: Mohammed
"""

"""
Write a program that asks the user to enter the rainfall for the first X months
of the year into a list, where X is an int value between 1 and 12. (Obtaining 
the rainfall input from the user should be done using a loop).  
 
The program should calculate and display: 
    • The average monthly rainfall 
    • The highest rainfall value received  
    • The lowest rainfall value received 
 
The following is a sample output of this program. 
How many months of data do you wish to enter: 6 
Please enter rainfall for month1 83.6 
Please enter rainfall for month2 46.6 
Please enter rainfall for month3 97.1 
Please enter rainfall for month4 46.4 
Please enter rainfall for month5 61.4 
Please enter rainfall for month6 164.5 
Highest rainfall value: 164.5 
Lowest rainfall value: 46.4 
Average is 83.2666666667 
 
 
"""
# getting the user input for how many months
def monthsToTrack():
    userMonthsTracking = int(input('How many months of data do you wish to enter: '))
    return userMonthsTracking

def rainfallCalculation(userMonthsTracking):
    #declaring the variable
    totalMonths = 0
    totalRainFall = 0
    
    #creating a list to hold the monthly rainfall
    rainFallList = [] 
    
    #with for loop adding no of months rainfall to the list
    for currentMonth in range(1, userMonthsTracking+1):
       
        monthlyRainfall = int(input('Please enter rainfall for month '+ format(currentMonth, 'd',)+ ':'))
        
        #adding months here
        totalMonths += 1
        #adding to the list for each month rainfall    
        rainFallList.append(monthlyRainfall)
        
        #adding total monthly rainfall
        totalRainFall += monthlyRainfall
    
    #getting the average monthly rainfall        
    averageRainfall = totalRainFall / totalMonths
    
    print('Total months: '+ str(totalMonths)) # str(totalMonths) will print out total number of months
    print('Total rain: ', format(totalRainFall), '(inch)') 
    print('Total average rainfall : ', round(averageRainfall,2), '(inch)')
    print('Min rain : ', format(min(rainFallList)), '(inch)') # will find out from the list lowest value
    print('Max rain : ', format(max(rainFallList)), '(inch)') # will find out from the list highest value
    
def main():
    #here monthsToTrack method is returning its value to the varaible userMonths
    userMonths = monthsToTrack()
    
    #here calling the method rainfallCalculation and passing the argument
    rainfallCalculation(userMonths)
       
main()
    
 
    