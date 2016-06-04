package com.pewpew.dodger.ObjectPool;

/**
 * Created by hakiyama on 9/1/15.
 */
public interface IPoolableObjectFactoryAndStorage {
    interface ObjectCreator {
        IPoolableObject createNewPoolableObject();
    }
}
