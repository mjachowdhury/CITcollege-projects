function validate() {
	var form = document.forms['attForm'];
	var v = trim(form.attachFile.value);
	if (v != null && v.length > 0) {
		return true;
	} else {
		alert("Please select a  file to upload to the server first.");
		form.attachFile.focus();
		return false;
	}
}

