package com.flair.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.TaskAction

/**
 * @author SamYStudiO on 08/11/2015.
 */
public class Scaffold extends DefaultTask
{
	public Scaffold()
	{
		group = "scaffold"
		description = ""
	}

	@TaskAction
	public void generateProject()
	{
		String appId = project.flair.appId
		String appName = project.flair.appName
		String moduleName = project.flair.moduleName

		appName = appName == "" ? project.name : appName

		if( appId.isEmpty( ) ) throw new IllegalArgumentException( "Missing appId property add\nflair {\n	appId = \"myAppid\"\n}\nto your build.gradle file." )
		if( project.file( moduleName ).exists( ) ) throw new Exception( "Scaffold already done." )

		String s = appId.replace( "." , File.separator )

		project.copy {
			from project.zipTree( getClass( ).getProtectionDomain( ).getCodeSource( ).getLocation( ).toExternalForm( ) )
			into project.getRootDir( )

			include "scaffold/**"
			exclude "**/.gitkeep"

			rename( "scaffold.iml" , "${ moduleName }.iml" )
		}

		FileTree tree = project.fileTree( "/scaffold" )

		tree.each { file ->

			if( file.isFile( ) )
			{
				String ext = file.getName( ).split( "\\." ).last( )
				String allowedExt = "xml,as,iml"

				if( allowedExt.indexOf( ext ) >= 0 )
				{
					String content = file.getText( )

					content = content.replace( "\${appId}" , appId )
							.replace( "_appId_" , appId )
							.replace( "\${projectName}" , project.getName( ) )
							.replace( "\${appName}" , appName )
							.replace( "\${moduleName}" , moduleName )

					file.write( content )
				}
			}
		}

		project.copy {
			from "scaffold/src/main/actionscript/_appId_"
			into "scaffold/src/main/actionscript/${ s }"
		}

		project.file( "scaffold" ).renameTo( moduleName )
		project.file( "app/src/main/actionscript/_appId_" ).deleteDir( )

		project.copy {
			from "${ moduleName }/libraries"
			into ".idea/libraries"
		}

		project.file( "${ moduleName }/libraries" ).deleteDir( )

		project.copy {
			from "${ moduleName }/runConfigurations"
			into ".idea/runConfigurations"
		}

		project.file( "${ moduleName }/runConfigurations" ).deleteDir( )

		project.copy {
			from "${ moduleName }/modules.xml"
			into ".idea/"
		}

		project.file( "${ moduleName }/modules.xml" ).delete( )

		project.copy {
			from "${ moduleName }/gitignore"
			into project.getRootDir( )

			rename( "gitignore" , ".gitignore" )
		}

		project.file( "${ moduleName }/gitignore" ).delete( )

		project.copy {
			from "${ moduleName }/local.properties"
			into project.getRootDir( )
		}

		project.file( "${ moduleName }/local.properties" ).delete( )
	}
}
