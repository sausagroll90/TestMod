package testMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class IncrementAction extends AbstractGameAction {
	
	private UUID uuid;
	private int miscIncrease;
	
	public IncrementAction(UUID uuid, int miscIncrease) {
		
		this.uuid = uuid;
		this.miscIncrease = miscIncrease;
		
	}
	
	public void update() {
		
		for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
			
			if (!c.uuid.equals(this.uuid)) {
				continue;
			}
			
			c.misc += this.miscIncrease;
			c.applyPowers();
			c.baseDamage = c.misc;
			c.isDamageModified = false;
			
		}
		
		for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
			c.misc += this.miscIncrease;
			c.applyPowers();
			c.baseDamage = c.misc;
		}
		
		this.isDone = true;
	}
}
