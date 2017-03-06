package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.LDoS;
import com.mygdx.game.sprites.enemies.Enemy;
import com.mygdx.game.sprites.items.Item;
import com.mygdx.game.sprites.Hero;
import com.mygdx.game.sprites.other.FireBall;
import com.mygdx.game.sprites.tileObjects.InteractiveTileObject;


public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case LDoS.MARIO_HEAD_BIT | LDoS.BRICK_BIT:
            case LDoS.MARIO_HEAD_BIT | LDoS.COIN_BIT:
                if(fixA.getFilterData().categoryBits == LDoS.MARIO_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Hero) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Hero) fixB.getUserData());
                break;
            case LDoS.ENEMY_HEAD_BIT | LDoS.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == LDoS.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead((Hero) fixB.getUserData());
                else
                    ((Enemy)fixB.getUserData()).hitOnHead((Hero) fixA.getUserData());
                break;
            case LDoS.ENEMY_BIT | LDoS.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == LDoS.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case LDoS.MARIO_BIT | LDoS.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == LDoS.MARIO_BIT)
                    ((Hero) fixA.getUserData()).hit((Enemy)fixB.getUserData());
                else
                    ((Hero) fixB.getUserData()).hit((Enemy)fixA.getUserData());
                break;
            case LDoS.ENEMY_BIT | LDoS.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
                break;
            case LDoS.ITEM_BIT | LDoS.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == LDoS.ITEM_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case LDoS.ITEM_BIT | LDoS.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == LDoS.ITEM_BIT)
                    ((Item)fixA.getUserData()).use((Hero) fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Hero) fixA.getUserData());
                break;
            case LDoS.FIREBALL_BIT | LDoS.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == LDoS.FIREBALL_BIT)
                    ((FireBall)fixA.getUserData()).setToDestroy();
                else
                    ((FireBall)fixB.getUserData()).setToDestroy();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}