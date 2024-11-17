package dev.taycee.aspectratio.mixin;

import dev.taycee.aspectratio.AspectRatio;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Shadow private float zoom;

    @Shadow private float zoomX;

    @Shadow private float zoomY;

    @Shadow public abstract float getFarPlaneDistance();

    @Inject(
            method = "getBasicProjectionMatrix",
            at = @At("HEAD"),
            cancellable = true
    )
    public void getBasicProjectionMatrix(float fovDegrees, CallbackInfoReturnable<Matrix4f> cir) {
        cir.cancel();
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.peek().getPositionMatrix().identity();
        if (this.zoom != 1.0F) {
            matrixStack.translate(zoomX, -zoomY, 0.0F);
            matrixStack.scale(this.zoom, this.zoom, 1.0F);
        }

        float aspectRatio = (float) AspectRatio.getInstance().getAspectRatioOptions().getAspectRatio().getValue() / 10;

        matrixStack.peek().getPositionMatrix().mul((new Matrix4f()).setPerspective((float)(fovDegrees * 0.01745329238474369), aspectRatio, 0.05F, this.getFarPlaneDistance()));

        cir.setReturnValue(matrixStack.peek().getPositionMatrix());
    }
}
