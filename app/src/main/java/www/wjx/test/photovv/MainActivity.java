package www.wjx.test.photovv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import www.wjx.test.photovv.PathView.DefaultParse;
import www.wjx.test.photovv.PathView.Drawable.PathDrawable;
import www.wjx.test.photovv.PathView.PathView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WorldParse defaultParse = new WorldParse();
        defaultParse.parse(this, R.raw.world);
        final PathView pathView = findViewById(R.id.photo);
        WorldPathDrawable drawable = new WorldPathDrawable(defaultParse.getNames(),defaultParse.getData(), defaultParse.getBound());
        pathView.setImageDrawable(drawable);
        pathView.setMaximumScale(8);


    }
}
