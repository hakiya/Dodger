package com.pewpew.dodger.GameEnvironment;

import com.pewpew.dodger.Game.InputHandler.IInputHandler;
import com.pewpew.dodger.Game.InputHandler.Touchscreen.InputHandlerTouchScreenImpl;
import com.pewpew.dodger.MainView.FrameDrawer.FrameFactoryAndStorage;
import com.pewpew.dodger.MainView.FrameDrawer.IFrameFactoryAndStorage;

/**
 * Created by hakiyama on 2/15/16.
 */
public class GameEnvironmentImpl implements IGameEnvironment {
    private IInputHandler inputHandler;
    private IFrameFactoryAndStorage frameFactoryAndStorage;

    public GameEnvironmentImpl() {
        this.inputHandler = new InputHandlerTouchScreenImpl();
        this.frameFactoryAndStorage = new FrameFactoryAndStorage();
    }

    @Override
    public IInputHandler getInputHandler() {
        if (inputHandler == null) {
            inputHandler = new InputHandlerTouchScreenImpl();
        }
        return inputHandler;
    }

    @Override
    public IFrameFactoryAndStorage getFrameFactoryAndStorage() {
        if (frameFactoryAndStorage == null) {
            frameFactoryAndStorage = new FrameFactoryAndStorage();
        }
        return frameFactoryAndStorage;
    }
}
