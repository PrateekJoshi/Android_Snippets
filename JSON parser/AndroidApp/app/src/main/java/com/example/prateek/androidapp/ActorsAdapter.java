package com.example.prateek.androidapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 7/18/2015.
 */
public class ActorsAdapter extends ArrayAdapter<Actors> {

    ArrayList<Actors> list_actors;
    int resource;
    Context context;
    LayoutInflater layout_inflater;


    public ActorsAdapter(Context context, int resource, ArrayList<Actors> objects) {
        super(context, resource, objects);

        this.list_actors=objects;
        this.resource=resource;
        this.context=context;

        this.layout_inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null){
            convertView= layout_inflater.inflate(resource,null);
            holder=new ViewHolder();

            holder.image_view=(ImageView) convertView.findViewById(R.id.ivImage);
            holder.tv_name=(TextView) convertView.findViewById(R.id.tvName);
            holder.tv_description=(TextView) convertView.findViewById(R.id.tvDescriptionn);
            holder.tv_dob=(TextView) convertView.findViewById(R.id.tvDateOfBirth);
            holder.tv_country=(TextView) convertView.findViewById(R.id.tvCountry);
            holder.tv_height=(TextView) convertView.findViewById(R.id.tvHeight);
            holder.tv_spouse=(TextView) convertView.findViewById(R.id.tvSpouse);
            holder.tv_children=(TextView) convertView.findViewById(R.id.tvChildren);

            convertView.setTag(holder);

        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        new DownloadImageTask(holder.image_view).execute(list_actors.get(position).getImage());
        holder.tv_name.setText(list_actors.get(position).getName());
        holder.tv_description.setText(list_actors.get(position).getDescription());
        holder.tv_dob.setText("Bday: "+list_actors.get(position).getDob());
        holder.tv_country.setText(list_actors.get(position).getCountry());
        holder.tv_height.setText(list_actors.get(position).getHeight());
        holder.tv_spouse.setText(list_actors.get(position).getSpouse());
        holder.tv_children.setText(list_actors.get(position).getChildren());


        return convertView;
    }

    static class ViewHolder{
        public ImageView image_view;
        public TextView tv_name;
        public TextView tv_description;
        public TextView tv_dob;
        public TextView tv_country;
        public TextView tv_height;
        public TextView tv_spouse;
        public TextView tv_children;

    }

    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap>{


        ImageView bitmap_image;

        public DownloadImageTask(ImageView bmImage) {
            this.bitmap_image = bmImage;
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            String url_display=urls[0];
            Bitmap icon=null;

            try {
                InputStream in=new java.net.URL(url_display).openStream();
                icon= BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }

            return icon;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bitmap_image.setImageBitmap(result);
        }
    }
}
