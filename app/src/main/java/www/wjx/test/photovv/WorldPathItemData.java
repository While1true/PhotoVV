package www.wjx.test.photovv;

import android.graphics.Path;
import android.graphics.RectF;

import www.wjx.test.photovv.PathView.DefaultPathItemData;
import www.wjx.test.photovv.PathView.PathItem.IPathItemData;

public class WorldPathItemData extends DefaultPathItemData{
 private boolean isCountry;
    public WorldPathItemData(Path path, boolean isfill, int color) {
        super(path, isfill, color);
        isCountry = true;
    }
    public WorldPathItemData(Path path, boolean isfill, int color,boolean isCountry) {
        super(path, isfill, color);
        this.isCountry=isCountry;
    }
    @Override
    public boolean isClickRegion() {
        return super.isClickRegion()&&isCountry;
    }
}
