$(document).ready(function(){

//$('.card_back').hide();
$('#bet_slider').hide();
$('#amount').hide();

$('#bet').click(function(){
	showFlop(['AD','AS','AC','KH','KS']);
	showHand(['KD','KC']);
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

$('#bet_slider').change(function(){
	var value = $('#bet_slider').val();
	if(value == $('#bet_slider').attr("max"))
		value = "All-in";
	else
		value = '$'+value;

	$('#amount').html(value);
});

var changeButtons = function(){
	$('#bet').html('Raise');
	$('#check').html('Call');
}

var dealCommunity = function(){
	$('#0').fadeIn(500,function(){
		$('#1').fadeIn(500,function(){
			$('#2').fadeIn(500,function(){
				$('#3').fadeIn(500,function(){
					$('#4').fadeIn(500);
				});
			});
		});
	});
}

var showFlop = function(card_array){
	$('#0').fadeOut(500,function(){
		$('#0').removeClass('card_back');
		$('#0').addClass(card_array[0]);
		$('#0').fadeIn(500,	
			function(){
				$('#1').fadeOut(500,function(){
					$('#1').removeClass('card_back');
					$('#1').addClass(card_array[1]);
					$('#1').fadeIn(500,	
						function(){
							$('#2').fadeOut(500,function(){
								$('#2').removeClass('card_back');
								$('#2').addClass(card_array[2]);
								$('#2').fadeIn(500);
							});
						});
				});
			});
	});




}

var showHand = function(hand_array){
		$('#player5card2').fadeOut(500,function(){
		$('#player5card2').removeClass('card_back');
		$('#player5card2').addClass(hand_array[1]);
		$('#player5card2').fadeIn(500,
			function(){
				$('#player5card1').fadeOut(500,function(){
				$('#player5card1').removeClass('card_back');
				$('#player5card1').addClass(hand_array[0]);
				$('#player5card1').fadeIn(500);
			});
	});

});
	}



var showTurn = function(card_array){
	$('#3').fadeOut(500,function(){
		$('#3').removeClass('card_back');
		$('#3').addClass(card_array[3]);
		$('#3').fadeIn(500);
	});
}

var showRiver = function(card_array){
	$('#4').fadeOut(500,function(){
		$('#4').removeClass('card_back');
		$('#4').addClass(card_array[4]);
		$('#4').fadeIn(500);
	});	
}

dealCommunity();
//
});