package com.pewpew.dodger.Game;

import android.content.Context;

import com.pewpew.dodger.Game.GameObjects.GameObjectFactoryAndStorage;
import com.pewpew.dodger.Game.GameObjects.IGameObject;
import com.pewpew.dodger.Game.InputHandler.IInputHandler;
import com.pewpew.dodger.Game.InputHandler.Touchscreen.Action.IAction;
import com.pewpew.dodger.MainView.FrameDrawer.IFrameFactoryAndStorage;
import com.pewpew.dodger.MainView.IMainView;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hakiyama on 8/29/15.
 */

public class GameImpl implements IGame {
    private static Context context;
    private IInputHandler inputHandler;
    private IMainView mainView;
    private float xSize;
    private float ySize;
    private ArrayList<IGameObject> gameObjects;
    private GameObjectFactoryAndStorage gameObjectFactory;
    private IFrameFactoryAndStorage frameFactory;
    private int counter;

    public GameImpl(Context c, IFrameFactoryAndStorage frameFactory) {
        context = c;
        this.frameFactory = frameFactory;
        gameObjectFactory = new GameObjectFactoryAndStorage(this.frameFactory);
        gameObjects = new ArrayList<>();
        counter = 0;
    }

    @Override
    public void step() {
        IAction action = inputHandler.consumeNextAction();
        for (IGameObject gameObject : gameObjects) {
            gameObject.withPosIncrement(0.0f, -0.01f);
            gameObject.getFrame().withIncrementAngle(0.01f);
        }
        if (action != null) {
            switch (action.getAction()) {
                case down:
                    IGameObject iGameObject = gameObjectFactory.getGameObject(GameObjectFactoryAndStorage.ObjectChoice.starObject, action.getX(), action.getY(), mainView);
                    gameObjects.add(iGameObject);
                    action.free();
                    break;
                case up:
                    break;
                case move:
                    for (IGameObject gameObject : gameObjects) {
                        gameObject.withPosIncrement((action.getX() - gameObject.getXPos()) * 0.01f, (action.getY() - gameObject.getYPos()) * 0.01f);
                    }
                    action.free();
                    break;
            }
        }
        if (counter == 120) {
            if (!gameObjects.isEmpty()) {
                IGameObject gameObject2 = gameObjects.get(0);
                gameObjects.remove(gameObject2);
                gameObject2.free();
            }
            counter = 0;
        }
        counter++;
    }

    @Override
    public void start() {

    }

    @Override
    public void newGame() {

        float xPos = xSize * 0.5f;
        float yPos = ySize * 0.5f;

        IGameObject gameObject = gameObjectFactory.getGameObject(GameObjectFactoryAndStorage.ObjectChoice.starObject, xPos, yPos, this.mainView);
        gameObjects.add(gameObject);
        IGameObject gameObject2 = gameObjectFactory.getGameObject(GameObjectFactoryAndStorage.ObjectChoice.starObject, 0.0f, 0.0f, this.mainView);
        gameObjects.add(gameObject2);
    }

    @Override
    public void endGame() {
    }

    @Override
    public void pause() {
        endGame();
    }

    @Override
    public void screenChanged() {

    }

    @Override
    public IInputHandler getInputHandler() {
        return inputHandler;
    }

    @Override
    public IGame withView(IMainView mainView) {
        this.mainView = mainView;
        return this;
    }

    @Override
    public IGame withInput(IInputHandler inputHandler) {
        this.inputHandler = inputHandler;
        return this;
    }

    @Override
    public IGame withSize(float xSize, float ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        return this;
    }

    @Override
    public float getXsize() {
        throw new IllegalStateException();
    }

    @Override
    public float getYsize() {
        throw new IllegalStateException();
    }

    @Override
    public void draw(GL10 gl) {
        for (IGameObject gameObject : gameObjects) {
            gameObject.getFrame().draw(gl);
        }
    }
}