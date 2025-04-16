package net.danygames2014.unitweaks.mixin.bugfixes.pickblockfix;

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
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    public HitResult crosshairTarget;

    @Shadow
    public World world;

    @ModifyVariable(
            method = "handlePickBlock",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/entity/player/ClientPlayerEntity;",
                    opcode = Opcodes.GETFIELD
            ),
            index = 1
    )
    public int pickBlockId(int pickedId) {
        if (UniTweaks.BUGFIXES_CONFIG.pickBlockFix) {
            switch (this.crosshairTarget.type) {
                case BLOCK -> {
                    return getPickBlockId(
                            pickedId,
                            world.getBlockId(this.crosshairTarget.blockX, this.crosshairTarget.blockY, this.crosshairTarget.blockZ),
                            world.getBlockMeta(this.crosshairTarget.blockX, this.crosshairTarget.blockY, this.crosshairTarget.blockZ)
                    );
                }

                case ENTITY -> {
                    return getPickEntityId(
                            pickedId,
                            this.crosshairTarget.entity
                    );
                }
            }
        }

        return pickedId;
    }

    @Unique
    private final HashMap<Integer, Integer> pickBlockLookupMap = new HashMap<>() {{
        put(Block.REDSTONE_WIRE.id, Item.REDSTONE.id);
        put(Block.REPEATER.id, Item.REPEATER.id);
        put(Block.POWERED_REPEATER.id, Item.REPEATER.id);
        put(Block.DOOR.id, Item.WOODEN_DOOR.id);
        put(Block.IRON_DOOR.id, Item.IRON_DOOR.id);
        put(Block.SIGN.id, Item.SIGN.id);
        put(Block.WALL_SIGN.id, Item.SIGN.id);
        put(Block.WHEAT.id, Item.SEEDS.id);
        put(Block.BED.id, Item.BED.id);
        put(Block.CAKE.id, Item.CAKE.id);
    }};

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
            return switch (minecart.type) {
                case 1 -> Item.CHEST_MINECART.id;
                case 2 -> Item.FURNACE_MINECART.id;
                default -> Item.MINECART.id;
            };
        }
        return pickedEntityId;
    }

}
