package com.creativedeveloper.ecommerce.ui.login;

import android.content.Context;
import android.widget.EditText;

import com.creativedeveloper.ecommerce.data.remote.model.UserListItem;
import com.creativedeveloper.ecommerce.data.remote.model.UserResponse;
import com.creativedeveloper.ecommerce.ui.base.BaseViewModel;
import com.creativedeveloper.ecommerce.utils.SessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel {

    @Inject
    public LoginViewModel() {
      //  sessionManager = new SessionManager(context);
    }

    public String validateSignIn(EditText txtEmail, EditText txtPassword, List<UserListItem> userListItems) {

        if (txtEmail.getText().toString().length() == 0) {
            return "Please enter user name";
        } else if (txtPassword.getText().toString().length() == 0) {
            return "Please enter password";
        } else {

            for (UserListItem userListItem : userListItems) {
                if (userListItem.getUserName().equals(txtEmail.getText().toString()) && userListItem.getPassword().equals(txtPassword.getText().toString())) {
                     return String.valueOf(userListItem.getId() - 1);
                }
            }
            return "Please enter valid user name or password";
        }
    }

    public UserResponse loadJSONFromAsset(Context context) {
        String json = null;
        UserResponse userResponse = new UserResponse();
        try {
            InputStream is = context.getAssets().open("userList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            userResponse = gson.fromJson(json, UserResponse.class);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return userResponse;
    }
}
