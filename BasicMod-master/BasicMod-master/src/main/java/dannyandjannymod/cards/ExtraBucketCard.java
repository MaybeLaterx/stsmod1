package dannyandjannymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dannyandjannymod.AbstractCardEnum;
import dannyandjannymod.CustomTags;
import dannyandjannymod.powers.ExtraBucketPower;
import dannyandjannymod.util.CardInfo;

import static dannyandjannymod.BasicMod.makeID;

public class ExtraBucketCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ExtraBucketCard",
            3,
            CardType.POWER,
            CardTarget.NONE,
            CardRarity.RARE,
            AbstractCardEnum.MILKMAN_WHITE);

    public static final String ID = makeID(cardInfo.baseId);
    public static final int MAGIC = 1;



    public ExtraBucketCard() {
        super(cardInfo);
        setMagic(MAGIC);
        this.tags.add(CustomTags.BUCKET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
        //addToBot(new ApplyPowerAction(p, p, new FrailPower(p, this.magicNumber, false), this.magicNumber));

        //this.addToBot(new ApplyPowerAction(p, p, new DoubleTapPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new ExtraBucketPower(1), 1));
        //this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
        //this.addToBot(new ApplyPowerAction(p, p, new SpareBucketPower(p, 2), 2));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ExtraBucketCard();
    }
}