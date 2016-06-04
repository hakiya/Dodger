package com.pewpew.dodger.Game;

import com.pewpew.dodger.Game.InputHandler.IInputHandler;
import com.pewpew.dodger.MainView.IMainView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hakiyama on 8/29/15.
 */
public interface IGame {
    void start();
    void newGame();
    void endGame();
    void pause();
    void screenChanged();
    IInputHandler getInputHandler();

    IGame withView(IMainView mainView);
    IGame withInput(IInputHandler inputHandler);
    IGame withSize(float xSize, float ySize);

    float getXsize();
    float getYsize();
    void step();

    void draw(GL10 gl);
}
