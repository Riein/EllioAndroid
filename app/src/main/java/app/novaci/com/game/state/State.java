package app.novaci.com.game.state;

import android.view.MotionEvent;

import app.novaci.com.framework.util.Painter;
import app.novaci.com.simpleandroidgdf.GameMainActivity;

/**
 * Created by Sasha on 4/18/2016.
 */
public abstract class State {
    public void setCurrentState(State newState) {
        GameMainActivity.sGame.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);
}
