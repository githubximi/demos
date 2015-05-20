package com.example.mynewsdemo;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AnyTimeActivity {

    @ViewById(R.id.editText_register_userName)
	EditText userName;
    @ViewById(R.id.editText_register_email)
	EditText userEmail;
    @ViewById(R.id.editText_register_userPassword)
	EditText userPassword;
    @ViewById(R.id.editText_register_userPassword_again)
	EditText userPasswordAgain;
	private ProgressDialog progressDialog;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Click(R.id.button_i_need_register)
    void re(){
        if (userPassword.getText().toString()
                .equals(userPasswordAgain.getText().toString())) {
            if (!userPassword.getText().toString().isEmpty()) {
                if (!userName.getText().toString().isEmpty()) {
                    if (!userEmail.getText().toString().isEmpty()) {
                        progressDialogShow();
                        register();
                    } else {
                        showError(activity
                                .getString(R.string.error_register_email_address_null));
                    }
                } else {
                    showError(activity
                            .getString(R.string.error_register_user_name_null));
                }
            } else {
                showError(activity
                        .getString(R.string.error_register_password_null));
            }
        } else {
            showError(activity
                    .getString(R.string.error_register_password_not_equals));
        }


    }

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			Intent LoginIntent = new Intent(this, LoginActivity_.class);
			startActivity(LoginIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void register() {
    SignUpCallback signUpCallback = new SignUpCallback() {
      public void done(AVException e) {
        progressDialogDismiss();
        System.out.println("----"+e);
        if (e == null) {
          showRegisterSuccess();
          Intent mainIntent = new Intent(activity, MainActivity_.class);
          startActivity(mainIntent);
          activity.finish();
        } else {
          switch (e.getCode()) {
            case 202:
              showError(activity
                  .getString(R.string.error_register_user_name_repeat));
              break;
            case 203:
              showError(activity
                  .getString(R.string.error_register_email_repeat));
              break;
            default:
              showError(activity
                  .getString(R.string.network_error));
              break;
          }
        }
      }
    };
    String username = userName.getText().toString();
    String password = userPassword.getText().toString();
    String email = userEmail.getText().toString();
    //
    AVService.signUp(username, password, email, signUpCallback);
    
	}

  private void progressDialogDismiss() {
		if (progressDialog != null)
			progressDialog.dismiss();
	}

	private void progressDialogShow() {
		progressDialog = ProgressDialog
				.show(activity,
						activity.getResources().getText(
								R.string.dialog_message_title),
						activity.getResources().getText(
								R.string.dialog_text_wait), true, false);
	}

	private void showRegisterSuccess() {
		new AlertDialog.Builder(activity)
				.setTitle(
						activity.getResources().getString(
								R.string.dialog_message_title))
				.setMessage(
						activity.getResources().getString(
								R.string.success_register_success))
				.setNegativeButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).show();
	}
}
