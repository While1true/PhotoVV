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
    private String name;
    private float size=60;

    public DefaultPathItemData(Path path,String name) {
        this.path = path;
        this.color = Color.parseColor(getRandColorCode());
        this.name=name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    /**
     * 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
     * @return String
     */
    private static String getRandColorCode(){
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();
        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;
//        LogUtils.e("");
        return "#"+r+g+b;
    }
}
