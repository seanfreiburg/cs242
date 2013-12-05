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

var changeButtons = function(){
	if ($('#bet').html() == 'Bet')
		$('#bet').html('Raise');
	else
		$('#bet').html('Bet');

	if ($('#check').html('Check'))
		$('#check').html('Call');
	else
		$('#check').html('Check');
}

var start_of_game = function(){
	$('#bet_slider').hide();
	$('#amount').hide();
	 for(i=1; i<9; i++){
	 	$('#player'+i).css('opacity','0.0');
	 }
}

var showHand = function(hand_array){
		$('#player5card1').fadeOut(500,
		function(){
			$('#player5card1').removeClass('card_back');
			$('#player5card1').addClass(hand_array[1]);
			$('#player5card2').css('opacity','0.0');
			$('#player5card1').fadeIn(500,
				function(){
					$('#player5card2').fadeOut(500,
						function(){
							$('#player5card2').removeClass('card_back');
							$('#player5card2').addClass(hand_array[0]);
							$('#player5card2').css('opacity','1.0');
							$('#player5card2').fadeIn(500);
						});
				});

		});
}

var deal_players = function(num_players){

}

$(document).ready(function(){
	start_of_game();
	bet_raise_button_handler();
	bet_slider_handler();
});