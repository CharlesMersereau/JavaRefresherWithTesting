package com.revature.javarefresherwithtesting.ui.posts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.revature.javarefresherwithtesting.R;
import com.revature.javarefresherwithtesting.data.entities.Post;
import com.revature.javarefresherwithtesting.databinding.ItemPostBinding;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ListAdapter<Post, PostAdapter.PostHolder> {

    private Context context;
    private OnPostItemClickListener mOnPostItemClickListener;

    private static final DiffUtil.ItemCallback<Post> DIFF_CALLBACK = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getBody().equals(newItem.getTitle());
        }
    };

    public PostAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post = getItem(position);
        holder.itemPostBinding.tvItempostId.setText(context.getString(R.string.item_post_id, post.getId()));
        holder.itemPostBinding.tvItempostUserid.setText(context.getString(R.string.item_post_userid, post.getUserId()));
        holder.itemPostBinding.tvItempostTitle.setText(context.getString(R.string.item_post_title, post.getTitle()));
        holder.itemPostBinding.tvItempostBody.setText(context.getString(R.string.item_post_body, post.getBody()));
    }

    Post getNoteAt(Integer pos) {
        return getItem(pos);
    }

    class PostHolder extends RecyclerView.ViewHolder {

        ItemPostBinding itemPostBinding;

        PostHolder(@NonNull ItemPostBinding itemPostBinding) {
            super(itemPostBinding.getRoot());
            this.itemPostBinding = itemPostBinding;
            this.itemPostBinding.getRoot().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mOnPostItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        mOnPostItemClickListener.onPostItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnPostItemClickListener {
        void onPostItemClick(Post post);
    }

    public void setOnPostItemClickListener(OnPostItemClickListener onPostItemClickListener) {
        mOnPostItemClickListener = onPostItemClickListener;
    }
}
