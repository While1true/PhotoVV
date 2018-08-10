package yuer.svg.com.mysvgyuyahaodrawchinamap.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextUtils;

import www.wjx.test.photovv.Clickable;
import www.wjx.test.photovv.IDrawableItem;
import yuer.svg.com.mysvgyuyahaodrawchinamap.utils.LogUtils;

import static yuer.svg.com.mysvgyuyahaodrawchinamap.view.ChaneseAllMapView.getRandColorCode;


/**
 * 类功能描述：</br>
 *
 * @author 于亚豪
 * @version 1.0 </p> 修改时间：2018/8/2</br> 修改备注：</br>
 */
public class ItemProvins extends IBean<ItemProvins> implements IDrawableItem {

    Region tempRegion = new Region();
    private int color=Color.parseColor(getRandColorCode());
    private Path path;
    private boolean isNeedDraw=true;
    private String provinceName;
    private Paint paintText;
    public ItemProvins (Path path){
        this.path = path;
        paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setAntiAlias(true);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setStrokeWidth(1);
    }
    public ItemProvins (Path path, String provinceName){
        this.path = path;
        this.provinceName = provinceName;
        paintText = new Paint();
        paintText.setColor(Color.RED);
        paintText.setAntiAlias(true);
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setStrokeWidth(1);
    }


    public void drawChinaMap(Canvas canvas, Paint paint, boolean isSelected) {
        if(isNeedDraw){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
            canvas.drawPath(path,paint);
            RectF rectF = new RectF();
            path.computeBounds(rectF,true);
            if(isSelected){
                paint.clearShadowLayer();
                paint.setStrokeWidth(2);
                canvas.drawPath(path, paint);
                paint.setStyle(Paint.Style.STROKE);
                int strokeColor = Color.RED;
                paintText.setColor(strokeColor);
                paint.setColor(strokeColor);
                canvas.drawPath(path, paint);
                canvas.drawRect(rectF,paintText);
            }
            if(!TextUtils.isEmpty(provinceName)){
                LogUtils.e("name",rectF.left+"  " +rectF.top+"  " +rectF.right+"  " +rectF.bottom);
                float widthText = paint.measureText(provinceName);
                canvas.drawText(provinceName,rectF.centerX() - widthText/2,rectF.centerY() - paint.getStrokeWidth(),paintText);
            }
        }/*else{
            RectF rectF = new RectF();
            path.computeBounds(rectF,true);
            if(isSelected){
                paint.clearShadowLayer();
                paint.setStrokeWidth(1);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(color);
                canvas.drawPath(path, paint);
                paint.setStyle(Paint.Style.STROKE);
                int strokeColor = 0xFFD0E8F4;
                paint.setColor(strokeColor);
                canvas.drawPath(path, paint);
                canvas.drawRect(rectF,paintText);
            }
            if(!TextUtils.isEmpty(provinceName)){
                LogUtils.e("name",rectF.left+"  " +rectF.top+"  " +rectF.right+"  " +rectF.bottom);
                float widthText = paint.measureText(provinceName);
                canvas.drawText(provinceName,rectF.centerX() - widthText/2,rectF.centerY() - paint.getStrokeWidth(),paintText);
            }
        }*/

    }


    public void setDrawableColor(int color) {
        this.color = color;
    }


    @Override
    ItemProvins parse() {
        return null;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public void draw(Canvas canvas, Paint paint, boolean isSelected) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawPath(path,paint);
        if(isSelected){
            paint.clearShadowLayer();
            paint.setStrokeWidth(2);
            canvas.drawPath(path, paint);
            paint.setStyle(Paint.Style.STROKE);
            int strokeColor = Color.RED;
            paintText.setColor(strokeColor);
            paint.setColor(strokeColor);
            canvas.drawPath(path, paint);
        }
    }

    @Override
    public boolean isInRegion(Matrix matrix, int x, int y) {
        Path path=new Path();
        getPath().transform(matrix,path);
        RectF rectF=new RectF();
        path.computeBounds(rectF,true);
        tempRegion.setPath(path,new Region((int)rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom));
        return tempRegion.contains(x,y);
    }

    public boolean isNeedDraw() {
        return true;
    }

    @Override
    public RectF getRectF() {
        RectF bounds = new RectF();
        path.computeBounds(bounds, true);
        return bounds;
    }

    public void setNeedDraw(boolean needDraw) {
        isNeedDraw = needDraw;
    }

    public boolean handlerToucthOnclick(float rawX, float rawY) {
        boolean result = false;
        RectF rectF = new RectF();
        path.computeBounds(rectF,true);
        Region region = new Region();
        region.setPath(path,new Region((int)rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom));
        if(region.contains((int)rawX,(int)rawY) ){
            result =true;
        }
        return result;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
