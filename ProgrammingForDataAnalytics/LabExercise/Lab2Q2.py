# -*- coding: utf-8 -*-
"""
Created on Tue Sep 25 09:45:58 2018

@author: Mohammed
"""
"""
A development company will only employ an individual if they have a minimum of 4
years of commercial software development experience, a Microsoft certification and a first
class honours undergraduate computing degree.
(i) Write a program that will use nested if statements to determine if a user is
sufficiently qualified to be employed. The first if statement should check if the
user has 4 years of commercial software development experience, the second
nested if statement should check if they have a Microsoft certification and the
final nested if statement should check if the user has a first class honours
undergraduate computing degree.
If the user does not satisfy one of the conditions the program should inform the
user that they not eligible and should also inform the user of the requirement they
have failed. The program should then exit.

"""

softwareExperience = int(input('How many years Software Development experience do youhave?:'))
mSoftExperience = input('DO you hold Microsoft Certification?(y/n) :')
honoursDegree = input('Do you hold 1st Class Honours degree?(y/n) :') 

if (softwareExperience == 4 and mSoftExperience == 'y' and honoursDegree == 'y'):
    print('Yes! You can join with our company.')
    
if (softwareExperience != 4 and mSoftExperience == 'n' and honoursDegree == 'n'):
    print('\nSorry! You don not have qualification to join with our company.')
    print('You need to hold 4 years software Experience.')
    print('You also need Microsoft Certification & 1st Class Honours Degree')
    
