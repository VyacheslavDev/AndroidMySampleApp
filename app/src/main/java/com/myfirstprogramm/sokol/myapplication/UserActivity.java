package com.myfirstprogramm.sokol.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends Activity implements View.OnClickListener {
    private EditText editName;
    private EditText editSecond;
    private EditText editAge;
    private Button btnSave;
    private boolean mFlagEdit;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_layout);

        btnSave = (Button) findViewById(R.id.button2);
        btnSave.setOnClickListener(this);

        getAllEditText();

        Intent intent = getIntent();
        mFlagEdit = intent.getBooleanExtra("flag",false);

        if(mFlagEdit){
            contentTransfer(intent);
        }

    }

    private void getEditData(int position)
    {
        Intent intent = getIntent();
        intent.putExtra("name", editName.getText().toString());
        intent.putExtra("second", editSecond.getText().toString());
        intent.putExtra("age", editAge.getText().toString());
        intent.putExtra("position", position);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }


    private void getData() {

        if (checkData(editName) || checkData(editSecond) || checkData(editAge)) {
            Toast.makeText(this, "Одно из полей пустое", Toast.LENGTH_LONG).show();
        }
        else {

            Intent intent = new Intent();
            intent.putExtra("name", editName.getText().toString());
            intent.putExtra("second", editSecond.getText().toString());
            intent.putExtra("age", editAge.getText().toString());

            setResult(Activity.RESULT_OK,intent);
            finish();
        }

    }


    private void contentTransfer(Intent intent)
    {
        editName.setText(intent.getStringExtra("name"));
        editSecond.setText(intent.getStringExtra("second"));
        editAge.setText(String.valueOf(intent.getIntExtra("age",0)));
        mPosition = intent.getIntExtra("position",0);
    }

    private boolean checkData(EditText data) {
        return data.getText().toString().isEmpty();
    }

    private void getAllEditText()
    {
        editName = (EditText) findViewById(R.id.editText);
        editSecond = (EditText) findViewById(R.id.editText2);
        editAge = (EditText) findViewById(R.id.editText3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                if(mFlagEdit){
                    getEditData(mPosition);
                }
                getData();
                break;
            default:
                break;
        }

    }
}