package flair.gradle.tasks.variantFactories

import flair.gradle.tasks.TaskDefinition
import flair.gradle.tasks.process.ProcessAsLibraries
import flair.gradle.variants.Variant
import org.gradle.api.Project

/**
 * @author SamYStudiO ( contact@samystudio.net )
 */
class ProcessAsLibrariesTaskFactory implements IVariantTaskFactory<ProcessAsLibraries>
{
	public ProcessAsLibraries create( Project project , Variant variant )
	{
		String name = TaskDefinition.PROCESS_AS_LIBRARIES.name + variant.getNameWithType( Variant.NamingType.CAPITALIZE )

		ProcessAsLibraries t = project.tasks.findByName( name ) as ProcessAsLibraries

		if( !t ) t = project.tasks.create( name , ProcessAsLibraries )

		t.group = TaskDefinition.PROCESS_AS_LIBRARIES.group.name
		t.variant = variant

		return t
	}
}
