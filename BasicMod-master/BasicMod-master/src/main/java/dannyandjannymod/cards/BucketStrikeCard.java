package dannyandjannymod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dannyandjannymod.AbstractCardEnum;
import dannyandjannymod.CustomTags;
import dannyandjannymod.util.CardInfo;

import static dannyandjannymod.BasicMod.makeID;

public class BucketStrikeCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BucketStrikeCard",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            AbstractCardEnum.MILKMAN_WHITE);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int UPG_DAMAGE = 4;
    private static final int DAMAGE = 12;

    public BucketStrikeCard() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.tags.add(CustomTags.BUCKET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BucketStrikeCard();
    }
}