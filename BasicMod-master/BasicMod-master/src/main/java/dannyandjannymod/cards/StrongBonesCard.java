package dannyandjannymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import dannyandjannymod.util.CardInfo;

import static dannyandjannymod.BasicMod.makeID;

public class StrongBonesCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "StrongBonesCard",
            2,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.RARE,
            CardColor.RED);

    public static final String ID = makeID(cardInfo.baseId);
    public static final int MAGIC = 1;


    public StrongBonesCard() {
        super(cardInfo);
        setMagic(MAGIC);
        setSelfRetain(false, true);
        setExhaust(true, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StrongBonesCard();
    }
}