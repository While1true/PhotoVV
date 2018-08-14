package www.wjx.test.photovv;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.RawRes;
import android.support.v4.graphics.PathParser;
import android.util.TypedValue;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import www.wjx.test.photovv.PathView.DefaultPathItem;
import www.wjx.test.photovv.PathView.DefaultPathItemData;
import www.wjx.test.photovv.PathView.IParse;


public class WorldParse implements IParse<DefaultPathItem> {

	private DocumentBuilder builder;
	private RectF                 rectF = new RectF();
	private List<DefaultPathItem> paths = new ArrayList<>();
	private List<Name> mNames = new ArrayList<>();

	public WorldParse() {
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
		mNames.clear();
		try {
			InputStream is = context.getResources().openRawResource(raw);
			Document document = builder.parse(is);
			Element element = document.getDocumentElement();
			NodeList pathListx = element.getElementsByTagName("g");
			for (int x = 0; x < pathListx.getLength(); x++) {
				Element itemElementx = (Element) pathListx.item(x);
				String id = itemElementx.getAttribute("id");
				boolean isCountry = false;
				if ("countries".equals(id)) {
					isCountry = true;
				}
				NodeList pathList = itemElementx.getElementsByTagName("path");
				for (int i = 0; i < pathList.getLength(); i++) {
					try {
						Element itemElement = (Element) pathList.item(i);
						String pathData = itemElement.getAttribute("d");
						String fill = itemElement.getAttribute("fill");
						boolean isfill = true;
						int color = 0;
						if (fill == null || fill.equals("none")) {
							isfill = false;
							String stoken = itemElement.getAttribute("stroke");

							color = Color.parseColor(stoken);
						} else {
							color = Color.parseColor(fill);
						}
						@SuppressLint("RestrictedApi") Path path = PathParser.createPathFromPathData(pathData);
						WorldPathItemData defaultPathItemData = new WorldPathItemData(path, isfill,
																					  color == 0 ? Color.parseColor(getRandColorCode()) :
																					  color, isCountry);
						defaultPathItemData.setSize(
							TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, context.getResources().getDisplayMetrics()));
						paths.add(new DefaultPathItem(defaultPathItemData));
						if (paths.size() == 275) {
							System.out.println();
						}
						RectF bond = defaultPathItemData.getBond();
						left = left == -1 ? bond.left : Math.min(left, bond.left);
						top = left == -1 ? bond.top : Math.min(top, bond.top);
						right = left == -1 ? bond.right : Math.max(right, bond.right);
						bottom = left == -1 ? bond.bottom : Math.max(bottom, bond.bottom);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				rectF.set(left, top, right, bottom);
			}
			NodeList texts = element.getElementsByTagName("text");

			for (int i = 0; i < texts.getLength(); i++) {
				try {
					Element item = (Element) texts.item(i);
					String transform = item.getAttribute("transform");
					Element itemc = (Element) item.getFirstChild();
					String fill = itemc.getAttribute("fill");
					String nodeValue = itemc.getNodeValue();
					mNames.add(new Name(nodeValue,transform,Color.parseColor(fill)));
				} catch (DOMException e) {
					e.printStackTrace();
				}
			}

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

	/**
	 * 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
	 *
	 * @return String
	 */
	protected static String getRandColorCode() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();
		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;
		//        LogUtils.e("");
		return "#" + r + g + b;
	}
}
