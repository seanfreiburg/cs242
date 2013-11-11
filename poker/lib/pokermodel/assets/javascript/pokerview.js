$(document).ready(function(){

$('.card_back').hide();

var blah = function(){
$('#0').fadeOut(500,function(){
	$('#0').removeClass('card_back');
	$('#0').addClass('ace_hearts');
	$('#0').fadeIn(500);
});

}

$('#0').fadeIn(500,function(){
	$('#1').fadeIn(500,function(){
		$('#2').fadeIn(500,function(){
			$('#3').fadeIn(500,function(){
				$('#4').fadeIn(500,blah)
			});
		});
	});
});



});