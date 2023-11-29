package apollointhehouse.betterwithweed

import apollointhehouse.betterwithweed.block.ModBlocks
import apollointhehouse.betterwithweed.item.ModItems
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object BetterWithWeed: ModInitializer {
	const val MOD_ID: String = "betterwithweed"
	val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        LOGGER.info("BetterWithWeed Initialised!")

		ModBlocks.register()
		ModItems.register()
    }
}
