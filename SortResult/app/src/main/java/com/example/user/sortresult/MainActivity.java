package com.example.user.sortresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText search_text=(EditText)findViewById(R.id.editText);
           final TextView mTextView = (TextView)findViewById(R.id.textView);

        Button mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(),gridActivity.class);
                         String text=search_text.getText().toString();
                      //  String search="search_text";
                        intent.putExtra("search_text",text);
                        startActivity(intent);
                      // mTextView.setText(text);
                    }
                });

    }

        public void onClick(View v)
        {
        EditText search_text=(EditText)v.findViewById(R.id.editText);
        String text=search_text.getText().toString();
        TextView mTextView = (TextView)v.findViewById(R.id.textView);
        mTextView.setText(text);


        //  Intent intent = new Intent(this, gridActivity.class);
        // startActivity(intent);
    }

}

