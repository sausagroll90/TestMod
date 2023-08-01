package testMod.Relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.abstracts.CustomRelic;

import com.badlogic.gdx.graphics.Texture;

public class StrikeBlock extends CustomRelic{
	public static final String ID = "TestMod:StrikeBlock";
	public static final String imgpath = "TestModResources/Images/StrikeBlock.png";
	public static final Texture TEXTURE = new Texture(imgpath);
	
	public static final int BLOCK_AMT = 2;
	
	public StrikeBlock() {
		super(ID, TEXTURE, RelicTier.COMMON, LandingSound.CLINK);
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.hasTag(AbstractCard.CardTags.STRIKE)) {
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_AMT));
		}
	}
	
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new StrikeBlock();
	}
}
