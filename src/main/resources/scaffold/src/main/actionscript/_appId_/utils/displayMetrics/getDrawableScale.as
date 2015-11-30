package _appId_.utils.displayMetrics
{
	import _appId_.actors.R;

	/**
	 * @author SamYStudiO ( contact@samystudio.net )
	 */
	public function getDrawableScale() : Number
	{
		var bucket : String = R.getDrawableBucket();
		var drawableDensity : uint = getBucketDensity( bucket );

		return drawableDensity / getDensity();
	}
}
