package love.qiqi.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(this));
    }

    /**
     * 使用内部类 自定义一个简单的view
     */
    class CustomView extends View {
        Paint paint;
        private ArrayList<PointF> graphics = new ArrayList<PointF>();
        PointF point;

        public CustomView(Context context) {
            super(context);
            //设置一个笔刷大小是3的黄色色的画笔
            paint = new Paint();
            paint.setColor(Color.BLACK);
            /**
             * Paint.setStrokeJoin(Join join)设置结合处的样子，Miter:结合处为锐角，
             * Round:结合处为圆弧：BEVEL：结合处为直线。
             * setStrokeMiter(float miter )是设置笔画的倾斜度，如：小时候用的铅笔，
             * 削的时候斜与垂直削出来的笔尖效果是不一样的对吧？吼吼....
             */
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
        }

     /*   @Override
        protected void onDraw(Canvas canvas) {
            //绘制圆
            //canvas.drawCircle(100, 100, 90, paint);
            //绘制弧线区域
            RectF rect = new RectF(0, 0, 500, 500);//创建一个巨型
            *//*canvas.drawArc(rect,//弧线所使用的举行区域大小
                    0,//开始角度
                    90,//扫过的角度
                    true,//是否使用中心,.两图对比我们可以发现，
                    // 当 drawArcs(rect,startAngel,sweepAngel,useCenter,paint)中的useCenter为false时，
                    // 弧线区域是用弧线开始角度和结束角度直接连接起来的，当useCenter为true时，
                    // 是弧线开始角度和结束角度都与中心点连接，形成一个扇形。
                    paint);*//*
            RectF rectF = new RectF(0, 0, 1000, 1000);
            //canvas.drawArc(rectF, 0, 90, false, paint);
            //canvas.drawColor(Color.BLUE);//canvas.drawColor是直接将View显示区域用某个颜色填充满。
            //画一条直线
            //canvas.drawLine(100, 100, 900, 900, paint);
            //矩形区域内切椭圆
            RectF oval = new RectF(400, 400, 500, 600);
            //canvas.drawOval(oval, paint);//在矩形区域内画圆，受限于矩形的形状。长宽不一致就会画椭圆。
            //按照定制点绘制，文本
            float[] x_y = new float[]{
                    10, 100,
                    20, 200,
                    30, 300,
                    40, 400,
                    50, 500,
                    60, 600,
                    70, 700,
                    80, 800,
                    90, 900
            };
            //canvas.drawText("QiQi_love", 0, 9, 400, 1000, paint);
            //绘制矩形
            RectF rect1 = new RectF(500, 500, 800, 800);
            //canvas.drawRect(rect1, paint);//方正矩形
           *//* canvas.drawRoundRect(rect1,
                    30,//x轴半径 圆角半径
                    30,//y轴半径
                    paint);//圆角矩形*//*
            //路径绘制
            Path path = new Path();
            path.moveTo(100, 100);
            path.lineTo(500, 600);
            path.lineTo(800, 666);
            path.lineTo(100, 100);
            //paint.setTextScaleX(14);
            //canvas.drawPath(path, paint);
            //canvas.drawTextOnPath("QiQi,LoveYou,hhhhhhhhhhhhhhhhhh", path, 100, 100, paint);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            canvas.translate(canvas.getWidth() / 2, 200);//将位置移动到画纸坐标点；150，150
            canvas.drawCircle(0, 0, 100, paint);//画圆圈

            //使用path绘制路径文字。
            canvas.save();
            canvas.translate(-75, -75);
            Path path1 = new Path();
            path1.addArc(new RectF(0, 0, 150, 150), -180, 180);
            Paint citePaint = new Paint(paint);
            citePaint.setTextScaleX(14);
            citePaint.setStrokeWidth(1);
            canvas.drawTextOnPath("qiqi.love.demo", path1, 28, 0, citePaint);
            canvas.restore();
            //小刻度画笔
            Paint tmpPaint = new Paint(paint);
            tmpPaint.setStrokeWidth(1);
            float y = 100;
            int count = 60;//刻度总数
            for (int i = 0; i < count; i++) {
                if (i % 5 == 0) {
                    canvas.drawLine(0f, y, 0, y + 12f, paint);
                    canvas.drawText(String.valueOf(i / 5 + 1), -4f, y + 25f, tmpPaint);
                } else {
                    canvas.drawLine(0f, y, 0f, y + 5f, tmpPaint);
                }
                canvas.rotate(360 / count, 0f, 0f);//旋转画布
            }
            //绘制指针
            tmpPaint.setColor(Color.GRAY);
            tmpPaint.setStrokeWidth(4);
            canvas.drawCircle(0, 0, 7, tmpPaint);
            tmpPaint.setStyle(Paint.Style.FILL);
            tmpPaint.setColor(Color.YELLOW);
            canvas.drawCircle(0, 0, 5, tmpPaint);
            canvas.drawLine(0, 10, 0, -65, paint);
        }*/

        /**
         * 当用户点击时将出现一个小点，拖动时将画出一条用细点组成的虚线
         *
         * @param event
         * @return
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            graphics.add(new PointF(event.getX(), event.getY()));
            invalidate();//重新绘制区域
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (PointF point : graphics) {
                canvas.drawPoint(point.x, point.y, paint);
            }
        }
    }
}
