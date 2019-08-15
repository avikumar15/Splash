package com.example.playingwithcanvas3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CanvasClass canvasClass;
    TheatreSetup theatreSetup;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.FRAMELAYOUT);
        canvasClass = new CanvasClass(this);
        theatreSetup = new TheatreSetup(this);
        frameLayout.addView(theatreSetup);
        frameLayout.addView(canvasClass);

    }

    public class TheatreSetup extends View {
        Bitmap logo;
        Bitmap logoResize;
        //        Bitmap curtain;
//        Bitmap curtainResize;
        Bitmap chair;
        Bitmap chairResize;
        Paint Black;
        Paint screenBack;
        Paint screen;
        float[] stopsGradient;
        int[] colorsGradient;


        public TheatreSetup(Context context) {
            super(context);

            Black = new Paint();
            Black.setColor(Color.parseColor("#171414"));
            screen = new Paint();
            screen.setColor(Color.WHITE);
            screenBack = new Paint();
            screenBack.setColor(Color.parseColor("#000000"));


            logo = BitmapFactory.decodeResource(getResources(), R.drawable.festembertemp);
            //  curtain = BitmapFactory.decodeResource(getResources(),R.drawable.curt2);
            chair = BitmapFactory.decodeResource(getResources(), R.drawable.aud6);

        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);


            screen.setShader(new RadialGradient(getWidth() / 2, getHeight() / 4, getWidth() / 3.2f + getWidth() / 9f + 75, Color.parseColor("#FFFFFF"), Color.parseColor("#979797"), Shader.TileMode.CLAMP));

            canvas.drawRect(0, 0, getWidth(), getHeight(), Black);
            canvas.drawRect(getWidth() / 2f - getWidth() / 3.2f - getWidth() / 9f - 50, getHeight() / 9f - 50, getWidth() / 2f + getWidth() / 3.2f + getWidth() / 9f + 50, getHeight() / 2f - getHeight() / 9f + 150, screenBack);
            canvas.drawRect(getWidth() / 2f - getWidth() / 3.2f - getWidth() / 9f, getHeight() / 9f, getWidth() / 2f + getWidth() / 3.2f + getWidth() / 9f, getHeight() / 2f - getHeight() / 9f, screen);

            logoResize = Bitmap.createScaledBitmap(logo, (int) (getWidth() / 1.6), getHeight() / 13, true);
            canvas.drawBitmap(logoResize, getWidth() / 2f - getWidth() / 3.2f, getHeight() / 4f - getHeight() / 26f, null);

            //   curtainResize = Bitmap.createScaledBitmap(curtain,(int)(getWidth()*1.5),(int)(getHeight()/1.7),true);
            //   canvas.drawBitmap(curtainResize,(int)(-1*getWidth()*0.25),-2,null);

            //   chairResize = Bitmap.createScaledBitmap(chair,(int)(getWidth()*2.5),(int)(getHeight()),true);
            chairResize = Bitmap.createScaledBitmap(chair, (int) (getWidth() * 1.3), (int) (getHeight()), true);
            canvas.drawBitmap(chairResize, -1 * getWidth() * 0.1f, 0, null);
        }
    }

    int state = 0;

    public class CanvasClass extends View {
        Bitmap Cover;
        Paint trans;
        Paint paint;
        Paint screen;
        float x;
        float y;
        float z;
        float r;
        int TIME;
        final Handler handler;
        int random, random2;
        float[] stopsGradient;
        int[] colorsGradient;

        public CanvasClass(Context context) {
            super(context);

            paint = new Paint();
            trans = new Paint();
            screen = new Paint();

            x = 0;
            y = 0;
            TIME = 0;
            r = 1 * getResources().getDisplayMetrics().density;
            t = 0;
            handler = new Handler();

            trans.setAntiAlias(true);

        }

        float cx, cy;

        @Override
        protected void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);

            MakeCover();
            canvas.drawBitmap(Cover, 0, 0, null);

        }

        int t;

        public void MakeCover() {

            Cover = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);

            Canvas temp = new Canvas(Cover);
            paint.setColor(Color.parseColor("#000000"));

            trans.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            screen.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

            temp.drawRect(0, 0, getWidth(), getHeight(), paint);
            //   trans.setColor(Color.parseColor("#80FF0000"));
            screen.setColor(Color.TRANSPARENT);
            if (t < 30) {
                cx = getWidth() / 2f - getWidth() / 3.2f + x + z;
                cy = y + getHeight() / 4f;
                t++;
            } else if (r <= 30 * getResources().getDisplayMetrics().density) {
                cx = getWidth() / 2f - getWidth() / 3.2f + x + z;
                cy = y + getHeight() / 4f;
                r += 5;
            } else if (cx < getWidth() / 2f + getWidth() / 3.2f) {
                if (state == 0)
                    state++;
                if (state == 1) {
                    cx += 8;
                    cy = y + getHeight() / 4f + 100 * (float) Math.sin(cx / 50);
                }
                if (state == 2) {
                    if (cy < getHeight() / 2) {
                        cy += 5;
                        cx -= 5 * (getWidth() / 3.2f) / (getHeight() / 4f - y - 100 * (float) Math.sin((getWidth() / 2f + getHeight() / 3.2f) / 50));
                        r += 2;
                    } else
                        state = 3;
                }
            } else if (cy < getHeight() / 2) {
                if (state == 1)
                    state++;
                if (state == 2) {
                    if (cy >= getHeight() / 2)
                        state = 3;
                    cy += 5;
                    cx -= 5 * (getWidth() / 3.2f) / (getHeight() / 4f - y - 100 * (float) Math.sin((getWidth() / 2f + getHeight() / 3.2f) / 50));
                    r += 2;
                }

            }
            if (state == 3) {
                r += 30;
            }

            invalidate();

            stopsGradient = new float[]{0, 0.9f, 1};
            colorsGradient = new int[]{Color.parseColor("#00FFFFFF"), Color.parseColor("#58000000"), Color.parseColor("#88000000")};
            if (state != 3)
                trans.setShader(new RadialGradient(
                        cx,
                        cy,
                        10 + 3 * r / getResources().getDisplayMetrics().density,
                        colorsGradient,
                        stopsGradient,
                        Shader.TileMode.CLAMP
                ));
            else {
                trans.setShader(null);
                trans.setColor(Color.TRANSPARENT);
            }
            if (state != 3 && state != 2)
                trans.setMaskFilter(new BlurMaskFilter(10 + 3 * r / getResources().getDisplayMetrics().density, BlurMaskFilter.Blur.INNER));
            else {
                trans.setMaskFilter(null);
            }
            trans.setDither(true);
            trans.setAntiAlias(true);

            temp.drawCircle(cx, cy, 10 + 3 * r / getResources().getDisplayMetrics().density, trans);

        }
    }

}
