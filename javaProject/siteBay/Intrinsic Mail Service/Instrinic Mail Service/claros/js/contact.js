function nameTitle() {
	var form = document.forms[0];
	var firstName = form.firstName.value;
	var middleName = form.middleName.value;
	var lastName = form.lastName.value;

	var fullName = '';
	if (firstName == '' && middleName == '' && lastName == '') {
		fullName = "NO NAME";
	} else {
		if (nameFirst == true) {
			if (middleName == '') {
				fullName = firstName + " " + lastName;
			} else {
				fullName = firstName + " " + middleName + " " + lastName;
			}
		} else {
			if (middleName == '') {
				fullName = lastName + ", " + firstName;
			} else {
				fullName = lastName + ", " + firstName + " " + middleName;
			}
		}
	}
	document.getElementById('name').innerHTML = fullName.toUpperCase();
}

function validate() {
	var form = document.forms['contactFrm'];
	var firstName = trim(form.firstName.value);
	var lastName = trim(form.lastName.value);

	if (firstName.length == 0) {
		alert("Please enter the first name of the contact.");
		return false;
	}

	if (lastName.length == 0) {
		alert("Please enter the last name of the contact.");
		return false;
	}
	return true;
}

function submitFrm() {
	if (validate()) {
		document.forms['contactFrm'].submit();
	}
}
