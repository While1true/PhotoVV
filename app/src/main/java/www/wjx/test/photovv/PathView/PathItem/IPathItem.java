package www.wjx.test.photovv.PathView.PathItem;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by ckckck on 2018/8/10.
 *
 * life is short , bugs are too many !
 */

public interface IPathItem {

	void drawBackground(Canvas canvas,Matrix matrix, Paint paint, boolean isSelected);
	void draw(Canvas canvas,Matrix matrix, Paint paint, boolean isSelected);
	void drawForgrand(Canvas canvas,Matrix matrix, Paint paint, boolean isSelected);

	boolean isInRegion(Matrix matrix, int x, int y);

	boolean isNeedDraw();

	RectF getRectF();

	Path getPath();
}
