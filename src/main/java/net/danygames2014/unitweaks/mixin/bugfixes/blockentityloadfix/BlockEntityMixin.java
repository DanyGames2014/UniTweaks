package net.danygames2014.unitweaks.mixin.bugfixes.blockentityloadfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    @WrapOperation(method = "createFromNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntity;readNbt(Lnet/minecraft/nbt/NbtCompound;)V"))
    private static void aVoid(BlockEntity instance, NbtCompound nbtCompound, Operation<Void> original){
        try {
            original.call(instance, nbtCompound);
        } catch (Exception e) {
            System.err.println("Error while loading block entity");
            e.printStackTrace();
        }
    }
}
