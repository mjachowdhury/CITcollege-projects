#Task 2
#Write a bash script to list, one at a time, all regular files larger than 2Kbyte in a target directory
#tree. Give the user the option to copy or delete the file, then proceed to show the next one.
#Write to a logfile the names of all deleted files and the deletion times. Properly comment your
#code for easy understanding. For testing, create a folder in your working directory with multiple
#files of different sizes/subfolders. Properly comment your code.

						#################### REPORT ##########################

#This script is about to find out all the files which are larger then 2 Kbytes. After finding the files in the target directory specified by the programer it will show the user all the files. An from there user have to choose a file. Then it will give the user options what to do with that file. Either ZIP/REMOVE/COPY or to get back to the previous menu. If user choose ZIP it will zip and it will do in the same directory set by the programer. If user select no 2 then it will remove the file and if user select no 3 then it will copy the file and put them in the same directory but different folder. And all the activities will be logged in the log file with date time. log file can be found in hte tmp folder with the name task2_mohammed.log  

#!/bin/bash
 
# Script Name: task2.sh

#clearing the screen at first 
clear 

SCRIPT=${0##*/}

#getting the user 
RUNUSER=$(whoami) 

#fixing the size of the fiel 2000kbyte
SIZE=2000  

#TARGETDIR=~/SystemScripting/Assignment1

#Here I am getting the target directory 
TARGETDIR=/home/mohammed/SystemScripting/Assignment1

#This command will get every single file from the system
#TARGETDIR=/home/${RUNUSER}

#Creating the log file 
LOG=/tmp/${SCRIPT%%.sh}_${RUNUSER}.log

#Finding the files greater than 2 kbytes in the target directory
FILES=($(find ${TARGETDIR} -type f -size +${SIZE}c))
 
############ MAIN #############

#Will get 2 kbytes file in the target directory
echo "Showing all files larger than ${SIZE} bytes in ${TARGETDIR} sorted by size."

 
echo -e "\n\n____File menu____\n"

#Here files will be listing with options to choose what to do with the files by the user

while [[ ${#FILES[@]} -gt 0 ]]; 

do
       		 PS3="Please choose a file:"
          
       		 select file in ${FILES[@]}
                do
                        [[ -z ${file} ]] && {
                                echo "Invalid choice!"
                        } || {
                break
                        }
       			done    
     
 
PS3="Select an option:"

echo -e "\nYou have selected the below file."
echo ""

# Will show user which file s/he selected
ls -ltr ${file} 

echo "" #Creating space 

echo -e "Do you want to REMOVE or ZIP or COPY the file?\n"

#Will give options to user what to do with the file either to do zip or copy or to remove

select option in ZIP REMOVE COPY BACK
do
        [[ -z ${option} ]] && {
                echo "Invalid choice!"
        } || {
                break
        }
done

#Here if user select 1 | 2 | 3 | 4 . 1 will do zip and 2. will do remove and 3. will do copy and 4. will return previous menu and also will create log file in the tmp folder withname and time and date as well file name is - task2_mohammed.log and task will be finish in the directory /home/mohammed/SystemScripting/Assignment1

case ${option} in

        "ZIP" )
        echo "You chose ZIP. Zipping file now..."
        gzip ${file}
        echo "$(date "+%F %T") ${file} compressed." | tee -a ${LOG}
                ;;
                
        "REMOVE" )
        echo "You chose REMOVE. Removing file now..."
        rm ${file}
        echo "$(date "+%F %T") ${file} removed." | tee -a ${LOG}
                ;;
         
         
        "COPY" )
        echo "You chose COPY. Copying file now...and it will be save in the same directrory with folder name is - copyFileDes"
        cp ${file} /home/mohammed/SystemScripting/Assignment1/copyFileDes 
        echo "$(date "+%F %T") ${file} copied." | tee -a ${LOG}
                ;;
                
        "BACK" )
        continue
                ;;
esac
 
FILES=($(find ${TARGETDIR} -type f -size +${SIZE}c))
 
done

echo
