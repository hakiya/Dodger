package com.pewpew.dodger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.pewpew.dodger.Game.GameImpl;
import com.pewpew.dodger.Game.IGame;
import com.pewpew.dodger.GameEnvironment.GameEnvironmentImpl;
import com.pewpew.dodger.GameEnvironment.IGameEnvironment;
import com.pewpew.dodger.MainView.IMainView;
import com.pewpew.dodger.MainView.MainViewImpl;

public class MainActivity extends AppCompatActivity {

    private static Context context;

    private IMainView mainView;
    private IGame game;
    private IGameEnvironment gameEnvironment;

    protected void onCreate(Bundle savedInstanceState) {
        //remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        context = this;
        gameEnvironment = new GameEnvironmentImpl();
        mainView = new MainViewImpl(context, getWindowManager(), gameEnvironment);
        game = new GameImpl(context, gameEnvironment.getFrameFactoryAndStorage())
                .withInput(mainView.getInputHandler()).withView(mainView).withSize(mainView.screenSizeToGame(mainView.getXSize()), mainView.screenSizeToGame(mainView.getYSize()));
        mainView.setGame(game);
        game.newGame();
        game.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainView.pause();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainView.resume();
    }
}
