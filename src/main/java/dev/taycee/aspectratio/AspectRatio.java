package dev.taycee.aspectratio;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.option.SimpleOption;

public class AspectRatio implements ClientModInitializer {

    private final AspectRatioOptions aspectRatioOptions;
    private static AspectRatio instance;

    public AspectRatio() {
        this.aspectRatioOptions = new AspectRatioOptions();
    }

    @Override
    public void onInitializeClient() {
        instance = this;
    }

    public static AspectRatio getInstance() {
        return instance;
    }

    public AspectRatioOptions getAspectRatioOptions() {
        return aspectRatioOptions;
    }
}
