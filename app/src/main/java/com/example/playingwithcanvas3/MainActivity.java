package com.example.playingwithcanvas3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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

    public class TheatreSetup extends View
    {
        Bitmap logo;
        Bitmap logoResize;
        Bitmap curtain;
        Bitmap curtainResize;
        Bitmap chair;
        Bitmap chairResize;
        Paint Black;


        public TheatreSetup(Context context) {
            super(context);

            Black = new Paint();
            Black.setColor(Color.BLACK);

            logo = BitmapFactory.decodeResource(getResources(),R.drawable.festembertemp);
            curtain = BitmapFactory.decodeResource(getResources(),R.drawable.curt2);
            chair = BitmapFactory.decodeResource(getResources(),R.drawable.aud6);

        }

        public void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);

            canvas.drawRect(0,getHeight()/2f,getWidth(),getHeight(),Black);

            logoResize = Bitmap.createScaledBitmap(logo,(int)(getWidth()/1.6),getHeight()/17,true);
            canvas.drawBitmap(logoResize,getWidth()/2f-getWidth()/3.2f,getHeight()/4f-getHeight()/34f,null);

            curtainResize = Bitmap.createScaledBitmap(curtain,(int)(getWidth()*1.5),(int)(getHeight()/1.7),true);
            canvas.drawBitmap(curtainResize,(int)(-1*getWidth()*0.25),-2,null);

            chairResize = Bitmap.createScaledBitmap(chair,(int)(getWidth()*2.5),(int)(getHeight()),true);
            canvas.drawBitmap(chairResize,-1*getWidth()*0.1f,0,null);
        }
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
            trans.setColor(Color.parseColor("#00FFFFFF"));
            screen.setColor(Color.TRANSPARENT);

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
                                            r=getHeight()/4f-10*getResources().getDisplayMetrics().density;

                                            if(TIME>=1800)
                                            {
                                                r=getHeight()*2;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
            invalidate();

            temp.drawCircle(getWidth()/2f-getWidth()/3.2f+x+z,y+getHeight()/4f,10+3*r/getResources().getDisplayMetrics().density,trans);

        }
    }

}
