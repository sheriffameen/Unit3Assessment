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

        nameTextView = itemView.findViewById(R.id.name_display_textview);
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
