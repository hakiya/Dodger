package com.pewpew.dodger.ObjectPool;

/**
 * Created by hakiyama on 9/19/15.
 */
public class ObjectPoolFactory {
    public ObjectPoolFactory() {

    }

    public IObjectPool get() {
        return new ObjectPool();
    }
}
