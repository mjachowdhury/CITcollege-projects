tinyMCE.init({
	mode : "textareas",
	theme : "simple"
});

function validateFrm() {
	var form = document.forms['prefForm'];
	var fullName = trim(form.fullname.value);
	var fromAddress = trim(form.fromAddress.value);

	if (fullName.length == 0) {
		alert("Please enter your Full Name. It is a required field.");
		form.fullname.focus();
		return false;
	}

	if (fromAddress.length == 0) {
		alert("Please enter your From Address. It is a required field.");
		form.fromAddress.focus();
		return false;
	}
	return true;
}

function submitFrm() {
	if (validateFrm()) {
		document.forms['prefForm'].submit();
	}
}
