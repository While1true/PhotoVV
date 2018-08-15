package www.wjx.test.photovv;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import www.wjx.test.photovv.PathView.Drawable.PathDrawable;
import www.wjx.test.photovv.PathView.PathItem.IPathItem;
import www.wjx.test.photovv.PathView.PathItem.IPathItemData;

/**
 * Created by ckckck on 2018/8/14.
 *
 * life is short , bugs are too many !
 */

public class WorldPathDrawable extends PathDrawable {

	private List<Name> mNames = new ArrayList<>();

	public WorldPathDrawable(List<Name> mNames, List<IPathItem> mIPathItems, RectF pathBound) {
		super(mIPathItems, pathBound);
		this.mNames = mNames;
	}

	@Override
	protected boolean willDrawBgAndFG() {
		return true;
	}

	@Override
	protected boolean isVisableDraw(Matrix matrix, RectF rectF) {
		float[] values = new float[9];
		matrix.getValues(values);
		float value = values[Matrix.MSCALE_X];
		if(value<2.5&&(rectF.width()<15||rectF.height()<15)){
			return false;
		}
		return true;
	}

	@Override
	public void drawBackground(Canvas canvas) {
	}

	@Override
	public void drawForground(Canvas canvas) {
		for (Name name : mNames) {
			Matrix matrix = new Matrix(name.getMatrix());
			matrix.postConcat(getMatrix());
			getPaint().setColor(name.getColor());
			float[] floats = new float[9];
			matrix.getValues(floats);
			float aFloat = floats[Matrix.MSCALE_X];
			if (aFloat < 2.5 && name.getColor() == 0xFF000000) {
				continue;
			}
			canvas.drawText(name.getName(), floats[Matrix.MTRANS_X], floats[Matrix.MTRANS_Y], getPaint());
		}
	}

	public List<Name> getNames() {
		return mNames;
	}

	public void setNames(List<Name> names) {
		mNames = names;
	}
}
