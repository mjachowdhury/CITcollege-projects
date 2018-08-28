'''
Task 3
Write a Python function that accepts two parameters. The first parameter should bea 
list and the second parameter should be a number. Use a random generator to generate indexes 
in the range of 2 to 5 based on the second parameter and then randomly remove from the list
the items that corresponds to those indexes.
The function should return the remaining items as a tuple.Interactively create a list of words
or numbers that has at least 12 items and request a user to enter a number between 2 and 9. 
Invoke your function with the appropriate parameters. Output per line, the returned tuple and 
the original lists.
Sample program output
when 4 items were randomly deleted:
Output tuple
: (4, 5, 3, 8, 9, 5, 3, 5, 9)
Original List: 
[4, 5, 6, 3, 2, 8, 9, 3, 5, 4, 3, 5, 9]

'''

'''

      ***** REPORT *******
      First i have created empty list then interactively user will enter either
      number or words inside the list 12 items through for loop.

      later user will enter the number betweenb 2 to 9 to generate the random
      number.

      then i created the function called -- remove_valuesByIndex. then after deleting
      the items by index then converting the list items to tuple.
'''

import random

my_list = [] #empty list
print('Please enter 12 numbers or words for the list.')

for i in range(0,12): # with this loop user will enter list elements
    number = str(input('List '+str(i+1)+' is : ')) 
    my_list.append(number)# adding to the list
    
print('\nYou have entered those elements ' + str(my_list))   # will show the list items 
 
val = int(input('\nPlease enter the number between 2 to 9 to generate the random number : '))#user will put number 2 - 9


# function start here

def remove_valuesByIndex(the_list, val):
    for i in range(val):
        index = random.randint(2,5) # random number will generate
        print('\nRandom Number '+str(i+1)+' is : ' + str(index)) # will show the random nunber     
        del the_list[index] # deleting the list items by index
    
    print('\nAfter deleting the list elements by index the new list is :\n' +str(my_list)) # will show the new list after deleting
    
    print('\nList element are converting to tuple.\n')
    print(tuple(my_list))# converting the list items to tuple
     
# function ends here
 
print(remove_valuesByIndex(my_list, val)) # calling the function


 
