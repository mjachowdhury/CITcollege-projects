/**
	This file contains the solution to the assignment problems for chapter 1
**/
/* Write a javascript function named is_integer which checks if the 
passed argument is an integer. You can use any mathematical 
operator or functions defined in the Math object. */
/** Problem 1 **/
function is_integer(n) {
  return typeof(n) == "number" && Math.floor(n) == n;
}

/** Problem 2 **/
/* Using the forEach function defined for an array, 
find the sum of the array of numbers. [function add_all(arr) {...}] */
function add_all(arr) {
  var sum = 0;
  arr.forEach(function(e) {sum += e;});
  return sum;
}

/** Problem 3 **/
/* Write a JavaScript program to convert temperatures to and from celsius,
 fahrenheit. [ Use the formula : c/5 = (f-32)/9, 
where c = temperature in celsius and f = temperature in fahrenheit] */
function temp_converter(from, reading) {
  if (from === "celsius") {
	return (reading * 9/5) + 32 + " degree F";
  } else {
	return (reading -32) * 5 / 9 + " degree celsius";
  }
}

/** Problem 4 - Using iteration **/
/* Write a factorial function that returns the factorial of a given number, 
n. Make sure you return the calculated 
value and not just print it. [function factorial(n){...}] */
function factorial(n) {
  var fact = 1;
  for (var i=1; i <= n; i++) {
	fact = fact * i;
  }
  return fact;
}

/** Problem 4 - Using recursion, in case you know **/
function factorial(n) {
  if (n === 0) {
    return 1;
  }
  return n * factorial(n-1);
}

/** Problem 5 **/
/* Write a javascript function that converts a given amount of 
money into coins of denominations (1, 2, 5, 10 and 25). 
[function convert_to_coins(amount) {...}]. 
You may choose to print the coin denominations used on the console.
 E.g. convert_to_coins(87) should print 25 25 25 10 2. */
function convert_to_coins(amount) {
  var denominations = [25, 10, 5, 2, 1];
  var curr_denom_index = 0;
  while (amount > 0) {
    while (amount < denominations[curr_denom_index]) {
      curr_denom_index++;
    }
    amount = amount - denominations[curr_denom_index];
    console.log(denominations[curr_denom_index]);
  }
}