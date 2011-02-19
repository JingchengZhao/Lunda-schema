/*
* CDDL HEADER START
*
* The contents of this file are subject to the terms of the
* Common Development and Distribution License (the "License").
* You may not use this file except in compliance with the License.
*
* You can obtain a copy of the license in the LICENSE file
* or at http://dev.sikevux.se/LICENSE.txt
* See the License for the specific language governing permissions
* and limitations under the License.
*
* When distributing Covered Code, include this CDDL HEADER in each
* file and include the License file LICENSE.
* If applicable, add the following below this CDDL HEADER, with the
* fields enclosed by brackets "[]" replaced with your own identifying
* information: Portions Copyright [yyyy] [name of copyright owner]
*
* CDDL HEADER END
* 
*
* Copyright 2011 Patrik Greco All rights reserved.
* Use is subject to license terms.
*/

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
import android.view.Window;
import android.webkit.WebChromeClient;
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
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.main);
		getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON); 
		final SharedPreferences settings = getSharedPreferences("Schema", 0);
		final SharedPreferences.Editor editor = settings.edit();
		if(settings.getBoolean("firstRun", true)){
			changeSchool();
			changeId();
			editor.putBoolean("firstRun", false);
			editor.commit();
		}
		else {
			image();
		}
	}

	public void image() {
		final Activity progressBarActivity = this;
		WebView webView = (WebView) findViewById(R.id.webview);
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView webview, int progress) {
				progressBarActivity.setProgress(progress * 100);
			}
		});
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

	public void changeSchool() {
		final SharedPreferences settings = getSharedPreferences("Schema", 0);
		final SharedPreferences.Editor editor = settings.edit();
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
						Toast.makeText(getApplicationContext(), "Malia mig länken till schemat så det kan funka här :)", Toast.LENGTH_SHORT).show();
					}
				}
			});
		chooseSchool.show();
	}

	public void changePass() {
		final SharedPreferences settings = getSharedPreferences("Schema", 0);
		final SharedPreferences.Editor editor = settings.edit();
		final EditText passwordEditInput = new EditText(this);
		AlertDialog.Builder changePass = new AlertDialog.Builder(this);
		changePass.setTitle("Byt lösenord");
		changePass.setMessage("Inget kommer hända");
		changePass.setView(passwordEditInput);
		changePass.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				}
			});
		changePass.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				}
			});
		changePass.show();
		}

	public void changeId() {
		final SharedPreferences settings = getSharedPreferences("Schema", 0);
		final SharedPreferences.Editor editor = settings.edit();
		final EditText idEditInput = new EditText(this);
		AlertDialog.Builder changeId = new AlertDialog.Builder(this);
		changeId.setTitle("Byt ID");
		changeId.setMessage("Skriv in något av följande\n-Personnummer (YYMMDD-NNNN)\n-Klass\n-Lärarsignatur");
		changeId.setView(idEditInput);
		changeId.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String idSettingValue = idEditInput.getText().toString();
				editor.putString("id", idSettingValue + "|h%C3%B6stkyla");
				editor.commit();
				image();
			}
		});
		changeId.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		changeId.show();
	}

	public void changeWeek() {
		final SharedPreferences settings = getSharedPreferences("Schema", 0);
		final SharedPreferences.Editor editor = settings.edit();
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
		changeWeek.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		changeWeek.show();
	}

	public void changePeriod() {
		final SharedPreferences settings = getSharedPreferences("Schema", 0);
		final SharedPreferences.Editor editor = settings.edit();
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
	}

	public void about() {
		AlertDialog.Builder aboutBuilder = new AlertDialog.Builder(this);
		aboutBuilder.setTitle("Om");
		aboutBuilder.setMessage("Skapad av Patrik 'Sikevux' Greco\n<sikevux@sikevux.se>\nGillar du appen så går jag i NV1C på Katte.\nSurprise me ^_^");
		aboutBuilder.setNegativeButton("Tillbaka", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		aboutBuilder.show();
	}

	public void license() {
		AlertDialog.Builder licenseBuilder = new AlertDialog.Builder(this);
		licenseBuilder.setTitle("License");
		licenseBuilder.setMessage("Copyright 2011 Patrik Greco All rights reserved.\nUse is subject to license terms.\nA copy of the license can be retrived at http://dev.sikevux.se/LICENSE.txt");
		licenseBuilder.setNegativeButton("Tillbaka", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});
		licenseBuilder.show();
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.changeSchool:
			changeSchool();
			return true;
			/*		case R.id.changePass:
			changePass();
			license();
			return true;*/
		case R.id.license:
			license();
			return true;
		case R.id.changeId:
			changeId();
			return true;
		case R.id.changeWeek:
			changeWeek();
			return true;
		case R.id.changePeriod:
			changePeriod();
			return true;
		case R.id.about:
			about();
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
}