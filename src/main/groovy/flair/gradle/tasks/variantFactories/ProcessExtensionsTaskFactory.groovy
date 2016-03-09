package flair.gradle.tasks.variantFactories

import flair.gradle.tasks.TaskDefinition
import flair.gradle.tasks.process.ProcessExtensions
import flair.gradle.variants.Variant
import org.gradle.api.Project

/**
 * @author SamYStudiO ( contact@samystudio.net )
 */
class ProcessExtensionsTaskFactory implements IVariantTaskFactory<ProcessExtensions>
{
	public ProcessExtensions create( Project project , Variant variant )
	{
		String name = TaskDefinition.PROCESS_EXTENSIONS.name + variant.getNameWithType( Variant.NamingType.CAPITALIZE )

		ProcessExtensions t = project.tasks.findByName( name ) as ProcessExtensions

		if( !t ) t = project.tasks.create( name , ProcessExtensions )

		t.group = TaskDefinition.PROCESS_EXTENSIONS.group.name
		t.variant = variant

		return t
	}
}
