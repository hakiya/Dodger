package com.pewpew.dodger.ObjectPool;

import java.util.ArrayList;

/**
 * Created by hakiyama on 9/1/15.
 */
public class ObjectPool implements IObjectPool {

    private IPoolableObjectFactoryAndStorage poolObjectFactory;
    private IPoolableObjectFactoryAndStorage.ObjectCreator objectCreator;

    protected ArrayList<IPoolableObject> freeObjects;

    public ObjectPool() {
    }

    public ObjectPool initialize(IPoolableObjectFactoryAndStorage poolObjectFactory, IPoolableObjectFactoryAndStorage.ObjectCreator objectCreator) {
        this.poolObjectFactory = poolObjectFactory;
        this.objectCreator = objectCreator;
        this.freeObjects = new ArrayList<>();
        return this;
    }

    public final synchronized IPoolableObject getObject() {
        IPoolableObject obj;
        if (freeObjects.isEmpty()) {
            obj = objectCreator.createNewPoolableObject();
        }
        else {
            obj = freeObjects.get(freeObjects.size() - 1);
            freeObjects.remove(obj);
        }
        obj.setObjectPool(this);
        return obj;
    }

    public final synchronized void freeObject(IPoolableObject obj) {
        obj.withClean();
        freeObjects.add(obj);
    }
}
