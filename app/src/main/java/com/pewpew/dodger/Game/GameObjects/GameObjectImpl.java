package com.pewpew.dodger.Game.GameObjects;

import com.pewpew.dodger.MainView.FrameDrawer.IFrame;
import com.pewpew.dodger.ObjectPool.IObjectPool;

/**
 * Created by hakiyama on 9/19/15.
 */
public class GameObjectImpl implements IGameObject {

    private float xPos;
    private float yPos;
    private float xSize;
    private float ySize;
    private IFrame frame;
    private IObjectPool objectPool;

    public GameObjectImpl() {
        withClean();
    }

    @Override
    public IGameObject withPos(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        if (frame != null){
            this.frame.withPos(this.xPos, this.yPos);
        }
        return this;
    }

    @Override
    public IGameObject withSize(float xSize, float ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        if (frame != null) {
            frame.withSize(xSize, ySize);
        }
        return this;
    }

    @Override
    public IGameObject withFrame(IFrame frame) {
        if (frame != null) {
            this.frame = frame;
        }
        return this;
    }

    @Override
    public IGameObject withPosIncrement(float xShift, float yShift) {
        withPos(xPos + xShift, yPos + yShift);
        return this;
    }

    @Override
    public IFrame getFrame() {
        return frame;
    }

    @Override
    public float getXSize() {
        return xSize;
    }

    @Override
    public float getYSize() {
        return ySize;
    }

    @Override
    public float getXPos() {
        return xPos;
    }

    @Override
    public float getYPos() {
        return yPos;
    }

    @Override
    public IGameObject withClean() {
        return withPos(0.0f, 0.0f).withSize(0.0f, 0.0f);
    }

    @Override
    public void setObjectPool(IObjectPool objectPool) {
        this.objectPool = objectPool;
    }

    @Override
    public void free() {
        objectPool.freeObject(this);
    }
}
