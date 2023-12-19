package apollointhehouse.betterwithweed.item

import apollointhehouse.betterwithweed.BetterWithWeed
import apollointhehouse.betterwithweed.Config
import apollointhehouse.betterwithweed.Config.getItemID
import apollointhehouse.betterwithweed.item.crop.CannabisSeeds
import apollointhehouse.betterwithweed.item.flower.DriedBud
import apollointhehouse.betterwithweed.item.flower.FreshBud
import net.minecraft.core.item.Item
import turniplabs.halplibe.helper.ItemHelper

object ModItems {
	private val cfg = Config.cfg

	val cannabisSeeds: Item = ItemHelper.createItem(
		BetterWithWeed.MOD_ID,
		CannabisSeeds("crop.cannabis.bottom", cfg.getItemID("CannabisCropSeeds")),
		"crop.cannabis.seeds",
		"cannabis_seeds.png"
	)

	val FreshBud: Item = ItemHelper.createItem(
		BetterWithWeed.MOD_ID,
		Item(cfg.getItemID("FreshBud")),
		"flower.FreshBud",
		"freshbud.png"
	)

	val DriedBud: Item = ItemHelper.createItem(
		BetterWithWeed.MOD_ID,
		Item(cfg.getItemID("DriedBud")),
		"flower.DriedBud",
		"driedbud.png"
	)

	fun register() {
	}
}
