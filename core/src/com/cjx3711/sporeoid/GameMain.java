package com.cjx3711.sporeoid;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.cjx3711.sporeoid.managers.GameInputProcessor;
import com.cjx3711.sporeoid.managers.GameScene;
import com.cjx3711.sporeoid.managers.GameSceneManager;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.TimeKeeper;

public class GameMain extends ApplicationAdapter {

	private String message = "Touch something already!";

    GameInputProcessor inputProcessor;

    private GameSceneManager sceneManager;
	private GameScene mainScene;
	@Override
	public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

		ScalingUtil.init(Gdx.graphics);

        inputProcessor = new GameInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

        TimeKeeper.init();
		mainScene = new GameScene();

        sceneManager = GameSceneManager.getInstance();
        sceneManager.startGame();
	}

	@Override
	public void render () {
        sceneManager.calculate();
        sceneManager.render();
	}
	
	@Override
	public void dispose () {
        sceneManager.dispose();
	}
}
