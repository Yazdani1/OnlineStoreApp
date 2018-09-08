package yazdaniscodelab.onlinestoreapp;


import android.content.Intent;
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
public class AllFragment extends Fragment {


    private RecyclerView allRecycler;
    private RecyclerView recyclerViewCatTwo;

    //Firebase.

    private DatabaseReference mCatOneDatabase;

    private DatabaseReference mCatTwoDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.fragment_all, container, false);

        mCatOneDatabase= FirebaseDatabase.getInstance().getReference().child("CatOneDatabase");

        mCatOneDatabase.keepSynced(true);

        mCatTwoDatabase=FirebaseDatabase.getInstance().getReference().child("CatTwoDatabase");

        mCatTwoDatabase.keepSynced(true);

        ///Cat one Recycler..

        allRecycler=myview.findViewById(R.id.recycler_all);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        allRecycler.setHasFixedSize(true);
        allRecycler.setLayoutManager(layoutManager);


        //Cat two recycler...

        recyclerViewCatTwo=myview.findViewById(R.id.recycler_cattwo);

        LinearLayoutManager layoutManagerCatTwo=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        layoutManagerCatTwo.setStackFromEnd(true);
        layoutManagerCatTwo.setReverseLayout(true);

        recyclerViewCatTwo.setHasFixedSize(true);
        recyclerViewCatTwo.setLayoutManager(layoutManagerCatTwo);


        return myview;

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data,CatOneViewHolder>adapterOne=new FirebaseRecyclerAdapter<Data, CatOneViewHolder>
                (
                        Data.class,
                        R.layout.item_data,
                        AllFragment.CatOneViewHolder.class,
                        mCatOneDatabase

                ) {
            @Override
            protected void populateViewHolder(CatOneViewHolder viewHolder, final Data model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(model.getImage());

                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(getActivity(),CatOneDetailsActivity.class);

                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("image",model.getImage());
                        startActivity(intent);

                    }
                });


            }
        };

        allRecycler.setAdapter(adapterOne);


        FirebaseRecyclerAdapter<Data,CatTwoViewHolder>adapterTwo=new FirebaseRecyclerAdapter<Data, CatTwoViewHolder>
                (
                        Data.class,
                        R.layout.item_data,
                        CatTwoViewHolder.class,
                        mCatTwoDatabase
                ) {
            @Override
            protected void populateViewHolder(CatTwoViewHolder viewHolder, final Data model, int position) {

                viewHolder.msetTitle(model.getTitle());
                viewHolder.msetDescription(model.getDescription());
                viewHolder.msetImage(model.getImage());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(),CatTwoDetailsActivity.class);
                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("image",model.getImage());
                        startActivity(intent);
                    }
                });
            }
        };

        recyclerViewCatTwo.setAdapter(adapterTwo);

    }

    public static class CatOneViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public CatOneViewHolder(View itemView) {
            super(itemView);
            myview=itemView;
        }

        public void setTitle(String title){
            TextView mTitle=myview.findViewById(R.id.title);
            mTitle.setText(title);
        }

        public void setDescription(String description){
            TextView mDescription = myview.findViewById(R.id.description);
            mDescription.setText(description);
        }

        public void setImage(final String image){
            final ImageView mImage=myview.findViewById(R.id.imageView);

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

    public static class CatTwoViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public CatTwoViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void msetTitle(String title){
            TextView mTitle=mView.findViewById(R.id.title);
            mTitle.setText(title);
        }

        public void msetDescription(String description){
            TextView mDescription=mView.findViewById(R.id.description);
            mDescription.setText(description);
        }

        public void msetImage(final String image){
            final ImageView mImageview=mView.findViewById(R.id.imageView);

            Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mImageview, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(image).into(mImageview);
                }
            });

        }

    }




}
