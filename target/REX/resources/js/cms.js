/********** CMS start ****************/
function deleteImageFile(src, type) {
	$("#deleteFileModal .modal-footer .btn-danger").attr("onclick", "deleteFileProcess('"+src+"', '"+type+"')");
	$("#deleteFileModal").modal("show");
}
function deleteFileProcess(src, type) {
	$.ajax({
		type: 'POST',
		url: 'actions/admin/files/image.php',
		dataType: 'html',
		async: true,
		data: {
			src: src,
			type: type
		},
		success: fileDeletedSuccess,
		error: errorHandler		
	});
}
function fileDeletedSuccess(data, status) {
	var obj = JSON.parse(data);
	var elem = $("#fs_"+obj.type+" [style*=\"background-image: url(\'"+obj.src+"\')\"").parent().parent();
	elem.hide("slow", function () {
		elem.remove();
	});
	var ex = $("#fs_"+obj.type+" h4 span:nth-child(1)").text();
	ex--;
	$("#fs_"+obj.type+" h4 span:nth-child(1)").text(ex);
	var num = $("#fs_"+obj.type+" h4 span:nth-child(2)").text();
	num--;
	$("#fs_"+obj.type+" h4 span:nth-child(2)").text(num);
	var bytes = $("#fs_"+obj.type+" h4 span:nth-child(3)").text();
	$("#fs_"+obj.type+" h4 span:nth-child(3)").text(bytes-obj.bytes);
	if (ex == 0) {
		$("#fs_"+obj.type+" #dirImgClr").addClass("disabled");		
	}
	snackbar("Selected file Deleted Successfully");
}
function deleteAllFSImages(data, type) {
	$("#deleteFileModal .modal-info span").text("all unnessary files");
	$("#deleteFileModal .modal-footer .btn-danger").attr("onclick", "deleteAllFileProcess('"+data+"', '"+type+"')");
	$("#deleteFileModal").modal("show");
}
function deleteAllFileProcess(srcs, type) {
	$.ajax({
		type: 'POST',
		url: 'actions/admin/files/all.php',
		dataType: 'html',
		async: true,
		data: {
			srcs: srcs,
			type: type
		},
		success: allFilesDeletedSuccess,
		error: errorHandler
	});
}
function allFilesDeletedSuccess(data, status) {
	var data_arr = data.split("Rex123");
	var type = data_arr[1];
	var obj = JSON.parse(data_arr[0]);
	for (var i = 0; i < obj.length; i++) {
		var elem = $("#fs_"+type+" [style*=\"background-image: url(\'"+obj[i].src+"\')\"").parent().parent();
		elem.hide("slow", function () {
			elem.remove();
		});
	};
	var ex = +$("#fs_"+type+" h4 span:nth-child(1)").text();
	$("#fs_"+type+" h4 span:nth-child(1)").text("0");
	var num = +$("#fs_"+type+" h4 span:nth-child(2)").text();
	$("#fs_"+type+" h4 span:nth-child(2)").text(num-ex);
	$("#fs_"+type+" h4 span:nth-child(3)").text("0.00");
	$("#fs_"+type+" #dirImgClr").attr("onclick", "deleteAllFSImages('<srcs></srcs>')");
	$("#fs_"+type+" #dirImgClr").addClass("disabled");
	snackbar("All unnessary files are Deleted Successfully");
}
function deleteImageRow(piid) {
	$("#deleteRowModal .modal-footer .btn-danger").attr("onclick", "asyncProcess('actions/admin/tables/image.php', "+piid+", rowDeletedSuccess)");
	$("#deleteRowModal").modal("show");	
}
function rowDeletedSuccess(data, status) {
	var obj = JSON.parse(data);
	var elem = $(".db-image #p_i_r_id-"+obj.piid);
	elem.hide("slow", function () {
		elem.remove();
	});
	var ex = +$(".db-image h4 span:nth-child(1)").text();
	ex--;
	$(".db-image h4 span:nth-child(1)").text(ex);
	var num = +$(".db-image h4 span:nth-child(2)").text();
	num--;
	$(".db-image h4 span:nth-child(2)").text(num);
	snackbar("Selected row Deleted Successfully");
}
function deleteAllImagesRows(data) {
	$("#deleteRowModal .modal-info span").text("all unnessary rows");
	$("#deleteRowModal .modal-footer .btn-danger").attr("onclick", "asyncProcess('actions/admin/tables/image-all.php', '"+data+"', allRowsDeletedSuccess)");
	$("#deleteRowModal").modal("show");
}
function allRowsDeletedSuccess(data, status) {
	var obj = JSON.parse(data);
	for (var i = 0; i < obj.length; i++) {
		var elem = $(".db-image #p_i_r_id-"+obj[i].piid);
		elem.hide("slow", function () {
			elem.remove();
		});
	};
	var ex = +$(".db-image h4 span:nth-child(1)").text();
	$(".db-image h4 span:nth-child(1)").text("0");
	var num = +$(".db-image h4 span:nth-child(2)").text();
	$(".db-image h4 span:nth-child(2)").text(num-ex);
	$(".db-image h4 span:nth-child(3)").text("0.00");
	$(".db-image #dbImgClr").attr("onclick", "deleteAllImagesRows('<piids></piids>')");
	$(".db-image #dbImgClr").addClass("disabled");
	snackbar("All unnessary rows are Deleted Successfully");
}
function replacePropImage(src, title) {
	// $("#clientEditModal").modal('hide');
	$("#replaceImageModal .user-dp-xl").attr("style", "background-image: url('"+src+"');");
	$("#replaceImageModal .user-dp-xl").attr("onclick", "showImageModal('"+title+"', '"+src+"')");
	$("#replaceImageModal form").attr("action", "actions/admin/files/prop-replace.php?src="+src);
	$("#replaceImageModal").modal('show');
}
function renameImage(src, type) {
	src_arr = src.split("/");
	src = src_arr[src_arr.length-1];
	$("#renameModal #filename").val(src);
	$("#renameModal .modal-footer .btn-success").attr("onclick", "renameAction('"+src+"', '"+type+"')");
	$("#renameModal").modal('show');
}
function renameAction(osrc, type) {
	var fname = $("#renameModal #filename").val();
	$.ajax({
		type: 'POST',
		url: 'actions/admin/cms-rename.php',
		dataType: 'html',
		async: true,
		data: {
			filename: fname,
			old: osrc,
			type: type
		},
		success: renameSuccessBlock,
		error: errorHandler		
	});
}
function renameSuccessBlock(data, status) {
	var obj = JSON.parse(data);
	$("#cms #src-"+obj.oldName).text(obj.newName);
	$("#cms #src-"+obj.oldName).parent().find(".btn-warning").attr("onclick", "renameImage('"+obj.newName+"', '"+obj.type+"')");
	$("#cms #src-"+obj.oldName).attr("id", "src-"+obj.newSalt);
	snackbar("Selected filename has been renamed successfully!");
}
/****** CMS end ***********/
