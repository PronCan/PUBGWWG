package moe.proncan.wherewego;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;


@SuppressLint("DrawAllocation")
public class MainActivity extends Activity {
    ArrayList<Point> points = new ArrayList<Point>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView drawLine = new MyView(this);

        setContentView(drawLine);
    }

    class Point {
        float x;
        float y;
        boolean isDraw;

        public Point(float x, float y, boolean isDraw) {
            this.x = x;
            this.y = y;
            this.isDraw = isDraw;
        }
    }

    class MyView extends View {
        public MyView(Context context) {
            super(context);
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            this.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            points.add(new Point(event.getX(), event.getY(), true));
                            invalidate();
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_DOWN :
                            points.add(new Point(event.getX(), event.getY(), false));
                    }
                    return true;
                }
            });


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            p.setStrokeWidth(3);
            for(int i=1; i<points.size(); i++) {
                if(!points.get(i).isDraw) continue;
                canvas.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y, p);
            }
        }
    }


}
