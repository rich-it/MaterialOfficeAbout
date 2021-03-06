package org.richit.materialofficeaboutlib.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.richit.materialofficeaboutlib.Models.Link;
import org.richit.materialofficeaboutlib.R;

import java.util.ArrayList;

public class LinksRecyclerviewAdapter extends RecyclerView.Adapter<LinksRecyclerviewAdapter.ViewHolder> {

    Context context;
    ArrayList<Link> linkArrayList;

    public LinksRecyclerviewAdapter(Context context, ArrayList<Link> linkArrayList) {
        this.context = context;
        this.linkArrayList = linkArrayList;
    }

    @NonNull
    @Override

    public LinksRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.link_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinksRecyclerviewAdapter.ViewHolder holder, int position) {
        final Link link = linkArrayList.get(position);

        Picasso.get().load(link.getImageUrl()).fit().into(holder.imageView);
        holder.textViewName.setText("" + link.getName());
        holder.textViewUrl.setText("" + link.getUrl());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    openLink(link.getAlt());
                } catch (Exception e) {
                    try {
                        openLink(link.getUrl());
                    } catch (Exception ex) {
                        Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void openLink(String urlStr) {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(urlStr));
        context.startActivity(browse);
    }

    @Override
    public int getItemCount() {
        return linkArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName, textViewUrl;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.faceImage);
            textViewName = itemView.findViewById(R.id.nameTv);
            textViewUrl = itemView.findViewById(R.id.descTv);
            relativeLayout = itemView.findViewById(R.id.parentItemLayout);
        }
    }
}
