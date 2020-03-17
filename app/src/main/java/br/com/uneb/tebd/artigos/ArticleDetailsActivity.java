package br.com.uneb.tebd.artigos;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailsActivity extends AppCompatActivity {

    public static final String ARTICLE_ID = "article_id";
    public static final String ARTICLE_RESUME = "article_resume";

    private RetrofitConfig retrofitConfig;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        retrofitConfig = RetrofitConfig.getInstance();
        databaseHelper = DatabaseHelper.getInstance(this);
        getData();
    }

    private void getData() {
        int articleId = getIntent().getIntExtra(ARTICLE_ID, 0);
        String articleResume = getIntent().getStringExtra(ARTICLE_RESUME);

        TextView resume = findViewById(R.id.resume_text);
        resume.setText(articleResume);

        Call<Author> call = retrofitConfig.getRetrofitService().getAuthorFromArticle(articleId);
        call.enqueue(new Callback<Author>() {
            @Override
            public void onResponse(Call<Author> call, Response<Author> response) {
                TextView authorName = findViewById(R.id.author_name_text);
                TextView authorPhone = findViewById(R.id.phone_text);
                authorName.setText(response.body().getName());
                authorPhone.setText(response.body().getPhone());
                databaseHelper.saveAuthor(response.body());
            }

            @Override
            public void onFailure(Call<Author> call, Throwable t) {
                Toast.makeText(ArticleDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                TextView authorName = findViewById(R.id.author_name_text);
                TextView authorPhone = findViewById(R.id.phone_text);
                authorName.setText(R.string.default_author);
                authorPhone.setText(R.string.phone_author);
            }
        });
    }

}
