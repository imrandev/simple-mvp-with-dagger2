package com.nerdlogs.simplemvp.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nerdlogs.simplemvp.listener.OnLongPressListener;
import com.nerdlogs.simplemvp.ui.BaseActivity;
import com.nerdlogs.simplemvp.R;
import com.nerdlogs.simplemvp.adapter.UserAdapter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private MainPresenter mMainPresenter;
    private EditText editUserName;
    private UserAdapter userAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainPresenter = new MainPresenter(this);
        userAdapter = new UserAdapter(new ArrayList<String>(2));
        userAdapter.setOnLongPressListener(onLongPressListener);

        editUserName = findViewById(R.id.edit_user_name);
        Button btnAdd = findViewById(R.id.add_button);
        btnAdd.setOnClickListener(this);

        RecyclerView userListView = findViewById(R.id.user_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        userListView.setLayoutManager(layoutManager);
        userListView.setAdapter(userAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
                mMainPresenter.addNewUser(editUserName.getText().toString());
                break;
        }
    }

    @Override
    public UserAdapter getAdapterView() {
        return userAdapter;
    }

    private OnLongPressListener onLongPressListener
            = new OnLongPressListener() {
        @Override
        public void onLongPress(View v, String user) {
            mMainPresenter.deleteUser(user);
        }
    };
}
