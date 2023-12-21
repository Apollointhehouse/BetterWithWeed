package apollointhehouse.betterwithweed.mixin;

import apollointhehouse.betterwithweed.item.ModItems;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.generate.feature.WorldFeatureLabyrinth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(value = WorldFeatureLabyrinth.class, remap = false)
public abstract class WorldFeatureLabyrinthMixin {
	@Inject(
		method = "pickCheckLootItem",
		at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", shift = At.Shift.AFTER),
		cancellable = true
	)
	public void pickCheckLootItem(Random random, CallbackInfoReturnable<ItemStack> cir) {
		if (random.nextInt(17) == 0) {
			cir.setReturnValue(new ItemStack(ModItems.cannabisSeeds, random.nextInt(6) + 1));
		}
	}
}
