package com.pewpew.dodger.ObjectPool;

/**
 * Created by hakiyama on 9/19/15.
 */
public interface IPoolableObject {
    IPoolableObject withClean();
    void setObjectPool(IObjectPool objectPool);
    void free();
}
