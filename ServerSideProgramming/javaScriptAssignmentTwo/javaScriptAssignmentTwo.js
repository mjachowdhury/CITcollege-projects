//Question number 1

/*This function will return square number of whatever user will put.
*/

function squareNumber(number) {
  var result = number;
  result *= number;
  console.log('The result of squaring the ' + number + ' is : ' + result);
}

console.log(squareNumber(10));


/*This function will return half of the number whatever user will put.*/

function halfNumber(number) {
  var halfNumber = number / 2;
  console.log('Half of ' + number + ' is ' + halfNumber);
  return halfNumber;
}
console.log(halfNumber(40));


/*This function will return percentOf second number whatever user will put*/

function percentOf(numberOne, numberTwo) {
  var result = numberOne / numberTwo * 100;
  console.log(
    'First number is ' +
      numberOne +
      ' is percentage of ' +
      result +
      ' of ' +
      numberTwo
  );
  return result;
}
console.log(percentOf(3, 10));


/*This function will return the area of a circel whatever user will put and it will give two digit after decimal.*/

function areaOfCircle(radius) {
  var result = Math.PI * radius * radius;
  console.log(
    'The area for a circel with radius ' + radius + ' is ' + result.toFixed(2)
  );
  return result.toFixed(2);
}

console.log(areaOfCircle(2));


/*This function will do multiple opertion*/

function halfNumber(number) {
  var halfNumber = number / 2;
  console.log('Half of ' + number + ' is ' + halfNumber);
  return halfNumber;
}

function squareNumber(number) {
  var result = number;
  result *= number;
  console.log('The result of squaring the ' + number + ' is : ' + result);
}

function areaOfCircle(radius) {
  var result = Math.PI * radius * radius;
  console.log(
    'The area for a circel with radius ' + radius + ' is ' + result.toFixed(2)
  );
  return result.toFixed(2);
}

function percentOf(numberOne, numberTwo) {
  var result = numberOne / numberTwo * 100;
  console.log(
    'First number is ' +
      numberOne +
      ' is percentage of ' +
      result +
      ' of ' +
      numberTwo
  );
  return result;
}

function doingAllInOneFunction(number) {
  //HalfNumber
  var half = halfNumber(number);

  //Square number
  var square = squareNumber(half);

  //Area of circel
  var area = areaOfCircle(square);
  var percent = percentOf(square, area);
}

doingAllInOneFunction(3);



//Question 2

/*This function will print different words dependes on what ever amount user will put */

function DrEvil(amount) {
  //var result = amount;
  if (amount < 1000000) {
    return amount + ' dollars';
  } else {
    return amount + ' dollars (pinky)';
  }
}
console.log(DrEvil(10000));
console.log(DrEvil(1000000));




//Question 3

/* This function will slice two string and join with new string*/

function MixUp(stringOne, stringTwo) {
  var result =
    stringTwo.slice(0, 3) +
    stringOne.slice(1) +
    ' ' +
    stringOne.slice(0, 3) +
    stringTwo.slice(1);
  return result;
}

console.log(MixUp('Hello', 'you'));



//Question 4
/*This function will replace the charAt(1) to '*'*/

function fixStart(s) {
  var str = s.charAt(0);
  var newstr = str + str.replace(str, '*');
  return newstr;
}
console.log(fixStart('Gkjhkjood'));



//Question 5

/*This function will add "ing" if string lenght at least 3. 
if its less then 3 it will return as it is. 
if its contains "ing" then it will add "ly"  */

function verbing(s) {
  var result = s;
  if (s.length < 3) {
    return result;
  }
  if (s.slice(-3) == 'ing') {
    return result + 'ly';
  } else {
    return result + 'ing';
  }
}

console.log(verbing('boodfbing'));


//Question 6

/* This function will do the following task
 Create a function called notBad that takes a single argument, a string.
It should find the first appearance of the substring 'not' and 'bad'.
If the 'bad' follows the 'not', then it should replace the whole 'not'...'bad' substring with 'good' and return the result.
If it doesn't find 'not' and 'bad' in the right sequence (or at all), just return the original sentence.*/

function notBad(string) {
  var not = string.indexOf('not');
  var bad = string.indexOf('bad');
  if (not == -1 || bad == -1 || bad < not) return string;
  return string.slice(0, not) + 'good' + string.slice(bad + 3);
}

console.log(notBad('This is not that bad after all .'));

console.log(notBad('Todays weather is very good.'));


//Question 7

