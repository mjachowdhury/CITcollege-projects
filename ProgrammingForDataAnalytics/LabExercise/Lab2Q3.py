# -*- coding: utf-8 -*-
"""
Created on Tue Sep 25 10:21:28 2018

@author: Mohammed
"""
"""
A software company sells a package that retails for €99. Quantity discounts are given
according to the following table.
Quantity Discount
1 – 9 0%
10 – 19 20%
20 – 49 30%
50 – 99 40%
100 or more 50%
Write a program that will ask the user to enter the number of packages purchased. The
program should display the amount of the discount (if any) and the total amount of the
purchase after the discount. Please note that if a user indicates a negative quantity then they
should be told that the quantity value should be greater than zero.
Please use if elif statements to solve this problem and note that your solution to this problem
should make use of the and logical operator where possible. 
"""
packagePrice = 99

numberOfPackage = int(input('How many package do you want to buy? :'))
if (numberOfPackage >=1 and numberOfPackage <=9):
    print('Total Price without discount: ', numberOfPackage * packagePrice)
elif (numberOfPackage >=10 and numberOfPackage <= 19):
    totalPrice = numberOfPackage * packagePrice
    afterTwentyPercentDiscount = (totalPrice * 20) / 100
    print('You received Discount 20 % :', afterTwentyPercentDiscount)    
    print('After Twenty % discount total price is :' , totalPrice - afterTwentyPercentDiscount)
    
   
    

