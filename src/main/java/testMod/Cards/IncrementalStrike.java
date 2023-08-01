package testMod.Cards;

import basemod.abstracts.CustomCard;
import testMod.Actions.IncrementAction;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class IncrementalStrike extends CustomCard{
	
	public static String ID = "TestMod:Incremental Strike";
	public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static String name = cardStrings.NAME;
	public static String img = "TestModResources/Images/IncrementalStrike.png";
	public static int cost = 1;
	public static String rawDescription = cardStrings.DESCRIPTION;
	public static CardType type = AbstractCard.CardType.ATTACK;
	public static CardColor colour = AbstractCard.CardColor.RED;
	public static CardRarity rarity = AbstractCard.CardRarity.UNCOMMON;
	public static CardTarget target = AbstractCard.CardTarget.ENEMY;
	
	public static int DAMAGE = 3;
	public static int MAG = 2;
	public static int UP_MAG = 1;
	
	public IncrementalStrike() {
		super(ID, name, img, cost, rawDescription, type, colour, rarity, target);
		
		this.misc = DAMAGE;
		this.baseDamage = this.misc;
		this.baseMagicNumber = MAG;
		this.magicNumber = this.baseMagicNumber;
		this.tags.add(CardTags.STRIKE);
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		AbstractDungeon.actionManager.addToBottom(new IncrementAction(this.uuid, this.magicNumber));
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UP_MAG);
		}
	}
	
	@Override
	public void applyPowers() {
		this.baseDamage = this.misc;
		super.applyPowers();
		initializeDescription();
	}
}
