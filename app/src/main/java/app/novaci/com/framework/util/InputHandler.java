package app.novaci.com.framework.util;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import app.novaci.com.game.state.State;
import app.novaci.com.simpleandroidgdf.GameMainActivity;

/**
 * Created by Sasha on 4/19/2016.
 */
public class InputHandler implements OnTouchListener{

    private State currentState;

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int scaledX = (int) ((event.getX() / v.getWidth()) * GameMainActivity.GAME_WIDTH);
        int scaledY = (int) ((event.getY() / v.getHeight()) * GameMainActivity.GAME_HEIGHT);
        return currentState.onTouch(event, scaledX, scaledY);
    }
}
