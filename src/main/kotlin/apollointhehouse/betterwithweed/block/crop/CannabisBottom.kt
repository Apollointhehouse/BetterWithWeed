package apollointhehouse.betterwithweed.block.crop

import apollointhehouse.betterwithweed.BetterWithWeed
import apollointhehouse.betterwithweed.Config
import apollointhehouse.betterwithweed.Config.getGrowthRate
import apollointhehouse.betterwithweed.block.ModBlocks
import apollointhehouse.betterwithweed.item.ModItems
import net.minecraft.core.block.BlockCrops
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.enums.EnumDropCause
import net.minecraft.core.item.ItemStack
import net.minecraft.core.util.helper.Side
import net.minecraft.core.world.World
import net.minecraft.core.world.season.SeasonFall
import net.minecraft.core.world.season.SeasonSpring
import net.minecraft.core.world.season.SeasonSummer
import net.minecraft.core.world.season.SeasonWinter
import turniplabs.halplibe.helper.TextureHelper
import java.util.*

class CannabisBottom(key: String, id: Int): BlockCrops(key, id) {
	private val modID = BetterWithWeed.MOD_ID
	private val cannabisTopID = ModBlocks.cannabisTop.id
	private val stages = (0..4).map {
		TextureHelper.getOrCreateBlockTexture(modID, "cannabis_stage${it}_bottom.png")
	}
	private val textureByStage = stages.map {
		texCoordToIndex(it[0], it[1])
	}

	init {
		setTickOnLoad(true)
		setBlockBounds(0.3125f, 0.0f, 0.3125f, 0.6875f, 1.0f, 0.6875f)
	}

	private fun getGrowthRate(world: World): Float {
		var f = Config.cfg.getGrowthRate("Cannabis")
		val currentSeason = world.seasonManager.currentSeason ?: return f

		val growthFactor = when (currentSeason) {
			is SeasonSummer -> 1.0F
			is SeasonSpring -> 0.75F
			is SeasonWinter -> 0.5f
			is SeasonFall -> 1.5f
			else -> 1F
		}
		f *= growthFactor

		return f
	}

	override fun updateTick(world: World, x: Int, y: Int, z: Int, rand: Random) {
		val growthRate = getGrowthRate(world)
		val blockAbove = world.getBlockId(x, y + 1, z)
		val meta = world.getBlockMetadata(x, y, z)
		val canGrow = blockAbove == 0 || blockAbove == cannabisTopID && world.getBlockLightValue(x, y + 1, z) >= 9

		if (meta >= 4) return
		if (!canGrow) return

		if (rand.nextInt((100.0f / growthRate).toInt()) != 0) return

		world.setBlockAndMetadataWithNotify(x, y, z, id, meta + 1)
		if (meta + 1 >= 1) world.setBlockAndMetadataWithNotify(x, y + 1, z, cannabisTopID, meta)
	}

	override fun canBlockStay(world: World, x: Int, y: Int, z: Int): Boolean {
		val meta = world.getBlockMetadata(x, y, z)
		val blockAbove = world.getBlockId(x, y + 1, z)
		return super.canBlockStay(world, x, y, z) && blockAbove == ModBlocks.cannabisTop.id || meta < 1
	}

	override fun fertilize(world: World, x: Int, y: Int, z: Int) {
		val blockAbove = world.getBlockId(x, y + 1, z)
		val canGrow = blockAbove == 0 || blockAbove == cannabisTopID && world.getBlockLightValue(x, y + 1, z) >= 9
		if (!canGrow) return

		world.setBlockAndMetadataWithNotify(x, y, z, id, 4)
		world.setBlockAndMetadataWithNotify(x, y, z, cannabisTopID, 3)
		return
	}

	override fun getBlockTextureFromSideAndMetadata(side: Side, meta: Int): Int
		= textureByStage[if (meta in 0..4) meta else 4]

	override fun getBreakResult(
		world: World,
		dropCause: EnumDropCause,
		x: Int,
		y: Int,
		z: Int,
		meta: Int,
		tileEntity: TileEntity?
	): Array<ItemStack> {
		return when (dropCause) {
			EnumDropCause.PICK_BLOCK -> arrayOf(ItemStack(ModItems.cannabisSeeds, 1))
			else -> arrayOf()
		}
	}
}
