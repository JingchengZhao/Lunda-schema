package mitt.Schema;

import java.io.InputStream;
import java.net.URL;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Calendar;
import android.view.Display;

public class Schema extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		SharedPreferences settings = getSharedPreferences("Schema", 0);
		String schoolId = settings.getString("schoolId", "19400");
		
		String site = "http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?schoolid=" + schoolId + "&id=930131-3939&period=&week=" + weekNumber() + "&maxwidth=" + maxWidth() + "&maxheight=" + maxHeight() + "&width=" + maxWidth() + "&height="+ maxHeight();
        ImageView imageView = (ImageView) findViewById(R.id.ImageView01);
        Drawable drawable = getIt(site);
        imageView.setImageDrawable(drawable);
    }

	private int maxWidth() {
		Display sieez = getWindowManager().getDefaultDisplay();
		int width = sieez.getWidth();
		return width;
	}
	
	private int maxHeight() {
		Display sizz = getWindowManager().getDefaultDisplay();
		int height = sizz.getHeight();
		height = height - 10;
		return height;
	}

	private int weekNumber() {
		Calendar current = Calendar.getInstance();
		int week = current.get(Calendar.WEEK_OF_YEAR);
		week = week - 1;
		return week;
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
