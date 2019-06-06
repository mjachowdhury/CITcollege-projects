# -*- coding: utf-8 -*-
"""
Created on Tue Oct 16 20:36:34 2018

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


    
def generateRandomNumber():
    randomNumber = random.randint(1,101)
    return randomNumber

   # return random.randint(1,101)
     

def askUser():
    userName = input('Hello there...What is your name? : ')
    print('Well, '+ userName + ', I am guessing of a number between 1 to 100')
    
 
"""
def userGuessNumber():    
    guess = int(input('Take a guess: '))
    #print('Take a guess: ')
    #guess = input()
    #guess = int(guess)
    return guess
"""   
   

def checkGuess(randomNum):
   
    randomNum = generateRandomNumber()
    
    userGuessTaken = 0
    
    while userGuessTaken < 4:
        userGuess = int(input('Take a guess : '))
        userGuessTaken = userGuessTaken + 1
        
        
        if userGuess < randomNum:
            print('Your guess is too low')
         
        if userGuess > randomNum:
            print('Your guess is too high')
            
        if userGuess == randomNum:
            break
             
    if userGuess == randomNum:
        userGuessNumber = str('userGuessNumber')
        print('Good job. You guessed my number in '+ userGuessNumber +  ',guesses!')
       
    if userGuess != randomNum:
        randomNum = str(randomNum)
        print('Nope, The number I was thinking of was '+ randomNum)
 
      

def main():
    askUser()
    myRandom = generateRandomNumber()
    checkGuess(myRandom)       
       

main()
    