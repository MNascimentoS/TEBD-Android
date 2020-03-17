package br.com.uneb.tebd.artigos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    static DatabaseHelper getInstance(Context context) {
        if (instance == null) instance = new DatabaseHelper(context);
        return instance;
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Database.db";

    private static final String SQL_CREATE_ARTICLE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.ArticleEntry.TABLE_NAME + " (" +
                    DatabaseContract.ArticleEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.ArticleEntry.COLUMN_NAME_ID + " INT," +
                    DatabaseContract.ArticleEntry.COLUMN_NAME_RESUME + " TEXT," +
                    DatabaseContract.ArticleEntry.COLUMN_NAME_CREATED_AT + " TEXT," +
                    DatabaseContract.ArticleEntry.COLUMN_NAME_UPDATE_AT + " TEXT," +
                    DatabaseContract.ArticleEntry.COLUMN_NAME_DELETED_AT + " TEXT)";

    private static final String SQL_CREATE_AUTHOR_ENTRIES =
            "CREATE TABLE " + DatabaseContract.AuthorEntry.TABLE_NAME + " (" +
                    DatabaseContract.AuthorEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_ID + " INT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_NAME + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_ADDRESS + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_PHONE + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_JOB + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_INSCRIPTION_NUMBER + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_DUE_DATE + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_CREDIT_CARD_NUMBER + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_VOLUNTARY + " BOOL," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_CREATED_AT + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_UPDATE_AT + " TEXT," +
                    DatabaseContract.AuthorEntry.COLUMN_NAME_DELETED_AT + " TEXT)";

    private static final String SQL_CREATE_REVIEW_ENTRIES =
            "CREATE TABLE " + DatabaseContract.ReviewEntry.TABLE_NAME + " (" +
                    DatabaseContract.ReviewEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.ReviewEntry.COLUMN_NAME_AUTHOR_ID + " INT," +
                    DatabaseContract.ReviewEntry.COLUMN_NAME_ARTICLE_ID + " INT," +
                    DatabaseContract.ReviewEntry.COLUMN_NAME_COMMENTS + " TEXT," +
                    DatabaseContract.ReviewEntry.COLUMN_NAME_GRADE + " TEXT," +
                    DatabaseContract.ReviewEntry.COLUMN_NAME_CREATED_AT + " TEXT," +
                    DatabaseContract.ReviewEntry.COLUMN_NAME_UPDATE_AT + " TEXT," +
                    DatabaseContract.ReviewEntry.COLUMN_NAME_DELETED_AT + " TEXT)";

    private static final String SQL_CREATE_AUTHOR_ARTICLE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.AuthorArticleEntry.TABLE_NAME + " (" +
                    DatabaseContract.AuthorArticleEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.AuthorArticleEntry.COLUMN_NAME_AUTHOR_ID + " INT," +
                    DatabaseContract.AuthorArticleEntry.COLUMN_NAME_ARTICLE_ID + " INT," +
                    DatabaseContract.AuthorArticleEntry.COLUMN_NAME_CREATED_AT + " TEXT," +
                    DatabaseContract.AuthorArticleEntry.COLUMN_NAME_UPDATE_AT + " TEXT," +
                    DatabaseContract.AuthorArticleEntry.COLUMN_NAME_DELETED_AT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.ArticleEntry.TABLE_NAME;

    void saveArticle(Article article) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ArticleEntry.COLUMN_NAME_ID, article.getId());
        values.put(DatabaseContract.ArticleEntry.COLUMN_NAME_RESUME, article.getResume());
        values.put(DatabaseContract.ArticleEntry.COLUMN_NAME_CREATED_AT, article.getCreated_at());
        values.put(DatabaseContract.ArticleEntry.COLUMN_NAME_UPDATE_AT, article.getUpdate_at());
        values.put(DatabaseContract.ArticleEntry.COLUMN_NAME_DELETED_AT, article.getDeleted_at());

        instance.getWritableDatabase().insert(DatabaseContract.ArticleEntry.TABLE_NAME, null, values);
    }

    ArrayList<Article> getAllArticle() {
        ArrayList<Article> articles = new ArrayList<Article>();
        SQLiteDatabase db = instance.getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + DatabaseContract.ArticleEntry.TABLE_NAME, null);

        while(c.moveToNext()) {
            Article article = new Article();

            article.setId(c.getInt(c.getColumnIndex(DatabaseContract.ArticleEntry.COLUMN_NAME_ID)));
            article.setResume(c.getString(c.getColumnIndex(DatabaseContract.ArticleEntry.COLUMN_NAME_RESUME)));
            article.setCreated_at(c.getString(c.getColumnIndex(DatabaseContract.ArticleEntry.COLUMN_NAME_CREATED_AT)));
            article.setUpdate_at(c.getString(c.getColumnIndex(DatabaseContract.ArticleEntry.COLUMN_NAME_UPDATE_AT)));
            article.setDeleted_at(c.getString(c.getColumnIndex(DatabaseContract.ArticleEntry.COLUMN_NAME_DELETED_AT)));

            articles.add(article);
        }
        c.close();
        return articles;
    }

    void saveAuthor(Author author) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_ID, author.getId());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_NAME, author.getName());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_ADDRESS, author.getAddress());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_PHONE, author.getPhone());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_EMAIL, author.getEmail());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_JOB, author.getJob());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_INSCRIPTION_NUMBER, author.getInscription_number());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_DUE_DATE, author.getDue_date());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_CREDIT_CARD_NUMBER, author.getCredit_card_number());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_VOLUNTARY, author.isVoluntary());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_CREATED_AT, author.getCreated_at());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_UPDATE_AT, author.getUpdate_at());
        values.put(DatabaseContract.AuthorEntry.COLUMN_NAME_DELETED_AT, author.getDeleted_at());

        instance.getWritableDatabase().insert(DatabaseContract.ArticleEntry.TABLE_NAME, null, values);
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ARTICLE_ENTRIES);
        db.execSQL(SQL_CREATE_AUTHOR_ENTRIES);
        db.execSQL(SQL_CREATE_REVIEW_ENTRIES);
        db.execSQL(SQL_CREATE_AUTHOR_ARTICLE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}