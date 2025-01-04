package net.danygames2014.unitweaks.bugfixes.slabminingfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.event.entity.player.IsPlayerUsingEffectiveToolEvent;
import net.modificationstation.stationapi.api.event.entity.player.PlayerStrengthOnBlockEvent;

public class MiningListener {
    // Last tick value
    public int lastBlockId = 0;
    public int lastBlockMeta = 0;

    @EventListener
    public void isUsingEffectiveTool(IsPlayerUsingEffectiveToolEvent event) {
        // If Wooden Slab Mining Fix is not enabled, dont bother
        if (!UniTweaks.BUGFIXES_CONFIG.woodenSlabMiningFix) {
            return;
        }

        // If its already true, than making it more effective wont happen, the green light doesnt get any greener
        if (event.resultProvider.getAsBoolean()) {
            return;
        }

        // Get some values so the following code is more readable
        BlockPos pos = event.blockPos;
        BlockView world = event.blockView;
        int blockId = world.getBlockId(pos.x, pos.y, pos.z);
        int blockMeta = world.getBlockMeta(pos.x, pos.y, pos.z);

        // If the ID and Meta matches, true
        if(blockMeta == 2 && blockId == Block.SLAB.id){
            event.resultProvider = () -> true;
            
        // If it doesnt, it might be last breaking tick and check the last values
        } else if (blockMeta == 0 && blockId == 0) {
            // If in the last tick it matched, true
            if (lastBlockMeta == 2 && lastBlockId == Block.SLAB.id) {
                event.resultProvider = () -> true;
            }
        }

        // Store last tick values
        lastBlockId = blockId;
        lastBlockMeta = blockMeta;
    }

    @EventListener
    public void playerStrengthOnBlock(PlayerStrengthOnBlockEvent event) {
        // If Wooden Slab Mining Fix is not enabled, dont bother
        if (!UniTweaks.BUGFIXES_CONFIG.woodenSlabMiningFix) {
            return;
        }

        // Get some values so the following code is more readable
        BlockPos pos = event.blockPos;
        BlockView world = event.blockView;

        // If the ID and Meta matches, true
        if (  world.getBlockMeta(pos.x, pos.y, pos.z) == 2
           && world.getBlockId(pos.x, pos.y, pos.z) == Block.SLAB.id
           && null != event.player.inventory.getSelectedItem()
        ) {
            if(event.player.inventory.getSelectedItem().getItem() instanceof AxeItem axe){
                event.resultProvider = () -> axe.miningSpeed;
            } else if (event.player.inventory.getSelectedItem().getItem() instanceof PickaxeItem pickaxe) {
                event.resultProvider = () -> 1.0F;
            }
        }
    }
}
