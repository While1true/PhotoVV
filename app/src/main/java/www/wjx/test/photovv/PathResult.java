package www.wjx.test.photovv;

import android.graphics.RectF;

import java.util.List;

/**
 * Created by ckckck on 2018/8/10.
 *
 * life is short , bugs are too many !
 */

public class PathResult<T extends IDrawableItem> {

	private RectF               mPathRectF;
	private RectF               mScreenRectF;
	private List<T> mIDrawableItems;

	public PathResult() {
	}

	public PathResult(RectF mPathRectF,RectF mScreenRectF, List<T> IDrawableItems) {
		this.mPathRectF = mPathRectF;
		this.mScreenRectF = mScreenRectF;
		mIDrawableItems = IDrawableItems;
	}

	public RectF getPathRectF() {
		return mPathRectF;
	}

	public void setPathRectF(RectF pathRectF) {
		mPathRectF = pathRectF;
	}

	public RectF getScreenRectF() {
		return mScreenRectF;
	}

	public void setScreenRectF(RectF screenRectF) {
		mScreenRectF = screenRectF;
	}


	public List<T> getIDrawableItems() {
		return mIDrawableItems;
	}

	public void setIDrawableItems(List<T> IDrawableItems) {
		mIDrawableItems = IDrawableItems;
	}

}
