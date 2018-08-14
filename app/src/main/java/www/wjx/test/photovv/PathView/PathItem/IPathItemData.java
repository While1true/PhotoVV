package www.wjx.test.photovv.PathView.PathItem;


import android.graphics.Path;
import android.graphics.RectF;

public interface IPathItemData {
    Path getPath();
    RectF getBond();
    boolean isClickRegion();
}
