package com.demo.pro2.heightcalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class HeightCalculatorActivity extends AppCompatActivity {
    private Button calculatorButton;
    private EditText weightEditText;
    private RadioButton manRadioButton;
    private RadioButton womanRadioButton;
    private TextView resultTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_calculator);
        //从xml文件中获取获取对应的UI控件
    calculatorButton= (Button) findViewById(R.id.calculator);
    weightEditText= (EditText) findViewById(R.id.weight);
        manRadioButton= (RadioButton) findViewById(R.id.man);
        womanRadioButton= (RadioButton) findViewById(R.id.wonman);
    resultTextView= (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册事件
        registrEvent();
    }
    private void registrEvent(){
        //注册按钮事件
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已填写体重数据
                if(!weightEditText.getText().toString().trim().equals("")){
                    //判断是否有选择性别
                    if(manRadioButton.isChecked()||womanRadioButton.isChecked()){
                        Double weight =Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------评估结果----- \n");
                        if (manRadioButton.isChecked()){
                            sb.append("男性标准身高：");
                            //执行运算
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result+"(厘米)");
                        }
                        if (womanRadioButton.isChecked()){
                            sb.append("女性标准身高：");
                            //执行运算
                            double result = evaluateHeight(weight,"女");
                            sb.append((int)result+"(厘米)");
                        }
                        //输出页面显示结果
                        resultTextView.setText(sb.toString());
                    }else{
                        showMessage("请选择性别！");
                    }
                }else{
                    showMessage("请输入体重！");
                }
            }
        });
    }
    //计算处理执行事件代码
    private double evaluateHeight(double weight,String sex){
    double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    //消息提示
    private void showMessage(String message){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener(){
             public void onClick(DialogInterface dialog,int whichButton){

             }
        });
        alert.show();
    }

    //创建菜单
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }
    //菜单事件
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1://退出
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
