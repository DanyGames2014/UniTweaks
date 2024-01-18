package net.danygames2014.unitweaks.mixin.bugfixes.pickblockfix;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Util;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    public HitResult field_2823;

    @Shadow
    public World world;

    @ModifyVariable(
            method = "method_2103",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/entity/player/ClientPlayerEntity;",
                    opcode = Opcodes.GETFIELD
            ),
            index = 1
    )
    public int pickBlockId(int pickedId) {
        if(UniTweaks.BUGFIXES_CONFIG.pickBlockFix){
            switch (this.field_2823.type) {
                case BLOCK -> {
                    return getPickBlockId(
                            pickedId,
                            world.getBlockId(this.field_2823.blockX, this.field_2823.blockY, this.field_2823.blockZ),
                            world.getBlockMeta(this.field_2823.blockX, this.field_2823.blockY, this.field_2823.blockZ)
                    );
                }

                case ENTITY -> {
                    return getPickEntityId(
                            pickedId,
                            this.field_2823.entity
                    );
                }
            }
        }

        return pickedId;
    }

    @Unique
    private final Int2IntMap pickBlockLookupMap = Util.make(new Int2IntOpenHashMap(), map -> {
        map.put(Block.REDSTONE_WIRE.id, Item.REDSTONE.id);
        map.put(Block.REPEATER.id, Item.REPEATER.id);
        map.put(Block.POWERED_REPEATER.id, Item.REPEATER.id);
        map.put(Block.DOOR.id, Item.WOODEN_DOOR.id);
        map.put(Block.IRON_DOOR.id, Item.IRON_DOOR.id);
        map.put(Block.SIGN.id, Item.SIGN.id);
        map.put(Block.WALL_SIGN.id, Item.SIGN.id);
        map.put(Block.WHEAT.id, Item.SEEDS.id);
        map.put(Block.BED.id, Item.BED.id);
        map.put(Block.CAKE.id, Item.CAKE.id);
    });

    @Unique
    public int getPickBlockId(int pickedBlockId, int blockId, int blockMeta) {
        if (blockId == Block.PISTON_HEAD.id) {
            if (blockMeta < 8) {
                return Block.PISTON.id;
            } else {
                return Block.STICKY_PISTON.id;
            }
        }

        return pickBlockLookupMap.getOrDefault(blockId, pickedBlockId);
    }

    @Unique
    public int getPickEntityId(int pickedEntityId, Entity pickedEntity) {
        if (pickedEntity instanceof PaintingEntity) {
            return Item.PAINTING.id;
        }

        if (pickedEntity instanceof BoatEntity) {
            return Item.BOAT.id;
        }

        if (pickedEntity instanceof MinecartEntity minecart) {
            return switch (minecart.field_2275) {
                case 1 -> Item.CHEST_MINECART.id;
                case 2 -> Item.FURNACE_MINECART.id;
                default -> Item.MINECART.id;
            };
        }
        return pickedEntityId;
    }

}
