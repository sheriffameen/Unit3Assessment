package org.pursuit.unit_03_assessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PlanetViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTextView;


    public PlanetViewHolder(@NonNull final View itemView) {
        super(itemView);

        /*  The ViewHolder class is where you initialize the views in your itemview layout,
            & you are referencing a TexView you have in the activity_display.xml
            R.id.name_display_textview doesn't exist in your planet_list_itemview.xml
        */

        nameTextView = itemView.findViewById(R.id.planet_name_view);
    }

    public void onBind(final Planet planet){
        nameTextView.setText(planet.getName());


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), DisplayActivity.class);
                Bundle bundle = new Bundle();
                String planetName = planet.getName();
                String planetNumber = planet.getNumber();
                String image = planet.getImage();

                 bundle.putString("planetName", planetName);
                 bundle.putString("planetNumber", planetNumber);
                 bundle.putString("image",image);

                 intent.putExtras(bundle);

                 itemView.getContext().startActivity(intent);

            }
        });

    }
}
