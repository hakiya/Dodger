package com.pewpew.dodger.Game.InputHandler;

import com.pewpew.dodger.Game.InputHandler.Touchscreen.Action.ActionEvent;
import com.pewpew.dodger.Game.InputHandler.Touchscreen.Action.IAction;

/**
 * Created by hakiyama on 8/30/15.
 */
public interface IInputHandler {
    void addAction(float xPos, float yPos, ActionEvent e);
    IAction consumeNextAction();
}