/*This function will do the following task
Create an array to hold your top choices (colors, presidents, whatever).  
 For each choice, log to the screen a string like: "My #1 choice is blue."  
 Bonus: Change it to log "My 1st choice, "My 2nd choice", "My 3rd choice", 
picking the right suffix for the number based on what it is
*/
function choicePicker() {
  var choiceColor = ['Red', 'Yellow', 'Green', 'Black'];

  for (var i = 0; i < choiceColor.length; i++) {
    if (i == 0) {
      console.log('My - ' + (i + 1) + 'st choice is ' + choiceColor[i] + '.');
    } else if (i == 1) {
      console.log('My - ' + (i + 1) + 'nd choice is ' + choiceColor[i] + '.');
    } else if (i == 2) {
      console.log('My - ' + (i + 1) + 'rd choice is ' + choiceColor[i] + '.');
    } else {
      console.log('My - ' + (i + 1) + 'th choice is ' + choiceColor[i] + '.');
    }
  }
}

console.log(choicePicker());



//Question 8
/*
 This function will do the gusing game
 */
var wordLetters = ['G', 'O', 'A', 'T'];
var guessedLetters = ['_', '_', '_', '_'];

function guessLetter(letter) {
  var goodGuess = false;
  var moreToGuess = false;
  for (var i = 0; i < wordLetters.length; i++) {
    if (wordLetters[i] == letter) {
      guessedLetters[i] = letter;
      goodGuess = true;
    }
    if (guessedLetters[i] == '_') {
      moreToGuess = true;
    }
  }
  if (goodGuess) {
    console.log('Yay, you found a letter!');
    console.log(guessedLetters.join(''));
    if (!moreToGuess) {
      console.log('YOU WON!');
    }
  } else {
    console.log('Oh noes, thats not right!');
  }
}

guessLetter('G');
guessLetter('I');
guessLetter('O');
guessLetter('A');
guessLetter('T');



//Qustion 9

/*This function will print receipt details */

function fReceip() {
  var favoriteRecipe = {
    Title: 'Chicken Birani',
    Serving: 5,
    Ingredients: ['chicken', 'rice', 'spices', 'oil', 'butter etc.'],
  };

  console.log(favoriteRecipe.Title);
  console.log('Serving for ' + favoriteRecipe.Serving + ' persons.');
  console.log('Ingredients are :');
  for (var i = 0; i < favoriteRecipe.Ingredients.length; i++) {
    console.log(favoriteRecipe.Ingredients[i]);
  }
}
console.log(fReceip());



//Question 10

/* This function will hold array of Object and from their it will 
show wheather usre finished reading the book or not*/

function readingList() {
  var bookDetails = [
    {
      Title: 'Java Programming',
      Author: 'Mohammed Alom',
      finishedRead: 'false',
    },
    {
      Title: 'C++ Programming',
      Author: 'Opi Mozumder',
      finishedRead: 'true',
    },
  ];

  for (var i = 0; i < bookDetails.length; i++) {
    var result = bookDetails[i];
    var message = result.Title + ' by ' + result.Author;
    if (result.finishedRead) {
      console.log('You have finished read that : ' + message);
    } else {
      console.log('You have to finish to read ' + message);
    }
  }
}
console.log(readingList());


//Question 11

/*This function will display film details.*/

var film = {
  Title: 'Beautiful Day',
  Duration: 2,
  stars: ['Birds', 'Butterfly', 'Flowers'],
};

function filmDetails() {
  console.log('Film ' + film.Title + ' is lasts for ');
  console.log(film.Duration + 'hours. And stars ware ');
  for (var i = 0; i < film.stars.length; i++) {
    console.log(film.stars[i]);
  }
}

console.log(filmDetails());


//Question 12

/*
 This functionwill return the total price of the shoppingCart items.
 */
 
var shoppingCart = {
  'Ice Cream': '3.5',
  Banana: '2.5',
  Sweets: '5.5',
  Milk: '1.25',
};

function cashRegister(shoppingCart) {
  var items = Object.keys(shoppingCart); //arrays of items by key value pairs are storing to variable items
  var total = 0;
  for (var i = 0; i < items.length; i++) {
    var itemName = items[i]; //all the items are storing on variable itemName.
    var price = shoppingCart[itemName]; //getting all the item price and storing on variable price
    total += Number.parseFloat(price);
  }
  return total;
}

console.log(cashRegister(shoppingCart));


//Question 13 -- Credit Card Validation

/*
 This function will return user input credit card vaild or not.
 */
 
function validateCardNumber(number) {
  var regex = new RegExp('^[0-9]{16}$');
  if (!regex.test(number)) return false;

  return luhnCheck(number);
}

