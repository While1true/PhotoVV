package www.wjx.test.photovv.PathView;

import android.content.Context;
import android.graphics.RectF;
import android.support.annotation.RawRes;

import java.util.List;

import www.wjx.test.photovv.PathView.PathItem.IPathItem;


public interface IParse<T extends IPathItem> {
    void parse(Context context, @RawRes int raw);
    RectF getBound();
    List<T> getData();
}
