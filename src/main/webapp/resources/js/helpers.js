function deleteUser(url, elem) {
	elem.disabled=true;
	$.post(url, function(json) {
		if (!json['success']) {
			alert(json['msg']);
		}
		$(elem).closest('tr').remove();
	});
}