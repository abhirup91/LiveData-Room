package com.example.innofied.livedatademo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {
    private PersonRepository personRepository;
    private LiveData<List<Person>> personList;

    public PersonViewModel(Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        personList = personRepository.getPersonList();
    }

    public LiveData<List<Person>> getPersonList() {
        return personList;
    }

    public void insert(Person person) {
        personRepository.insert(person);
    }

//    public void delete(String person){
//        personRepository.delete(person);
//    }
}
