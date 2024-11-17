package dev.taycee.aspectratio.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.taycee.aspectratio.AspectRatio;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class MixinOptionsScreen {

    @Unique private final MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/DirectionalLayoutWidget;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;",
                    ordinal = 1
            )
    )
    public void init(CallbackInfo ci, @Local(ordinal = 0) DirectionalLayoutWidget directionalLayoutWidget) {
        directionalLayoutWidget.add(AspectRatio.getInstance().getAspectRatioOptions().getAspectRatio().createWidget(mc.options));
    }
}
