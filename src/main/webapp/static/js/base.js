$(document).ready(function() {
	$('.dateAnimate').bootstrapMaterialDatePicker({
		time: false,
		animation: true
	});
});
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