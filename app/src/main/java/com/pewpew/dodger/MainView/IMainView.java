package com.pewpew.dodger.MainView;

import android.view.View;

import com.pewpew.dodger.Game.IGame;
import com.pewpew.dodger.Game.InputHandler.IInputHandler;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hakiyama on 8/29/15.
 */
public interface IMainView {

    float getXSize();
    float getYSize();

    float screenSizeToGame(float v);
    float gameSizeToScreen(float v);

    void addView(View v);
    View getView();

    void resume();

    void pause();

    void draw(GL10 gl);
    void setGame(IGame game);

    IInputHandler getInputHandler();
}
