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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Schema extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image();
	}
	
	public void image() {
		ImageView imageView = (ImageView) findViewById(R.id.schedulePic);
		Drawable drawable = getIt(urlMaker());
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		imageView.setAdjustViewBounds(true);
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
		switch (item.getItemId()) {
		case R.id.changeSchool:
			final CharSequence[] schools = {"Katte", "Polhem", "Spyken", "Vipan"};
			AlertDialog.Builder chooseSchool = new AlertDialog.Builder(this);
			chooseSchool.setTitle("Välj skola");
			chooseSchool.setItems(schools, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int school) {
						if(school==0) {
							editor.putString("schoolId", "19400");
							editor.putString("period", "2");
							editor.commit();
							image();
						}
						else if(school==1) {
							editor.putString("schoolId", "18600&code=91094");
							editor.putString("id", "{5B7E01D2-49E1-4321-AA7A-3D43D07E4E62}");
							editor.putString("period", "P2");
							editor.commit();
							image();
						}
						else if(school==2) {
							Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
						}
						else if(school==3) {
							Toast.makeText(getApplicationContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
						}
					}
				});
			chooseSchool.show();
			return true;
		case R.id.changePass:
			final EditText passwordEditInput = new EditText(this);
			AlertDialog.Builder changePass = new AlertDialog.Builder(this);
			changePass.setTitle("Byt lösenord");
			changePass.setMessage("Inget kommer hända");
			changePass.setView(passwordEditInput);
			changePass.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});
			changePass.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});
			changePass.show();
			return true;
		case R.id.changeId:
			final EditText idEditInput = new EditText(this);
			AlertDialog.Builder changeId = new AlertDialog.Builder(this);
			changeId.setTitle("Byt ID");
			changeId.setMessage("YYMMDD-NNNN");
			changeId.setView(idEditInput);
			changeId.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						String idSettingValue = idEditInput.getText().toString();
						editor.putString("id", idSettingValue + "|h%C3%B6stkyla");
						editor.commit();
						image();
					}
				});
			changeId.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
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
		siteStringBuilder.append(settings.getString("id", "{5363F05C-349F-4AF9-9AA9-55A48A3B628D}|h%C3%B6stkyla"));
		//siteStringBuilder.append(settings.getString("password", "|h%C3%B6stkyla"));
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
	/*	
	private void getIt(String url) {
		try {
			InputStream inputStream = (InputStream) new URL(url).getContent();
			File root = Environment.getExternalStorageDirectory();
			String localFilePath = root.getPath() + "/Schema.png";
			FileOutputStream fileOutputStream = new FileOutputStream(localFilePath, false);
			OutputStream outputStream = new BufferedOutputStream(fileOutputStream);
			byte[] buffer = new byte[1024];
			int byteRead = 0;

			while ((byteRead = is.read(buffer)) != -1) {
				outputStream.write(buffer, 0, byteRead);
			}

			fileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
			Drawable draw = Drawable.createFromStream(inputStream, "src name");
			return draw;
		}


		catch (Exception exception) {
			System.out.println("Exc=" + exception);
			return null;
			}
	*/

}

