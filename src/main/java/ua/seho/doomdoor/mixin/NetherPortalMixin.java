package ua.seho.doomdoor.mixin;

import net.minecraft.world.dimension.NetherPortal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherPortal.class)
public abstract class NetherPortalMixin {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void onPortalCreate(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
        cir.cancel();
    }
}

