package apollointhehouse.betterwithweed.block.crop

import apollointhehouse.betterwithweed.BetterWithWeed
import apollointhehouse.betterwithweed.block.ModBlocks
import apollointhehouse.betterwithweed.item.ModItems
import net.minecraft.core.block.BlockCrops
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.enums.EnumDropCause
import net.minecraft.core.item.ItemStack
import net.minecraft.core.util.helper.Side
import net.minecraft.core.world.World
import turniplabs.halplibe.helper.TextureHelper
import java.util.*

class CannabisTop(key: String, id: Int): BlockCrops(key, id) {
	private val modID = BetterWithWeed.MOD_ID
	private val stages = (0..2).map {
		TextureHelper.getOrCreateBlockTexture(modID, "cannabis_top/stage${it}.png")
	}
	private val textureByStage = stages.map {
		texCoordToIndex(it[0], it[1])
	}

	init {
		setTickOnLoad(true)
		setBlockBounds(0.3125f, 0.0f, 0.3125f, 0.6875f, 1.0f, 0.6875f)
	}

	override fun updateTick(world: World, x: Int, y: Int, z: Int, rand: Random) {
	}

	override fun fertilize(world: World, x: Int, y: Int, z: Int) {
		world.setBlockMetadataWithNotify(x, y, z, 3)
		world.setBlockMetadataWithNotify(x, y - 1, z, 4)
		return
	}

	override fun canBlockStay(world: World, x: Int, y: Int, z: Int): Boolean {
		return super.canBlockStay(world, x, y, z) || world.getBlockId(x, y - 1, z) == ModBlocks.cannabisBottom.id
	}

	override fun getBlockTextureFromSideAndMetadata(side: Side, meta: Int): Int
		= textureByStage[if (meta in 0..2) meta else 3]

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
			else -> arrayOf(
				ItemStack(ModItems.cannabisSeeds, if (meta >= 3) (2..4).random() else 1)
			)
		}
	}
}
