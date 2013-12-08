function TableView(num_players){

	this.num_players = num_players;

	this.changeButtons = function(){
		if ($('#bet').html() == 'Bet')
			$('#bet').html('Raise');
		else
			$('#bet').html('Bet');

		if ($('#check').html('Check'))
			$('#check').html('Call');
		else
			$('#check').html('Check');
		}

	this.start_of_round = function(){
		$('#bet_slider').hide();
		$('#amount').hide();
	 	for(i=1; i<9; i++){
	 		$('#player'+i+' .well').css('opacity','0.0');
	 		$('#player'+i+'card1').css('opacity','0.0');
	 		$('#player'+i+'card2').css('opacity','0.0');
	 		if(i < 6)
	 			$('#community'+(i-1)).hide();
	 	}
	}

	var dealCommunity = function(){
		$('#community0').fadeIn(500,function(){
			$('#community1').fadeIn(500,function(){
				$('#community2').fadeIn(500,function(){
					$('#community3').fadeIn(500,function(){
						$('#community4').fadeIn(500);
					});
				});
			});
		});
	}

	var deal_recursive = function(sequence,i,j){
		if(i == sequence.length && j == 2){
			$('.deal_static').hide(dealCommunity());
			return;
		}
		if(i == sequence.length){
			j=j+1;
			i=0;
		}
		$('.deal_static div').addClass('deal_player'+sequence[i]+'_card'+j)
		$('.deal_static div').one('webkitAnimationEnd',[deal_sequence,i,j],
			function(e){
				$('#player'+e.data[0][e.data[1]]+'card'+e.data[2]).css('opacity','1.0');
				$('.deal_static div').removeClass();
				deal_recursive(e.data[0],e.data[1]+1,e.data[2]);
			});
	}

	this.deal_players = function(){
		var deal_orders = {2:[1,5],3:[1,2,5],4:[1,2,5,6],
							5:[1,3,5,7,8],6:[1,2,3,5,6,7],7:[1,2,3,4,5,6,7],8:[1,2,3,4,5,6,7,8]};
		deal_sequence = deal_orders[this.num_players];
		i = 1
		deal_sequence.forEach(function(id){
				$('#player'+id+' .well').animate({'opacity':'1.0'},1000);
				if(id != 5){
					$('#player'+id+' .well').html('Bot_'+i+'<br/>$1000');
					i++;
				}
			});
		deal_recursive(deal_sequence,0,1);
		
	}

	this.showHand = function(hand_array){
		for(i = 0; i<2; i++){
			hand_array[i] = '_'+hand_array[i];
		}
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

	this.showFlop = function(card_array){
		for(i=0;i<3;i++){
			card_array[i] = '_'+card_array[i];
		}
		$('#community0').fadeOut(500,function(){
			$('#community0').removeClass('card_back');
			$('#community0').addClass(card_array[0]);
			$('#community0').fadeIn(500,	
				function(){
					$('#community1').fadeOut(500,function(){
						$('#community1').removeClass('card_back');
						$('#community1').addClass(card_array[1]);
						$('#community1').fadeIn(500,	
							function(){
								$('#community2').fadeOut(500,function(){
									$('#community2').removeClass('card_back');
									$('#community2').addClass(card_array[2]);
									$('#community2').fadeIn(500);
								});
							});
					});
				});
		});


	}

	this.showTurn = function(card_array){
		card_array[3] = '_'+card_array[3];
		$('#community3').fadeOut(500,function(){
			$('#community3').removeClass('card_back');
			$('#community3').addClass(card_array[3]);
			$('#community3').fadeIn(500);
		});
	}

	this.showRiver = function(card_array){
		card_array[4] = '_'+card_array[4];
		$('#community4').fadeOut(500,function(){
			$('#community4').removeClass('card_back');
			$('#community4').addClass(card_array[4]);
			$('#community4').fadeIn(500);
		});	
	}

}
