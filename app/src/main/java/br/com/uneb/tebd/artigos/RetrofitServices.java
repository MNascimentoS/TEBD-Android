package br.com.uneb.tebd.artigos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitServices {

    @GET("authors")
    Call<ArrayList<Author>> getAllAuthors();

    @GET("authors/{authorId}/articles")
    Call<ArrayList<Article>> getAllArticlesFromAuthor(@Path("authorId") int authorId);

    @GET("articles")
    Call<ArrayList<Article>> getAllArticles();

    @GET("articles/{articleId}/article")
    Call<Author> getAuthorFromArticle(@Path("articleId") int articleId);

    @GET("articles/{articleId}/reviews")
    Call<ArrayList<Review>> getAllReviewFromArticle(@Path("articleId") int articleId);

}
