package br.com.uneb.tebd.artigos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ArticleAdapter.ArticleClickListener {

    private RetrofitConfig retrofitConfig;
    private DatabaseHelper databaseHelper;

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitConfig = RetrofitConfig.getInstance();
        databaseHelper = DatabaseHelper.getInstance(this);
        mRecyclerView = findViewById(R.id.recycler_view);

        initUi();
    }

    private void initUi() {
        mAdapter = new ArticleAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        Call<ArrayList<Article>> call = retrofitConfig.getRetrofitService().getAllArticles();
        call.enqueue(new Callback<ArrayList<Article>>() {
            @Override
            public void onResponse(Call<ArrayList<Article>> call, Response<ArrayList<Article>> response) {
                mAdapter.addArticles(response.body());
                response.body().forEach(article -> {
                    databaseHelper.saveArticle(article);
                });
            }

            @Override
            public void onFailure(Call<ArrayList<Article>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                ArrayList<Article> articles = databaseHelper.getAllArticle();
                mAdapter.addArticles(articles);
            }
        });
    }

    @Override
    public void onClick(Article article) {
        Intent intent = new Intent(this, ArticleDetailsActivity.class);
        intent.putExtra(ArticleDetailsActivity.ARTICLE_ID, article.getId());
        intent.putExtra(ArticleDetailsActivity.ARTICLE_RESUME, article.getResume());
        startActivity(intent);
    }
}
