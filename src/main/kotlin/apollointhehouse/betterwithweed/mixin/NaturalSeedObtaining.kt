package apollointhehouse.betterwithweed.mixin

import net.minecraft.core.world.generate.feature.WorldFeatureLabyrinth
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject

@Mixin(WorldFeatureLabyrinth.class ) )
class NaturalSeedObtaining {

	@Inject(at = @At("HEAD"), method = "pickCheckLootItem")

}


