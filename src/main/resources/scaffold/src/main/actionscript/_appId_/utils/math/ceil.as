package _appId_.utils.math
{
	/**
	 * @author SamYStudiO ( contact@samystudio.net )
	 */
	public function ceil( n : Number ) : int
	{
		var ni : int = n;
		return ( n >= 0 && n != ni ) ? ni + 1 : ni;
	}
}
