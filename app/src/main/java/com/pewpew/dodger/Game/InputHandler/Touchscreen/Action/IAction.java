package com.pewpew.dodger.Game.InputHandler.Touchscreen.Action;

import com.pewpew.dodger.ObjectPool.IObjectPool;
import com.pewpew.dodger.ObjectPool.IPoolableObject;

/**
 * Created by hakiyama on 8/30/15.
 */
public interface IAction extends IPoolableObject{

    void setObjectPool(IObjectPool objectPool);

    float getX();
    float getY();
    ActionEvent getAction();

    IAction withAction(float x, float y, ActionEvent actionEvent);
}
