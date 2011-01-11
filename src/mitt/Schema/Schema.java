package mitt.Schema;

import java.io.InputStream;
import java.net.URL;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Schema extends Activity {
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ImageView imageView =(ImageView)findViewById(R.id.ImageView01);
        Drawable drawable = getIt("http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?schoolid=19400&id=930131-3939|h%C3%B6stkyla&period=&week=3&maxwidth=545&maxheight=253&width=545&height=253");
        imageView.setImageDrawable(drawable);

    }

    private Drawable getIt(String url)
    {
	try
	    {
		InputStream is = (InputStream) new URL(url).getContent();
		Drawable d = Drawable.createFromStream(is, "src name");
		return d;
	    }catch (Exception e) {
	    System.out.println("Exc="+e);
	    return null;
	}
    }
}
