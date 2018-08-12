package www.wjx.test.photovv.PathView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.RawRes;
import android.support.v4.graphics.PathParser;
import android.util.TypedValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class DefaultParse implements IParse<DefaultPathItem> {
    private DocumentBuilder builder;
    private RectF rectF = new RectF();
    private List<DefaultPathItem> paths = new ArrayList<>();
    public DefaultParse() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parse(Context context, @RawRes int raw) {
        float left = -1;
        float top = -1;
        float right = -1;
        float bottom = -1;
        paths.clear();
        try {
            InputStream is = context.getResources().openRawResource(raw);
            Document document = builder.parse(is);
            Element element = document.getDocumentElement();
            NodeList pathList = element.getElementsByTagName("path");
            for (int i = 0; i < pathList.getLength(); i++) {
                Element itemElement = (Element) pathList.item(i);
                String pathData = itemElement.getAttribute("d");
                String name = itemElement.getAttribute("title");
                @SuppressLint("RestrictedApi") Path path = PathParser.createPathFromPathData(pathData);
                DefaultPathItemData defaultPathItemData = new DefaultPathItemData(path,name);
                defaultPathItemData.setSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics()));
                paths.add(new DefaultPathItem(defaultPathItemData));

                RectF bond = defaultPathItemData.getBond();
                left = left == -1 ? bond.left : Math.min(left, bond.left);
                top = left == -1 ? bond.top : Math.min(top, bond.top);
                right = left == -1 ? bond.right : Math.max(right, bond.right);
                bottom = left == -1 ? bond.bottom : Math.max(bottom, bond.bottom);
            }
            rectF.set(left, top, right, bottom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RectF getBound() {
        return rectF;
    }

    @Override
    public List getData() {
        return paths;
    }
}
