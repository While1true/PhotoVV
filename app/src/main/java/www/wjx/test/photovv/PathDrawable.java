package www.wjx.test.photovv;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.speech.RecognitionListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckckck on 2018/8/10.
 *
 * life is short , bugs are too many !
 */

public class PathDrawable extends Drawable implements Clickable {

	private Paint mPaint;

	private List<IDrawableItem> mIDrawableItems = new ArrayList<>();

	private PathRegionClickListener listener;
	private int selectIndex = -1;
	Region screenRegion = new Region();
	RectF  tempRectF    = new RectF();
	RectF pathBound;
	RectF screenBound;

	public List<IDrawableItem> getIDrawableItems() {
		return mIDrawableItems;
	}

	public PathDrawable(PathResult pathResult) {
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		setIDrawableItems(pathResult.getIDrawableItems());
		pathBound = pathResult.getPathRectF();
		screenBound = pathResult.getScreenRectF();
	}

	public void setIDrawableItems(List<IDrawableItem> IDrawableItems) {
		mIDrawableItems.clear();
		selectIndex = -1;
		mIDrawableItems.addAll(IDrawableItems);
	}

	private PathDrawable() {
	}

	@Override
	public void draw(@NonNull Canvas canvas) {
		//		canvas.drawRect(250+getBounds().left,250+getBounds().top,250+getBounds().left+500,250+getBounds().top+500,mPaint);
		for (IDrawableItem iDrawableItem : mIDrawableItems) {
			if (iDrawableItem.isNeedDraw() && isInScreen(iDrawableItem.getPath(), canvas.getMatrix())) {
				iDrawableItem.draw(canvas, mPaint, mIDrawableItems.indexOf(iDrawableItem) == selectIndex);
			}
		}
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
	public void setAlpha(int i) {

	}

	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter) {

	}

	@Override
	public int getOpacity() {
		return PixelFormat.OPAQUE;
	}

	@Override
	public void handlerOnClick(Matrix matrix, int x, int y) {
		for (IDrawableItem iDrawableItem : mIDrawableItems) {
			if (iDrawableItem.isInRegion(matrix, x, y)) {
				selectIndex = mIDrawableItems.indexOf(iDrawableItem);
				if (listener != null) {
					listener.onRegionClicked(selectIndex, iDrawableItem);
				}
				invalidateSelf();
				break;
			}
		}
	}

	private boolean isInScreen(Path path, Matrix matrix) {
		path.computeBounds(tempRectF, true);
		matrix.mapRect(tempRectF);
		return getScreenRegion().quickReject((int) tempRectF.left, (int) tempRectF.top, (int) tempRectF.right, (int) tempRectF.bottom);
	}

	private Region getScreenRegion() {
		if (screenRegion.isEmpty()) {
			screenRegion.set(0, 0, (int) screenBound.width(), (int) screenBound.height());
		}
		return screenRegion;
	}

	public void setRegionClickListener(PathRegionClickListener listener) {
		this.listener = listener;
	}
}
