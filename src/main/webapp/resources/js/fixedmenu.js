
 $(document).ready( function() {

 	var navOffset =  $('.nav2').offset();
 	$(window).scroll(function() {
 		if( $(document).scrollTop() > navOffset.top) {
 			$('.nav2').addClass('nav2Fixed');
 		} else {
 			$('.nav2').removeClass('nav2Fixed');
 		}
 	});
 });

