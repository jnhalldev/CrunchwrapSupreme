package com.example.crunchwrapsupreme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crunchwrapsupreme.databinding.CustomAdsLayoutBinding;

import java.util.ArrayList;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.UserViewHolder> {

    private Context context;
    private ArrayList<Ads> adsArrayList;
    AdsAdapter.OnItemClickListener mListener;

    public AdsAdapter(Context context, ArrayList<Ads> adsArrayList) {
        this.context = context;
        this.adsArrayList = adsArrayList;
    }


    public interface OnItemClickListener {
        void onDeleteClick(Ads ads, int position);
    }

    public void setOnItemClickListener(AdsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomAdsLayoutBinding binding = CustomAdsLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Ads ads = adsArrayList.get(position);
        holder.bind(ads);
    }

    @Override
    public int getItemCount() {
        return adsArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private final CustomAdsLayoutBinding binding;

        public UserViewHolder(@NonNull CustomAdsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.deleteBtn.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Ads ads = adsArrayList.get(position);
                    mListener.onDeleteClick(ads, position);
                }
            });
        }

        public void bind(Ads ads) {

            Glide.with(context).load(ads.getImage())
                            .placeholder(R.drawable.nophoto)
                                    .into(binding.adImage);
            binding.adDescription.setText(ads.getDescription());
            binding.adName.setText(ads.getName());
            binding.adUrl.setText(ads.getUrl());

        }
    }
}
