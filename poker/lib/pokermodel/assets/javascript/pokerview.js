$(document).ready(function(){

$('.card_back').hide();

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
		$('#0').fadeIn(500);
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
//ShowFlop(['AD','AS','AC']);

});