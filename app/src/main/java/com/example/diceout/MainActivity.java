package com.example.diceout;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //The text field that holds the roll result in the form of text.
    TextView rollResult;

    //Variable in order to store the score
    int score;

    //Field to hold roll button
    Button rollButton;

    //Genereate a random number
    Random ran;

    //hold value of die
    int die1,die2,die3;

    //Field in order to hold the score
    TextView scoreText;

    //Array list to hold all three value of the dice
    ArrayList<Integer> dice;

    //Array list to hold all three dice ijmages
    ArrayList<ImageView> diceImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //set initial variable of score as 0.
        score = 0;

        rollResult = (TextView) findViewById(R.id.rollResult);
        rollButton = (Button) findViewById(R.id.rollButton);

        scoreText = (TextView) findViewById(R.id.scoretext);

        //Initialize the random object
        ran = new Random();

        //Create ArrayList container for the dice values
        dice = new ArrayList<Integer>();

        ImageView die1Image = (ImageView) findViewById(R.id.dice1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.dice2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.dice3Image);

        diceImageViews = new ArrayList<ImageView>();

        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);

        //Display a welcome message
        Toast.makeText(getApplicationContext(),"Welcome to the DiceOut Game!!!", Toast.LENGTH_SHORT).show();
    }

    public void rollDice(View v)
    {
        //Roll dice
        die1 = ran.nextInt(6)+1;
        die2 = ran.nextInt(6)+1;
        die3 = ran.nextInt(6)+1;

        //clear the vlaues stored in arraylist in order to add another value in the arraylist.
        dice.clear();

        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet = 0; dieOfSet < 3;dieOfSet++)
        {
            String imageName = "die_" + dice.get(dieOfSet) + ".png";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //rollResult.setText("Button Clicked!!");

        /*
        int num = ran.nextInt(6)+1;
        String randomValue = "Number Generated:" + num;
        Toast.makeText(getApplicationContext(),randomValue,Toast.LENGTH_SHORT).show();
        */

        //Roll Die 1
        //die1 = ran.nextInt(6)+1;

        //Print message with the result
        String msg; //= "You rolled a " + die1 + ", a " + die2 + ", and a" + die3;

        if (die1 == die2 && die1 == die3)
        {
            //Triples
            int scoreData = die1 *100;
            msg = "You rolled a triple " + die1 +"! You score " + scoreData + " points!";
            score += scoreData;
        }

        else if (die1 == die2 || die1 == die3 || die2 == die3)
        {
            //Doubles
            msg = "You rolled double for 50 points!";
            score += 50;
        }

        else
        {
            msg = "Sorry you could not score any point. Try again!!!";
        }


        //Update the aoo in order to display the result message.
        rollResult.setText(msg);
        scoreText.setText("Score : " + score);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
