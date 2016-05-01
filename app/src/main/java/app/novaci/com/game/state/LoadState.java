package app.novaci.com.game.state;

import android.view.MotionEvent;

import app.novaci.com.framework.util.Painter;
import app.novaci.com.simpleandroidgdf.Assets;

/**
 * Created by Sasha on 4/29/2016.
 */
public class LoadState extends State{
    @Override
    public void init() {
        Assets.load();
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Painter g) {

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
