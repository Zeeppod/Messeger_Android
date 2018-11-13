package messeger.e.messeger;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView tw;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tw = itemView.findViewById(R.id.mess_item);
    }
}
