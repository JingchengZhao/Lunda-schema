package mitt.Schema;

import java.io.InputStream;
import java.net.URL;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Schema extends Activity {
	private static final String TAG = "Schema";
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		SharedPreferences settings = getSharedPreferences("schoolId", 0);
		String schoolId = settings.getString("schoolId", "19400");
		
		String site = "http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?schoolid=" + schoolId + "&id=930131-3939&period=&week=3&maxwidth=545&maxheight=253&width=545&height=253";
        ImageView imageView = (ImageView) findViewById(R.id.ImageView01);
        Drawable drawable = getIt(site);
        imageView.setImageDrawable(drawable);
		
    }

    private Drawable getIt(String url)
    {
	try
	    {
		InputStream inputStream = (InputStream) new URL(url).getContent();
		Drawable draw = Drawable.createFromStream(inputStream, "src name");
		return draw;
	    } 
	catch (Exception exception) {
		System.out.println("Exc="+exception);
		return null;
	}
    }

}
