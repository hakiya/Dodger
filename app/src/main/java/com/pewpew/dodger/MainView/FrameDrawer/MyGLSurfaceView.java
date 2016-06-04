package com.pewpew.dodger.MainView.FrameDrawer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.pewpew.dodger.Game.InputHandler.IInputHandler;
import com.pewpew.dodger.Game.InputHandler.Touchscreen.Action.ActionEvent;
import com.pewpew.dodger.MainView.IMainView;

/**
 * Created by hakiyama on 8/29/15.
 */
public class MyGLSurfaceView extends GLSurfaceView
{
    private final MyGLRenderer renderer;
    private float xSize;
    private float ySize;
    private IInputHandler inputHandler;
    private IMainView mainView;

    public MyGLSurfaceView(Context context, IMainView mainView, IInputHandler inputHandler) {
        super(context);
        this.mainView = mainView;
        this.xSize = this.mainView.getXSize();
        this.ySize = this.mainView.getYSize();
        this.inputHandler = inputHandler;
        renderer = new MyGLRenderer(context, mainView);
        setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float xPos = mainView.screenSizeToGame(e.getX());
        float yPos = mainView.screenSizeToGame(ySize - e.getY());

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                inputHandler.addAction(xPos, yPos, ActionEvent.move);
                break;
            case MotionEvent.ACTION_DOWN:
                inputHandler.addAction(xPos, yPos, ActionEvent.down);
                break;
            case MotionEvent.ACTION_UP:
                inputHandler.addAction(xPos, yPos, ActionEvent.up);
                break;
        }
        return true;
    }
}