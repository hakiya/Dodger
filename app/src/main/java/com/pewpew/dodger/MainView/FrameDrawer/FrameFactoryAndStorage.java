package com.pewpew.dodger.MainView.FrameDrawer;

import com.pewpew.dodger.ObjectPool.IObjectPool;
import com.pewpew.dodger.ObjectPool.IPoolableObject;
import com.pewpew.dodger.ObjectPool.IPoolableObjectFactoryAndStorage;
import com.pewpew.dodger.ObjectPool.ObjectPoolFactory;

/**
 * Created by hakiyama on 9/10/15.
 */
public class FrameFactoryAndStorage implements IFrameFactoryAndStorage {

    private IObjectPool frameObjectPool;

    public FrameFactoryAndStorage() {
        frameObjectPool = new ObjectPoolFactory().get().initialize(this, new IPoolableObjectFactoryAndStorage.ObjectCreator() {
            @Override
            public IPoolableObject createNewPoolableObject() {
                return new FrameImpl().withClean();
            }
        });
    }

    @Override
    public IFrame getFrame() {
        return (IFrame) frameObjectPool.getObject();
    }
}
