package com.example.android.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faruk on 20.02.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Information> data = new ArrayList<Information>();
    private Context context;
    private ClickListener clickListener;

    public Adapter(Context context, List<Information> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;

    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        Log.d("AFG","onCreateViewHolder called");
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Information current = data.get(position);
        Log.d("AFG","onBindViewHolder called "+position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
        
       /*bu sekılde verebılecegımız gıbı dırek viewholdeda da set edebılırız
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item clicked at "+ position, Toast.LENGTH_SHORT).show();
            }
        }); */
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;


        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            // adam burda bunu yapmasına ragmen dıyorkı, bu bunun gorevı degıl..
            //yanı aktıvıte baslatmak aktıvıtenın gorevı adapterın degıl...
            //context.startActivity(new Intent(context,SubActivity.class));
            // tanımladıgımız clicklistener üzerinden yapacagız

            if(clickListener != null){
                clickListener.itemClicked(view,getPosition());
            }

            //delete(getPosition());
            //Toast.makeText(context, "Item clicked at "+ getPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    public interface ClickListener{
        public void itemClicked(View view,int position);
    }
}
