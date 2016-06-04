package com.pewpew.dodger.ObjectPool;

/**
 * Created by hakiyama on 9/1/15.
 */
public interface IObjectPool {
    IPoolableObject getObject();
    void freeObject(IPoolableObject obj);
    IObjectPool initialize(IPoolableObjectFactoryAndStorage poolObjectFactory, IPoolableObjectFactoryAndStorage.ObjectCreator objectCreator);
}
