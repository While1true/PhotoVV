package www.wjx.test.photovv.PathView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.RawRes;
import android.util.AttributeSet;
import android.view.View;

import com.github.chrisbanes.photoview.OnViewTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import www.wjx.test.photovv.PathView.Drawable.OnPathRegionClickListener;
import www.wjx.test.photovv.PathView.Drawable.PathDrawable;

public class PathView extends PhotoView implements OnViewTapListener {
    private OnPathRegionClickListener onPathRegionClickListener;
    private OnViewTapListener onViewTapListener;
    private DefaultParse parse;

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, AttributeSet attr) {
        this(context, attr,0);
    }

    public PathView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        super.setOnViewTapListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable instanceof PathDrawable) {
            ((PathDrawable) drawable).setMatrix(getImageMatrix());
            ((PathDrawable) drawable).getScreenBond().set(0,0,getMeasuredWidth(),getMeasuredHeight());
            ((PathDrawable) drawable).setRegionClickListener(onPathRegionClickListener);
            drawable.draw(canvas);
        } else {
            super.onDraw(canvas);
        }
    }

    public void setListener(OnPathRegionClickListener listener) {
        this.onPathRegionClickListener = listener;
    }

    @Override
    public void setOnViewTapListener(OnViewTapListener listener) {
        this.onViewTapListener = listener;
        super.setOnViewTapListener(this);
    }

    @Override
    public void onViewTap(View view, float x, float y) {
        if (onViewTapListener != null) {
            onViewTapListener.onViewTap(view, x, y);
        }
        Drawable drawable = getDrawable();
        if (drawable instanceof PathDrawable) {
            ((PathDrawable) drawable).handlerOnClick(getImageMatrix(), (int) x, (int) y);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (parse != null) {
            setImageDrawable(new PathDrawable(parse.getData(), parse.getBound()));
            parse = null;
        }
    }

    public void setSVG(@RawRes int svg) {
        parse = new DefaultParse();
        parse.parse(getContext(), svg);
    }
}
