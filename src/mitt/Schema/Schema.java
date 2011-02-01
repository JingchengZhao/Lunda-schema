package mitt.Schema;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Schema extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Toast.makeText(getApplicationContext(), "Plix W8", Toast.LENGTH_SHORT).show();
		image();
	}

	public void image() {
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
	final SharedPreferences settings = getSharedPreferences("Schema", 0);
    final SharedPreferences.Editor editor = settings.edit();
	final CharSequence[] peeps = {"Sikevux", "Zaso", "NV1C"};

		switch (item.getItemId()) {
		case R.id.changeSchool:
			final String[] schools = new String []{"Katte", "Polhem"};
			AlertDialog.Builder chooseSchool = new AlertDialog.Builder(this);
			chooseSchool.setTitle("Välj skola");
			chooseSchool.show();
			Toast.makeText(getApplicationContext(), "WIP", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.changePass:
			AlertDialog.Builder changePass = new AlertDialog.Builder(this);
			changePass.setTitle("Byt lösenord");
			changePass.show();
			Toast.makeText(getApplicationContext(), "WIP", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.changeId:
			AlertDialog.Builder changeId = new AlertDialog.Builder(this);
			changeId.setTitle("Byt ID");
			changeId.setItems(peeps, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int peep) {
						if(peep==0) {
							editor.putString("id", "{5363F05C-349F-4AF9-9AA9-55A48A3B628D}");
							editor.commit();
							image();
						}
						else if(peep==1) {
							editor.putString("id", "{FB80544D-2DDD-42AC-AB93-11B882BB868E}");
							editor.commit();
							image();
						}
						else if(peep==2) {
							editor.putString("id", "{5D7E8811-1511-4C34-A95B-240D712C7EE7}");
							editor.commit();
							image();
						}
					}
				});

			changeId.show();
			return true;
		case R.id.quit:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private String urlMaker() {
		SharedPreferences settings = getSharedPreferences("Schema", 0);
		StringBuilder siteStringBuilder = new StringBuilder(
				"http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?format=png&schoolid=");
		siteStringBuilder.append(settings.getString("schoolId", "19400"));
		siteStringBuilder.append("&id=");
		siteStringBuilder.append(settings.getString("id", "{5363F05C-349F-4AF9-9AA9-55A48A3B628D}"));
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