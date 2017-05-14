package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.utils.TimeKeeper;

/**
 * Manages the scenes of the games
 */

public class GameSceneManager {
    private static GameSceneManager singleton = new GameSceneManager();
    public static GameSceneManager getInstance() {
        return singleton;
    }

    private GameScene gameScene;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture img;


    private GameSceneManager() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        img = new Texture("badlogic.jpg");

    }

    public void startGame() {
        gameScene = new GameScene();
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void calculate() {

        TimeKeeper.calculate();

        gameScene.calculate(TimeKeeper.getDelta());

    }

    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.draw(img, 0, 0);
//
////		message = "";
////		for(int i = 0; i < 5; i++){
////			if(touches.get(i).isTouched())
////				message += "Finger:" + Integer.toString(i) + "touch at:" +
////						Float.toString(touches.get(i).getTouchX()) +
////						"," +
////						Float.toString(touches.get(i).getTouchY()) +
////						"\n";
////
////		}
////
//////        Gdx.app.debug("GameMain", message);
//
//        batch.end();
        gameScene.render(shapeRenderer);
    }

    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
