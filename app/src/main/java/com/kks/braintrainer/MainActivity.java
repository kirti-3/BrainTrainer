package com.kks.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int LocationOfcorrectAnswer;
    int question;
    int correctAns;
    Button startButton;
    TextView addTextview;
    TextView resultTextView;
    TextView textViewScoreCounter;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView timertextview;

    public void generatequestion()
    {
        Random rn = new Random();
        int a = rn.nextInt(50) ;
        int b= rn.nextInt(50) ;
        int result = a+b;
        addTextview = (TextView)findViewById(R.id.textViewAdd);
        addTextview.setText(a +" + " + b);

        LocationOfcorrectAnswer = rn.nextInt(4);
        answers.clear();
        int incorrect;


        for(int i = 0; i <=3 ; i++)
        {
            if(i == LocationOfcorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                incorrect = rn.nextInt(100);
                while (incorrect ==a+b )
                {
                    incorrect = rn.nextInt(100);
                }
                answers.add(incorrect);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void start(View view)
    {
         startButton.setVisibility(view.INVISIBLE);
    }

    public void check(View view)
    {

        resultTextView =(TextView)findViewById(R.id.textViewResult);
        textViewScoreCounter =(TextView)findViewById(R.id.textViewScoreCounter);


       if(view.getTag().toString().equals(Integer.toString(LocationOfcorrectAnswer))) {
           correctAns++;
           resultTextView.setText("CORRECT!");
           generatequestion();
       }
       else {
           resultTextView.setText("WRONG!");
           generatequestion();
       }
       question++;
       textViewScoreCounter.setText(Integer.toString(correctAns)+"/"+Integer.toString(question));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        timertextview = (TextView)findViewById(R.id.textViewTimer);
         generatequestion();

         new CountDownTimer(30000,1000)
         {

             @Override
             public void onTick(long millisUntilFinished) {
                 timertextview.setText(String.valueOf(millisUntilFinished/1000) + "s");
             }

             @Override
             public void onFinish() {
                 timertextview.setText("0s");
                resultTextView.setText("Your score=" + correctAns);
             }
         }.start();


    }
}
