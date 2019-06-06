function navhover(obj) {
	obj.id = 'navrowhovered';
}

function navunhover(obj) {
	obj.id = 'navrow';
}

function toggle(s) {
	try {
		var div;
		if (s == 'email') {
			div = document.getElementById('maildiv');
		} else if (s == 'notes') {
			div = document.getElementById('notesdiv');
		}
		if (div.style.display != 'none') {
			div.style.display = 'none';
		} else {
			div.style.display = '';
		}
	} catch (e) {
		alert(e.message);
	}
}