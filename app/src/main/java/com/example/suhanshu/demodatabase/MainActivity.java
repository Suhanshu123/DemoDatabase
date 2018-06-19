package com.example.suhanshu.demodatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    Helper helper;
    EditText name,surname,marks,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new Helper(this);

        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        marks=findViewById(R.id.marks);
        id=findViewById(R.id.id_view);



    }

    public void add_data(View view) {
        boolean bool=helper.insertData(name.getText().toString(),surname.getText().toString(), Integer.parseInt(marks.getText().toString()));
        if(bool==false){
            Toast.makeText(this,"Error Ocurred",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
    }


    public void get_data(View view) {
       Cursor cursor= helper.getData();
       StringBuffer stringBuffer=new StringBuffer();
       while (cursor.moveToNext()){
           stringBuffer.append("Id :"+cursor.getString(0)+"\n");
           stringBuffer.append("Name :"+cursor.getString(1)+"\n");
           stringBuffer.append("Surname :"+cursor.getString(2)+"\n");
           stringBuffer.append("Marks :"+cursor.getInt(3)+"\n"+"\n");
       }

        AlertDialog.Builder alert=new AlertDialog.Builder(this);
       alert.setCancelable(true);
       alert.setTitle("Data");
       alert.setMessage(stringBuffer);
       alert.show();
    }

    public void update(View view){
       boolean bool= helper.update(Integer.parseInt(id.getText().toString()),name.getText().toString(),surname.getText().toString(),Integer.parseInt(marks.getText().toString()));
        if(bool==false){
            Toast.makeText(this,"Error Ocurred",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
    }

    public void delete(View view) {
        SQLiteDatabase database=helper.getWritableDatabase();
        int i=database.delete(Helper.TABLE_NAME,Helper.COL_1+" = "+Integer.parseInt(id.getText().toString()),null);
        if(i!=0)
            Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Error Ocurred",Toast.LENGTH_LONG).show();

    }
}
