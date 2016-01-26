package flair.gradle.tasks.variantFactories

import flair.gradle.tasks.Tasks
import flair.gradle.tasks.process.ProcessAssets
import flair.gradle.variants.Variant
import org.gradle.api.Project

/**
 * @author SamYStudiO ( contact@samystudio.net )
 */
class ProcessAssetsTaskFactory implements IVariantTaskFactory<ProcessAssets>
{
	public ProcessAssets create( Project project , Variant variant )
	{
		String name = Tasks.PROCESS_ASSETS.name + variant.getNameWithType( Variant.NamingTypes.CAPITALIZE )

		ProcessAssets t = project.tasks.findByName( name ) as ProcessAssets

		if( !t ) t = project.tasks.create( name , ProcessAssets )

		t.group = Tasks.PROCESS_ASSETS.group.name
		t.variant = variant

		return t
	}
}
