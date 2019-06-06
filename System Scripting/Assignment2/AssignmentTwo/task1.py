'''
Task1:
Write three Python functions that accept a list as parameter. The first function 
should return the largest value in the list. The second function should return the smallest value in the list. 
The third function should replicate the list by 3, output the new length of the list and also output,
using a loop, the odd numbers in the list.In the main program, interactively create a list of 12numbers.
Present a user with a set of menu to select from. If a user enters the character ‘B’
or ‘b’, the program should output the largest number in the list. When a user enters the character ‘S’or ‘s’,
the program should output the smallest number in the list. When a user enters any number, the program should 
perform the replication, outputting of length and all odd numbers in the list. 
When a user enters ‘E’ or ‘e’, the program should terminate with a goodbye message.
''' 

'''
                   ##########   REPORT ###################

For this task fist I wrote three functions one for find out biggest number and second one to find out smallest number
and third function is about to replicate the list 3 times and after that I have taken out the odd number from the list.

I have created empty list which name is -- new_list [] -- then Interactively user will enter 12 numbers through for
loop i used to enter 12 numbers inside the list. Then I created interactive user menu with while loop.
'''

#This function will find out the biggest number from the list.

def findBiggestNumber(maxNum):
    num = maxNum[0]
    for i in maxNum:
        if num < i:
            num=i
    return num

 

#This function will find out the smallest number from the list.

def findSmallestNumber(maxNum):
    num = maxNum[0]
    for i in maxNum:
        if num > i:
            num=i
    return num


#This function will replicate the list 3 times and will out put the length of the new list.
#this function will also find out the odd number from the new list.

def listReplicate(num):
    l = num[:]
    ans=(l * 3)
    print('\nReplicating the list :\n' + str(ans))
    print("\nLength of the new List is : " + str(len(ans)))
    
    new_list = []
    
    for i in num :
        if i % 2 != 0 :
           new_list.append(i)
    return '\nOdd number from the new list: ' +str(new_list)

    

listNum = [] # this is empty list
print('Please enter 12 numbers for the list.')

for i in range(0,12): # with this for loop user will enter 12 numbers
    number = int(input('Numbers '+str(i+1)+' is : '))
    listNum.append(number)
    
print('\nYou have entered those numbers ' + str(listNum))   # here it will show the user what numbers they entered.


ans=True
while ans:
    print(""" \n  ****USER MENU****\n
    1.To find out biggest number choose B | b.
    2.To find out smallest number choose S | s. 
    3.To replicate the list press any number.
    4.TO Exit/Quit press E | e.
    """)
    ans=input("\nWhat would you like to do? ")
    
    if ans=="B" or ans=="b":
       print('\nBiggest Number is : '+str(findBiggestNumber(listNum))) # this will find out biggest number
       
    elif ans=="S" or ans=="s":
       print('\nSmallest Number is : '+str(findSmallestNumber(listNum)))# this will find out smallest number
       
    elif ans.isdecimal():#this will take care any number user can input to get the result
        print(listReplicate(listNum))
        
    elif ans=="E" or ans=="e":
      print("\n Goodbye") 
      ans = None
      
    else:
       print("\n Not Valid Choice Try again")



 
