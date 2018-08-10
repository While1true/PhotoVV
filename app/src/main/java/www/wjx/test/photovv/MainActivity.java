package www.wjx.test.photovv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PhotoView photoView=findViewById(R.id.photo);
		photoView.setImageDrawable(new PathDrawable());
		photoView.setScaleLevels(0.5f,5f,10f);
	}
}
