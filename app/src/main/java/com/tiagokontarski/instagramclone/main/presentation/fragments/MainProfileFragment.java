package com.tiagokontarski.instagramclone.main.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tiagokontarski.instagramclone.R;

public class MainProfileFragment extends Fragment {
    public MainProfileFragment() {
    }

    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_profile, container, false);

        rv = view.findViewById(R.id.main_profile_rv);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv.setAdapter(new MockAdapter());

        return view;
    }

    private class MockAdapter extends RecyclerView.Adapter<MockViewHolder>{

        private int[] image = new int[]{
                R.drawable.ic_insta_add,
                R.drawable.ic_insta_add,
                R.drawable.ic_insta_add,
                R.drawable.ic_insta_add,
                R.drawable.ic_insta_add,
                R.drawable.ic_insta_add,
                R.drawable.ic_insta_add,
                R.drawable.ic_insta_add
        };

        @NonNull
        @Override
        public MockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = getLayoutInflater().inflate(R.layout.item_rv_main_profile, parent, false);
            return new MockViewHolder(root);
        }

        @Override
        public void onBindViewHolder(@NonNull MockViewHolder holder, int position) {
            holder.bind(image[position]);
        }

        @Override
        public int getItemCount() {
            return image.length;
        }
    }

    private static class MockViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imagePost;

        public MockViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePost = itemView.findViewById(R.id.profile_image_grid);
        }

        public void bind(int image){
            this.imagePost.setImageResource(image);
        }
    }
}
