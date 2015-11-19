package _appId_.utils.math
{
	/**
	 * @author SamYStudiO ( contact@samystudio.net )
	 */
	public function round( n : Number ) : int
	{
		return n < 0 ? n + .5 == ( n | 0 ) ? n : n - .5 : n + .5;
	}
}
