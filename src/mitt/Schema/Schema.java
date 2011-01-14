package mitt.Schema;

import java.io.InputStream;
import java.net.URL;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.format.Time;
import android.view.Display;

public class Schema extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SharedPreferences settings = getSharedPreferences("Schema", 0);
		String schoolId = settings.getString("schoolId", "19400");
		String id = settings.getString("id", "930131-3939");
		String password = settings.getString("password", "|h%C3%B6st*********");
		String period = settings.getString("period", "2");

		StringBuilder siteStringBuilder = new StringBuilder("http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?schoolid=");
		siteStringBuilder.append(schoolId);
		siteStringBuilder.append("&id=");
		siteStringBuilder.append(id);
		siteStringBuilder.append(password);
		siteStringBuilder.append("&period=");
		siteStringBuilder.append(period);
		siteStringBuilder.append("&week=");
		siteStringBuilder.append("&maxwidth=");
		siteStringBuilder.append(maxWidth());
		siteStringBuilder.append("&maxheight=");
		siteStringBuilder.append(maxHeight());
		siteStringBuilder.append("&width=");
		siteStringBuilder.append(maxWidth());
		siteStringBuilder.append("&height=");
		siteStringBuilder.append(maxHeight());

		String site = siteStringBuilder.toString();
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
		return height;
	}

	private Drawable getIt(String url) {
		try {
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