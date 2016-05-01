package app.novaci.com.game.state;

import android.view.MotionEvent;

import app.novaci.com.framework.util.Painter;
import app.novaci.com.simpleandroidgdf.Assets;

/**
 * Created by Sasha on 4/29/2016.
 */
public class MenuState extends State {
    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome, 0, 0);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
