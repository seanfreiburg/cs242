$(document).ready(function(){

$('.card_back').hide();

var blah = function(){
$('#0').fadeOut(1000,function(){
	$('#0').removeClass('card_back');
	$('#0').addClass('ace_hearts');
	$('#0').fadeIn(1000);
});

}

$('#0').fadeIn(1000,function(){
	$('#1').fadeIn(1000,function(){
		$('#2').fadeIn(1000,function(){
			$('#3').fadeIn(1000,function(){
				$('#4').fadeIn(1000,blah)
			});
		});
	});
});



});