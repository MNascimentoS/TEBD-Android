package br.com.uneb.tebd.artigos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<Article> mArticleList = new ArrayList<Article>();
    private ArticleClickListener mListener;

    ArticleAdapter(ArticleClickListener listener) {
        mListener = listener;
    }

    void addArticles(ArrayList<Article> articleList) {
        mArticleList.addAll(articleList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.resume.setText(mArticleList.get(position).getResume());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(mArticleList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    interface ArticleClickListener {
        void onClick(Article article);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View container;
        TextView resume;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            resume = itemView.findViewById(R.id.resume_text);
        }
    }

}
