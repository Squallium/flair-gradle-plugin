package flair.gradle.tasks.variantFactories

import flair.gradle.tasks.TaskDefinition
import flair.gradle.tasks.processes.ProcessIcons
import flair.gradle.utils.Variant
import org.gradle.api.Project

/**
 * @author SamYStudiO ( contact@samystudio.net )
 */
class ProcessIconsTaskFactory implements IVariantTaskFactory<ProcessIcons>
{
	public ProcessIcons create( Project project , Variant variant )
	{
		String name = TaskDefinition.PROCESS_ICONS.name + variant.getName( Variant.NamingType.CAPITALIZE )

		ProcessIcons t = project.tasks.findByName( name ) as ProcessIcons

		if( !t ) t = project.tasks.create( name , ProcessIcons )

		t.group = TaskDefinition.PROCESS_ICONS.group.name
		t.variant = variant

		return t
	}
}
