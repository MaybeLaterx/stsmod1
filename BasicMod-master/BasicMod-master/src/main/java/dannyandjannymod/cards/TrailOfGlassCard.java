package dannyandjannymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import dannyandjannymod.AbstractCardEnum;
import dannyandjannymod.FemurAction;
import dannyandjannymod.TrailOfGlassAction;
import dannyandjannymod.util.CardInfo;

import static dannyandjannymod.BasicMod.makeID;

public class TrailOfGlassCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "TrailOfGlassCard",
            1,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.RARE,
            AbstractCardEnum.MILKMAN_WHITE);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public TrailOfGlassCard() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, 2), 2));

        this.addToBot(new TrailOfGlassAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new TrailOfGlassCard();
    }
}