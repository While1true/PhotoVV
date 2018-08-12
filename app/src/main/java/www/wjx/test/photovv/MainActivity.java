package www.wjx.test.photovv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.wjx.test.photovv.PathView.DefaultParse;
import www.wjx.test.photovv.PathView.PathView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DefaultParse defaultParse = new DefaultParse();
        defaultParse.parse(this, R.raw.china2);
        final PathView pathView = findViewById(R.id.photo);
        pathView.setSVG(R.raw.china2);
        pathView.setMaximumScale(8);


    }
}
