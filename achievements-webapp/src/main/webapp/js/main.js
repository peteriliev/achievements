/**
 * 
 */

$( document ).ready(function() {
	
	$(".btn_complete").click(function() {
        var selectedTargetUser = $('#target_user').find(":selected").val();
        var currentAdminUser = $('#current_admin').val();
        var auuid = $(this).attr("auuid");
        
        var request = $.ajax({
          url: "CreateARecord",
          type: "POST",
          data: { selectedTargetUser:selectedTargetUser, currentAdminUser:currentAdminUser, auuid:auuid },
          dataType: "html"
        });
         
        request.done(function( msg ) {
          alert("done");
        });
         
        request.fail(function( jqXHR, textStatus ) {
          alert( "Request failed: " + textStatus );
        });
		
	});
	
	$(".btn_claim").click(function() {
        var achievement_uuid = $(this).attr('achievement_uuid');
        var user_uuid = $(this).attr("user_uuid");
        
        var request = $.ajax({
          url: "ClaimAchievement",
          type: "POST",
          data: { achievement_uuid:achievement_uuid, user_uuid:user_uuid, note:'hard coded note: claim' },
          dataType: "html"
        });
         
        request.done(function( msg ) {
        	console.info('Claim achievement: success - ' + msg);
        });
         
        request.fail(function( jqXHR, textStatus ) {
        	console.info('Claim achievement: fail - ' + textStatus);
        });
		
	});
	
	$(".btn_reclaim").click(function() {
		alert('btn_reclaim');
        var selectedTargetUser = $('#target_user').find(":selected").val();
        var currentAdminUser = $('#current_admin').val();
        var auuid = $(this).attr("auuid");
        //alert("selectedTargetUser = " + selectedTargetUser);
        //alert("currentAdminUser = " + currentAdminUser);
        //alert("auuid = " + auuid);
        
        var request = $.ajax({
          url: "CreateARecord",
          type: "POST",
          data: { selectedTargetUser:selectedTargetUser, currentAdminUser:currentAdminUser, auuid:auuid },
          dataType: "html"
        });
         
        request.done(function( msg ) {
          alert("done");
        });
         
        request.fail(function( jqXHR, textStatus ) {
          alert( "Request failed: " + textStatus );
        });
		
	});
	
	$(".btn_reject").click(function() {
		alert('btn_reject');
        var selectedTargetUser = $('#target_user').find(":selected").val();
        var currentAdminUser = $('#current_admin').val();
        var auuid = $(this).attr("auuid");
        //alert("selectedTargetUser = " + selectedTargetUser);
        //alert("currentAdminUser = " + currentAdminUser);
        //alert("auuid = " + auuid);
        
        var request = $.ajax({
          url: "CreateARecord",
          type: "POST",
          data: { selectedTargetUser:selectedTargetUser, currentAdminUser:currentAdminUser, auuid:auuid },
          dataType: "html"
        });
         
        request.done(function( msg ) {
          alert("done");
        });
         
        request.fail(function( jqXHR, textStatus ) {
          alert( "Request failed: " + textStatus );
        });
		
	});
	
	$(".btn_approve").click(function() {
		alert('btn_approve');
        var selectedTargetUser = $('#target_user').find(":selected").val();
        var currentAdminUser = $('#current_admin').val();
        var auuid = $(this).attr("auuid");
        //alert("selectedTargetUser = " + selectedTargetUser);
        //alert("currentAdminUser = " + currentAdminUser);
        //alert("auuid = " + auuid);
        
        var request = $.ajax({
          url: "CreateARecord",
          type: "POST",
          data: { selectedTargetUser:selectedTargetUser, currentAdminUser:currentAdminUser, auuid:auuid },
          dataType: "html"
        });
         
        request.done(function( msg ) {
          alert("done");
        });
         
        request.fail(function( jqXHR, textStatus ) {
          alert( "Request failed: " + textStatus );
        });
		
	});
});