var formatDate = function(value) {
	if (typeof value == 'undefined') {
		var d = new Date();
		var month = d.getMonth() + 1;
		var day = d.getDate();
		return d.getFullYear() + '-' + (('' + month).length < 2 ? '0' : '') + month + '-' + (('' + day).length < 2 ? '0' : '') + day;
	}
	var d = new Date(value);
	var month = d.getMonth() + 1;
	var day = d.getDate();
	return (('' + day).length < 2 ? '0' : '') + day + '/' + (('' + month).length < 2 ? '0' : '') + month + '/' + d.getFullYear();
};
var numbericFilter = function(event, value) {
	if ($.inArray(event.keyCode, [ 46, 8, 9, 27, 13, 110, 190 ]) !== -1 ||
	// Allow: Ctrl+A, Command+A
	(event.keyCode === 65 && (event.ctrlKey === true || event.metaKey === true)) ||
	// Allow: home, end, left, right, down, up
	(event.keyCode >= 35 && event.keyCode <= 40)) {
		if (value.indexOf('.') !== -1 && event.keyCode == 190) {
			event.preventDefault();
		}
		return;
	}
	// Ensure that it is a number and stop the keypress
	if ((event.shiftKey || (event.keyCode < 48 || event.keyCode > 57)) && (event.keyCode < 96 || event.keyCode > 105)) {
		event.preventDefault();
	}
};
var getRandom = function(arr, n) {
	var result = new Array(n);
	var len = arr.length;
	var taken = new Array(len);

	if (n > len) {
		throw new RangeError('getRandom: more elements taken than available.');
	}
	while (n--) {
		var x = Math.floor(Math.random() * len);
		result[n] = arr[x in taken ? taken[x] : x];
		taken[x] = --len;
	}
	return result;
};