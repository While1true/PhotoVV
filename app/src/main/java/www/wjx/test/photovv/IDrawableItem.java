package www.wjx.test.photovv;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

/**
 * Created by ckckck on 2018/8/10.
 *
 * life is short , bugs are too many !
 */

public interface IDrawableItem {

	void draw(Canvas canvas, Paint paint, boolean isSelected);

	boolean isInRegion(Matrix matrix, int x, int y);

	boolean isNeedDraw();

	RectF getRectF();

	Path getPath();
}
