	 //Author: Mohammed Alom 
	//this function for sliding the image

	var image1 = new Image()
	image1.src = "images/header1.jpg";

	var image2 = new Image()
	image2.src = "images/header2.jpg";

	var image3 = new Image()
	image3.src = "images/header3.png";

	var image4 = new Image()
	image4.src = "images/header4.jpg";

	var step = 1;

	function slideit()
	{
		document.images.myPhoto.src=eval("image"+step+".src");
		if(step<4){
		step++;}
		else
		{step=1;}
		setTimeout("slideit()",2500);
	}


	/*
	var myImage = document.getElementById("myPhoto");

	var imageArray=["images/header1.jpeg","images/header2.jpeg","images/header3.jpeg","images/header4.jpeg","images/header5.jpeg"];

	var imageIndex=0;
	function changeImage()
	{
		myPhoto.setAttribute("src",imageArray [imageIndex]);
		imageIndex++;
		if(imageIndex>=imageArray.length)
		{
			imageIndex=0;
		}
		
	}

	var intervarHandle = setInterval(changeImage,2000);

	myPhoto.onclick=function()
	{
		clearInterval(intervarHandle);
	}

	*/

	//This function will checks which options are checked and adds the amount to sum to get the total amount
	//if deselect from the check box then price will automatically will update in the form



	function sumTotal() 
	{
	   // var sum is variable & will store all the prie value 
	  var sum = 0;
	  if (document.getElementById('Small').checked ) 
	  {
		sum = sum + 9.99;
		
	  }
	  if (document.getElementById('Medium').checked) 
	  {
		sum = sum + 12.99;
	  }
	  if (document.getElementById('Large').checked) 
	  {
		sum = sum + 14.99;
	  }
	  if (document.getElementById('ExtraLarge').checked) 
	  {
		sum = sum + 15.99;
	  }
	  if (document.getElementById('Onion').checked)
	  {
		sum = sum + 0.50;
	  }
	  if (document.getElementById('Olive').checked) 
	  {
		sum = sum + 0.50;
	  }
	  if (document.getElementById('Chicken').checked) 
	  {
		sum = sum + 1.00;
	  }
	  if (document.getElementById('Papperoni').checked) 
	  {
		sum = sum + 1.00;
	  }
	  if (document.getElementById('Pappers').checked) 
	  {
		sum = sum + 0.50;
	  }
	  //discount part
	 
	  if (document.getElementById('Email').checked) 
	  {
		var discount=0.05;
		sum = sum -(sum*discount);
	  }

	  var total = document.getElementById("totalPrice"); //var total is variable & will work with id 'totalPrice'

	  total.style.display = 'block'; // will display total price as a block
	  total.innerHTML = "Total Price For The Pizza  &euro;  " + sum.toFixed(2); // € is for euro sign and toFixed(2) is for total price will show after decemil value untill 2 number
	  var price = document.getElementById('tprice').value = sum.toFixed(2); //this part for the after submit to see in the form total price.
	}

/*
	function validateEmail() {
		 
	 var input = document.createElement('input');
	  input.type='email';
	  //input.type= /^[A-Z0-9._%+-]+@(mycit\.ie) | (cit\.ie)/;
	  input.value=document.getElementById('email').value;
		
	  if (input.checkValidity()) {
		 
		document.getElementById('a'); alert('passed');
	  } else {
		alert('failed');
		//document.getElementById('a'); alert('failed');
	  }
		
	  return false;
	}
*/
	 

	// This function for submit the form and also will give alert if nothing select
	function submitForm() 
	{	
		if (document.getElementById('tprice').value == '')
		{
			alert('Please Select Product Before Order');
			return false;
		}
		return true;
	}


	//This function for during onload the page total price for the pizza will not show before select anything  
	function hideTotal() 
	{
	  var total = document.getElementById('totalPrice');
	  total.style.display = 'none';
	}


	 
	//this function for selecting pizza
	  function pizzaSizeChange(id) {
	   var el = document.getElementById(id);

	   if (el.style.visibility=="visible") {
			  el.style.visibility="hidden";
		 }
		 else {
			  el.style.visibility="visible";
		 }
	 }
	//this function for selecting topping
	 function pizzaToppingChange(id) {
	   var el = document.getElementById(id);

	   if (el.style.visibility=="visible") {
			  el.style.visibility="hidden";
		 }
		 else {
			  el.style.visibility="visible";
		 }
	 }
	 
	 
	  
	 
	 
	 
	 
	 