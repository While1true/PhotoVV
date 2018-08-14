package www.wjx.test.photovv;

import android.graphics.Canvas;

import www.wjx.test.photovv.PathView.Drawable.PathDrawable;

/**
 * Created by ckckck on 2018/8/14.
 *
 * life is short , bugs are too many !
 */

public class WorldPathDrawable extends PathDrawable {

	@Override
	protected boolean willDrawBgAndFG() {
		return true;
	}

	@Override
	public void drawBackground(Canvas canvas) {
	}

	@Override
	public void drawForground(Canvas canvas) {
	}
}
