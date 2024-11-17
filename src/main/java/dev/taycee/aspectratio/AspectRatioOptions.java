package dev.taycee.aspectratio;

import com.mojang.serialization.Codec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

public class AspectRatioOptions {

    private final SimpleOption<Integer> aspectRatio;

    public AspectRatioOptions() {
        this.aspectRatio = new SimpleOption<>("options.aspectratio.aspectratio", SimpleOption.emptyTooltip(), GameOptions::getGenericValueText,
                new SimpleOption.ValidatingIntSliderCallbacks(10, 400), Codec.DOUBLE.xmap((value) -> (int)(value * 40.0 + 70.0),
                (value) -> ((double)value - 70.0) / 40.0), 20, (value) -> MinecraftClient.getInstance().worldRenderer.scheduleTerrainUpdate());
    }

    public SimpleOption<Integer> getAspectRatio() {
        return this.aspectRatio;
    }
}
