package com.pewpew.dodger.Game.InputHandler.Touchscreen.Action;

import com.pewpew.dodger.ObjectPool.IObjectPool;
import com.pewpew.dodger.ObjectPool.IPoolableObject;
import com.pewpew.dodger.ObjectPool.IPoolableObjectFactoryAndStorage;
import com.pewpew.dodger.ObjectPool.ObjectPoolFactory;

/**
 * Created by hakiyama on 8/30/15.
 */
public class ActionFactoryAndStorage implements IPoolableObjectFactoryAndStorage {

    private IObjectPool actionObjectPool;

    public ActionFactoryAndStorage() {
        actionObjectPool = new ObjectPoolFactory().get().initialize(this, new IPoolableObjectFactoryAndStorage.ObjectCreator() {
            @Override
            public IPoolableObject createNewPoolableObject() {
                return new ActionImpl().withClean();
            }
        });
    }

    public IAction GetAction() {
        return (IAction) actionObjectPool.getObject();
    }
}
