# -*- coding: utf-8 -*-
"""
Created on Tue Oct 16 09:50:12 2018

@author: Mohammed
"""


"""
(i)  
Calculate the average temperature value (index 9) for the entire dataset. Note the temperature values in this 
column have been normalized by dividing by 41. 
"""

import numpy as np

#calling the method in side the main
def main():
    findAverageTemputure()
    

 
def findAverageTemputure():
    data = np.genfromtxt('G:/bike.csv', delimiter=',')# getting the data from where the fiel saved

    array1 = data[:,9] # here am getting the coloum number 9 from the csv file and all the row 

    sum = 0
    counter = 0
    for i in array1:
        sum = sum +i
        counter = counter +1 
        Avg = sum/counter
    
    print('Average Temputer is :', Avg)    
    

#calling main function
main()