package com.pewpew.dodger.MainView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.pewpew.dodger.Game.IGame;
import com.pewpew.dodger.Game.InputHandler.IInputHandler;
import com.pewpew.dodger.GameEnvironment.IGameEnvironment;
import com.pewpew.dodger.MainView.FrameDrawer.MyGLSurfaceView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hakiyama on 8/29/15.
 */
public class MainViewImpl implements IMainView {

    private final Context context;
    private final WindowManager windowManager;
    private float x;
    private float y;
    private final RelativeLayout relativeLayout;
    private static float PPM = 128.0f;

    private MyGLSurfaceView glView;

    private IGame game;
    private IInputHandler inputHandler;
    private IGameEnvironment gameEnvironment;

    public MainViewImpl(Context context, WindowManager windowManager, IGameEnvironment gameEnvironment) {

        this.context = context;
        this.windowManager = windowManager;
        this.relativeLayout = new RelativeLayout(this.context);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        DisplayMetrics metrics = new DisplayMetrics();
        this.windowManager.getDefaultDisplay().getMetrics(metrics);

        Display d = this.windowManager.getDefaultDisplay();
        Point size = new Point();
        try {
            d.getSize(size);
            x = (float) size.x;
            y = (float) size.y;
        } catch (NoSuchMethodError ignore) {
            x = d.getWidth();
            y = d.getHeight();
        }

        this.gameEnvironment = gameEnvironment;
        inputHandler = gameEnvironment.getInputHandler();
        glView = new MyGLSurfaceView(context, this, this.inputHandler);

        addView(glView);
        ((Activity) context).setContentView(relativeLayout);
    }

    @Override
    public float getXSize() {
        return x;
    }

    @Override
    public float getYSize() {
        return y;
    }

    @Override
    public float screenSizeToGame(float v) {
        return v / PPM;
    }

    @Override
    public float gameSizeToScreen(float v) {
        return v * PPM;
    }

    @Override
    public void addView(View v) {
        relativeLayout.addView(v);
    }

    @Override
    public View getView() {
        return this.relativeLayout;
    }

    @Override
    public void resume() {
        glView.onResume();
    }

    @Override
    public void pause() {
        glView.onPause();
    }

    @Override
    public void draw(GL10 gl) {
        game.draw(gl);
        game.step();
    }

    @Override
    public void setGame(IGame game) {
        this.game = game;
    }

    @Override
    public IInputHandler getInputHandler() {
        return inputHandler;
    }
}
