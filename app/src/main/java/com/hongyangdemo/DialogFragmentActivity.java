package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hongyangdemo.recommend.dialog.EditNameDialogFragment;
import com.hongyangdemo.recommend.dialog.LoginDialogFragment;

public class DialogFragmentActivity extends AppCompatActivity implements LoginDialogFragment.LoginInputListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
    }

    public void editnamedialogfragment(View view){
        EditNameDialogFragment editNameDialog = new EditNameDialogFragment();
        editNameDialog.show(getSupportFragmentManager(), "EditNameDialog");
    }


    public void showLoginDialog(View view){
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getSupportFragmentManager(), "loginDialog");
    }


    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this, "帐号：" + username + ",  密码 :" + password,
                Toast.LENGTH_SHORT).show();
    }
}
