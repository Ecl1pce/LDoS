package com.mygdx.game.sprites.tileObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.LDoS;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.sprites.Hero;

public class DeadZone extends InteractiveTileObject {
    public DeadZone(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(LDoS.DEATH_BIT);
    }
    @Override
    public void onHeadHit(Hero mario) {

    }
}
