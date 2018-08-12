package www.wjx.test.photovv.PathView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;

import www.wjx.test.photovv.PathView.PathItem.AbstractPathItem;

public class DefaultPathItem extends AbstractPathItem<DefaultPathItemData> {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF=new RectF();

    public DefaultPathItem(DefaultPathItemData pathItemData){
        setPath(pathItemData);
    }
    @Override
    public void draw(Canvas canvas, Matrix matrix, Paint paint, boolean isSelected) {
        mPaint.set(paint);
        mPaint.setColor(getPathData().getColor());
        tempPath.reset();
        getPath().transform(matrix, tempPath);

        canvas.drawPath(tempPath, mPaint);
        if (isSelected) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.RED);
            canvas.drawPath(tempPath, mPaint);
        }
      }

    @Override
    public void drawForgrand(Canvas canvas, Matrix matrix, Paint paint, boolean isSelected) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(getPathData().getSize());
        tempPath.computeBounds(rectF,true);
        mPaint.setStrokeWidth(5);

        float x = rectF.left + rectF.width() / 2 - mPaint.measureText(getPathData().getName()) / 2;
        float y = rectF.top + rectF.height() / 2;
        mPaint.setColor(0x22FFFFFF);
        float v = mPaint.measureText(getPathData().getName()) / getPathData().getName().length();
        canvas.drawRect(x,y-v,x+mPaint.measureText(getPathData().getName()),y,mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText(getPathData().getName(), x, y,mPaint);
    }
}
