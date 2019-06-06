# -*- coding: utf-8 -*-
"""
Created on Tue Oct 16 10:15:57 2018

@author: Mohammed
"""

"""
Print out the average number of rental users for all days classified as holidays as well as the average for all 
days classified as non-holidays. (Note holidays =1 and non-holidays = 0). 
Holidays attribute is stored at index 5.  
"""

import numpy as np

def main():
    calculateAverageUsersHoliday()

def calculateAverageUsersHoliday():
    data = np.genfromtxt('G:/bike.csv', delimiter=',')# getting the data from where the fiel saved
    
    array1 = data[:,5]
    
    sum = 0
    counter = 0
    for i in array1:
        sum = sum +i
        counter = counter +1 
        Avg = sum/counter
    
    print('Average Holiday is :', Avg)  
    
 
main()    