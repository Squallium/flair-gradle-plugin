package flair.gradle.tasks

import flair.gradle.cli.Adb
import flair.gradle.cli.Adt
import flair.gradle.cli.ICli
import flair.gradle.extensions.FlairProperty
import flair.gradle.utils.CliDevicesOutputParser
import flair.gradle.utils.Platform
import flair.gradle.utils.Variant
import org.gradle.api.tasks.TaskAction

/**
 * @author SamYStudiO ( contact@samystudio.net )
 */
class Install extends VariantTask
{
	private ICli adb = new Adb( )

	private ICli adt = new Adt( )

	@Override
	public void setVariant( Variant variant )
	{
		super.variant = variant

		description = "Installs ${ variant.name } from first connect device"
	}

	public Install()
	{
		group = TaskGroup.INSTALL.name
	}

	@TaskAction
	public void install()
	{
		String path = "${ project.buildDir.path }/${ extensionManager.getFlairProperty( variant , FlairProperty.PACKAGE_FILE_NAME ) }.${ variant.platform.extension }"

		if( variant.platform == Platform.DESKTOP )
		{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream( )

			project.exec {
				executable project.file( path ).path

				ignoreExitValue = true
				standardOutput = outputStream
			}

			println( outputStream.toString( ) )
		}
		else if( variant.platform == Platform.ANDROID )
		{
			adb.addArgument( "devices" )
			String id = new CliDevicesOutputParser( ).parse( adb.execute( project , variant.platform ) )

			if( id )
			{
				adb.clearArguments( )
				adb.addArgument( "-s ${ id }" )
				adb.addArgument( "install" )
				adb.addArgument( "-r" )
				adb.addArgument( project.file( path ).path )

				adb.execute( project , variant.platform )
			}
			else println( "No device detected" )
		}
		else
		{
			String platformSdk = extensionManager.getFlairProperty( variant , FlairProperty.PACKAGE_PLATFORM_SDK )

			adt.addArgument( "-devices" )
			adt.addArgument( "-platform ios" )
			String id = new CliDevicesOutputParser( ).parse( adt.execute( project , variant.platform ) )
			id = !id && platformSdk ? "ios_simulator" : id

			if( id )
			{
				adt.clearArguments( )
				adt.addArgument( "-installApp" )
				adt.addArgument( "-platform ios" )
				if( platformSdk ) adt.addArgument( "-platformsdk ${ platformSdk }" )
				adt.addArgument( "-device ${ id }" )
				adt.addArgument( "-package" )
				adt.addArgument( project.file( path ).path )
				adt.execute( project , variant.platform )
			}
			else println( "No device detected" )
		}
	}
}
