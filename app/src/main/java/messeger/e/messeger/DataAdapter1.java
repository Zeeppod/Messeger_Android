package messeger.e.messeger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DataAdapter1 extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<String> messege;

    public static int SizeArray;

    LayoutInflater inflater;

    public DataAdapter1(Context context, ArrayList<String> messege) {
        this.messege = messege;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_messege, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            String msg = messege.get(i);
            viewHolder.tw.setText(msg);
    }

    @Override
    public int getItemCount() {
        SizeArray = messege.size();
        return messege.size();
    }
}
