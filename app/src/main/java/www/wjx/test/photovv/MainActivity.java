package www.wjx.test.photovv;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

import yuer.svg.com.mysvgyuyahaodrawchinamap.view.ChaneseAllMapView;

public class MainActivity extends AppCompatActivity {
	@SuppressLint("HandlerLeak")
	Handler mHandler =new Handler(){
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if(msg.what==200){
			PathResult obj = (PathResult) msg.obj;
			PhotoView photoView=findViewById(R.id.photo);
			photoView.setImageDrawable(new PathDrawable(obj));
			photoView.setMaximumScale(10);
		}
	}
};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new ChaneseAllMapView(this).setcc(new ChaneseAllMapView.VV() {
			@Override
			public void Result(PathResult result) {
				Message message = new Message();
				message.obj=result;
				message.what=200;
				mHandler.sendMessage(message);
			}
		});

	}
}
