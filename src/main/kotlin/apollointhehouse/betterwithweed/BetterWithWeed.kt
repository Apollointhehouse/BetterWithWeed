package apollointhehouse.betterwithweed

import apollointhehouse.betterwithweed.block.ModBlocks
import apollointhehouse.betterwithweed.item.ModItems
import net.fabricmc.api.ModInitializer
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import turniplabs.halplibe.helper.RecipeHelper

object BetterWithWeed: ModInitializer {
	const val MOD_ID: String = "betterwithweed"
	val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        LOGGER.info("BetterWithWeed Initialised!")

		ModBlocks.register()
		ModItems.register()

		RecipeHelper.craftingManager.addShapelessRecipe(ItemStack(ModItems.Joint), ModItems.DriedBud, Item.paper, Item.paper)
    }
}
