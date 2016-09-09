package com.sheenline.muis.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class DrawWaveViewByAll extends SurfaceView implements SurfaceHolder.Callback {
    // 刷新线程
    private class DrawThread extends Thread {

        public Boolean isRunning = false;

        public DrawThread() {
            isRunning = true;
        }

        @Override
        public void run() {
            long deltaTime = 0;
            long tickTime = 0;

            while (isRunning) {
                Canvas canvas = null;

                tickTime = System.currentTimeMillis();

                try {
                    synchronized (mHolder) {
                        canvas = mHolder.lockCanvas();

                        if (drawkey != "999") {
//                            canvas.drawColor(Color.WHITE);
                            canvas.drawColor(Color.rgb(255, 236, 139));

                           drawAllViewAxs1(canvas, XLength, YLength, YLength, "512");

                        } else {
                            canvas.drawColor(Color.WHITE);

                            // drawAllAView(canvas,
                            // Datalist, XLength,
                            // YLength);

                            //drawAllAView2(canvas, Datalist, XLength, YLength);

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != mHolder) {
                        try {
                            mHolder.unlockCanvasAndPost(canvas);
                        } catch (Exception e) {
                        }
                    }
                }

                deltaTime = System.currentTimeMillis() - tickTime;

                if (deltaTime < DRAW_INTERVAL) {
                    try {

                        Thread.sleep(DRAW_INTERVAL - deltaTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            tickTime = System.currentTimeMillis();
        }
    }

    // 单通道数组
    private static String[] Data;

    // 多通道数据
    private static TreeMap<String, int[]> Datalist;

    // 最大帧数 (1000 / 30)
    private static final int DRAW_INTERVAL = 130;

    // 通道号
    private static String drawkey;

    int bottommargin = 30;                        // 下空间


    double dissw = 10.0;                        // 压缩数据阈值

    int drawallscale = 220;

    int intthlength;

    int intThreshold;

    int intthstart;
    int intthsteplength;
    int intzero;
    int leftmargin = 30;                        // 左空间
    private DrawThread mDrawThread;
    private ArrayList<Integer> mGridColorLevel = new ArrayList<Integer>();
    private ArrayList<String> mGridLevelText = new ArrayList<String>();

    private ArrayList<Integer> mGridTxtColorLevel = new ArrayList<Integer>();

    private SurfaceHolder mHolder;

    // public int XScale; // X的刻度长度

    private Paint mPaint;

    private int mWidth, mHight;

    private ArrayList<Integer> mXLevel = new ArrayList<Integer>();

    // public int YScale; // Y的刻度长度

    private ArrayList<Integer> mYLevel = new ArrayList<Integer>();

    double NewScale;

    public int[] NewxLable;

    int rightmargin = 0;                        // 右空间

    public String Title;                                            // 显示的标题

    int topmargin = 0;                        // 上空间

    int wavescale = 3;                        // 波幅比例

    public String[] XLabel;                                            // X的刻度

    // private void drawAllAView(Canvas canvas, TreeMap<String, byte[]>
    // datalist1, int a, int b)
    // {
    // if (datalist1 == null)
    // {
    // return;
    // }
    //
    // // canvas.drawColor(Color.WHITE);// 设置背景颜色
    //
    // Set<String> keyset = datalist1.keySet();
    // Object[] keystring = keyset.toArray();
    // int yyb = b;
    // int keycount = keyset.size();
    //
    // int index = 0;
    // long timer1 = System.currentTimeMillis();
    // for (index = 0; index < keycount; index++)
    // {
    // drawAView(canvas,
    // DataConvertTools.bytesToStringArrayPercent(datalist1.get(keystring[index]),(String)keystring[index]),
    // a, yyb / keycount * (index + 1), yyb / keycount, (String)
    // keystring[index]);
    // }
    // Log.d("debug2", String.valueOf(System.currentTimeMillis() - timer1));
    // }

    public int XLength;                                        // X轴的长度

    public int XPoint;                                            // 原点的X坐标

    public String[] YLabel;                                            // Y的刻度
    public int YLength;                                        // Y轴的长度
    public int YPoint;                                            // 原点的Y坐标

    public DrawWaveViewByAll(Context context) {
        super(context);

    }

    public DrawWaveViewByAll(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);

        mHolder = this.getHolder();
        mHolder.addCallback(this);

    }

    private void drawAllAView2(Canvas canvas, TreeMap<String, int[]> datalist1, float a, float b) {
        if (datalist1 == null) {
            return;
        }

        // canvas.drawColor(Color.WHITE);// 设置背景颜色

        Set<String> keyset = datalist1.keySet();
        Object[] keystring = keyset.toArray();
        int keycount = keyset.size();

        int index = 0;

        int lineinterval = 15;// 通道线间隔

        int position = 0;
        boolean direction = true;

        XPoint = leftmargin;
        float yYPoint = b + bottommargin;

        float XScale = (a) / (XLabel.length - 1);

        float YScale = (yYPoint) / (YLabel.length - 1);

        // 画笔
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);// 去锯齿
        paint.setColor(Color.LTGRAY);// 颜色
        paint.setStrokeWidth(4);

        Paint paintoutwave = new Paint();
        paintoutwave.setStyle(Paint.Style.STROKE);
        paintoutwave.setAntiAlias(true);// 去锯齿
        paintoutwave.setColor(Color.BLUE);// 颜色
        paintoutwave.setStrokeWidth(4);

        Paint paintlostwave = new Paint();
        paintlostwave.setStyle(Paint.Style.STROKE);
        paintlostwave.setAntiAlias(true);// 去锯齿
        paintlostwave.setColor(Color.YELLOW);// 颜色
        paintlostwave.setStrokeWidth(4);

        Paint paintdoublewave = new Paint();
        paintdoublewave.setStyle(Paint.Style.STROKE);
        paintdoublewave.setAntiAlias(true);// 去锯齿
        paintdoublewave.setColor(Color.YELLOW);// 颜色
        paintdoublewave.setStrokeWidth(4);

        // paint.setStrokeWidth(8);
        float scale1 = 1;

        for (index = 0; index < keycount; index++) {
            switch ((String) keystring[index]) {

                case "0":

                    position = -1;
                    direction = true;
                    scale1 = 0.25641f;// 160/624
                    break;
                case "256":

                    position = -3;
                    direction = true;
                    scale1 = 0.25641f;
                    break;
                case "512":

                    position = -5;
                    direction = true;
                    scale1 = 0.47436f;// 296/624
                    break;

                case "4097":

                    position = 1;
                    direction = true;

                    break;

                case "4353":

                    position = 1;
                    direction = false;

                    break;

                case "4098":
                    position = 3;
                    direction = true;

                    break;

                case "4354":

                    position = 3;
                    direction = false;

                    break;

                case "4099":
                    position = 5;
                    direction = true;

                    break;
                case "4355":

                    position = 5;
                    direction = false;

                    break;

            }

            drawAllViewAxs(canvas, Tools.intStringArrayPercent(datalist1.get(keystring[index]), (String) keystring[index]), a, b, position * lineinterval, direction, (String) keystring[index], scale1);

        }

        // 设置Y1轴
        canvas.drawLine(XPoint, yYPoint - (YLabel.length - 1) * YScale, XPoint, yYPoint, paint); // 左轴线

        // 设置Y2轴
        canvas.drawLine(XLength - rightmargin, yYPoint - (YLabel.length - 1) * YScale, XLength - rightmargin, yYPoint, paint); // 右轴线

        for (int i = 0; i < YLabel.length; i++) {
            canvas.drawLine(XPoint, yYPoint - i * YScale, XPoint + 5, yYPoint - i * YScale, paint); // 左刻度

            canvas.drawLine(XLength - rightmargin, yYPoint - i * YScale, XLength - rightmargin - 5, yYPoint - i * YScale, paint); // 右刻度

        }

        // 设置X轴
        canvas.drawLine(XPoint, yYPoint / 2 - lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 - lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 - lineinterval, XPoint + (int) (200 * a / drawallscale), yYPoint / 2 - lineinterval, paintoutwave); // 轴线

        canvas.drawLine(XPoint, yYPoint / 2 - 3 * lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 - 3 * lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 - 3 * lineinterval, XPoint + (int) (200 * a / drawallscale), yYPoint / 2 - 3 * lineinterval, paintoutwave); // 轴线

        canvas.drawLine(XPoint, yYPoint / 2 - 5 * lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 - 5 * lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 - 5 * lineinterval, XPoint + (int) (200 * a / drawallscale), yYPoint / 2 - 5 * lineinterval, paintoutwave); // 轴线

        // 512
        canvas.drawLine(XPoint, yYPoint / 2 + 5 * lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 + 5 * lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 + 5 * lineinterval, XPoint + (int) (70 * a / drawallscale), yYPoint / 2 + 5 * lineinterval, paintoutwave); // 轴线
        canvas.drawLine(XPoint + (int) (70 * a / drawallscale), yYPoint / 2 + 5 * lineinterval, XPoint + (int) (100 * a / drawallscale), yYPoint / 2 + 5 * lineinterval, paintdoublewave); // 轴线
        canvas.drawLine(XPoint + (int) (100 * a / drawallscale), yYPoint / 2 + 5 * lineinterval, XPoint + (int) (160 * a / drawallscale), yYPoint / 2 + 5 * lineinterval, paintoutwave); // 轴线
        canvas.drawLine(XPoint + (int) (160 * a / drawallscale), yYPoint / 2 + 5 * lineinterval, XPoint + (int) (176 * a / drawallscale), yYPoint / 2 + 5 * lineinterval, paintlostwave); // 轴线

        // 256
        canvas.drawLine(XPoint, yYPoint / 2 + 3 * lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 + 3 * lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 + 3 * lineinterval, XPoint + (int) (80 * a / drawallscale), yYPoint / 2 + 3 * lineinterval, paintoutwave); // 轴线
        canvas.drawLine(XPoint + (int) (80 * a / drawallscale), yYPoint / 2 + 3 * lineinterval, XPoint + (int) (120 * a / drawallscale), yYPoint / 2 + 3 * lineinterval, paintdoublewave); // 轴线
        canvas.drawLine(XPoint + (int) (120 * a / drawallscale), yYPoint / 2 + 3 * lineinterval, XPoint + 220 * a / drawallscale, yYPoint / 2 + 3 * lineinterval, paintoutwave); // 轴线

        // 0
        canvas.drawLine(XPoint, yYPoint / 2 + lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 + lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 + lineinterval, XPoint + (int) (80 * a / drawallscale), yYPoint / 2 + lineinterval, paintoutwave); // 轴线
        canvas.drawLine(XPoint + (int) (80 * a / drawallscale), yYPoint / 2 + lineinterval, XPoint + (int) (120 * a / drawallscale), yYPoint / 2 + lineinterval, paintdoublewave); // 轴线
        canvas.drawLine(XPoint + (int) (120 * a / drawallscale), yYPoint / 2 + lineinterval, XPoint + 220 * a / drawallscale, yYPoint / 2 + lineinterval, paintoutwave); // 轴线

    }

    private final void drawAllViewAxs(Canvas canvas, String[] datastr, float a, float b, int interval, boolean positive, String title, Float scale1) {
        // String[] datastrexe = exactpoint(datastr);
        String[] datastrexe = exactpoint2(datastr, intThreshold);
        // String[] datastrexe = datastr;
        float yYPoint = b + bottommargin;
        float XScale = (a) / (XLabel.length - 1);

        float YScale = (yYPoint) / (YLabel.length - 1);

        Paint paintLine = new Paint();
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setAntiAlias(true);// 去锯齿
        paintLine.setColor(Color.MAGENTA);// 颜色
        paintLine.setStrokeWidth(2);

        for (int i = 0; i < datastrexe.length - 1; i++) {

            if (datastrexe[i] == "0" && datastrexe[i + 1] == "0") {
            } else {
                //

                if (interval < 0) {
                    if (positive == true) {
                        canvas.drawLine(XPoint + (int) (i * scale1 * a / drawallscale), -1 * YCoordScale(datastrexe[i], yYPoint, YScale) + 3 * yYPoint / 2 - interval, XPoint + (int) ((i + 1) * scale1 * a / drawallscale), -1 * YCoordScale(datastrexe[i + 1], yYPoint, YScale) + 3 * yYPoint / 2 - interval, paintLine);

                    }

                } else {
                    if (positive == true) {
                        canvas.drawLine(XPoint + (int) (((i + (int) Math.round(intzero * NewScale)) * XScale / Integer.valueOf(XLabel[1])) / NewScale * a / drawallscale), YCoordScale(datastrexe[i], yYPoint, YScale) - yYPoint / 2 - interval, XPoint + (int) (((i + (int) Math.round(intzero * NewScale) + 1) * XScale / Integer.valueOf(XLabel[1])) / NewScale * a / drawallscale), YCoordScale(datastrexe[i + 1], yYPoint, YScale) - yYPoint / 2 - interval, paintLine);
                    } else {
                        canvas.drawLine(XLength - rightmargin - 1 * (XPoint - leftmargin + (i + (int) Math.round(intzero * NewScale)) * XScale / Integer.valueOf(XLabel[1])), YCoordScale(datastrexe[i], yYPoint, YScale) - yYPoint / 2 - interval, XLength - rightmargin - 1 * (XPoint - leftmargin + (i + (int) Math.round(intzero * NewScale) + 1) * XScale / Integer.valueOf(XLabel[1])), YCoordScale(datastrexe[i + 1], yYPoint, YScale) - yYPoint / 2 - interval, paintLine);
                    }
                }

            }

        }

    }

    // private String[] exactpoint(String[] datastr)
    // {
    //
    //
    // ArrayList<String> newdataarray = new ArrayList<String>();
    // newdataarray.add("0");
    // newdataarray.add(datastr[0]);
    // String[] newdatastr = new String[datastr.length];
    //
    // int j = 2;
    //
    // for (int i = 0; i < datastr.length - 2; )
    // {
    //
    // double dis = pointToLine(i, Integer.valueOf(datastr[i]),i+j,
    // Integer.valueOf(datastr[i+j]), i+j-1,
    // Integer.valueOf(datastr[i+j-1]));
    //
    // if (dis >= dissw)
    // {
    // newdataarray.add(String.valueOf(i+j-1));
    // newdataarray.add(datastr[i+j-1]);
    // i=i+j-1;
    // j=2;
    //
    // }
    // else
    // {
    // j++;
    // }
    //
    // if (i+j == datastr.length) break;
    //
    // }
    //
    // for (int i = 0;i<newdatastr.length;i++)
    // {
    //
    // newdatastr[i] = "0" ;
    //
    // }
    //
    //
    // for (int i = 0;i<newdataarray.size();i=i+2)
    // {
    // newdatastr[Integer.valueOf(newdataarray.get(i))]=
    // newdataarray.get(i+1);
    // }
    //
    // return newdatastr;
    // }

    private final void drawAllViewAxs1(Canvas canvas, float xlen, float ylen, int scale, String title) {

        int lineinterval = 15;// 通道线间隔
        XPoint = 0;

        float yYPoint = ylen; //下边界

        float XScale = (xlen) / (XLabel.length - 1);//x比例

        float YScale = (scale) / (YLabel.length - 1);//y比例

        // 画笔
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);// 去锯齿
        paint.setColor(Color.BLACK);// 颜色

        Paint paintdoublewave = new Paint();
        paintdoublewave.setStyle(Paint.Style.STROKE);
        paintdoublewave.setAntiAlias(true);// 去锯齿
        paintdoublewave.setColor(Color.RED);// 颜色
        paintdoublewave.setStrokeWidth(4);
        Paint paintoutwave = new Paint();
        paintoutwave.setStyle(Paint.Style.STROKE);
        paintoutwave.setAntiAlias(true);// 去锯齿
        paintoutwave.setColor(Color.BLUE);// 颜色
        paintoutwave.setStrokeWidth(4);

        Paint paintlostwave = new Paint();
        paintlostwave.setStyle(Paint.Style.STROKE);
        paintlostwave.setAntiAlias(true);// 去锯齿
        paintlostwave.setColor(Color.RED);// 颜色
        paintlostwave.setStrokeWidth(4);
        // 设置Y1轴
        canvas.drawLine(XPoint, yYPoint - (YLabel.length - 1) * YScale, XPoint, yYPoint, paint); // 左轴线

        // 设置Y2轴
        canvas.drawLine(XLength - rightmargin, yYPoint - (YLabel.length - 1) * YScale, XLength - rightmargin, yYPoint, paint); // 右轴线

        for (int i = 0; i < YLabel.length; i++) {
            canvas.drawLine(XPoint, yYPoint - i * YScale, XPoint + 5, yYPoint - i * YScale, paint); // 左刻度

            canvas.drawLine(XLength - rightmargin, yYPoint - i * YScale, XLength - rightmargin - 5, yYPoint - i * YScale, paint); // 右刻度

        }

        // 设置X轴
        canvas.drawLine(XPoint, yYPoint / 2 - lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 - lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 - lineinterval, XPoint + (int) (200 *xlen / drawallscale), yYPoint / 2 - lineinterval, paintoutwave); // 轴线

        canvas.drawLine(XPoint, yYPoint / 2 - 3 * lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 - 3 * lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 - 3 * lineinterval, XPoint + (int) (200 * xlen / drawallscale), yYPoint / 2 - 3 * lineinterval, paintoutwave); // 轴线

        canvas.drawLine(XPoint, yYPoint / 2 - 5 * lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 - 5 * lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 - 5 * lineinterval, XPoint + (int) (200 *xlen / drawallscale), yYPoint / 2 - 5 * lineinterval, paintoutwave); // 轴线

        // 512
        canvas.drawLine(XPoint, yYPoint / 2 + 5 * lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 + 5 * lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 + 5 * lineinterval, XPoint + (int) (70 * xlen / drawallscale), yYPoint / 2 + 5 * lineinterval, paintoutwave); // 轴线
        canvas.drawLine(XPoint + (int) (70 * xlen / drawallscale), yYPoint / 2 + 5 * lineinterval, XPoint + (int) (100 * xlen / drawallscale), yYPoint / 2 + 5 * lineinterval, paintdoublewave); // 轴线
        canvas.drawLine(XPoint + (int) (100 * xlen / drawallscale), yYPoint / 2 + 5 * lineinterval, XPoint + (int) (160 * xlen / drawallscale), yYPoint / 2 + 5 * lineinterval, paintoutwave); // 轴线
        canvas.drawLine(XPoint + (int) (160 * xlen / drawallscale), yYPoint / 2 + 5 * lineinterval, XPoint + (int) (176 * xlen / drawallscale), yYPoint / 2 + 5 * lineinterval, paintlostwave); // 轴线

        // 256
        canvas.drawLine(XPoint, yYPoint / 2 + 3 * lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 + 3 * lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 + 3 * lineinterval, XPoint + (int) (80 * xlen / drawallscale), yYPoint / 2 + 3 * lineinterval, paintoutwave); // 轴线
        canvas.drawLine(XPoint + (int) (80 * xlen / drawallscale), yYPoint / 2 + 3 * lineinterval, XPoint + (int) (120 * xlen / drawallscale), yYPoint / 2 + 3 * lineinterval, paintdoublewave); // 轴线
        canvas.drawLine(XPoint + (int) (120 * xlen / drawallscale), yYPoint / 2 + 3 * lineinterval, XPoint + 220 * xlen / drawallscale, yYPoint / 2 + 3 * lineinterval, paintoutwave); // 轴线

        // 0
        canvas.drawLine(XPoint, yYPoint / 2 + lineinterval, (XLabel.length - 1) * XScale - rightmargin, yYPoint / 2 + lineinterval, paint); // 轴线
        canvas.drawLine(XPoint, yYPoint / 2 + lineinterval, XPoint + (int) (80 * xlen / drawallscale), yYPoint / 2 + lineinterval, paintoutwave); // 轴线
        canvas.drawLine(XPoint + (int) (80 * xlen / drawallscale), yYPoint / 2 + lineinterval, XPoint + (int) (120 * xlen / drawallscale), yYPoint / 2 + lineinterval, paintdoublewave); // 轴线
        canvas.drawLine(XPoint + (int) (120 * xlen / drawallscale), yYPoint / 2 + lineinterval, XPoint + 220 * xlen / drawallscale, yYPoint / 2 + lineinterval, paintoutwave); // 轴线


        Paint paintLine = new Paint();
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setAntiAlias(true);// 去锯齿

        Log.d("testsys",String.valueOf(Data.length));
        for (int i = 0; i < Data.length - 1; i = i + 1) {


            if (XPoint + (i + (int) Math.round(intzero * NewScale) + 1) * XScale / Integer.valueOf(XLabel[1]) >= 0) {
                canvas.drawLine(XPoint + (i + (int) Math.round(intzero * NewScale)) * XScale / Integer.valueOf(XLabel[1]), YCoord(Data[i], yYPoint, YScale), XPoint + (i + (int) Math.round(intzero * NewScale) + 1) * XScale / Integer.valueOf(XLabel[1]), YCoord(Data[i + 1], yYPoint, YScale), paintLine);
            }

        }

    }

    private final void drawAView(Canvas canvas, String[] datastr, float a, float b, int scale, String title) {
        // canvas.drawColor(Color.WHITE);// 设置背景颜色
        String[] datastrarray = exactpoint2(datastr, intThreshold);
        // XLength = a;
        // YLength = b;

        XPoint = 0;

        if (datastrarray == null) {
            return;
        }

        String titletext = titlemaker(title);

        float yYPoint = b;
        // 刻度

        float XScale = (a) / (XLabel.length - 1);

        float YScale = (scale) / (YLabel.length - 1);

        // 画笔
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);// 去锯齿
        paint.setColor(Color.BLACK);// 颜色

        // 画虚线的画笔
        Paint paintVLine = new Paint();
        paintVLine.setStyle(Paint.Style.STROKE);
        paintVLine.setAntiAlias(true);// 去锯齿
        paintVLine.setColor(Color.BLACK);// 颜色

        Paint paintLine = new Paint();
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setAntiAlias(true);// 去锯齿

        switch (title) {
            case "0":

                paintLine.setColor(Color.CYAN);// 颜色
                break;
            case "256":

                paintLine.setColor(Color.BLUE);// 颜色
                break;
            case "512":

                paintLine.setColor(Color.BLACK);// 颜色
                break;

            case "4097":

                paintLine.setColor(Color.RED);// 颜色
                break;
            case "4098":

                paintLine.setColor(Color.MAGENTA);// 颜色
                break;
            case "4099":

                paintLine.setColor(Color.LTGRAY);// 颜色
                break;

            case "4353":

                paintLine.setColor(Color.GREEN);// 颜色
                break;

            case "4354":

                paintLine.setColor(Color.YELLOW);// 颜色
                break;
            case "4355":

                paintLine.setColor(Color.DKGRAY);// 颜色
                break;
            default:
                paintLine.setColor(Color.BLACK);// 颜色
                break;
        }

        // paintLine.setStrokeWidth(8);

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setAntiAlias(true);// 去锯齿
        paint1.setColor(Color.BLACK);
        paint.setTextSize(8); // 设置轴文字大小

        // 设置Y轴
        canvas.drawLine(XPoint, yYPoint - (YLabel.length - 1) * YScale, XPoint, yYPoint, paint); // 轴线

        for (int i = 0; i < YLabel.length; i++) {
            canvas.drawLine(XPoint, yYPoint - i * YScale, XPoint - 5, yYPoint - i * YScale, paint); // 刻度

            try {
                if (i == 1) {
                    // canvas.drawText(YLabel[i], XPoint -
                    // 18, yYPoint - i *
                    // YScale + 10, paint); // 文字
                }
            } catch (Exception e) {
            }

        }

        if (NewxLable != null && NewxLable.length > 0) {

            for (int i = 0; i < NewxLable.length - 1; i = i + 2) {

                canvas.drawLine(XPoint + NewxLable[i + 1] * XScale / Integer.valueOf(XLabel[1]), yYPoint, XPoint + NewxLable[i + 1] * XScale / Integer.valueOf(XLabel[1]), yYPoint + 5, paint); // 刻度
                canvas.drawText(Integer.toString(NewxLable[i]), XPoint + NewxLable[i + 1] * XScale / Integer.valueOf(XLabel[1]) - 20, yYPoint + 10, paint); // 文字

            }
        }

        // 设置X轴
        canvas.drawLine(XPoint, yYPoint, XPoint + (XLabel.length - 1) * XScale, yYPoint, paint); // 轴线

        // 绘制点
        for (int i = 0; i < datastrarray.length - 1; i = i + 1) {

            if (XPoint + (i + (int) Math.round(intzero * NewScale) + 1) * XScale / Integer.valueOf(XLabel[1]) >= 0) {
                canvas.drawLine(XPoint + (i + (int) Math.round(intzero * NewScale)) * XScale / Integer.valueOf(XLabel[1]), YCoord(datastrarray[i], yYPoint, YScale), XPoint + (i + (int) Math.round(intzero * NewScale) + 1) * XScale / Integer.valueOf(XLabel[1]), YCoord(datastrarray[i + 1], yYPoint, YScale), paintLine);
            }

        }

        paint.setTextSize(16);

        canvas.drawText(titletext, XLength - 60, yYPoint - 2, paint);

        if (drawkey != "999") {
            Paint paintLine1 = new Paint();
            paintLine1.setStyle(Paint.Style.STROKE);
            paintLine1.setAntiAlias(true);// 去锯齿

            paintLine1.setColor(Color.RED);
            canvas.drawLine(XPoint + ((int) Math.round(intthstart * NewScale)) * XScale / Integer.valueOf(XLabel[1]), yYPoint - 40 * YScale / Integer.valueOf(YLabel[1]), XPoint + ((int) Math.round((intthstart + intthlength) * NewScale)) * XScale / Integer.valueOf(XLabel[1]), yYPoint - 40 * YScale / Integer.valueOf(YLabel[1]), paintLine1); // 刻度

            String[] tempdata1 = new String[(int) Math.round(intthlength * NewScale)];
            System.arraycopy(datastrarray, (int) Math.round((intthstart - intzero) * NewScale), tempdata1, 0, tempdata1.length);
            int[] maxvalue = Tools.getMax(tempdata1);
            Paint paint2 = new Paint();
            paint2.setTextSize(30);
            paint2.setColor(Color.RED);
            canvas.drawText(Integer.toString(maxvalue[0]), XPoint + ((int) Math.round(intthstart * NewScale + maxvalue[1])) * XScale / Integer.valueOf(XLabel[1]), yYPoint - 50 * YScale / Integer.valueOf(YLabel[1]), paint2);

            Log.d("maxvalue", Integer.toString(maxvalue[0]));

            // canvas.drawText(String.valueOf(Math.round(intthstart)),
            // XPoint +
            // ((int) Math.round(intthstart * NewScale)) * XScale /
            // Integer.valueOf(XLabel[1]), yYPoint - 30 * YScale /
            // Integer.valueOf(YLabel[1]), paint2);

            canvas.drawText(Integer.toString(Math.round(intthstart + (int) Math.round(maxvalue[1] / NewScale))), XPoint + ((int) Math.round(intthstart * NewScale + maxvalue[1])) * XScale / Integer.valueOf(XLabel[1]), yYPoint - 30 * YScale / Integer.valueOf(YLabel[1]), paint2);
        }

    }

    private String[] exactpoint2(String[] datastr, int thres)// 抽稀
    {
        String[] newdatastr = new String[datastr.length];
        for (int i = 0; i < datastr.length; i++) {
            if (Integer.valueOf(datastr[i]) < thres) {
                newdatastr[i] = "0";
            } else {
                newdatastr[i] = datastr[i];
            }
        }
        return newdatastr;
    }

    // 初始化每个区间的颜色
    public void initGridColorLevel(ArrayList<Integer> list) {
        mGridColorLevel.clear();
        mGridColorLevel.addAll(list);
    }

    // 初始化每个区间的提示文字，如果不想显示可以设置""
    public void initGridLevelText(ArrayList<String> list) {
        mGridLevelText.clear();
        mGridLevelText.addAll(list);
    }

    // 初始化每个区间的提示文字颜色
    public void initGridTxtColorLevel(ArrayList<Integer> list) {
        mGridTxtColorLevel.clear();
        mGridTxtColorLevel.addAll(list);
    }

    // 初始化XY轴title
    public void initTitleXY(String x, String y) {
    }

    // 初始化X轴的坐标区间点值，可以不均等分
    public void initXLevelOffset(ArrayList<Integer> list) {
        mXLevel.clear();
        mXLevel.addAll(list);
    }

    // 初始化Y轴的坐标区间点值，可以不均等分
    public void initYLevelOffset(ArrayList<Integer> list) {
        mYLevel.clear();
        mYLevel.addAll(list);
    }

    private double lineSpace(int x1, int y1, int x2, int y2) {

        double lineLength = 0;

        lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)

                * (y1 - y2));

        return lineLength;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        try {
            super.onDraw(canvas);

        } catch (Exception e) {
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHight = getHeight();
    }


    public void setinfoaxs(String[] XLabels, int[] newxlabels, double newscales, String[] YLabels, TreeMap<String, int[]> AllData) {

        XLabel = XLabels;
        NewxLable = newxlabels;
        NewScale = newscales;
        YLabel = YLabels;

        XLength = getWidth();
        YLength = getHeight() - bottommargin;

        Data = Tools.intStringArrayPercent(AllData.get("512"), "512");

    }


    public void setinfo(String[] XLabels, int[] newxlabels, double newscales, String[] YLabels, TreeMap<String, int[]> AllData, String strTitle, String key, int thstart, int thlength, int zero, int Threshold) {

        if (AllData == null) {

            return;
        }

        if (AllData.size() == 0) {

            return;
        }

        XLabel = XLabels;
        NewxLable = newxlabels;
        NewScale = newscales;
        YLabel = YLabels;
        Title = strTitle;
        XLength = getWidth();
        YLength = getHeight() - bottommargin;

        drawkey = key;
        intthstart = thstart;
        intthlength = thlength;
        intzero = zero;
        intThreshold = Threshold;

        if (key == "999") {
            Datalist = AllData;
            Data = null;
        } else {
            if (AllData.containsKey(key) && AllData.get(key).length > 0) {

                Data = Tools.intStringArrayPercent(AllData.get(key), key);

            }
        }

    }


    // 设置XY轴顶角的title字体大小
    public void setTitleTextSize(int size) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO 自动生成的方法存根
        Log.d("testsys","2222");
        if (null == mDrawThread) {
            mDrawThread = new DrawThread();

            mDrawThread.start();
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO 自动生成的方法存根

    }

    private String titlemaker(String a) {
        String b = null;

        switch (a) {
            case "0":

                b = "37°+";
                break;
            case "256":

                b = "37°-";
                break;
            case "512":

                b = "0°";
                break;

            case "4097":

                b = "70°+1";
                break;
            case "4098":

                b = "70°+2";
                break;
            case "4099":

                b = "70°+3";
                break;

            case "4353":

                b = "70°-1";
                break;

            case "4354":

                b = "70°-2";
                break;
            case "4355":

                b = "70°-3";
                break;
            default:
                break;
        }

        return b;

    }

    // 设置当前比值
    public void updateValues() {

        postInvalidateDelayed(0);
    }

    private float YCoord(String y0, float yypoint, float yyscale) // 计算绘制时的Y坐标，无数据时返回-999
    {
        float y;
        try {

            y = Float.valueOf(y0);

            // if (y > 100)
            // {
            // y = 100;
            // }

        } catch (Exception e) {
            return -999; // 出错则返回-999
        }
        try {

            // return yypoint - y *
            // (getWidth()-20)/(YLabel.length-1) /
            // Integer.valueOf(YLabel[1]);
            return yypoint - y * yyscale / Integer.valueOf(YLabel[1]);

        } catch (Exception e) {
            return -999; // 出错则返回-999
        }

    }

    // 点到直线的最短距离的判断 点（x0,y0） 到由两点组成的线段（x1,y1） ,( x2,y2 )

    // 计算两点之间的距离

    private float YCoordScale(String y0, float yypoint, float yyscale) // 计算绘制时的Y坐标，无数据时返回-999
    {
        float y;
        try {

            y = Float.valueOf(y0) / wavescale;

            // if (y > 100)
            // {
            // y = 100;
            // }

        } catch (Exception e) {
            return -999; // 出错则返回-999
        }
        try {

            return yypoint - y * yyscale / Integer.valueOf(YLabel[1]);

        } catch (Exception e) {
            return -999; // 出错则返回-999
        }

    }

}
