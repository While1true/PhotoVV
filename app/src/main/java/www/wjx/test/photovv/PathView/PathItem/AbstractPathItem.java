package www.wjx.test.photovv.PathView.PathItem;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

public abstract class AbstractPathItem<T extends IPathItemData> implements IPathItem {

    protected  T pathData;
    protected boolean isNeedDraw=true;
    protected Path tempPath=new Path();
    private Region tempRegion = new Region();
    private Region tempRegion2 = new Region();
    private RectF rectF=new RectF();

    @Override
    public boolean isInRegion(Matrix matrix, int x, int y) {
        getPath().transform(matrix,tempPath);
        tempPath.computeBounds(rectF,true);
        tempRegion2.set((int)rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom);
        tempRegion.setPath(tempPath,tempRegion2);
        return tempRegion.contains(x,y);
    }

    @Override
    public void drawBackground(Canvas canvas, Matrix matrix, Paint paint, boolean isSelected) {

    }

    @Override
    public void drawForgrand(Canvas canvas, Matrix matrix, Paint paint, boolean isSelected) {

    }

    @Override
    public boolean isNeedDraw() {
        return isNeedDraw;
    }

    @Override
    public RectF getRectF() {
        return pathData.getBond();
    }

    @Override
    public Path getPath() {
        return pathData.getPath();
    }

    public void setPath(T pathData) {
        this.pathData = pathData;
    }

    public void setNeedDraw(boolean needDraw) {
        isNeedDraw = needDraw;
    }
    public T getPathData(){
        return pathData;
    }
}
