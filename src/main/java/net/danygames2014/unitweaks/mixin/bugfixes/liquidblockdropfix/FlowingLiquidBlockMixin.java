package net.danygames2014.unitweaks.mixin.bugfixes.liquidblockdropfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingLiquidBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(FlowingLiquidBlock.class)
public class FlowingLiquidBlockMixin {
  /**
   * Fixes liquid flowing down from above not dropping items such as torches and rails when broken.
   */
  @Inject(
    method = "onTick",
    at = @At(
      value = "INVOKE",
      // target both calls where the liquid tries to spread downwards
      target = "Lnet/minecraft/world/World;setBlock(IIIII)Z"
    )
  )
  public void preventLiquidBreakingBlocksWithoutDrops(
    World world,
    int x,
    int y,
    int z,
    Random random,
    CallbackInfo ci
  ) {
    if (!UniTweaks.BUGFIXES_CONFIG.liquidBlockDropFix) {
      return;
    }

    var id = world.getBlockId(x, y - 1, z);
    if (id == 0) return;

    var block = Block.BLOCKS[id];
    block.dropStacks(world, x, y - 1, z, world.getBlockMeta(x, y - 1, z));
  }
}
