package com.pewpew.dodger.GameEnvironment;

import com.pewpew.dodger.Game.InputHandler.IInputHandler;
import com.pewpew.dodger.MainView.FrameDrawer.IFrameFactoryAndStorage;

/**
 * Created by hakiyama on 2/15/16.
 */
public interface IGameEnvironment {
    IInputHandler getInputHandler();
    IFrameFactoryAndStorage getFrameFactoryAndStorage();
}
