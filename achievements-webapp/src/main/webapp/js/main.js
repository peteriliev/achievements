/**
 * 
 */

$( document ).ready(function() {
	//alert('foo');
	
	$(".btn_complete").click(function() {
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