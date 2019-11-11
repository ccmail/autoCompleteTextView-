package com.example.cjs707_test006;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开始定义自动填充
        autoCompleteTextView = findViewById(R.id.auto_address);
            //为自动填充绑定array,内容存储在value的XML文件中
        ArrayAdapter autoComplete_adapter = ArrayAdapter.createFromResource(this,R.array.address,android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(autoComplete_adapter);
        //自动填充定义结束


        //开始定义日期选择器
        DatePicker datePicker = (DatePicker)findViewById(R.id.choose_date);
            //获取日期信息
        Calendar calendar = Calendar.getInstance();
            //获取当前日期
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

            //将选择的日期在编辑框中显示出来
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MainActivity.this.year = year;
                MainActivity.this.month = month;
                MainActivity.this.day = day;

                //showDate为编辑框设置的显示日期方法
                showDate(year,monthOfYear,dayOfMonth);

            }
        });

        //绑定显示日期的编辑框
        final EditText show_date = (EditText)findViewById(R.id.show_date);

        //绑定Spinner下拉框
        final Spinner spinner = (Spinner)findViewById(R.id.check_email);
            //Spinner下拉框的值存储在email.XML文件中,将其链接起来
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(this,R.array.email,android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinner_adapter);

        //绑定输入邮箱的编辑框
        final EditText editText = (EditText)findViewById(R.id.email);
            //创建提示信息,该编辑框限制为只能输入数字,英文字母,下划线以及半角句号
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast toast = Toast.makeText(MainActivity.this,"邮箱地址输入格式限制为数字,英文字母,下划线以及半角句号",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        //绑定显示用户信息的文本框
        final TextView textView = (TextView)findViewById(R.id.message_show);

        //绑定提交按钮
        Button button = (Button)findViewById(R.id.submit_button);
            //为提交按钮添加监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当点击提交按钮时,获取上述各组件的值
                String user_address = autoCompleteTextView.getText().toString();
                String user_date = show_date.getText().toString();
                String user_email_number = editText.getText().toString();
                String user_email_server = spinner.getSelectedItem().toString();
                String user_email = user_email_number + user_email_server;
                //将获取到的值显示到文本框中
                textView.setText("您的居住地址为:"+user_address
                                +"\n您的出生日期为:"+user_date
                                +"\n您的电子邮箱为:"+user_email);
            }
        });

    }

    //获取年月日的方法
    private void showDate(int year,int month,int day){
        EditText show = (EditText)findViewById(R.id.show_date);
        month++;
        show.setText(year+"年"+month+"月"+day+"日");
    }
}