function luhnCheck(val) {
  var sum = 0;
  for (var i = 0; i < val.length; i++) {
    var intVal = parseInt(val.substr(i, 1));
    if (i % 2 == 0) {
      intVal *= 2;
      if (intVal > 9) {
        intVal = 1 + intVal % 10;
      }
    }
    sum += intVal;
  }
  return sum % 10 == 0;
}

console.log(validateCardNumber(456812546325785));
console.log(validateCardNumber(4568125463257858));


//Question 14

/*This function will do the recursion
 We’ve seen that % (the remainder operator) can be used to test whether a number
 is even or odd by using % 2 to check whether it’s divisible by two. Here’s another 
 way to define whether a positive whole number is even or odd:  
• Zero is even. 
• One is odd.  
• For any other number N, its evenness is the same as N - 2. Define a recursive
 function isEven corresponding to this description. The function should accept a
 number parameter and return a Boolean. Test it on 50 and 75. See how it behaves 
 on -1. Why? Can you think of a way to fix this? */

function isEven(number) {
  if (number < 0) {
    number = number * -1;
  }

  if (number % 2 === 0) {
    return true;
  } else {
    return false;
  }
}

console.log('------------Recursion------------');
console.log(isEven(50));
console.log(isEven(75));
console.log(isEven(-1));


//Question 15

/*This function will do the counting B's on the give nuser input and also will count how many same char in a given string  
Bean counting
Write a function countBs that takes a string as its only argument and returns a number that indicates
how many uppercase “B” characters are in the string.
Next, write a function called countChar that behaves like countBs, except it takes a second argument that indicates
the character that is to be counted (rather than counting only uppercase “B” characters). Rewrite countBs to make use of this new function.*/

function countBs(string) {
  var count = 0;
  for (var i = 0; i < string.length; i++) {
    if (string[i] === 'B') {
      count += 1;
    }
  }
  return "Here B's are : " + count;
}

function countChar(string, char) {
  var count = 0;
  for (var i = 0; i < string.length; i++) {
    if (string[i] === char) {
      count += 1;
    }
  }
  return 'Here same char are :' + count;
}

console.log('-----------Bean counting---------');
console.log(countBs('BBC'));
console.log(countChar('caccerla', 'c'));


//Question 16

/*This function will handlethe exceptions while multipling two numbers
Say you have a function primitiveMultiply that, in 50 percent of cases,
 multiplies two numbers, and in the other 50 percent, raises an exception of
 type MultiplicatorUnitFailure. Write a function that wraps this clunky function
 and just keeps trying until a call succeeds, after which it returns the result.
 Make sure you handle only the exceptions you are trying to handle. */

function primitiveMultiply(p1, p2) {
  var fail = Math.floor(Math.random() * 2);
  if (fail) {
    throw new Error('MultiplicatorUnitFailure');
  } else {
    return p1 * p2;
  }
}

function attemptMultiply(a, b) {
  var gotResult = false;
  while (gotResult === false) {
    try {
      var result = primitiveMultiply(a, b);
      console.log('Got a successful return, value =  ' + result);
      captureOutput.push('Got a successful return, value =  ' + result);
      gotResult = true;
    } catch (error) {
      console.log('This is the error.message value: ' + error.message);
      captureOutput.push('This is the error.message value: ' + error.message);
      if (error.message !== 'MultiplicatorUnitFailure') {
        throw error;
      }
    }
  }
}


//Question 17

/*
Write a function called withBoxUnlocked that takes a function value as 
argument, unlocks the box, runs the function, and then ensures that the
 box is locked again before returning, regardless of whether the argument
 function returned normally or threw an exception
 */
// The locked box
//
// This is the "box" object

var box = {
  locked: true,
  unlock: function() {
    this.locked = false;
  },
  lock: function() {
    this.locked = true;
  },
  _content: [],
  get content() {
    if (this.locked) throw new Error('Locked!');
    return this._content;
  },
};

// this is the main function - unlock the box, run the function
// passed in as the argument to the function.

function withBoxUnlocked(body) {
  box.unlock();
  try {
    return body();
  } catch (e) {
    console.log('Error thrown: ', e);
  } finally {
    box.lock();
  }
}
// add something to the box.
withBoxUnlocked(function() {
  box.content.push('gold piece');
});

try {
  withBoxUnlocked(function() {
    throw new Error('Pirates on the horizon! Abort!');
  });
} catch (e) {
  console.log('Error raised: ', e);
}
console.log(box.locked);
// this is a test function
withBoxUnlocked(function() {
  console.log("This is what's in the box: " + box.content);
});




