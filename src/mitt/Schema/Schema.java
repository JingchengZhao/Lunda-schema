package mitt.Schema;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class Schema extends Activity {
	protected static final String TAG = "moo";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageView imageView = (ImageView) findViewById(R.id.schedulePic);
		Drawable drawable = getIt(urlMaker());
		imageView.setImageDrawable(drawable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.changeSchool:
			quit();
			return true;
		case R.id.changePass:
			quit();
			return true;
		case R.id.changeId:
			quit();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private boolean quit() {
		return true;
	}

	private String urlMaker() {
		SharedPreferences settings = getSharedPreferences("Schema", 0);

		StringBuilder siteStringBuilder = new StringBuilder(
				"http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?format=png&schoolid=");
		siteStringBuilder.append(settings.getString("schoolId", "19400"));
		siteStringBuilder.append("&id=");
		siteStringBuilder.append(settings.getString("id", "930131-3939"));
		siteStringBuilder.append(settings.getString("password", "|h%C3%B6stkyla"));
		siteStringBuilder.append("&period=");
		siteStringBuilder.append(settings.getString("period", "2"));
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
		return site;
	}

	private int maxWidth() {
		Display sieez = getWindowManager().getDefaultDisplay();
		int width = sieez.getWidth();
		return width;
	}

	private int maxHeight() {
		Display sizz = getWindowManager().getDefaultDisplay();
		int height = sizz.getHeight();
		height = height + 300;
		return height;
	}

	private Drawable getIt(String url) {
		try {
			InputStream inputStream = (InputStream) new URL(url).getContent();
			Drawable draw = Drawable.createFromStream(inputStream, "src name");
			return draw;
		}

		catch (Exception exception) {
			System.out.println("Exc=" + exception);
			return null;
		}
	}
}