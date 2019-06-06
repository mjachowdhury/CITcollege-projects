# -*- coding: utf-8 -*-
"""
Created on Tue Oct 16 10:31:26 2018

@author: Mohammed
"""

import numpy as np

def main():
    
    bicycleDataSoluation()
    #calculateAverageUsersHoliday(data, dayType)
    calculateUsersPerMonth()
    

def bicycleDataSoluation():
    data = np.genfromtxt('G:/bike.csv', delimiter=',')
    
    #Q1(i)
    allAverage = np.mean(data, axis=0)
    print('Average Temperature is :', allAverage[9]*41)#this to get absulatre cel
    
    #Q1(ii)
    
    print('Average number of non-holiday user{}'.format(calculateAverageUsersHoliday(data, 0)))
    
    print('Average number of non-holiday user{}'.format(calculateAverageUsersHoliday(data, 1)))
    
    #Q1(iii)
    calculateUsersPerMonth(data)
    
    #for temp in range(1,40,5):
       # analyseTemp(data, temp , temp+4)
        
def calculateAverageUsersHoliday(data, dayType):
    
    dayTypeSubset = data[data[:,5]==dayType]
    numberOfUsers = dayTypeSubset[:,15]
    return (np.mean(numberOfUsers))


def calculateUsersPerMonth(data):
    monthName = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
    
    for currentMonth in range(1,12):
        booleanRowsForMonth = (data[:,3] == currentMonth)
        
        dataForMonth = data[booleanRowsForMonth]
        
        print('Total users formonth {} is {}'.format(monthName[currentMonth-1],np.sum(dataForMonth[:,13])))
        
        
main()        
        
        
        
        