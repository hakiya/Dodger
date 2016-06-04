package com.pewpew.dodger.Game.InputHandler.Accelerometer;

import com.pewpew.dodger.Game.InputHandler.IInputHandler;
import com.pewpew.dodger.Game.InputHandler.Touchscreen.Action.ActionEvent;
import com.pewpew.dodger.Game.InputHandler.Touchscreen.Action.ActionFactoryAndStorage;
import com.pewpew.dodger.Game.InputHandler.Touchscreen.Action.IAction;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by hakiyama on 8/30/15.
 */
public class InputHandlerAccelerometerImpl implements IInputHandler {

    private final ArrayBlockingQueue<IAction> actionBlockingQueue;
    private final ActionFactoryAndStorage actionFactory;

    public InputHandlerAccelerometerImpl() {
        actionBlockingQueue = new ArrayBlockingQueue<>(30);
        actionFactory = new ActionFactoryAndStorage();
    }

    @Override
    public void addAction(float xPos, float yPos, ActionEvent e) {
        try {
            actionBlockingQueue.put(actionFactory.GetAction().withAction(xPos, yPos, e));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public IAction consumeNextAction() {
        try {
            return actionBlockingQueue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }
}
