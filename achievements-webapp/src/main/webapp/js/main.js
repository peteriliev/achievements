$(document).ready(function() {
	renderActions();
	
	$('#target_user').change(function() {
		console.info('ddl change');
		var target_usr_uuid = $(this).find(":selected").val()
		var cat_uuid = $('#current_cat').attr('value');
		var url = '/achievements-webapp/UserCategory.jsp?catUUID=' + cat_uuid + '&targetUsrUUID=' + target_usr_uuid;
		$(location).attr('href',url);
	});

	$('.btn_claim').click(function() {
		var achievement_uuid = $(this).attr('achievement_uuid');
		var user_uuid = $(this).attr('user_uuid');

		var request = $.ajax({
			url : 'ClaimAchievement',
			type : 'POST',
			data : {
				achievement_uuid : achievement_uuid,
				user_uuid : user_uuid,
				note : 'hard coded note: claim'
			},
			dataType : 'html'
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
			url : 'ReclaimAchievement',
			type : 'POST',
			data : {
				achievement_uuid : achievement_uuid,
				user_uuid : user_uuid,
				note : 'hard coded note: reclaim'
			},
			dataType : 'html'
		});

		request.done(function(msg) {
			console.info('Reclaim achievement: success - ' + msg);
		});

		request.fail(function(jqXHR, textStatus) {
			console.info('Reclaim achievement: fail - ' + textStatus);
		});
	});

	$('.btn_reject').click(function() {
		var achievement_uuid = $(this).attr('achievement_uuid');
		var user_uuid = $(this).attr('user_uuid');

		var request = $.ajax({
			url : 'RejectAchievement',
			type : 'POST',
			data : {
				achievement_uuid : achievement_uuid,
				user_uuid : user_uuid,
				note : 'hard coded note: reject'
			},
			dataType : 'html'
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
			url : 'ApproveAchievement',
			type : 'POST',
			data : {
				achievement_uuid : achievement_uuid,
				user_uuid : user_uuid,
				note : 'hard coded note: approve'
			},
			dataType : 'html'
		});

		request.done(function(msg) {
			console.info('Approve achievement: success - ' + msg);
		});

		request.fail(function(jqXHR, textStatus) {
			console.info('Approve achievement: fail - ' + textStatus);
		});
	});
});

function renderActions() {
	var target_user = $('#target_user').val();
	var current_admin = $('#current_admin').attr('value');
	var current_user_type = $('#current_user_type').attr('value');
	
	console.info('tu = ' + target_user);
	console.info('ca = ' + current_admin);
	
	$('.action_menu').each(function() {

		var request = $.ajax({
			url : 'GetAvailableActions',
			type : 'POST',
			data : {
				target_user			: target_user,
				current_admin		: current_admin,
				current_user_type	: current_user_type,
				achievement_uuid	: $(this).attr('achievement_uuid'),
				achievement_type	: $(this).attr('achievement_type'),
				record_status		: $(this).attr('record_status')
			},
			dataType : 'html'
		});

		request.done(function(data) {
			console.info('Render actions: success - ' + data);
		});

		request.fail(function(jqXHR, textStatus) {
			console.info('Render actions: fail - ' + textStatus);
		});
		
		console.info($(this).attr('achievement_uuid'));
	});
};