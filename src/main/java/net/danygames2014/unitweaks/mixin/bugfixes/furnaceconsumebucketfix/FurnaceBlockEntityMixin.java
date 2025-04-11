package net.danygames2014.unitweaks.mixin.bugfixes.furnaceconsumebucketfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FurnaceBlockEntity.class, priority = 1100)
public class FurnaceBlockEntityMixin {
    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/item/ItemStack;count:I"
            ),
            require = 0
    )
    public void iNotHasABucket(ItemStack stack, int value, Operation<Void> original) {
        if (UniTweaks.BUGFIXES_CONFIG.furnaceConsumeBucketFix) {
            if (stack.itemId == Item.LAVA_BUCKET.id) {
                stack.itemId = Item.BUCKET.id;
                stack.count = 1;
            } else {
                stack.count = value;
            }
        } else {
            original.call(stack, value);
        }
    }
}
