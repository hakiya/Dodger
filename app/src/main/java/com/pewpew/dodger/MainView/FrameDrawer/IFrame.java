package com.pewpew.dodger.MainView.FrameDrawer;

import com.pewpew.dodger.MainView.IMainView;
import com.pewpew.dodger.ObjectPool.IPoolableObject;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hakiyama on 8/30/15.
 */
public interface IFrame extends IPoolableObject {
    void draw(GL10 gl);

    IFrame withPos(float x, float y);

    IFrame withAlpha(float alpha);

    IFrame withAngle(float angle);

    IFrame withIncrementAngle(float angleIncrement);

    IFrame withAtlas(float xAtlas, float yAtlas);

    IFrame withAtlasLen(float xAtlasLen, float yAtlasLen);

    IFrame withSize(float xSize, float ySize);

    IFrame withView(IMainView mainView);

    IFrame withRefreshVertices();

}
