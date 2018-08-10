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

	private final Paint mPaint;

	private List<IDrawableItem>mIDrawableItems=new ArrayList<>();

	private int selectIndex=-1;
	Region region = new Region();
	Region screenRegion = new Region();
	RectF rectF=new RectF();
	public List<IDrawableItem> getIDrawableItems() {
		return mIDrawableItems;
	}

	public void setIDrawableItems(List<IDrawableItem> IDrawableItems) {
		mIDrawableItems.clear();
		selectIndex=-1;
		mIDrawableItems.addAll(IDrawableItems);
	}

	public PathDrawable(){
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
	}
	@Override
	public void draw(@NonNull Canvas canvas) {
		canvas.drawRect(250+getBounds().left,250+getBounds().top,250+getBounds().left+500,250+getBounds().top+500,mPaint);
		for (IDrawableItem iDrawableItem : mIDrawableItems) {
			if(iDrawableItem.isNeedDraw()&&isInScreen(iDrawableItem.getPath(),canvas.getMatrix())){
				iDrawableItem.draw(canvas,mPaint,mIDrawableItems.indexOf(iDrawableItem)==selectIndex);
			}
		}
	}

	@Override
	public int getIntrinsicWidth() {
		return 1000;
	}

	@Override
	public int getIntrinsicHeight() {
		return 1000;
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
			if (iDrawableItem.isInRegion(matrix,x,y)){
				selectIndex=mIDrawableItems.indexOf(iDrawableItem);
				invalidateSelf();
				break;
			}
		}
	}
	private boolean isInScreen(Path path,Matrix matrix){
		region.setPath(path,screenRegion);
		rectF.set(0,0,getIntrinsicWidth(),getIntrinsicHeight());
		matrix.mapRect(rectF);
		return region.quickReject((int)rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom);
	}
	private Region getScreenRegion(){
		if(screenRegion.isEmpty()){
			screenRegion.set(0,0,getIntrinsicWidth(),getIntrinsicHeight());
		}
		return screenRegion;
	}
}
