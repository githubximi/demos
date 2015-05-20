package com.example.mynewsdemo;









import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AnyTimeActivity{
   @ViewById(R.id.button_login)
	 Button loButton;
	 @ViewById(R.id.editText_userName)
	 EditText userNameEditText;
    @ViewById(R.id.editText_userPassword)
	 EditText userPasswordEditText;
	private ProgressDialog progressDialog;

    @Click(R.id.button_register)
    void intentRe(){
        Toast.makeText(LoginActivity.this, "已到注册页面",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(activity,RegisterActivity_.class);
        startActivity(intent);
         activity.finish();
    }
    @AfterViews
    void init(){
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            Intent intent=new Intent(LoginActivity.this,MainActivity_.class);
            startActivity(intent);
        }

          }

            private void progressDialogShow() {
                progressDialog = ProgressDialog
                        .show(activity,
                                activity.getResources().getText(
                                        R.string.dialog_message_title),
                                activity.getResources().getText(
                                        R.string.dialog_text_wait), true, false);
            }

            private void showUserNameEmptyError() {
                new AlertDialog.Builder(activity)
                        .setTitle(
                                activity.getResources().getString(
                                        R.string.dialog_error_title))
                        .setMessage(
                                activity.getResources().getString(
                                        R.string.error_register_user_name_null))
                        .setNegativeButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                }).show();

            }

            private void showUserPasswordEmptyError() {
                new AlertDialog.Builder(activity)
                        .setTitle(
                                activity.getResources().getString(
                                        R.string.dialog_error_title))
                        .setMessage(
                                activity.getResources().getString(
                                        R.string.error_register_password_null))
                        .setNegativeButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                }).show();

            }
 //       });

//    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Click
    void button_login(){
        Log.d("22222","点了");
        String username = userNameEditText.getText().toString();
        if (username.isEmpty()) {
            showUserNameEmptyError();
            return;
        }
        if (password().isEmpty()) {
            showUserPasswordEmptyError();
            return;
        }
        progressDialogShow();
        AVUser.logInInBackground(username,password(),new LogInCallback() {

            public void done(AVUser user, AVException e) {
                if (user != null) {

                    progressDialogDismiss();
                    Intent mainIntent = new Intent(activity,
                            MainActivity_.class);
                    startActivity(mainIntent);
                    activity.finish();
                } else {
                    progressDialogDismiss();
                    showLoginError();
                }
            }

            private void showLoginError() {
                new AlertDialog.Builder(activity)
                        .setTitle(
                                activity.getResources().getString(
                                        R.string.dialog_error_title))
                        .setMessage(
                                activity.getResources().getString(
                                        R.string.error_login_error))
                        .setNegativeButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                }).show();

            }

            private void progressDialogDismiss() {
                if (progressDialog != null)
                    progressDialog.dismiss();

            }
        });
    }






	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);



//           setContentView(R.layout.activity_login);
//            loButton=(Button) findViewById(R.id.button_login);
//            reButton=(Button) findViewById(R.id.button_register);
//            userNameEditText = (EditText) findViewById(R.id.editText_userName);
//            userPasswordEditText = (EditText) findViewById(R.id.editText_userPassword);
//            reButton.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(LoginActivity.this, "已到注册页面",Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(activity,RegisterActivity.class);
//                    startActivity(intent);
//                    activity.finish();
//                }
//            });
//            AVUser currentUser = AVUser.getCurrentUser();
//            if (currentUser != null) {
//                Intent intent=new Intent(LoginActivity.this,MainActivity_.class);
//                startActivity(intent);
//            }
//            loButton.setOnClickListener(new OnClickListener() {
//
//                @TargetApi(Build.VERSION_CODES.GINGERBREAD)
//                @SuppressWarnings({ "rawtypes", "unchecked" })
//                public void onClick(View arg0) {
//                    String username = userNameEditText.getText().toString();
//                    if (username.isEmpty()) {
//                        showUserNameEmptyError();
//                        return;
//                    }
//                    if (password().isEmpty()) {
//                        showUserPasswordEmptyError();
//                        return;
//                    }
//                    progressDialogShow();
//                    AVUser.logInInBackground(username,password(),new LogInCallback() {
//                        public void done(AVUser user, AVException e) {
//                            if (user != null) {
//                                progressDialogDismiss();
//                                Intent mainIntent = new Intent(activity,
//                                        MainActivity_.class);
//                                startActivity(mainIntent);
//                                activity.finish();
//                            } else {
//                                progressDialogDismiss();
//                                showLoginError();
//                            }
//                        }
//
//                        private void showLoginError() {
//                            new AlertDialog.Builder(activity)
//                                    .setTitle(
//                                            activity.getResources().getString(
//                                                    R.string.dialog_error_title))
//                                    .setMessage(
//                                            activity.getResources().getString(
//                                                    R.string.error_login_error))
//                                    .setNegativeButton(android.R.string.ok,
//                                            new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog,
//                                                                    int which) {
//                                                    dialog.dismiss();
//                                                }
//                                            }).show();
//
//                        }
//
//                        private void progressDialogDismiss() {
//                            if (progressDialog != null)
//                                progressDialog.dismiss();
//
//                        }
//                    });
//                }
//
//                private void progressDialogShow() {
//                    progressDialog = ProgressDialog
//                            .show(activity,
//                                    activity.getResources().getText(
//                                            R.string.dialog_message_title),
//                                    activity.getResources().getText(
//                                            R.string.dialog_text_wait), true, false);
//
//                }
//
//                private void showUserNameEmptyError() {
//                    new AlertDialog.Builder(activity)
//                            .setTitle(
//                                    activity.getResources().getString(
//                                            R.string.dialog_error_title))
//                            .setMessage(
//                                    activity.getResources().getString(
//                                            R.string.error_register_user_name_null))
//                            .setNegativeButton(android.R.string.ok,
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog,
//                                                            int which) {
//                                            dialog.dismiss();
//                                        }
//                                    }).show();
//
//                }
//
//                private void showUserPasswordEmptyError() {
//                    new AlertDialog.Builder(activity)
//                            .setTitle(
//                                    activity.getResources().getString(
//                                            R.string.dialog_error_title))
//                            .setMessage(
//                                    activity.getResources().getString(
//                                            R.string.error_register_password_null))
//                            .setNegativeButton(android.R.string.ok,
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog,
//                                                            int which) {
//                                            dialog.dismiss();
//                                        }
//                                    }).show();
//
//                }
//            });

        }

		
			

			 private String password() {
			      return userPasswordEditText.getText().toString();
			    }
	
	

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

}
