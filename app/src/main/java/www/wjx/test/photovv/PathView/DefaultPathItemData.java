package www.wjx.test.photovv.PathView;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;

import java.util.Random;

import www.wjx.test.photovv.PathView.PathItem.IPathItemData;

public class DefaultPathItemData implements IPathItemData {
    private Path path;
    private RectF rectF = new RectF();
    private int color;
    private float size=60;
    private boolean isfill=true;

    public DefaultPathItemData(Path path,boolean isfill,int color) {
        this.path = path;
        this.color = color;
        this.isfill=isfill;
        path.computeBounds(rectF, true);
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public RectF getBond() {
        return rectF;
    }

    @Override
    public boolean isClickRegion() {
        return isfill;
    }

    public boolean isIsfill() {
        return isfill;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setIsfill(boolean isfill) {
        this.isfill = isfill;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

}
