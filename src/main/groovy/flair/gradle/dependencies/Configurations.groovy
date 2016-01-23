package flair.gradle.dependencies

/**
 * @author SamYStudiO ( contact@samystudio.net )
 */
enum Configurations {
	COMPILE( "compile" , "libs_as" ) ,
	LIBRARY_COMPILE( "swcCompile" , [ dir: "libs_swc" , include: "**/*.swc" ] ) ,
	NATIVE_COMPILE( "nativeCompile" , [ dir: "libs_ane" , include: "**/*.ane" ] ) ,
	PACKAGE( "package" ) ,

	IOS_COMPILE( "iosCompile" ) ,
	IOS_LIBRARY_COMPILE( "iosLibraryCompile" ) ,
	IOS_NATIVE_COMPILE( "iosNativeCompile" ) ,
	IOS_PACKAGE( "package" ) ,

	ANDROID_COMPILE( "androidCompile" ) ,
	ANDROID_LIBRARY_COMPILE( "androidLibraryCompile" ) ,
	ANDROID_NATIVE_COMPILE( "androidNativeCompile" ) ,
	ANDROID_PACKAGE( "package" ) ,

	DESKTOP_COMPILE( "desktopCompile" ) ,
	DESKTOP_LIBRARY_COMPILE( "desktopLibraryCompile" ) ,
	DESKTOP_NATIVE_COMPILE( "desktopNativeCompile" ) ,
	DESKTOP_PACKAGE( "package" ) ,

	public static final List<Configurations> DEFAULTS = [ COMPILE ,
														  LIBRARY_COMPILE ,
														  NATIVE_COMPILE ,
														  PACKAGE ]

	private String name

	private String[] files

	private Map<String , String> fileTree

	Configurations( String name )
	{
		this.name = name
	}

	Configurations( String name , String... files )
	{
		this.name = name
		this.files = files
	}

	Configurations( String name , Map<String , String> fileTree )
	{
		this.name = name
		this.fileTree = fileTree
	}

	public String getName()
	{
		return name
	}

	public String[] getFiles()
	{
		return files
	}

	public Map<String , String> getFileTree()
	{
		return fileTree
	}
}