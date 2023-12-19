package apollointhehouse.betterwithweed

import turniplabs.halplibe.util.ConfigUpdater
import turniplabs.halplibe.util.TomlConfigHandler
import turniplabs.halplibe.util.toml.Toml

object Config {
	private val updater = ConfigUpdater.fromProperties()
	private val properties = Toml("BetterWithWeed's TOML Config")
	val cfg: TomlConfigHandler by lazy { TomlConfigHandler(updater, BetterWithWeed.MOD_ID, properties) }

	init {
		properties.addCategory("Settings") {
			addCategory("CannabisCrop") {
				addEntry("GrowthRate", 50.0)
			}
		}

		properties.addCategory("Block IDs") {
			addEntry("CannabisCropBottom", 568)
			addEntry("CannabisCropTop", 569)
		}

		properties.addCategory("Item IDs") {
			addEntry("CannabisCropSeeds", 18_400)
			addEntry("DriedBud", 18_401)
			addEntry("FreshBud", 18_402)
		}
	}

	private fun Toml.addCategory(name: String, block: Toml.() -> Unit): Toml = this.addCategory(name).apply(block)
	fun TomlConfigHandler.getBlockID(name: String): Int = this.getInt("Block IDs.$name")
	fun TomlConfigHandler.getItemID(name: String): Int = this.getInt("Item IDs.$name")
}
