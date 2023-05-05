package team.teampotato.HealthNaNFix;


import net.fabricmc.api.ModInitializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class HealthNaNFixConfig implements ModInitializer {
    public static String debug = "false";
    public static final String JSON = "HealthNaNFix.json";
    @Override
    public void onInitialize() {
        loadConfig();
    }

    public static void loadConfig() {
        File configFolder = new File("config");
        if (!configFolder.exists()) {
            configFolder.mkdirs();
        }

        File configFile = new File(configFolder, JSON);
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                HealthNaNFix.I.info("Loading configuration file...");
                JsonObject config = new Gson().fromJson(reader, JsonObject.class);
                debug = config.get("debug").getAsString();
            } catch (IOException e) {
                e.printStackTrace();
                HealthNaNFix.I.error("Failed to load configuration file!" + e);
            }
        } else {
            try {
                HealthNaNFix.I.info("Generating configuration file...");
                configFile.createNewFile();
                JsonObject config = new JsonObject();
                config.addProperty("debug","false");

                try (FileWriter writer = new FileWriter(configFile)) {
                    writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(config));
                }
            } catch (IOException e) {
                e.printStackTrace();
                HealthNaNFix.I.info("Error generating configuration file!" + e);
            }
        }
    }

}
