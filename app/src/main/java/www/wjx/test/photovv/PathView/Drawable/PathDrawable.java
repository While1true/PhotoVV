package www.wjx.test.photovv.PathView.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import www.wjx.test.photovv.PathView.PathItem.IPathItem;

public class PathDrawable extends Drawable implements IPathClickable {

    private List<IPathItem> mIPathItems = new ArrayList<>();

    private int selectIndex = -1;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private OnPathRegionClickListener listener;

    private RectF pathBound = new RectF();
    private Rect screenBond = new Rect();
    private Matrix matrix = new Matrix();
    private Path tempPath = new Path();

    RectF rectF = new RectF();
    private Region region = new Region();
    private Region region2 = new Region();

    public PathDrawable() {

    }

    public void setTextSize(int px) {
        mPaint.setTextSize(px);
    }

    public PathDrawable(List<IPathItem> mIPathItems, RectF pathBound, Rect screenBond) {
        this.mIPathItems = mIPathItems;
        this.pathBound = pathBound;
        this.screenBond.set(screenBond);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int save = canvas.save();
        canvas.clipRect(screenBond);
        int size = mIPathItems.size();

        for (int i = 0; i < size; i++) {
            IPathItem iPathItem = mIPathItems.get(i);
            if (iPathItem.isNeedDraw()) {
                iPathItem.drawBackground(canvas, matrix, mPaint, i == selectIndex);
            }
        }

        for (int i = 0; i < size; i++) {
            IPathItem iPathItem = mIPathItems.get(i);
            if (iPathItem.isNeedDraw()) {
                iPathItem.draw(canvas, matrix, mPaint, i == selectIndex);
            }
        }
        for (int i = 0; i < size; i++) {
            IPathItem iPathItem = mIPathItems.get(i);
            if (iPathItem.isNeedDraw()) {
                iPathItem.drawForgrand(canvas, matrix, mPaint, i == selectIndex);
            }
        }
        canvas.restoreToCount(save);
    }
    private boolean isInScreen(Path path, Matrix matrix) {
        tempPath.reset();
        path.transform(matrix, tempPath);
        tempPath.computeBounds(rectF, true);
        region2.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        region.setPath(tempPath, region2);
        return !region.quickReject(screenBond);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) pathBound.width();
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) pathBound.height();
    }

    @Override
    public void handlerOnClick(Matrix matrix, int x, int y) {
        for (IPathItem iPathItem : mIPathItems) {
            if (iPathItem.isInRegion(matrix, x, y)) {
                selectIndex = mIPathItems.indexOf(iPathItem);
                if (listener != null) {
                    listener.onRegionClicked(selectIndex, iPathItem);
                }
                invalidateSelf();
                break;
            }
        }
    }

    public List<IPathItem> getmIPathItems() {
        return mIPathItems;
    }

    public void setmIPathItems(List<IPathItem> mIPathItems, RectF pathBound, Rect screenBond) {
        this.mIPathItems = mIPathItems;
        selectIndex = -1;
        this.pathBound = pathBound;
        this.screenBond.set(screenBond);
        invalidateSelf();
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public void setRegionClickListener(OnPathRegionClickListener listener) {
        this.listener = listener;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
