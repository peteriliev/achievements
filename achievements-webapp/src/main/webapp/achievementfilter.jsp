<div id="achievement-filters" class="data-filters">

	<div class="profile-progress">
		<div class="progress-wrapper">
			<div class="progress-bar" style="width: 24%"></div>
		</div>
		<span>30 / 125</span>
	</div>

	<div class="ui-dropdown" id="type-filter">
		<select>
			<option value="all">All</option>
			<option value="earned">Earned</option>
			<option value="unearned">Unearned</option>
		</select>
	</div>

	<span class="clear">
		<!-- -->
	</span>

	<script type="text/javascript">
		$(function() {
			Filter.initialize(function(params) {
				if (params.filter) {
					Sc2.filterContent('#achievements-wrapper', 'achievement',
							params.filter);
					$("#type-filter select").val(params.filter);
				}
			});

			$('.ui-dropdown').dropdown(
					{
						callback : function(dropdown, value) {
							Filter.addParam('filter', value);
							Filter.applyQuery();

							Sc2.filterContent('#achievements-wrapper',
									'achievement', value);
						}
					});
		});
	</script>
</div>