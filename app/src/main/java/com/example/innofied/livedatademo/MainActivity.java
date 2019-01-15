package com.example.innofied.livedatademo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.innofied.livedatademo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;
    public static final int CREATE_PERSON_ACTIVITY_REQUEST_CODE = 11;
    private PersonViewModel personViewModel;
    private PersonListAdapter personListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.add.setOnClickListener(MainActivity.this);

        personListAdapter = new PersonListAdapter();
        RecyclerView personRecyclerView = findViewById(R.id.name_list);
        personRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        personRecyclerView.setAdapter(personListAdapter);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        personListAdapter.setItemClickListner(new PersonListAdapter.ItemClickListner() {
            @Override
            public void onDeleteClick(View v, int pos, Person person) {
                //personViewModel.delete(person.getName());
            }
        });

        initializeViewModel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                startActivityForResult(new Intent(MainActivity.this, AddPersons.class), CREATE_PERSON_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_PERSON_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] values = data.getStringArrayExtra(AddPersons.REPLY);
            Person person = new Person();
            person.setName(values[0]);
            person.setPhone(values[1]);
            person.setAddress(values[2]);
            personViewModel.insert(person);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Input empty", Toast.LENGTH_LONG).show();
        }
    }

    private void initializeViewModel() {
        personViewModel = ViewModelProviders.of(MainActivity.this).get(PersonViewModel.class);
        personViewModel.getPersonList().observe(MainActivity.this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> personList) {
                personListAdapter.setPersons(personList);
            }
        });
    }
}
