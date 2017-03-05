package com.mygdx.game.sprites.tileObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.LDoS;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.sprites.Hero;

public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(LDoS.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Hero mario) {
        if(mario.isBig()) {
            setCategoryFilter(LDoS.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
            LDoS.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        }
        LDoS.manager.get("audio/sounds/bump.wav", Sound.class).play();
    }

}