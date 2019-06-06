# -*- coding: utf-8 -*-
"""
Created on Thu Oct 18 16:37:05 2018

@author: Mohammed
"""

"""
Write a guessing game for a user. This program should initially generate a random number between 1 and 100.  
 
It should then repeatedly ask the user to guess the random number.  
 
Each time the user enters a guess the program should tell them that their guess was too high, too low or correct.  
 
When the user finally guesses the correct number the program should tell the user 
how many guesses they made before arriving at the correct number.  
 
Your program should make use of the following methods: 
• generateRandomNumber. This function will generate a random number between 0 100 and return the result. 
• askUser. This function will ask the user to enter a guess and will return the result 
• checkGuess. This function will take in the users guess and the random number as parameters and 
will return True if the user entered the correct value and False otherwise.  
 
"""

import random

guessTaken = 0

print('Hello! What is your name? :')
myName = input()

number = random.randint(1,101)

print('Well, '+ myName + ', I am thinking of a number between 1 and 100')

while guessTaken < 3:
    print('Take a guess. ')
    guess = input()
    guess = int(guess)
    
    guessTaken += 1
    
    if guess < number:
        print('Your guess is too low.')
    if guess > number:
        print('Your guess is too high.')
    if guess == number:
        break
    
if guess == number:
    guessTaken = str(guessTaken)    
    print('Good job, '+ myName + '! You guess my nunber in ' + guessTaken + 'guesses!')

if guess != number:
    number = str(number)
    print('Nope. The number I was thinking of was '+ number)
    