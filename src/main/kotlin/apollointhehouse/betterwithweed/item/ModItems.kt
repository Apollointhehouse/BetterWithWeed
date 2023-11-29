package apollointhehouse.betterwithweed.item

import apollointhehouse.betterwithweed.BetterWithWeed
import apollointhehouse.betterwithweed.Config
import apollointhehouse.betterwithweed.Config.getItemID
import apollointhehouse.betterwithweed.item.crop.CannabisSeeds
import net.minecraft.core.item.Item
import turniplabs.halplibe.helper.ItemHelper

object ModItems {
	private val cfg = Config.cfg

	val cannabisSeeds: Item = ItemHelper.createItem(
		BetterWithWeed.MOD_ID,
		CannabisSeeds("crop.cannabis.bottom", cfg.getItemID("CannabisSeeds")),
		"crop.cannabis.seeds",
		"cannabis_seeds.png"
	)

	fun register() {
	}
}
