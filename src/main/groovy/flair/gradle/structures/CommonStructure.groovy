package flair.gradle.structures

import flair.gradle.extensions.FlairProperty
import flair.gradle.extensions.IExtensionManager
import org.gradle.api.Project

/**
 * @author SamYStudiO ( contact@samystudio.net )
 */
public class CommonStructure implements IStructure
{
	@Override
	public void create( Project project , File source )
	{
		IExtensionManager extensionManager = project.flair as IExtensionManager
		String moduleName = extensionManager.getFlairProperty( FlairProperty.MODULE_NAME )

		if( project.fileTree( "${ moduleName }" ).size( ) == 0 )
		{
			String packageName = extensionManager.getFlairProperty( FlairProperty.PACKAGE_NAME )
			String s = packageName.replace( "." , "/" )

			project.copy {
				from "${ source.path }/src/main"
				into "${ moduleName }/src/main"

				exclude "**/ios/**" , "**/android/**" , "**/desktop/**" , "**/_packageName_/**" , "**/atlases/**"
			}

			project.copy {
				from "${ source.path }/libs_ane"
				into "${ moduleName }/libs_ane"
			}

			project.copy {
				from "${ source.path }/libs_as"
				into "${ moduleName }/libs_as"
			}

			project.copy {
				from "${ source.path }/libs_swc"
				into "${ moduleName }/libs_swc"
			}

			project.copy {
				from "${ source.path }/src/main/actionscript/_packageName_"
				into "${ moduleName }/src/main/actionscript/${ s }"
			}

			project.fileTree( "${ moduleName }/src/main/actionscript/${ s }" ).each { file ->

				file.write( file.text.replace( "_packageName_" , packageName ) )
			}
		}
	}
}
