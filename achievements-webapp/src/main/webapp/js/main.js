$(document)
		.ready(
				function() {
					setAchievementStates();
					restoreTargetUser();

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
										
										$.cookie('cookie_targetUsrUUID', target_usr_uuid, {expires:100, path :'/'});
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
										var record_uuid = $(this).attr('record_uuid');
										var achievement_uuid = $(this).attr('achievement_uuid');
										var achievement_type = $(this).attr('achievement_type');

										var request = $
												.ajax({
													url : 'ReclaimAchievement',
													type : 'POST',
													data : {
														record_uuid : record_uuid,
														note : 'hard coded note: reclaim'
													},
													dataType : 'json'
												});

										request.done(function(msg) {
													updateButtonState(achievement_type, msg.record_status, achievement_uuid);
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
										var achievement_uuid = $(this).attr('achievement_uuid');
										var achievement_type = $(this).attr('achievement_type');


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
													updateButtonState(achievement_type, msg.record_status, achievement_uuid);
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

		// TODO:peteri - magic string
		$.each(data, function(i, item) {
			
			if ('USR_CLAIM' == item) {
				$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_claim').css('display', 'block');

			} else if ('USR_RECLAIM' == item) {
				$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_reclaim').css('display', 'block');

			} else if ('ADMIN_APPROVE' == item) {
				$('#achievement-action-menu-' + achievement_uuid + ' > li > a.btn_approve').css('display', 'block');

			} else if ('ADMIN_REJECT' == item) {
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
		$('#achievement-status-' + achievement_uuid).text('--APPROVED--');

	} else if ('CLAIM' == record_status) {
		$('#achievement-' + achievement_uuid).removeClass("earned").addClass("unearned");
		$('#achievement-icon-portrait-' + achievement_uuid).addClass("tile-locked");
		$('#achievement-icon-frame-' + achievement_uuid).css('background-position', '-225px -90px');
		$('#achievement-status-' + achievement_uuid).text('--CLAIM--');

	} else if ('REJECTED' == record_status){
		$('#achievement-' + achievement_uuid).removeClass("earned").addClass("unearned");
		$('#achievement-icon-portrait-' + achievement_uuid).addClass("tile-locked");
		$('#achievement-icon-frame-' + achievement_uuid).css('background-position', '-225px -90px');
		$('#achievement-status-' + achievement_uuid).text('--REJECTED--');

		
	} else if ('RECLAIM' == record_status){
		$('#achievement-' + achievement_uuid).removeClass("earned").addClass("unearned");
		$('#achievement-icon-portrait-' + achievement_uuid).addClass("tile-locked");
		$('#achievement-icon-frame-' + achievement_uuid).css('background-position', '-225px -90px');
		$('#achievement-status-' + achievement_uuid).text('--RECLAIM--');

	} else if ('NULL' == record_status){
		$('#achievement-' + achievement_uuid).removeClass("earned").addClass("unearned");
		$('#achievement-icon-portrait-' + achievement_uuid).addClass("tile-locked");
		$('#achievement-icon-frame-' + achievement_uuid).css('background-position', '-225px -90px');
		$('#achievement-status-' + achievement_uuid).text('--NULL--');
	}
}

function setAchievementStates() {
	$('.achievement-block').each(function() {
		var achievement_type = $(this).attr('achievement_type');
		var record_status = $(this).attr('record_status');
		var achievement_uuid = $(this).attr('achievement_uuid');
		
		updateButtonState(achievement_type, record_status, achievement_uuid);
	});
}

function restoreTargetUser() {
	var myTarget = urlParam('targetUsrUUID');
	if (myTarget) {
		window.console.info('URL override');
		return;
	}
	
	var cookieVal = $.cookie('cookie_targetUsrUUID');
	if (!cookieVal) {
		window.console.info('No cookie');
		return;
	}
	
	window.console.info('Try to select' + cookieVal);
	$('#target_user').val(cookieVal);
}

function urlParam (name){
    var results = new RegExp('[\?&amp;]' + name + '=([^&amp;#]*)').exec(window.location.href);
    if (results == null)
    {
    	return null;
    }
    return results[1] || 0;
}