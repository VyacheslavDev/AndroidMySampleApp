package com.myfirstprogramm.sokol.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainClass extends Activity implements View.OnClickListener {

    private Button btnActfirst;
    private ListView userTable;
    private String name;
    private String second;
    private String age;
    public ArrayList<User> users;
    private UsersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        users = new ArrayList<>();
        users.add(new User("Гена", "Петрович", 18));
        users.add(new User("Василий", "Потапович", 42));
        users.add(new User("Василий", "Хороший", 22));

        userTable = (ListView) findViewById(R.id.userTable);
        mAdapter = new UsersAdapter(this, users);
        userTable.setAdapter(mAdapter);

        btnActfirst = (Button) findViewById(R.id.buttonFirst);
        btnActfirst.setOnClickListener(this);

        userTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainClass.this);

                builder.setTitle("Внимание ")
                        .setMessage("Изменить данные ?"
                                + "\nИмя: " + users.get(position).getName() +
                                "\nФамилия: " + users.get(position).getLastName() +
                                "\nВозраст: " +
                                users.get(position).getAge())
                        .setNegativeButton("Изменить",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                        Intent intent = new Intent(MainClass.this, UserActivity.class);

                                        intent.putExtra("flag", true);

                                        intent.putExtra(("position"), position);
                                        intent.putExtra("name", users.get(position).getName());
                                        intent.putExtra("second", users.get(position).getLastName());
                                        intent.putExtra("age", users.get(position).getAge());

                                        startActivityForResult(intent, 2);

                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Удалить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                users.remove(position);

                                mAdapter.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFirst:
                Intent intent = new Intent(this, UserActivity.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    String name = data.getStringExtra("name");
                    String second = data.getStringExtra("second");
                    int age = Integer.parseInt(data.getStringExtra("age"));

                    users.add(new User(name, second, age));
                    mAdapter.notifyDataSetChanged();
                    break;

                case 2:
                    String nameEdit = data.getStringExtra("name");
                    String secondEdit = data.getStringExtra("second");
                    int ageEdit = Integer.parseInt(data.getStringExtra("age"));
                    int positionEdit = Integer.parseInt(String.valueOf(data.getIntExtra("position",0)));


                    users.set(positionEdit,new User(nameEdit, secondEdit, ageEdit));
                    mAdapter.notifyDataSetChanged();
                    break;

            }
        }

    }
}