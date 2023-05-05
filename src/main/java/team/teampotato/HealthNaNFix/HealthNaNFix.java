package team.teampotato.HealthNaNFix;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.TranslatableText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class HealthNaNFix implements ModInitializer {
	public static final Logger I = LoggerFactory.getLogger("HealthNaNFix");

	@Override
	public void onInitialize() {

		I.info("Hello! HealthNaNFix Mod!");
		if(FabricLoader.getInstance().isModLoaded("infinity")) {
			I.info("INFINITY!!!");
		}
		LocalDate today = LocalDate.now();
		if (today.getMonthValue() == 4 && today.getDayOfMonth() == 1) {
			I.info("Nanomachine son!");
		}
		HealthNaNFixConfig.loadConfig();
	}
}
