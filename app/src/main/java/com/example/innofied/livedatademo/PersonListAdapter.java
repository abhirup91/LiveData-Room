package com.example.innofied.livedatademo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListViewHolder> {
    private List<Person> personList;
    private ItemClickListner itemClickListner;

    protected class PersonListViewHolder extends RecyclerView.ViewHolder {
        private TextView name, phone,address;

        PersonListViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            address=view.findViewById(R.id.address);
        }
    }
    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
    @NonNull
    @Override
    public PersonListAdapter.PersonListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PersonListViewHolder((LayoutInflater.from(viewGroup.getContext()))
                .inflate(R.layout.menu_item_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonListAdapter.PersonListViewHolder personListViewHolder, int i) {

        personListViewHolder.name.setText("Name: "+personList.get(i).getName());
        personListViewHolder.phone.setText("Phone: "+personList.get(i).getPhone());
        personListViewHolder.address.setText("Address: "+personList.get(i).getAddress());



    }

    @Override
    public int getItemCount() {
        try {
            return personList.size();
        }
        catch(NullPointerException n) {
            return 0;
        }
    }

    public void setPersons(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    public interface ItemClickListner {

        void onDeleteClick(View v, int pos, Person person);
    }
}
