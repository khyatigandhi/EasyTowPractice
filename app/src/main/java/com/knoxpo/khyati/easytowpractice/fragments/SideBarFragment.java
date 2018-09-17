package com.knoxpo.khyati.easytowpractice.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knoxpo.khyati.easytowpractice.R;
import com.knoxpo.khyati.easytowpractice.models.SideItem;
import com.knoxpo.khyati.easytowpractice.models.SideItemLab;

public class SideBarFragment extends Fragment {

    public interface Callback{
        void onMenuClicked(long menuId);
    }

    private Callback mCallback;

    private RecyclerView mRecyclerView;
    private SideItemLab mSideItemLab;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) getActivity();
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSideItemLab = SideItemLab.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_side_bar, container, false);

        init(v);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new SideItemAdapter());

        return v;
    }

    private void init(View v){
        mRecyclerView = v.findViewById(R.id.recyclerView);
    }

    private class SideItemAdapter extends RecyclerView.Adapter<SideItemVH>{

        private LayoutInflater mInflater;

        public SideItemAdapter(){
            mInflater = LayoutInflater.from(getActivity());
        }

        @NonNull
        @Override
        public SideItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new SideItemVH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull SideItemVH holder, int position) {
            holder.bindSideItem(mSideItemLab.getSideItems().get(position));
        }

        @Override
        public int getItemCount() {
            return mSideItemLab.getSize();
        }
    }

    private class SideItemVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        private SideItem mBoundSideItem;

        public SideItemVH(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void bindSideItem(SideItem sideItem){
            mBoundSideItem = sideItem;
            ((TextView)itemView).setText(sideItem.getTitle());
        }

        @Override
        public void onClick(View v) {
            if(mBoundSideItem != null){
                mCallback.onMenuClicked(mBoundSideItem.getId());
            }
        }
    }
}
