package testMod;

import java.util.ArrayList;
import java.util.Random;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.CardStrings;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.red.PommelStrike;
import com.megacrit.cardcrawl.cards.red.TwinStrike;
import com.megacrit.cardcrawl.cards.red.WildStrike;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;

import testMod.Cards.IncrementalStrike;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.PostCreateStartingRelicsSubscriber;
import basemod.interfaces.PostCreateStartingDeckSubscriber;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;


@SpireInitializer
public class TestMod implements PostExhaustSubscriber, PostCreateStartingRelicsSubscriber, PostCreateStartingDeckSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber {
	
	public static final Logger logger = LogManager.getLogger(TestMod.class.getName());
	public static Random rand = new Random();
	
	public TestMod() {
		logger.info("subscribing to PostCreateStartingRelics, PostExhaust, and PostCreateStartingDeck WOOWOOWOOWOOWOOWOOWOOWOO");
		BaseMod.subscribe(this);
	}
	
	public static void initialize() {
		@SuppressWarnings("unused")
		TestMod mod = new TestMod();
	}
	
	@Override
	public void receivePostExhaust(AbstractCard c) {
		
	}
	
	@Override
	public void receivePostCreateStartingRelics(PlayerClass playerClass, ArrayList<String> relicsToAdd) {
		
		double rng = rand.nextDouble();
		
		switch(playerClass) {
			case IRONCLAD:
				relicsToAdd.remove("Burning Blood");
				break;
			case THE_SILENT:
				relicsToAdd.remove("Ring of the Snake");
				break;
			case DEFECT:
				relicsToAdd.remove("Cracked Core");
				break;
			case WATCHER:
				relicsToAdd.remove("PureWater");
		}
		
		if (playerClass == PlayerClass.IRONCLAD) {
			relicsToAdd.add("TestMod:StrikeBlock");
		} else {
			if (rng < 0.5) {
				relicsToAdd.add("Boot");
			} else {
				relicsToAdd.add("Akabeko");
			}
		}
	}
	
	@Override
	public void receivePostCreateStartingDeck(PlayerClass playerClass, CardGroup cardGroup) {
		if (playerClass == PlayerClass.IRONCLAD) {
			
			for (int i = 0; i < 5; i++) {
				cardGroup.removeCard("Strike_R");
			}
			
			AbstractCard pommel = new PommelStrike();
			AbstractCard twin = new TwinStrike();
			AbstractCard wild = new WildStrike();
			AbstractCard perfected = new PerfectedStrike();
			AbstractCard incremental = new IncrementalStrike();
			
			cardGroup.addToBottom(pommel);
			cardGroup.addToBottom(twin);
			cardGroup.addToBottom(wild);
			cardGroup.addToBottom(perfected);
			cardGroup.addToBottom(incremental);
			
		}
	}
	
	@Override
	public void receiveEditCards() {
		BaseMod.addCard(new testMod.Cards.IncrementalStrike());
	}
	
	@Override
	public void receiveEditRelics() {
		BaseMod.addRelic(new testMod.Relics.StrikeBlock(), RelicType.SHARED);
	}
	
	@Override
	public void receiveEditStrings() {
		BaseMod.loadCustomStringsFile(RelicStrings.class, "TestModResources/localization/eng/TestMod-Relic-Strings.json");
		BaseMod.loadCustomStringsFile(CardStrings.class, "TestModResources/localization/eng/TestMod-Card-Strings.json");
	}
}
