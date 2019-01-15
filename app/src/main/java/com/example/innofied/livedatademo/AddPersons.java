package com.example.innofied.livedatademo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.example.innofied.livedatademo.databinding.ActivityAddPersonBinding;

public class AddPersons extends AppCompatActivity implements View.OnClickListener {
    private ActivityAddPersonBinding activityAddPersonBinding;
    public static final String REPLY = "reply";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddPersonBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_person);

        activityAddPersonBinding.save.setOnClickListener(AddPersons.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(activityAddPersonBinding.name.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String[] values = new String[3];
                    values[0] = activityAddPersonBinding.name.getText().toString();
                    values[1] = activityAddPersonBinding.phone.getText().toString();
                    values[2] = activityAddPersonBinding.address.getText().toString();
                    replyIntent.putExtra(REPLY, values);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
                break;
        }
    }
}
