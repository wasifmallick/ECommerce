package com.creativedeveloper.ecommerce.ui.login;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.creativedeveloper.ecommerce.AppController;
import com.creativedeveloper.ecommerce.R;
import com.creativedeveloper.ecommerce.data.remote.model.UserListItem;
import com.creativedeveloper.ecommerce.databinding.LoginFragmentBinding;
import com.creativedeveloper.ecommerce.databinding.SplashFragmentBinding;
import com.creativedeveloper.ecommerce.factory.ViewModelFactory;
import com.creativedeveloper.ecommerce.ui.base.BaseFragment;
import com.creativedeveloper.ecommerce.utils.NavigationUtils;
import com.creativedeveloper.ecommerce.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class LoginFragment extends BaseFragment {
    LoginViewModel loginViewModel;
    private LoginFragmentBinding binding;
    @Inject
    ViewModelFactory viewModelFactory;
    List<UserListItem> userListItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        initialiseViewModel();
    }

    private void initialiseViewModel() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

    }

    private void initialiseView() {
        userListItems = loginViewModel.loadJSONFromAsset(getActivity()).getUserList();
        binding.btnSignIn.setOnClickListener(v -> {
            String validation = loginViewModel.validateSignIn(binding.txtEmail, binding.txtPassword, userListItems);
            if (!validation.equals("") && validation.length() >1) {
                handleErrorResponse(validation);
            } else {

                saveDataToLocalStorage(userListItems.get(Integer.parseInt(validation)));
                Toast.makeText(getActivity(), "Login successfully!", Toast.LENGTH_SHORT).show();
                NavigationUtils.replaceFragment(getActivity(), R.id.productListFragment);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        initialiseView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void handleErrorResponse(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void saveDataToLocalStorage(UserListItem userListItem) {
        SessionManager sessionManager = new SessionManager(getActivity());
        sessionManager.createLoginSession(userListItem.getName(), userListItem.getPassword(), userListItem.getRole(), userListItem.getUserName());

    }
}
