function toggleAll(form) {
	var newStatus = form.nope.checked;
	var inp = form.getElementsByTagName("input");
	for (var i=0;i<inp.length;i++) {
		inp[i].checked = newStatus;
	}
}

function trim(str) {
	if (str == null) return null;
	return str.replace(/^\s*|\s*$/g,"");
}
