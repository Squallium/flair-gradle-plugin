package _appId_.view
{
	import _appId_.actors.STARLING_MAIN;
	import _appId_.theme.Theme;
	import _appId_.theme.dpiBucketID;
	import _appId_.view.home.HomeScreen;

	import feathers.controls.ScreenNavigatorItem;

	import flash.filesystem.File;
	import flash.system.Capabilities;

	/**
	 * @author SamYStudiO ( contact@samystudio.net )
	 */
	public class StarlingMain extends AAssetsNavigatorScreen
	{
		/**
		 *
		 */
		public function StarlingMain()
		{
			super();

			STARLING_MAIN = this;
			_screenID = EnumScreen.MAIN;
			new Theme();
		}

		/**
		 * @inheritDoc
		 */
		protected override function _initialize() : void
		{
			super._initialize();

			addScreen( EnumScreen.HOME , new ScreenNavigatorItem( HomeScreen ) );
		}

		/**
		 * @inheritDoc
		 */
		protected override function _draw() : void
		{
			super._draw();

			if( isInvalid( INVALIDATION_FLAG_SIZE ) )
			{

			}
		}

		/**
		 * @inheritDoc
		 */
		protected override function _addAssets() : void
		{
			_assets.enqueue( File.applicationDirectory.resolvePath( "resources/drawable-" + dpiBucketID ) );

			var localeStrings : File = File.applicationDirectory.resolvePath( "resources/values-" + Capabilities.language.toLowerCase() );
			if( !localeStrings.exists ) localeStrings = File.applicationDirectory.resolvePath( "resources/values-" + Capabilities.language.toLowerCase().split( "-" )[ 0 ] );
			if( !localeStrings.exists ) localeStrings = File.applicationDirectory.resolvePath( "resources/values" );

			_assets.enqueue( localeStrings );
		}
	}
}
