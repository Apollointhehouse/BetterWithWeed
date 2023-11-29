package apollointhehouse.betterwithweed

import turniplabs.halplibe.util.ConfigUpdater
import turniplabs.halplibe.util.TomlConfigHandler
import turniplabs.halplibe.util.toml.Toml

object Config {
	private val updater = ConfigUpdater.fromProperties()
	private val properties = Toml("BetterWithWeed's TOML Config")
	val cfg: TomlConfigHandler by lazy { TomlConfigHandler(updater, BetterWithWeed.MOD_ID, properties) }

	init {
		properties.addCategory("Growth Rates") {
			addEntry("Cannabis", 1.0f)
		}

		properties.addCategory("Block IDs") {
			addEntry("CannabisBottom", 568)
			addEntry("CannabisTop", 569)
		}

		properties.addCategory("Item IDs") {
			addEntry("CannabisSeeds", 18_400)
		}
	}

	private fun Toml.addCategory(name: String, block: Toml.() -> Unit): Toml = this.addCategory(name).apply(block)
	fun TomlConfigHandler.getBlockID(name: String): Int = this.getInt("Block IDs.$name")
	fun TomlConfigHandler.getItemID(name: String): Int = this.getInt("Item IDs.$name")
	fun TomlConfigHandler.getGrowthRate(name: String): Float = this.getFloat("Growth Rates.$name")
}
