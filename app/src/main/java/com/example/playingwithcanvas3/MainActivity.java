package com.example.playingwithcanvas3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CanvasClass canvasClass;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.darkLayout);
        canvasClass = new CanvasClass(this);
        relativeLayout.addView(canvasClass);

    }

    public class CanvasClass extends View
    {
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
        int random,random2;

        public CanvasClass(Context context) {
            super(context);

            paint = new Paint();
            trans = new Paint();
            screen = new Paint();

            x=0;
            y=0;
            TIME=0;

            r=getResources().getDisplayMetrics().density;

            handler = new Handler();

            trans.setAntiAlias(true);

        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);

            MakeCover();
            canvas.drawBitmap(Cover, 0, 0, null);

        }

        public void MakeCover()
        {

            Cover = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);

            Canvas temp = new Canvas(Cover);
            paint.setColor(Color.parseColor("#000000"));

            trans.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            screen.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

            temp.drawRect(0,0,getWidth(),getHeight(),paint);
            trans.setColor(Color.parseColor("#80FFFF33"));
            screen.setColor(Color.TRANSPARENT);
/*

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(r<= 20*getResources().getDisplayMetrics().density)
                        r *= 1.5;*/
/*
                    else
                    {
                        x+=3;
                        *//*
*/
/*random = new Random().nextInt(100);
                        y=(int)(random*getResources().getDisplayMetrics().density);*//*
*/
/*
                        y=(float) (25*getResources().getDisplayMetrics().density*Math.sin(x/40));
                    }*//*

                }
            }, 700);
*/

            TIME+=3;

            if(TIME>=300)
            {
                while(r<= 30*getResources().getDisplayMetrics().density)
                {
                    r *= 1.5;
                    invalidate();
                }

                if(TIME>=500) {
                    x = getWidth() / 9f;
                 /*   random = new Random().nextInt(3);
                    y = (int)(random*getResources().getDisplayMetrics().density);
                    random2 = new Random().nextInt(3);
                    z = (int)(random2*getResources().getDisplayMetrics().density);
*/
                    if (TIME >= 700)
                    {
                        x = 2 * getWidth() / 9f;
                        if(TIME >= 900)
                        {
                            x=3*getWidth()/9f;

                            if(TIME>=1100)
                            {
                                x=4*getWidth()/9f;

                                if(TIME>=1300)
                                {
                                    x=5*getWidth()/9f;

                                    if(TIME>=1500)
                                    {
                                        x=6*getWidth()/9f;

                                        if(TIME>=1700)
                                        {
                                            x=getWidth()/2f-80*getResources().getDisplayMetrics().density;
                                            y=getHeight()/4f-100*getResources().getDisplayMetrics().density;
                                         //   r=5*getResources().getDisplayMetrics().density*-1;
                                            r=getHeight()/4f-10*getResources().getDisplayMetrics().density;

                                            if(TIME>=1800)
                                            {
                                               // r=5*getResources().getDisplayMetrics().density*-1;
                                                temp.drawRect(0,0,getWidth(),getHeight()/2f,screen);
                                            }
                                        }

                                        /*if(TIME>=1700)
                                        {
                                            x=7*getWidth()/9f;

                                            if(TIME>=1900)
                                            {
                                                x=8*getWidth()/9f;
                                            }
                                        }*/
                                    }
                                }
                            }
                        }
                    }
                }
            }
            invalidate();

            temp.drawCircle(80*getResources().getDisplayMetrics().density+x+z,y+100*getResources().getDisplayMetrics().density,5*getResources().getDisplayMetrics().density+r,trans);

        }
    }

}
