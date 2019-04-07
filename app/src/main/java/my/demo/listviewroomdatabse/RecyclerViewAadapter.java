package my.demo.listviewroomdatabse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAadapter extends RecyclerView.Adapter<RecyclerViewAadapter.MyViewHolder> {
    Context context;
    List<User> user;

    public RecyclerViewAadapter(Context context) {
        this.context = context;
        user = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_listcontent, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(user.get(i).getName());
        myViewHolder.age.setText(user.get(i).getAge());
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public void addUsers(List<User> users){
        this.user.addAll(users);
    }

    public  void addLastUser(List<User> user)
    {
        this.user.addAll(user);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, age;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
        }
    }
}
