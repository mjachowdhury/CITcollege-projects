 /*
 Question 1: The age calculator
 This function will return person future age based on user input
 */

function getFutureYear(birthYear,futureYear){
  var result = futureYear - birthYear;
  return" I will be either "+ result +" age." ;
}
console.log(getFutureYear());



//Question 2:
/*
*This function will calculate the with age and amount per day consumed a
 person and how long It will last
*/
function calculateSuppply(personAge, amountPerDay){ 
	var personMaxAge = 101;
    var year = 365;
    var totalConsume = (year*amountPerDay) * (personMaxAge - personAge);
    var result = "You will need : "+ totalConsume + " amount  to last you until the ripe old age of " + maxAge;   
	return result;
  }
console.log(calculateSuppply(80,10));
console.log(calculateSuppply(90,15));
console.log(calculateSuppply(100,18));




//Question 3:
/*
This function will calculate the area and perimeter of the circle
*/
 
function calculateArea(radius){
  return Math.PI * radius * radius;
}
console.log(calculateArea(2));

/*
This function will calculate the perimeter of the circle
*/
function calculatePerimeter(radius){
  return 2 * Math.PI * radius;
}
console.log(calculatePerimeter(5));




//Question 4
/*
 This function will convert celsius to fahrenheit
 */
 function celsiusToFahrenheit(celsius){
   var celsiusTem = celsius;
   var result = celsiusTem * 9/5 +32;
   //var message = celsiusTem + '\xB0C is '+ result + '\xB0F.';
   return "Celsius to Fahrenheit is : " +result;
 }
 
 console.log(celsiusToFahrenheit(50));
 
 /*
 This function will convert fahrenheit to celsius
 */
 function fahrenheitToCelsius(fahrenheit){
   var fahrenheitTemp = fahrenheit;
   var result = (fahrenheitTemp - 32) * 5/9;
   //var message = fahrenheitTemp + '\xB0F is '+ result + '\xB0C.';
   return "Fahrenheit to celsius tempature is : " +result;
 }
 
 console.log(fahrenheitToCelsius(100));
 
 
 
 
 //Question 5
 /*
 Fortune teller
 This function will take four arguments and will return the user input result;
 */
 function fortuneTeller( jobTitle,geograpicLocation, partnersName, numberOfChildren){
   this.numberOfChildren = numberOfChildren;
   this.partnersName = partnersName;
   this.geograpicLocation = geograpicLocation;
   this.jobTitle = jobTitle;
   
   var result = "You will be "+jobTitle+ " in " + geograpicLocation+ " and married to " +partnersName+ " with "+numberOfChildren + "Children";
   return result;
 }
 console.log(fortuneTeller("happy","Jannah","Opi",4 ));
 
 
 
 
 //Question 6
  /*
 This function will return dog age based on 1 human year = 7 years of dog.
 */
 function calculateDogAge(puppyAge){
   this.puppyAge = puppyAge;
   var oneHumanYear = 7;//dog years
   result = puppyAge* oneHumanYear;
   return "Your dogie is " + result + " years old in dog years";
 }
 
 console.log(calculateDogAge(7));
 console.log(calculateDogAge(10));
 console.log(calculateDogAge(5));

 
 
 
 //Question 7:
 /*This function will find out given year is leap year or not.*/

function leapYear(year){
  var result = year;
 //if user given year is divided by 4 and 400 but not divided by 100 then its a leap year. else its not a leap year.
  if((year % 4 === 0) && (year % 100 !==0) || (year % 400 === 0)){
    alert(result + " is a leap year.");
  }
  else{
    alert(result + " is not a leap year.");
  }
}

console.log(leapYear(1996));




//Question 8:
/*This function will print triangle on console
 from user input*/
 
 function getTriangle(number){
   for(var i = 1; i <= number; i++){
     var result = "";
     for(var j = 1; j <= i; j++){
      result += "*";
     
     }
     console.log(result);
   }
   
 }
 
 console.log(getTriangle(7));
 
 
 
 
 //Question 9:
 /*This function will find out which number is divided by 3 or 5
and will print out Fizz if its divided by 3 and Buzz if its divided by 5*/

 
function fizzBuzz(){
 for (var i = 1; i <= 100; i++) {
  var f = i % 3 === 0, b = i % 5 === 0;
  console.log(f ? b ? "FizzBuzz" : "Fizz" : b ? "Buzz" : i);
}
}

console.log(fizzBuzz());


//Question 10
/*This function will check any string whether it is palindrome or not.*/

function isPalindrome (string) {
    var reverseString = '';
    for (var i=string.length; i > 0; i--) {
        reverseString += string[i-1] ;
    }
    return string == reverseString;
}


console.log(isPalindrome('good'));
console.log(isPalindrome('mum'));
console.log(isPalindrome('hello'));




//Question 11
/*This function will print chess board on the console from the user input throgh passing parameter*/

 function chessBoard(num){
  var board ="";
  var evenRow = "|_#_|_#_|_#_|_#_| ";
  var oddRow = "_|_#_|_#_|_#_|_#_|";
  for(var i =0 ; i < num; i++){
    if(i % 2 === 0) board += evenRow +'\n';//if number is divided by 2 it will print evenRow
    else board += oddRow +'\n';//else it will print oddRow
    }
    console.log(board);
  }

console.log(chessBoard(8));
'\n';
console.log(chessBoard(4));









