package apollointhehouse.betterwithweed.block

import apollointhehouse.betterwithweed.BetterWithWeed
import apollointhehouse.betterwithweed.Config
import apollointhehouse.betterwithweed.Config.getBlockID
import apollointhehouse.betterwithweed.block.crop.CannabisBottom
import apollointhehouse.betterwithweed.block.crop.CannabisTop
import net.minecraft.client.render.block.model.BlockModelRenderBlocks
import net.minecraft.client.sound.block.BlockSounds
import net.minecraft.core.block.Block
import net.minecraft.core.block.tag.BlockTags
import turniplabs.halplibe.helper.BlockBuilder

object ModBlocks {
	private val cfg = Config.cfg

	val cannabisTop: Block = BlockBuilder(BetterWithWeed.MOD_ID)
		.setBlockSound(BlockSounds.GRASS)
		.setBlockModel(BlockModelRenderBlocks(1))
		.setTags(BlockTags.NOT_IN_CREATIVE_MENU)
		.setTickOnLoad()
		.build(CannabisTop("crop.cannabis.top", cfg.getBlockID("CannabisCropTop")))

	val cannabisBottom: Block = BlockBuilder(BetterWithWeed.MOD_ID)
		.setBlockSound(BlockSounds.GRASS)
		.setBlockModel(BlockModelRenderBlocks(1))
		.setTags(BlockTags.NOT_IN_CREATIVE_MENU)
		.setTickOnLoad()
		.build(CannabisBottom("crop.cannabis.bottom", cfg.getBlockID("CannabisCropBottom")))

	fun register() {
	}
}
