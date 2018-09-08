package yazdaniscodelab.onlinestoreapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import yazdaniscodelab.onlinestoreapp.Model.Data;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatOneFragment extends Fragment {


    private RecyclerView recyclerView;

    //Firebase..

    private DatabaseReference mCatOneDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.fragment_cat_one, container, false);

        mCatOneDatabase= FirebaseDatabase.getInstance().getReference().child("CatOneDatabase");
        mCatOneDatabase.keepSynced(true);

        recyclerView=myview.findViewById(R.id.recycler_cat_one);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        return myview;

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,CatOneviewHolder>adapter=new FirebaseRecyclerAdapter<Data, CatOneviewHolder>
                (
                        Data.class,
                        R.layout.cus_item_data,
                        CatOneFragment.CatOneviewHolder.class,
                        mCatOneDatabase

                ) {
            @Override
            protected void populateViewHolder(CatOneviewHolder viewHolder, Data model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(model.getImage());

            }
        };

        recyclerView.setAdapter(adapter);

    }

    public static class CatOneviewHolder extends RecyclerView.ViewHolder{

        View mView;

        public CatOneviewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setTitle(String title){
            TextView mTitle=mView.findViewById(R.id.post_title);
            mTitle.setText(title);
        }

        public void setDescription(String description){
            TextView mDescription=mView.findViewById(R.id.post_details);
            mDescription.setText(description);
        }

        public void setImage(final String image){
            final ImageView mImage=mView.findViewById(R.id.post_image);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).into(mImage);
                }
            });

        }


    }


}
