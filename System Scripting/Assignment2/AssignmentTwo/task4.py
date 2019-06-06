'''
Task4
Write a Python function that accepts three parameters. The first parameter should be a sentence, 
the second parameter should be a word and the third parameter is another word. The function 
should search the string for the first word and replace it with the second word. 
Then write a Python script that demands a user to input a sentence, a word that occurred in the 
sentence and a replacement word for it. Call your function with these input values. Output the 
original sentence and the resulting changed sentence.Never use the replace() string method for this task!!
Sample Inputs:
Sentence: “How are you doing?”
Word1: “you”
Word2: “we”
Output sample:
Original sentence: How are you doing?
Changed sentence: How are we doing?
'''

'''
         **** REPORT ****
For this task I have created the function will called replace_string will take 3 parameters.
first parameter i split it and called split() method and stored the result inside the split_target.

then with for loop i have search the word which is second parameters. if its find it then it will
replace and will add to the result variable. after that it will return the result for this i used strip() method
'''

#Function start here

def replace_string(target, find, replace):
    split_target = target.split()
    result = ' '
    
    for i in split_target:
        if i == find: # if second parameter is equal with split_ target then it will replace
            i = replace
        result += i + ' ' # adding to the result
    return result.strip()

# function ends here

print('\nEnter one  Sentence: ')
target = input()

print('\nEnter a word: ')
find = input()

print('\nEnter a second word: ')
replace = input()

print('\nOriginal sentence\n') # Original sentence will print
print(target)


print('\nAfter changing the sentence.\n')
print(replace_string(target, find, replace)) # calling the function


 
