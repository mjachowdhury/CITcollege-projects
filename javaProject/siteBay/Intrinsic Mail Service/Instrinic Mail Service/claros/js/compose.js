function mySubmit(actId) {
	document.forms['myForm'].action.value=actId;
	if (!validate()) {
		return false;
	}
	document.forms['myForm'].submit();
}

var req;
var elements;
var activeText;

function search(idField) {
	var key;
	if (!document.all) {
		key = idField.which;
	} else {
		key = event.keyCode;
	}
  
//	var key = event.keyCode;
	if (key == 9 || 
		key == 13) {
		return;
	}
	if (idField.value.length == 0) {
		return;
	}

	var searchTerm = trim(idField.value);
	var pos = searchTerm.lastIndexOf(',');
	if (pos < 0) {
		pos = searchTerm.lastIndexOf(';');
	}
	if (pos >= 0) {
		searchTerm = trim(searchTerm.substr(pos + 1));
	}

	try {
		var url = "/claros/contacts/ajaxLookup.cl";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("POST", url, true);
		req.onreadystatechange = callback;
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("name=" + encodeURI(searchTerm) + "&s=" + session);
	} catch (e) {
		alert(e.message);
	}
}

function callback() {
	try {
	    if (req.readyState == 4) {
	        if (req.status == 200) {
	        	var text = req.responseText;
	        	
	        	text = unescape(text);
				text = trim(text.replace(/\+/g," "));
	        	
	        	elements = new Array();
	        	
	        	// adding contacts
	        	var pos = text.indexOf("~~~^^^");
	        	var counter = 0;
	        	while (pos > 0) {
	        		elements[counter] = text.substr(0,pos);
	        		text = text.substr(pos + 6);
	        		pos = text.indexOf("~~~^^^");
	        		counter++;
	        	}

				// adding the groups
	        	pos = text.indexOf("|||^^^");
	        	if (pos > 0) {
	        		elements[counter] = " ";
	        	}
	        	counter++;
	        	while (pos > 0) {
	        		elements[counter] = text.substr(0,pos);
	        		text = text.substr(pos + 6);
	        		pos = text.indexOf("|||^^^");
	        		counter++;
	        	}
	        	
	        	if (elements.length > 0) {
	        		showDiv();
	        	}
	        }
	    }			
	} catch (e) {
		alert(e.message);
	}
}

function showDiv() {
	var tbl = document.getElementById("contactlist");
	while ((rowCount = tbl.rows.length) > 0) {
		tbl.deleteRow(rowCount - 1);
	}

	for (i=0;i<elements.length;i++) {
		var newRow = tbl.insertRow(tbl.rows.length);
		if (elements[i] == ' ') {
			var newCell = newRow.insertCell(0);
			newCell.innerHTML = "<hr>";
		} else {
			var newCell = newRow.insertCell(0);
			elements[i] = elements[i].replace(/</g,"&lt;");
			elements[i] = elements[i].replace(/>/g,"&gt;");
			newCell.innerHTML = "<span onclick='addRecipient(this)'>" + trim(elements[i]) + "</span>";
		}
	}
}

function addRecipient(obj) {
	var dest = activeText;
	if (dest == null) {
		dest = document.forms['myForm'].to;
	}
	var str = obj.innerHTML;

	str = str.replace(/&lt;/g,"<");
	str = str.replace(/&gt;/g,">");

	dest.value = trim(dest.value);
	dest.focus();
	if (document.all) {
		var sel = document.selection.createRange();
		if (trim(dest.value) == '') {
			sel.text = str;
		} else {
			sel.text = str + ", ";
		}
	} else {
		if (trim(dest.value) == '') {
			dest.value = str;
		} else {
			dest.value = dest.value + ", " + str;
		}
	}
}

function selectAdr(me) {
	var form = document.forms['myForm'];
	var to = form.to;
	var cc = form.cc;
	var bcc = form.bcc;

	to.className = 'normalAdr';
	cc.className = 'normalAdr';
	bcc.className = 'normalAdr';
	me.className = 'selectedAdr';
	activeText=me;
}

function validate() {
	var form = document.forms['myForm'];
	if (form.action.value==1) {
		var to = trim(form.to.value);
		var cc = trim(form.cc.value);
		var bcc = trim(form.bcc.value);
		var subject = trim(form.subject.value);
	
		if (to.length == 0 && cc.length == 0 && bcc.length == 0) {
			alert("Please enter some recipients in one of the to, cc or bcc fields");
			return false;
		}
		
		if (subject.length == 0) {
			if (!confirm("Are you sure you want to send this message without a subject?")) {
				return false;
			}
		}
	}
	return true;
}
