package com.np.nirab.rockpaperscissorlizardspock;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {

	Button play;
	Spinner choice;
	String player_choice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		play = (Button) findViewById(R.id.btn_play);
		choice = (Spinner) findViewById(R.id.spin_playerchoice);
		ArrayAdapter<CharSequence> choicesadapter = ArrayAdapter
				.createFromResource(this, R.array.player_choices,
						R.layout.spinner_layout);
		choicesadapter.setDropDownViewResource(R.layout.spinner_layout);
		choice.setAdapter(choicesadapter);

		play.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				player_choice = choice.getItemAtPosition(
						choice.getSelectedItemPosition()).toString();
				rpsls(player_choice);

			}
		});

	}

	public void rpsls(String player_choice) {
		Random r = new Random();
		int player_number = nameToNumber(player_choice);
		int comp_number = r.nextInt(5);
		String comp_choice = numberToName(comp_number);
		int result = (comp_number - player_number) % 5;

		String resultMsg = " ";

		if (result == 0) {
			resultMsg = getString(R.string.tie);
		} else if (result == 1 || result == 2) {
			resultMsg = getString(R.string.comp_win);
		} else if (result == 3 || result == 4) {
			resultMsg = getString(R.string.player_win);
		}

		buildAlertMessage(player_choice, comp_choice, resultMsg);

	}

	private void buildAlertMessage(String player_choice, String comp_choice,
			String resultMsg) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"Your choice: " + player_choice + "\nComputer's coice: "
						+ comp_choice + "\n" + resultMsg).setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int id) {
						dialog.cancel();
					}
				});

		final AlertDialog alert = builder.create();
		alert.show();

	}

	public String numberToName(int number) {

		String name = "Rock";
		if (number == 0) {
			name = "Rock";
		} else if (number == 1) {
			name = "Spock";
		} else if (number == 2) {
			name = "Paper";
		} else if (number == 3) {
			name = "Lizard";
		} else if (number == 4) {
			name = "Scissors";
		}
		return name;

	}

	public int nameToNumber(String name) {

		int number = 0;
		if (name == "Rock") {
			number = 0;
		} else if (name == "Spock") {
			number = 1;
		} else if (name == "Paper") {
			number = 2;
		} else if (name == "Lizard") {
			number = 3;
		} else if (name == "Scissors") {
			number = 4;
		}
		return number;

	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

}
