package mitt.Schema;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Schema extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image();
	}

	public void image() {
		WebView webView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true); 
		webView.loadUrl(urlMaker());
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
							editor.putInt("school", 0);
							editor.commit();
							image();
						}
						else if(school==1) {
							editor.putInt("school", 1);
							editor.commit();
							image();
						}
						else if(school==2) {
							editor.putInt("school", 2);
							editor.commit();
							image();
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
		case R.id.changeWeek:
			final EditText weekEditInput = new EditText(this);
            AlertDialog.Builder changeWeek = new AlertDialog.Builder(this);
            changeWeek.setTitle("Byt Vecka");
            changeWeek.setMessage("0=Denna veckan");
            changeWeek.setView(weekEditInput);
            changeWeek.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String weekSettingValue = weekEditInput.getText().toString();
                        editor.putInt("week", Integer.parseInt(weekSettingValue));
                        editor.commit();
                        image();
                    }
                });
            changeWeek.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

            changeWeek.show();
            return true;
		case R.id.changePeriod:
			final CharSequence[] periods = {"Använd Veckor", "Period 1", "Period 2"};
			AlertDialog.Builder choosePeriod = new AlertDialog.Builder(this);
			choosePeriod.setTitle("Välj period");
			choosePeriod.setItems(periods, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int period) {
						if(period == 0) {
							editor.putInt("period", 0);
							editor.commit();
							image();
						}
						else if(period == 1) {
							editor.putInt("period", 1);
							editor.commit();
							image();
						}
						else if(period == 2) {
							editor.putInt("period", 2);
							editor.commit();
							image();
						}
					}
				});
			choosePeriod.show();
			return true;
		case R.id.about:
			AlertDialog.Builder aboutBuilder = new AlertDialog.Builder(this);
			aboutBuilder.setTitle("Om");
			aboutBuilder.setMessage("Skapad av Patrik 'Sikevux' Greco");
			aboutBuilder.setNegativeButton("Tillbaka", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});
			aboutBuilder.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public final Calendar getWeekNumber = Calendar.getInstance();

	private String urlMaker() {
		SharedPreferences settings = getSharedPreferences("Schema", 0);
		int school = settings.getInt("school", 0);
		StringBuilder siteStringBuilder = new StringBuilder(
				"http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?format=png&schoolid=");
		if(school == 0) {
			siteStringBuilder.append("19400");
		}
		else if(school == 1) {
			siteStringBuilder.append("18600");
		}
		else if(school == 2) {
			siteStringBuilder.append("26900");
		}
		siteStringBuilder.append("&id=");
		siteStringBuilder.append(settings.getString("id", "{5363F05C-349F-4AF9-9AA9-55A48A3B628D}|h%C3%B6stkyla"));
		siteStringBuilder.append("&period=");
		if(settings.getInt("period", 0) != 0) {
			if(school == 0) {
				siteStringBuilder.append(String.valueOf(settings.getInt("period", 0)));
			}
			else {
				siteStringBuilder.append("P" + String.valueOf(settings.getInt("period", 0)));
			}

		}
		siteStringBuilder.append("&week=");
		if(settings.getInt("period", 0) == 0) {
			int currentWeekNumber = getWeekNumber.get(3);
			if(settings.getInt("week", 0) != 0) {
			currentWeekNumber = settings.getInt("week", 0);
			}
		siteStringBuilder.append(currentWeekNumber);
		}
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
		Display getWidth = getWindowManager().getDefaultDisplay();
		int width = getWidth.getWidth();
		width = width + 150;
		return width;
	}

	private int maxHeight() {
		Display getHeight = getWindowManager().getDefaultDisplay();
		int height = getHeight.getHeight();
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

