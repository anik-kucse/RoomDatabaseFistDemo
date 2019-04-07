package my.demo.listviewroomdatabse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<User> userList;


    public CustomAdapter(Context applicationContext){
        this.context = applicationContext;
        this.inflater = (LayoutInflater.from(applicationContext));
        userList = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addUsers(List<User> users){
        this.userList.addAll(users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_listcontent, null);
        TextView name = convertView.findViewById(R.id.name);
        TextView age = convertView.findViewById(R.id.age);
        name.setText(userList.get(position).getName());
        age.setText(userList.get(position).getAge());
        return convertView;
    }
}
