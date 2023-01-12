package dannyandjannymod;

import basemod.BaseMod;
import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import basemod.animations.SpriterAnimation;
import basemod.interfaces.EditCharactersSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static dannyandjannymod.BasicMod.modID;
import static org.apache.logging.log4j.core.util.Loader.initializeClass;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import dannyandjannymod.cards.*;
import dannyandjannymod.relics.BreakfastRelic;

public class MilkmanCharacter extends CustomPlayer {
    /*
    private static final String[] orbTextures = {
            modID + "Resources/images/char/mainChar/orb/layer1.png",
            modID + "Resources/images/char/mainChar/orb/layer2.png",
            modID + "Resources/images/char/mainChar/orb/layer3.png",
            modID + "Resources/images/char/mainChar/orb/layer4.png",
            modID + "Resources/images/char/mainChar/orb/layer5.png",
            modID + "Resources/images/char/mainChar/orb/layer6.png",
            modID + "Resources/images/char/mainChar/orb/layer1d.png",
            modID + "Resources/images/char/mainChar/orb/layer2d.png",
            modID + "Resources/images/char/mainChar/orb/layer3d.png",
            modID + "Resources/images/char/mainChar/orb/layer4d.png",
            modID + "Resources/images/char/mainChar/orb/layer5d.png",};
     */
    private static final String[] orbTextures = { "dannyandjannymod/character/char_button.png" };
    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String MY_CHARACTER_SHOULDER_2 = "dannyandjannymod/character/char_button.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "dannyandjannymod/character/char_button.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "dannyandjannymod/character/char_button.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "dannyandjannymod/character/MilkmanTest.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "dannyandjannymod/character/MilkmanTest.json"; // spine animation json

    public MilkmanCharacter (String name) {

        super(name,
                MilkmanClassEnum.MILKMAN_WHITE,
                new CustomEnergyOrb(orbTextures,
                        "dannyandjannymod/character/char_button.png",
                        null),
                //new SpriterAnimation(modID + "Resources/images/char/mainChar/static.scml"));
                new SpineAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1f));

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        //loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

        AnimationState.TrackEntry e = this.state.setAnimation(0, "IDLE", true);
        //e.setTime(e.getEndTime() * MathUtils.random());
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Shiv");
        retVal.add(new MilkmanStrikeCard().cardID);
        retVal.add(new MilkmanStrikeCard().cardID);
        retVal.add(new MilkmanStrikeCard().cardID);
        retVal.add(new MilkmanStrikeCard().cardID);
        retVal.add(new MilkmanDefendCard().cardID);
        retVal.add(new MilkmanDefendCard().cardID);
        retVal.add(new MilkmanDefendCard().cardID);
        retVal.add(new MilkmanDefendCard().cardID);
        retVal.add(new StrongerBonesCard().cardID);
        retVal.add(new OneTwoPunchCard().cardID);

        /*
        retVal.add("MilkmanStrikeCard");
        retVal.add("MilkmanStrikeCard");
        retVal.add("MilkmanStrikeCard");
        retVal.add("MilkmanStrikeCard");
        retVal.add("MilkmanDefendCard");
        retVal.add("MilkmanDefendCard");
        retVal.add("MilkmanDefendCard");
        retVal.add("MilkmanDefendCard");
        retVal.add("StrongerBonesCard");
        retVal.add("OneTwoPunchCard");
        */

        return retVal;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        //retVal.add("dannyandjannymod:BreakfastRelic");
        retVal.add(new BreakfastRelic().relicId);
        UnlockTracker.markRelicAsSeen(new BreakfastRelic().relicId);
        return retVal;
    }

    public static final int STARTING_HP = 70;
    public static final int MAX_HP = 70;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;
    public static final int ORB_SLOTS = 0;

    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("The Milkman", "Who's your daddy?",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "The Milkman";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.MILKMAN_WHITE;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new StrongerBonesCard();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f)); //TODO 'SOTE_SFX_Blood_1_v2'
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY"; //TODO
    }

    @Override
    public String getLocalizedCharacterName() {
        return "The Milkman";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new MilkmanCharacter(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return "NL You churn with rage...";
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.WHITE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us~ ~milky~ ~one,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }

}