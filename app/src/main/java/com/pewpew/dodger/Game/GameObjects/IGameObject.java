package com.pewpew.dodger.Game.GameObjects;

import com.pewpew.dodger.MainView.FrameDrawer.IFrame;
import com.pewpew.dodger.ObjectPool.IPoolableObject;

/**
 * Created by hakiyama on 9/9/15.
 */
public interface IGameObject extends IPoolableObject {

    IGameObject withPos(float xPos, float yPos);
    IGameObject withSize(float xSize, float ySize);
    IGameObject withFrame(IFrame frame);

    IGameObject withPosIncrement(float xShift, float yShift);

    IFrame getFrame();
    float getXSize();
    float getYSize();
    float getXPos();
    float getYPos();
}
