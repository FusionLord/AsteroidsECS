package net.infinitycorp.asteroidsecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.infinitycorp.asteroidsecs.components.*;
import net.infinitycorp.asteroidsecs.systems.*;

public class AsteroidsECS extends ApplicationAdapter {
    Engine engine;

    @Override
    public void create() {

        engine = new Engine();

        engine.addSystem(new RenderSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new ScreenWrapSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new ShootingSystem(engine));
        engine.addSystem(new LifetimeSystem(engine));
        engine.addSystem(new AsteroidSpawningSystem(engine));
        engine.addSystem(new BulletCollisionSystem(engine));

        Texture shipTexture = new Texture("ship.png");
        Texture bulletTexture = new Texture("bullet.png");

        Entity ship = new Entity();
        ship.add(new VisualComponent(new TextureRegion(shipTexture)));
        ship.add(new PositionComponent(250, 250));
        ship.add(new VelocityComponent(0,0));
        ship.add(new MaxSpeedComponent(300));
        ship.add(new ScreenWrapComponent());
        ship.add(new RotationComponent(125, 150));
        ship.add(new PlayerControlComponent());
        ship.add(new ShootingComponent(true, 0.1f));
        engine.addEntity(ship);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(delta);
    }

    @Override
    public void dispose() {
    }
}
