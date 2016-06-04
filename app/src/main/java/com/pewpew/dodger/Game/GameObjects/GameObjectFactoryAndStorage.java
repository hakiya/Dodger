package com.pewpew.dodger.Game.GameObjects;

import com.pewpew.dodger.MainView.FrameDrawer.IFrameFactoryAndStorage;
import com.pewpew.dodger.MainView.IMainView;
import com.pewpew.dodger.ObjectPool.IObjectPool;
import com.pewpew.dodger.ObjectPool.IPoolableObject;
import com.pewpew.dodger.ObjectPool.IPoolableObjectFactoryAndStorage;
import com.pewpew.dodger.ObjectPool.ObjectPoolFactory;

/**
 * Created by hakiyama on 9/19/15.
 */
public class GameObjectFactoryAndStorage implements IPoolableObjectFactoryAndStorage {

    private IObjectPool gameObjectPool;
    private IFrameFactoryAndStorage frameFactoryAndStorage;

    public GameObjectFactoryAndStorage(IFrameFactoryAndStorage frameFactoryAndStorage) {
        gameObjectPool = new ObjectPoolFactory().get().initialize(this, new IPoolableObjectFactoryAndStorage.ObjectCreator() {
            @Override
            public IPoolableObject createNewPoolableObject() {
                return new GameObjectImpl().withClean();
            }
        });
        this.frameFactoryAndStorage = frameFactoryAndStorage;
    }

    public IGameObject getGameObject(ObjectChoice objectChoice, float xPos, float yPos, IMainView mainView) {
        switch (objectChoice) {
            case starObject:
                return ((IGameObject) gameObjectPool.getObject())
                        .withPos(xPos, yPos).withSize(0.5f, 0.5f)
                        .withFrame(frameFactoryAndStorage.getFrame()
                                .withPos(xPos, yPos).withSize(0.5f, 0.5f)
                                .withAtlas(0.0f, 0.0f).withAtlasLen(1.0f, 1.0f)
                                .withAlpha(1.0f).withAngle(0.0f)
                                .withView(mainView)
                                .withRefreshVertices());
        }
        return null;
    }

    public enum ObjectChoice {
        starObject
    }
}
