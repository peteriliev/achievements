$( document ).ready(function() {

	$('.btn_claim').click(function() {
        var achievement_uuid = $(this).attr('achievement_uuid');
        var user_uuid = $(this).attr('user_uuid');
        
        var request = $.ajax({
          url: 'ClaimAchievement',
          type: 'POST',
          data: { 
          	achievement_uuid	: achievement_uuid,
          	user_uuid			: user_uuid,
          	note				: 'hard coded note: claim' },
          dataType: 'html'
        });
         
        request.done(function(msg) {
        	console.info('Claim achievement: success - ' + msg);
        });
         
        request.fail(function(jqXHR, textStatus) {
        	console.info('Claim achievement: fail - ' + textStatus);
        });
	});
	
	$('.btn_reclaim').click(function() {
        var achievement_uuid = $(this).attr('achievement_uuid');
        var user_uuid = $(this).attr('user_uuid');
        
        var request = $.ajax({
          url: 'ReclaimAchievement',
          type: 'POST',
          data: {
          	achievement_uuid	: achievement_uuid,
          	user_uuid			: user_uuid,
          	note				: 'hard coded note: reclaim' },
          dataType: 'html'
        });
         
        request.done(function( msg ) {
        	console.info('Reclaim achievement: success - ' + msg);
        });
         
        request.fail(function( jqXHR, textStatus ) {
        	console.info('Reclaim achievement: fail - ' + textStatus);
        });
	});
	
	$('.btn_reject').click(function() {
        var achievement_uuid = $(this).attr('achievement_uuid');
        var user_uuid = $(this).attr('user_uuid');
        
        var request = $.ajax({
          url: 'RejectAchievement',
          type: 'POST',
          data: {
          	achievement_uuid	: achievement_uuid,
          	user_uuid			: user_uuid,
          	note				: 'hard coded note: reject' },
          dataType: 'html'
        });
         
        request.done(function(msg) {
        	console.info('Reject achievement: success - ' + msg);
        });
         
        request.fail(function(jqXHR, textStatus) {
        	console.info('Reject achievement: fail - ' + textStatus);
        });
	});
	
	$('.btn_approve').click(function() {
        var achievement_uuid = $(this).attr('achievement_uuid');
        var user_uuid = $(this).attr('user_uuid');
        
        var request = $.ajax({
          url: 'ApproveAchievement',
          type: 'POST',
          data: {
          	achievement_uuid	: achievement_uuid,
          	user_uuid			: user_uuid,
          	note				: 'hard coded note: approve' },
          dataType: 'html'
        });
         
        request.done(function(msg) {
        	console.info('Approve achievement: success - ' + msg);
        });
         
        request.fail(function(jqXHR, textStatus) {
        	console.info('Approve achievement: fail - ' + textStatus);
        });
	});
});