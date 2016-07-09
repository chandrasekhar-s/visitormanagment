
/**Preview functions*/
$(function() {
	$(".firstName").keyup(function() {
		var word = $(this).val();
		$(".firstname_preview").html(word);
		return false;
	});
});
$(function() {
	$(".purpose").keyup(function() {
		var word = $(this).val();
		$(".purpose_preview").html(word);
		return false;
	});
});
$(function() {
	$(".email").keyup(function() {
		var word = $(this).val();
		$(".email_preview").html(word);
		return false;
	});
});
$(function() {
	$(".mobile").keyup(function() {
		var word = $(this).val();
		$(".tel_preview").html(word);
		return false;
	});
});
$(function() {
	$(".building").change(function() {
		var word = $(this).val();
		var added = $(".building_preview").html();
		added = added + "," + word;
		if (added.match("^,")){
			$(".building_preview").html(added.substring(1));
			$("#buildingstr").val(added.substring(1));
			
		}
		else{
			$(".building_preview").html(added);
			$("#buildingstr").val(added);
		}
		
		return false;
	});
});
$(function() {
	$(".company").blur(function() {
		var word = $(this).val();
		$(".company_preview").html(word);
		return false;
	});
});
$(function() {
	$(".vechno").keyup(function() {
		var word = $(this).val();
		$(".vechno_preview").html(word);
		return false;
	});
});

$(function() {
	$(".validupto").change(function() {
		var word = $(this).val();
		$(".validupto_preview").html(word);
		return false;
	});
	
});	
	

	



/**
 * Function to clear Preview Pass area
 * */
function clear() {
	document.getElementById('vpreview').src = "";
	$(".firstname_preview").html("");
	$(".purpose_preview").html("");
	$(".email_preview").html("");
	$(".tel_preview").html("");
	$(".building_preview").html("");
	$(".company_preview").html("");
	$(".vechno_preview").html("");
	$(".validupto_preview").html("");
	$(".passid_preview").html("");
	$(".accmpny_cnt_preview").html("");
	$(".date").html("");
	$(".time").html("");
	$(".message").html("");
	document.getElementById('print').disabled = "true";
	
}


/**
 * Function to generate pass code:
 * NOT used
 * */
function genBarCode() {
	var code = $(".firstName").val().substring(0, 5) + $(".mobile").val()
			+ $(".building").val().substring(0, 4)
			+ $(".company").val().substring(0, 4);
	$("#bcTarget").barcode(code, "code128", {
		barWidth : 1,
		barHeight : 10,
		showHRI : false
	});
}



/**To be removed*/
function printDiv(divName) {
	var printContents = document.getElementById(divName).innerHTML;
	var originalContents = document.body.innerHTML;
	document.body.innerHTML = printContents;
	window.print();
	document.body.innerHTML = originalContents;
	// $("#col3").print()
	window.location.reload()

}



