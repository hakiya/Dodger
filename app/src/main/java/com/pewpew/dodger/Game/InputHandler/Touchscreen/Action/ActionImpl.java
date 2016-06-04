package com.pewpew.dodger.Game.InputHandler.Touchscreen.Action;

import com.pewpew.dodger.ObjectPool.IObjectPool;

/**
 * Created by hakiyama on 8/30/15.
 */
public class ActionImpl implements IAction {

    private float x;
    private float y;
    private ActionEvent actionEvent;
    private IObjectPool objectPool;

    public ActionImpl() {
        withClean();
    }

    @Override
    public IAction withClean() {
        return withAction(0.0f, 0.0f, ActionEvent.down);
    }

    @Override
    public void setObjectPool(IObjectPool objectPool) {
        this.objectPool = objectPool;
    }

    @Override
    public void free() {
        objectPool.freeObject(this);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public ActionEvent getAction() {
        return actionEvent;
    }

    @Override
    public IAction withAction(float x, float y, ActionEvent actionEvent) {
        this.x = x;
        this.y = y;
        this.actionEvent = actionEvent;
        return this;
    }
}
