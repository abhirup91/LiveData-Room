package com.example.innofied.livedatademo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PersonRepository {
    private PersonDao personDao;
    private LiveData<List<Person>> personList;

    public PersonRepository(Application application) {
        LiveDataApplication db = LiveDataApplication.getInstance(application);
        personDao = db.personDao();
        personList = personDao.getAll();
    }

    public LiveData<List<Person>> getPersonList() {
        return personList;
    }

    public void insert(Person person) {
        new InsertAsyncTask(personDao).execute(person);
    }

    public static class InsertAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        InsertAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.insert(people[0]);

            return null;
        }
    }

//    public void delete(String person) {
//        new DeleteAsyncTask(personDao).execute(person);
//    }
//
//    public static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {
//        private PersonDao personDao;
//
//        DeleteAsyncTask(PersonDao personDao) {
//            this.personDao = personDao;
//        }
//
//        @Override
//        protected Void doInBackground(String... strings) {
////            personDao.delete(strings[0]);
//            return null;
//        }
//    }

}
