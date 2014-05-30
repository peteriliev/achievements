$(document)
		.ready(
				function() {
					// renderActions();
					setAchievementStates();

					$('#target_user')
							.change(
									function() {
										console.info('ddl change');
										var target_usr_uuid = $(this).find(':selected').val()
										var cat_uuid = $('#current_cat').attr('value');
										var url = '/achievements-webapp/UserCategory.jsp?catUUID='
												+ cat_uuid
												+ '&targetUsrUUID='
												+ target_usr_uuid;
										$(location).attr('href', url);
									});

					$('.btn_claim')
							.click(
									function() {
										var achievement_uuid = $(this).attr('achievement_uuid');
										var user_uuid = $(this).attr('user_uuid');
										var achievement_type = $(this).attr('achievement_type');

										var request = $
												.ajax({
													url : 'ClaimAchievement',
													type : 'POST',
													data : {
														achievement_uuid : achievement_uuid,
														user_uuid : user_uuid,
														note : 'hard coded note: claim'
													},
													dataType : 'json'
												});

										request.done(function(msg) {
													updateButtonState(achievement_type, msg.record_status, achievement_uuid);
													console.info('Claim achievement: success - ' + msg);
												});

										request.fail(function(jqXHR, textStatus) {
													console.info('Claim achievement: fail - ' + textStatus);
												});
									});

					$('.btn_reclaim')
							.click(
									function() {
										var achievement_uuid = $(this).attr('achievement_uuid');
										var user_uuid = $(this).attr('user_uuid');

										var request = $
												.ajax({
													url : 'ReclaimAchievement',
													type : 'POST',
													data : {
														achievement_uuid : achievement_uuid,
														user_uuid : user_uuid,
														note : 'hard coded note: reclaim'
													},
													dataType : 'json'
												});

										request.done(function(msg) {
													console.info('Reclaim achievement: success - ' + msg);
												});

										request.fail(function(jqXHR, textStatus) {
													console.info('Reclaim achievement: fail - ' + textStatus);
												});
									});

					$('.btn_reject')
							.click(
									function() {
										var record_uuid = $(this).attr('record_uuid');
										var admin_uuid = $('#current_admin').attr('value');

										var request = $
												.ajax({
													url : 'RejectAchievement',
													type : 'POST',
													data : {
														record_uuid : record_uuid,
														admin_uuid : admin_uuid,
														note : 'hard coded note: reject'
													},
													dataType : 'json'
												});

										request.done(function(msg) {
													console.info('Reject achievement: success - ' + msg);
												});

										request.fail(function(jqXHR, textStatus) {
													console.info('Reject achievement: fail - ' + textStatus);
												});
									});

					$('.btn_approve').click(
									function() {
										var record_uuid = $(this).attr('record_uuid');
										var admin_uuid = $('#current_admin').attr('value');
										var achievement_uuid = $(this).attr('achievement_uuid');
										var achievement_type = $(this).attr('achievement_type');

										var request = $
												.ajax({
													url : 'ApproveAchievement',
													type : 'POST',
													data : {
														record_uuid : record_uuid,
														admin_uuid : admin_uuid,
														note : 'hard coded note: approve'
													},
													dataType : 'json'
												});

										request.done(function(msg) {
													updateButtonState(achievement_type, msg.record_status, achievement_uuid);
													console.info('Approve achievement: success - '+ msg);
												});

										request.fail(function(jqXHR, textStatus) {
													console.info('Approve achievement: fail - '+ textStatus);
												});
									});
				});

function updateButtonState(achievement_type, record_status, achievement_uuid) {
	console.info('achievement_type = ' + achievement_type);
	console.info('record_status = ' + record_status);

	var user_type = $('#current_user_type').attr('value');

	var request = $.ajax({
		url : 'GetAvailableActions',
		type : 'POST',
		data : {
			user_type : user_type,
			achievement_type : achievement_type,
			record_status : record_status
		},
		dataType : 'json'
	});

	request.done(function(data) {
		setAchievementState(record_status, achievement_uuid);

		$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_claim').css('display', 'none');
		$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_reclaim').css('display', 'none');
		$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_approve').css('display', 'none');
		$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_reject').css('display', 'none');

		$.each(data, function(i, item) {
			if ('USR_CLAIM' == item) {
				$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_claim').css('display', 'block');

			} else if ('USR_RECLAIM' == item) {
				$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_reclaim').css('display', 'block');

			} else if ('ADM_APPROVE' == item) {
				$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_approve').css('display', 'block');

			} else if ('ADM_REJECT' == item) {
				$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_reject').css('display', 'block');

			}
		});

		console.info('Render actions: success - ' + data);
	});

	request.fail(function(jqXHR, textStatus) {
		console.info('Render actions: fail - ' + textStatus);
	});
}

function setAchievementState(record_status, achievement_uuid) {
	if ('APPROVED' == record_status) {
		$('#achievement-' + achievement_uuid).removeClass("unearned").addClass("earned");
		$('#achievement-icon-portrait-' + achievement_uuid).removeClass("tile-locked");
		$('#achievement-icon-frame-' + achievement_uuid).css('background-position', '-180px -90px');
		$('#achievement-status-' + achievement_uuid).text('Approved');

	} else if ('CLAIM' == record_status) {
		$('#achievement-' + achievement_uuid).removeClass("earned").addClass("unearned");
		$('#achievement-icon-portrait-' + achievement_uuid).addClass("tile-locked");
		$('#achievement-icon-frame-' + achievement_uuid).css('background-position', '-225px -90px');
		$('#achievement-status-' + achievement_uuid).text('(Awaiting approval)');

	} else if ('REJECTED'){
	} else if ('RECLAIM'){
	} else if ('NULL'){
	}
}

function setAchievementStates() {
	$('.achievement-block').each(function() {
		var achievement_type = $(this).attr('achievement_type');
		var record_status = $(this).attr('record_status');
		var achievement_uuid = $(this).attr('achievement_uuid');
		
		console.info('achievement_type' + achievement_type);
		console.info('record_status' + record_status);
		console.info('achievement_uuid' + achievement_uuid);

		updateButtonState(achievement_type, record_status, achievement_uuid);
	});
}

function renderActions() {
	var target_user = $('#target_user').val();
	var current_admin = $('#current_admin').attr('value');
	var user_type = $('#current_user_type').attr('value');

	console.info('tu = ' + target_user);
	console.info('ca = ' + current_admin);

	$('.action_menu').each(function() {

		var request = $.ajax({
			url : 'GetAvailableActions',
			type : 'POST',
			data : {
				target_user			: target_user,
				current_admin		: current_admin,
				user_type			: user_type,
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