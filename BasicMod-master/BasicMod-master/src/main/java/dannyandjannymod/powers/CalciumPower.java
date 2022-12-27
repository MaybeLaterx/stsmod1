package dannyandjannymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dannyandjannymod.relics.CheeseWheelRelic;
import sun.security.util.Debug;

public class CalciumPower extends AbstractPower {
    public static final String POWER_ID = "CalciumPower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public CalciumPower(AbstractCreature owner, int newAmount) {
        this.name = NAME;
        this.ID = "CalciumPower";
        this.owner = owner;
        this.amount = newAmount;
        this.type = PowerType.BUFF;
        this.updateDescription();
        this.loadRegion("mantra");
        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_SHACKLE", 0.05F);
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Shackled"));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }


    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof CalciumPower)
            triggerCheeseWheelRelic(power.amount);
        super.onApplyPower(power, target, source);
    }

    @Override
    public void onInitialApplication() {
        triggerCheeseWheelRelic(this.amount);
        super.onInitialApplication();
    }

    private void triggerCheeseWheelRelic(int chargeAmount) {
        Debug d = new Debug();
        d.println("Triggered! AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        if (chargeAmount > 0) {
            AbstractPlayer p = AbstractDungeon.player;
            if (p.hasRelic("dannyandjanny:CheeseWheelRelic")) {
            CheeseWheelRelic r = (CheeseWheelRelic) p.getRelic("dannyandjanny:CheeseWheelRelic");
            r.counter++;
                if (r.counter == 1) {
                    r.flash();
                    this.addToBot(new RelicAboveCreatureAction(p, r));
                    this.addToBot(new DrawCardAction(p, r.getDrawAmount()));
                } else d.println("not 1 stack");
            } else d.println("doesn't have relic");
        } else d.println("Charge too small");
        super.onGainCharge(chargeAmount);
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new CalciumComedownPower(this.owner, this.amount), this.amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "CalciumPower"));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("CalciumPower");
        //NAME = powerStrings.NAME;
        //DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        NAME = "Calcium";
        DESCRIPTIONS = new String[] {"Gain ", " Strength and Dexterity next turn, for one turn."} ;
    }
}