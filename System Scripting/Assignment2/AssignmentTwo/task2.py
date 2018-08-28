'''Task 2
Write a function named reducer()that accepts an integer parameter named number. If 
number is even then reducer()should divide number by 2 and return this value. If number
is odd, then reducer should return 3 times number+ 1.
Then write a program that requires a user to enter an integer number and that keeps calling 
reducer() on that number until the function returns the value 1. (Amazingly, this sequence 
works for any integer value. Sooner or later you will arrive at value 1). Example output 
sequence for entering the number 3 is:
10
5
16
8
4
2
1
'''
 
'''
         ##### REPORT########
         
Here I have created the function clled reducer which will calculate the task 2
if nubmer is even then it will retun the value until it reach to the number 1
and if the nubmer is odd then this function will calcualte the nuber 3 times  + 1
and atthe end it will reduce to the number 1.

'''
#Function start here

def reducer(number):
    if number % 2 == 0: # wil ldivide the even nubmer
        print(number // 2)
        return number // 2
    
    elif number % 2 == 1 : # wil ldivide the odd number
        result = (3 * number) +1
        print(result)
        return result

# function ends here


print('Enter a number please :')
num = int(input())

while num !=1:
    num = reducer(int(num)) # calling the function
    
#print(num)
