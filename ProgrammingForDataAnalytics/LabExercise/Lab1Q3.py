# -*- coding: utf-8 -*-
"""
Created on Sun Oct  7 20:34:47 2018

@author: Mohammed
"""

"""
Write a program that asks the user to enter a distance 
in kilometres and then converts that distance to miles 
(Miles = Kilometres * 0.6214).   
 
"""
 
kDistance = input('Enter the distance :')
kDistance = float(kDistance)
mile = kDistance * 0.6214
print(kDistance , ' Kilometers is equivalant to ', mile , ' miles.')