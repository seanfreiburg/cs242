var bet_raise_button_handler = function(){
 $('#bet').click(function(){
	$('#bet_slider').toggle();
	$('#amount').toggle();
	if( $('#bet').html() == 'Bet' ){
		$('#bet').html('Submit');
		$('#bet_slider').val(0);
		$('#amount').html('$0');
	}
	else
		$('#bet').html('Bet');
	});
}

var bet_slider_handler = function(){
	$('#bet_slider').change(function(){
		var value = $('#bet_slider').val();
		if(value == $('#bet_slider').attr("max"))
			value = "All-in";
		else
			value = '$'+value;

		$('#amount').html(value);
	});
}

var start_of_game = function(){
	$('#bet_slider').hide();
	$('#amount').hide();
	 for(i=1; i<9; i++){
	 	$('#player'+i).css('opacity','0.0');
	 }
}

$(document).ready(function(){
	start_of_game();
	bet_raise_button_handler();
	bet_slider_handler();
});