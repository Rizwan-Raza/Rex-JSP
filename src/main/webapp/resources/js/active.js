/** **** Login start ********** */
function readURL(input, query) {
	var files = !!input.files ? input.files : [];
	if (!files.length || !window.FileReader)
		return;
	if (/^image/.test(files[0].type)) {
		var reader = new FileReader();
		reader.readAsDataURL(files[0]);
		reader.onloadend = function() {
			$(query).css("background-image", "url(" + this.result + ")");
		}
	}
}
function readURLs(input) {
	var files = !!input.files ? input.files : [];
	if (!files.length || !window.FileReader)
		return;
	$(".insert-here").empty();
	for (var i = files.length - 1; i >= 0; i--) {
		if (/^image/.test(files[i].type)) {
			var reader = new FileReader();
			reader.readAsDataURL(files[i]);
			reader.onloadend = function() {
				$(".insert-here").append(
						"<div class='col-3 prop-image' style='background-image: url("
								+ this.result + ")'></div>");
			}
		}
	}
}
function changeAddress(street, town, city, state, add_id, type) {
	$("#changeAddressModal #street").val(street);
	$("#changeAddressModal #town").val(town);
	$("#changeAddressModal #city").val(city);
	$("#changeAddressModal #state").val(state);
	$("#changeAddressModal .modal-header h4 span").html(type);
	$("#changeAddressModal form").attr("action",
			"Change-Address?type=" + type + "&add_id=" + add_id);
	$("#changeAddressModal").modal('show');
}
function editPropFeatures(bhk, bath, age, furn, hosp, school, rail, area,
		l_area, pid) {
	// alert(pid);
	var elem = document.getElementById("changeFeatForm");
	elem.bhk.value = bhk;
	elem.bath.value = bath;
	elem.age.value = age;
	elem.furnished.value = furn;
	elem.h_dis.value = hosp;
	elem.s_dis.value = school;
	elem.r_dis.value = rail;
	elem.p_area.value = area;
	elem.land.value = l_area;
	$("#changeFeaturesModal #changeFeatForm").attr("action",
			"Prop-Change-Features?pid=" + pid);
	$("#changeFeaturesModal").modal("show");
}
function editPropInfo(amens, units, floor, t_floors, desc, tnc, pid) {
	// alert(pid);
	var elem = document.getElementById("changeInfoForm");
	$("#changeInfoForm input[checked]").removeAttr("checked");
	elem.reset();
	if (amens != "NULL") {
		var amenities = amens.split(",");
		for (var i = 0; i < amenities.length; i++) {
			switch (amenities[i]) {
			case 'Internet / Wi-Fi':
				amenities[i] = "net";
				break;
			case 'Air-Conditioned':
				amenities[i] = "air";
				break;
			case 'RO Water System':
				amenities[i] = "ro";
				break;
			case 'Gas Supply':
				amenities[i] = "gas";
				break;
			case 'Water Supply and Pipeling':
				amenities[i] = "water";
				break;
			}
		}
		for (var i = 0; i < amenities.length; i++) {
			$("#changeInfoForm input[value='" + amenities[i] + "']").attr(
					"checked", "checked");
		}
	}
	elem.units.value = units;
	if (floor != '-5') {
		elem.floor.value = floor;
	} else {
		elem.floor.value = "";
	}
	elem.t_floors.value = t_floors;
	if (desc != "NULL") {
		elem.desc.value = desc;
	}
	if (tnc != "NULL") {
		elem.tnc.value = tnc;
	}
	$("#changeInfoModal #changeInfoForm").attr("action",
			"Prop-Change-Informations?pid=" + pid);
	$("#changeInfoModal").modal("show");
}
function showPropImages(srcs, title, pid) {
	srcs = srcs.slice(1, -1);
	var src_arr = srcs.split(", ");
	var str = "";
	var num = src_arr.length;
	var div = 12 / num;
	switch (num) {
	case 1:
	case 3:
		div = 12 / num;
		break;
	case 2:
	case 4:
		div = 6;
		break;
	case 5:
	case 6:
	case 9:
		div = 4;
		break;
	case 7:
	case 8:
	case 10:
	case 11:
	case 12:
		div = 3;
		break;
	case 13:
	case 14:
		div = 2;
		break;
	case 15:
	case 16:
		div = 3;
		break;
	default:
		div = 2;
		break;
	}
	for (i = 0; i < num; i++) {
		str += "<div class='col-"
				+ div
				+ "'> <div class='buy-prop-image image-triggerer' style=\"background-image: url('"
				+ src_arr[i]
				+ "')\" onclick=\"showImageModal('"
				+ title
				+ "', '"
				+ src_arr[i]
				+ "');\"><div class='remove-image diggle'><i class='fa fa-remove fa-fw'></i></div></div></div>";
	}
	$("#propImagesModal .row#prop-img-holder").html(str);
	$("#propImagesModal .modal-footer p").html(
			'<p>Change <a id="change-prop-images" href="javascript:addOrRemoveImages(\''
					+ srcs + '\', \'' + title + '\', ' + pid
					+ ')">Images?</a></p>');
	$("#propImagesModal").modal('show');
}
function addOrRemoveImages(srcs, title, pid) {
	var src_arr = srcs.split(", ");
	var str = "";
	var num = src_arr.length;
	for (i = 0; i < num; i++) {
		$(
				"div[class*='col-']:nth-child(" + (i + 1)
						+ ") .buy-prop-image .remove-image").attr(
				"onclick",
				"removeImage('" + src_arr[i] + "', '" + title + "', " + pid
						+ ")");
	}
	$(".buy-prop-image").removeClass("image-triggerer");
	$(".buy-prop-image").removeAttr("onclick");
	$(".buy-prop-image .remove-image").show();
	$("#propImagesModal .modal-footer p").html(
			'<p>Add <a id="change-prop-images" href="javascript:addImage('
					+ pid + ')">Image?</a></p>');
	// $("#propImagesModal .modal-footer .pull-right p").html('<input
	// type="file">');
}
function removeImage(src, title, pid) {
	// alert(src);
	$("#removePropImageModal .modal-body p span").html("<b>" + title + "</b>");
	$("#removePropImageModal .modal-body .image-triggerer").removeAttr("style");
	$("#removePropImageModal .modal-body .image-triggerer").css(
			"background-image", "url('" + src + "')");
	$("#removePropImageModal .modal-body .image-triggerer").attr("onclick",
			"showImageModal('" + title + "', '" + src + "')");
	$("#removePropImageModal .modal-footer .btn").attr(
			"onclick",
			"asyncProcess('Prop-Remove-Image?pid=" + pid + "', '" + src.trim()
					+ "', removeImageSuccess)");
	$("#removePropImageModal").modal("show");
}

function removeImageSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#removePropImageModal").modal("hide");
	if (obj.response != "OK") {
		snackbar(obj.message);
		return;
	}
	var a = $("a[href*=\"" + obj.src + "\"");
	var srcs = a.attr("href");
	var new_srcs = srcs.replace(", " + obj.src, "");
	if (new_srcs.length > srcs.length) {
		new_srcs = srcs.replace(obj.src + ",", "");
	}
	a.attr("href", new_srcs);
	var num = $(".buy-prop-image[style*='" + obj.src + "'").parent().parent()
			.children().length;
	$(
			"#propImagesModal .buy-prop-image[style*=\"background-image: url('"
					+ obj.src + "')\"]").parent().hide(
			"slow",
			function() {
				$(
						"#propImagesModal .buy-prop-image[style*=\"background-image: url('"
								+ obj.src + "')\"]").parent().remove();
			});
	$(
			"#my-property .buy-prop-image[style*=\"background-image: url('"
					+ obj.src + "')\"]").parent().hide(
			"slow",
			function() {
				$(
						"#my-property .buy-prop-image[style*=\"background-image: url('"
								+ obj.src + "')\"]").parent().remove();
			});
	num -= 1;
	var div = 12 / num;
	switch (num) {
	case 1:
	case 3:
		div = 12 / num;
		break;
	case 2:
	case 4:
		div = 6;
		break;
	case 5:
	case 6:
	case 9:
		div = 4;
		break;
	case 7:
	case 8:
	case 10:
	case 11:
	case 12:
		div = 3;
		break;
	case 13:
	case 14:
		div = 2;
		break;
	case 15:
	case 16:
		div = 3;
		break;
	default:
		div = 2;
		break;
	}
	$(".buy-prop-image[style*=\"background-image: url('" + obj.src + "')\"")
			.parent().parent().children().attr("class", "bsdk col-" + div);
	snackbar(obj.message);
}
function addImage(pid) {
	$("#addPropImageModal form").attr("action", "Prop-Add-Image?pid=" + pid);
	$("#addPropImageModal").modal("show");
}
function addImageAsync(pid) {
	var x = $("#addPropImageModal .user-dp").css("background-image");
	alert(x);
}
function editProp(title, type, t_type, price, d_p, avail, pid) {
	var elem = document.getElementById('editPropForm');
	elem.p_type.value = type;
	elem.t_type.value = t_type;
	elem.title.value = title;
	elem.price.value = price;
	elem.price_display.value = d_p;
	elem.available.value = avail;
	// $("#propEditModal #editPropForm").attr("onsubmit",
	// "editPropAction('actions/props/edit-prop.php', "+pid+")");
	// $("#propEditModal #editPropForm").attr("onsubmit",
	// "editPropAction('actions/props/test.php', "+pid+")");
	$("#propEditModal #editPropForm").attr("action", "Prop-Edit?pid=" + pid);
	$("#propEditModal").modal('show');
}
function editPropAction(url, pid) {
	// var elem = document.getElementById("editPropForm");
	// alert("Here");
	// $.ajax({
	// type: 'POST',
	// url: url,
	// dataType: 'html',
	// async: true,
	// data: {
	// pid: pid,
	// p_type: elem.p_type.value,
	// t_type: elem.t_type.value,
	// price: elem.price.value,
	// price_display: elem.price_display.value,
	// available: elem.available.value,
	// p_edit_submit: elem.p_edit_submit.value
	// },
	// success: editPropSuccess,
	// error: errorHandler
	// });
}
function editPropSuccess(data, status) {
	// $("#propEditModal").modal('hide');
	// alert("Done"+ data);
	// showSnackbar("propEditedSnackbar");
}
function deleteProp(name, pid) {
	$("#deletePropModal .modal-info > b > span").text(name);
	// $("#deletePropModal .modal-footer > .btn-danger").attr("onclick",
	// "window.location.href='actions/props/delete-prop.php?pid="+pid+"'");
	$("#deletePropModal .modal-footer > .btn-success").attr("onclick",
			"asyncProcess('Prop-Delete'," + pid + ", deletePropSuccess)");
	$("#deletePropModal").modal('show');
}
function deletePropSuccess(data, status) {
	var obj = JSON.parse(data);
	if (obj.response == "OK") {
		if (obj.type == "ADMIN") {
			$("#posts b#post-num").text((+$("#posts b#post-num").text()) - 1);
			$("#deletePropModal").modal("hide");
			$("tr#for-" + obj.pid).hide("slow", function() {
				$("tr#for-" + obj.pid).remove();
			});
		} else {
			$("#my-property #prop-" + obj.pid).hide("slow", function() {
				$("#my-property #prop-" + obj.pid).remove();
			});
		}
	}
	snackbar(obj.message);
}
function editRequest(type, city, state, bhk, bath, a_from, a_to, p_from, p_to,
		pr_id, by) {
	if (by == "client") {
		$("#myRequirementModal").modal("hide");
	}
	var elem = document.getElementById('editRequestForm');
	elem.p_type.value = type;
	elem.city.value = city;
	elem.state.value = state;
	elem.bhk.value = bhk;
	elem.bath.value = bath;
	// elem.c_area.value = a_from + " Sq-Ft. - " + a_to + " Sq-Ft.";
	// elem.budget.value = "Rs. " + p_from + " - Rs. " + p_to;
	$("#area-slider-range-e").slider(
			{
				values : [ a_from, a_to ],
				slide : function(event, ui) {
					$("#e_c_area").val(
							ui.values[0] + " Sq-Ft. - " + ui.values[1]
									+ " Sq-Ft.");
				}
			});
	$("#e_c_area")
			.val(
					$("#area-slider-range-e").slider("values", 0)
							+ " Sq-Ft. - "
							+ $("#area-slider-range-e").slider("values", 1)
							+ " Sq-Ft.");

	$("#budget-slider-range-e").slider(
			{
				values : [ p_from, p_to ],
				slide : function(event, ui) {
					$("#e_budget").val(
							"Rs. " + ui.values[0] + " - Rs. " + ui.values[1]);
				}
			});
	$("#e_budget").val(
			"Rs. " + $("#budget-slider-range-e").slider("values", 0)
					+ " - Rs. "
					+ $("#budget-slider-range-e").slider("values", 1));

	$("#requestEditModal #editRequestForm").attr("action",
			"actions/props/edit-request.php?pr_id=" + pr_id);
	$("#requestEditModal").modal('show');
}
function deleteRequest(pr_id, type) {
	if (type == "client") {
		$("#myRequirementModal").modal("hide");
	}
	$("#deleteRequestModal .modal-footer > .btn-danger").attr(
			"onclick",
			"asyncProcess('actions/props/delete-request.php'," + pr_id
					+ ", deleteRequestSuccess)");
	$("#deleteRequestModal").modal('show');
}
function deleteRequestSuccess(data, status) {
	var obj = JSON.parse(data);
	if (obj.log == "admin") {
		$("#requires b#request-num").text(
				(+$("#requires b#request-num").text()) - 1);
		$("#deleteRequestModal").modal("hide");
		$("tr#for-req-" + obj.prId).hide("slow", function() {
			$("tr#for-req-" + obj.prId).remove();
		});
	} else {
		$("#myRequirementModal tr#for-my-req-" + obj.prId).remove();
	}
	showSnackbar("requestDeletedSnackbar");
}
/** **** Login end ********** */
