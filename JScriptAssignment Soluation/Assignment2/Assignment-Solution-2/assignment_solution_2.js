/**
	This file contains the solution to the assignment problems for chapter 2
**/

/** Problem 1 
Write a javascript function named reverse which takes a string argument 
and returns the reversed string.
**/
function str_reverse(str) {
  return str.split('').reverse().join('');
}

/** Problem 2 
Given a javascript array of objects having a radius property as shown 
[{radius: 5}, {radius: 9}, {radius: 2}], write a comparator function to 
sort it.
**/
function circle_comparator(c1, c2) {
  return c1.radius - c2.radius;  
}
// then we can use this in the sort function


/** Problem 3 
Write a javascript function named length_of_array, which takes an array 
as argument and returns the number of elements in that array (as opposed 
to what is given by the length property of the array). Remember undefined
 can also be an element if it is explicitly set at some index,
 e.g. x[5] = undefined;. This undefined should be counted, but 
 elements which are not present in the array (as arrays can be sparse), 
 should not be counted.
**/
function length_of_array(arr) {

  var counter = 0;
  
  arr.forEach(function() {counter++;});
  
  return counter;
}
