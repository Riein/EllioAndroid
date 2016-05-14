package app.novaci.com.game.state;

import android.view.MotionEvent;
import android.util.Log;

import app.novaci.com.framework.util.Painter;
import app.novaci.com.framework.util.UIButton;
import app.novaci.com.simpleandroidgdf.Assets;

/**
 * Created by Sasha on 4/29/2016.
 */
public class MenuState extends State {

    private UIButton playButton, scoreButton;

    @Override
    public void init() {
        playButton = new UIButton(316, 227, 484, 286, Assets.start, Assets.startDown);
        scoreButton = new UIButton(316, 300, 484, 359, Assets.score, Assets.scoreDown);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome, 0, 0);
        playButton.render(g);
        scoreButton.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        Log.d("MenuState", "Touched Down!");
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            playButton.onTouchDown(scaledX, scaledY);
            scoreButton.onTouchDown(scaledX, scaledY);
        }

        if(e.getAction() == MotionEvent.ACTION_UP) {
            // If the play button is active and the release was within the play button:
            if (playButton.isPressed(scaledX, scaledY)) {
                playButton.cancel();
                Log.d("MenuState", "Play button Pressed!");
                setCurrentState(new PlayState());
            } else if (scoreButton.isPressed(scaledX, scaledY)) {
                scoreButton.cancel();
                Log.d("MenuState", "Score Button Pressed!");
            } else {
                // Cancel all actions.
                playButton.cancel();
                scoreButton.cancel();
            }
        }
        return true;
    }
}
