package com.pewpew.dodger.MainView.FrameDrawer;

import com.pewpew.dodger.ObjectPool.IPoolableObjectFactoryAndStorage;

/**
 * Created by hakiyama on 2/14/16.
 */
public interface IFrameFactoryAndStorage extends IPoolableObjectFactoryAndStorage {
    IFrame getFrame();
}
