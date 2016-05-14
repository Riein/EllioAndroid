package app.novaci.com.simpleandroidgdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import app.novaci.com.framework.util.InputHandler;
import app.novaci.com.framework.util.Painter;
import app.novaci.com.game.state.LoadState;
import app.novaci.com.game.state.State;

/**
 * Created by Sasha on 4/18/2016.
 */
public class GameView extends SurfaceView  implements Runnable{

    private Bitmap gameImage;
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    private Painter graphics;

    private Thread gameThread;
    private volatile boolean running = false;
    private volatile State currentState;

    private InputHandler inputHandler;

    public GameView(Context context, int gameWidth, int gameHeight) {
        super(context);
        gameImage = Bitmap.createBitmap(gameWidth, gameHeight, Bitmap.Config.RGB_565);
        gameImageSrc = new Rect(0,0, gameImage.getWidth(), gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage);
        graphics = new Painter(gameCanvas);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                initInput();
                if(currentState == null) {
                    setCurrentState(new LoadState());
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                pauseGame();
            }
        });
    }

    //public GameView(Context context) {
    //    super(context);
    //}

    public void setCurrentState(State newState) {
        System.gc();
        newState.init();
        currentState = newState;
        inputHandler.setCurrentState(currentState);
    }

    private void initInput() {
        if (inputHandler == null) {
            inputHandler = new InputHandler();
        }
        setOnTouchListener(inputHandler);
    }

    @Override
    public void run() {
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        while (running) {
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis + updateDurationMillis;
            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;
            sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);
        }

        try {
            Thread.sleep(sleepDurationMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGame() {
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    private void pauseGame() {
        running = false;
        while (gameThread.isAlive()) {
            try {
                gameThread.join();
                break;
            } catch (InterruptedException e) {

            }
        }
    }

    private void updateAndRender(long delta) {
        currentState.update(delta / 1000f);
        currentState.render(graphics);
        renderGameImage();
    }

    private void renderGameImage() {
        Canvas screen = getHolder().lockCanvas();
        if (screen != null) {
            screen.getClipBounds(gameImageDst);
            screen.drawBitmap(gameImage,gameImageSrc, gameImageDst, null);
            getHolder().unlockCanvasAndPost(screen);
        }
    }
}
