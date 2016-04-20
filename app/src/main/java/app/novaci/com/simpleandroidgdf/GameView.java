package app.novaci.com.simpleandroidgdf;

import android.content.Context;
import android.view.SurfaceView;

import app.novaci.com.game.state.State;

/**
 * Created by Sasha on 4/18/2016.
 */
public class GameView extends SurfaceView {

    public GameView(Context context, int gameWidth, int gameHeight) {
        super(context);
    }

    public GameView(Context context) {
        super(context);
    }

    public void setCurrentState(State newState) {
    }
}
