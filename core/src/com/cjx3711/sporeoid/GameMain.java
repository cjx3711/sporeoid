package com.cjx3711.sporeoid;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMain extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	private String message = "Touch something already!";
	Texture img;
	private int w,h;


	private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
    private int maxTouches = 6;

    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    private long previousMills = 0;
    private long currentMills = 0;

	@Override
	public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		Gdx.input.setInputProcessor(this);
		for(int i = 0; i < 5; i++){
			touches.put(i, new TouchInfo());
		}
        previousMills = currentMills = TimeUtils.millis();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);

		message = "";
		for(int i = 0; i < 5; i++){
			if(touches.get(i).isTouched())
				message += "Finger:" + Integer.toString(i) + "touch at:" +
						Float.toString(touches.get(i).getTouchX()) +
						"," +
						Float.toString(touches.get(i).getTouchY()) +
						"\n";

		}

//        Gdx.app.debug("GameMain", message);

		batch.end();

		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.circle(50, 50, 32);
		shapeRenderer.end();

        currentMills = TimeUtils.millis();
        float deltaMills = currentMills - previousMills;
        previousMills = currentMills;
        for ( GameObject gameObject : gameObjects ) {
            gameObject.calculate(deltaMills / 1000.0f);
        }

        for ( GameObject gameObject : gameObjects ) {
            gameObject.render(shapeRenderer);
        }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(pointer < maxTouches){
            touches.get(pointer).start(screenX, screenY);
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(pointer < maxTouches){
            TouchInfo touch = touches.get(pointer);
            float startX = touch.getStartX();
            float startY = h - touch.getStartY();
            float deltaX = touch.getDeltaX();
            float deltaY = -touch.getDeltaY();
            float time = touch.getElapsedTime();
            float distance = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            float speed = distance / (time / 1000);
            Gdx.app.debug("GameMain", "Distance: " + distance + " speed: " + speed + " px/s");
			touches.get(pointer).end();
            if ( speed > 10 ) {
                GameObjectDynamic gameObject = new GameObjectDynamic(startX, startY);
                gameObject.setVelocity(deltaX, deltaY);
                gameObjects.add(gameObject);
            }
		}
		return true;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(pointer < maxTouches){
            touches.get(pointer).update(screenX, screenY);
        }
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
