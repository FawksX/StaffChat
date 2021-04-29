package games.synx.staffchat.config;

import org.spongepowered.configurate.BasicConfigurationNode;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.ValueReference;
import org.spongepowered.configurate.reference.WatchServiceListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

    private static Config instance;

    private final Path pluginDirectory;
    private final Path configFile;

    private WatchServiceListener watchServiceListener;
    private ConfigurationReference<BasicConfigurationNode> base;
    private ValueReference<?, BasicConfigurationNode> config;



    public Config(Path pluginDirectory) {
        instance = this;

        this.pluginDirectory = pluginDirectory;
        this.configFile = Paths.get(this.pluginDirectory + File.separator + "config.json");

        init();
    }

    private void init() {

        try {
            this.watchServiceListener = WatchServiceListener.create();
            this.base = this.watchServiceListener.listenToConfiguration(file -> GsonConfigurationLoader.builder()
                            .defaultOptions(opts -> opts.shouldCopyDefaults(true))
                            .path(this.configFile)
                            .build(), configFile);

            this.config = this.base.referenceTo(Settings.class);
            this.base.save();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @ConfigSerializable
    public static class Settings {

    }

    public Settings getConfig() {
        return (Settings) this.config.get();
    }

    public Config getInstance() {
        return instance;
    }

}
