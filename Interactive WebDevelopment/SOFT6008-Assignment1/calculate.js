 //Author: Mohammed Alom Student ID: R00144214 Email: mohammed.alom@mycit.ie


//This function will checks which options are checked and adds the amount to sum to get the total amount
//if deselect from the check box then price will automatically will update in the form
function sumTotal() 
{
  var sum = 0; // var sum is variable & will store all the prie value 
  if (document.getElementById('Small').checked) 
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
  if (document.getElementById('TransparentBase').checked)
  {
    sum = sum + 3.00;
  }
  if (document.getElementById('Thermos').checked) 
  {
    sum = sum + 7.00;
  }
  if (document.getElementById('UploadLogo').checked) 
  {
    sum = sum + 6.00;
  }
  if (document.getElementById('EngravedInitials').checked) 
  {
    sum = sum + 2.00;
  }
  if (document.getElementById('HotPlate').checked) 
  {
    sum = sum + 15.00;
  }
  if (document.getElementById('MugLid').checked) 
  {
    sum = sum + 3.00;
  }
  if (document.getElementById('MugPullCord').checked) 
  {
    sum = sum + 6.00;
  }
  if (document.getElementById('Coffee').checked) 
  {
    sum = sum + 15.00;
  }
  if (document.getElementById('Milk').checked) 
  {
    sum = sum + 3.00;
  }
  if (document.getElementById('Tea').checked) 
  {
    sum = sum + 6.00;
  }

  var total = document.getElementById("totalPrice"); //var total is variable & will work with id 'totalPrice'

  total.style.display = 'block'; // will display total price as a block
  total.innerHTML = "Total Price For The Mug  € " + sum.toFixed(2); // € is for euro sign and toFixed(2) is for total price will show after decemil value untill 2 number
  var price = document.getElementById('tprice').value = sum.toFixed(2); //this part for the after submit to see in the form total price.
}

function submitForm() // This function for submit the form and also will give alert if nothing select
{	
	if (document.getElementById('tprice').value == '')
	{
		alert('Please Select Product Before Order');
		return false;
	}
	return true;
}

 
function hideTotal() //This function for during onload the page total price for the mug will not show before select anything 
{
  var total = document.getElementById('totalPrice');
  total.style.display = 'none';
}

//This function will checks the colour choosen and changes the image depending on the option
function colourChange() 
{
  var colour = document.getElementById("colour").value; //var color is a variable and will store the color choose option
  document.getElementById("mugColour").src = "images/" + colour + ".jpg"; // All the image is .jpg format
}