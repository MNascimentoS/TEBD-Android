package br.com.uneb.tebd.artigos;

import android.provider.BaseColumns;

final class DatabaseContract {

    static class ArticleEntry implements BaseColumns {
        static final String TABLE_NAME = "article";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_RESUME = "resume";
        static final String COLUMN_NAME_CREATED_AT = "created_at";
        static final String COLUMN_NAME_UPDATE_AT = "update_at";
        static final String COLUMN_NAME_DELETED_AT = "deleted_at";
    }

    static class AuthorEntry implements BaseColumns {
        static final String TABLE_NAME = "author";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_ADDRESS = "address";
        static final String COLUMN_NAME_PHONE = "phone";
        static final String COLUMN_NAME_EMAIL = "email";
        static final String COLUMN_NAME_JOB = "job";
        static final String COLUMN_NAME_INSCRIPTION_NUMBER = "inscription_number";
        static final String COLUMN_NAME_DUE_DATE = "due_date";
        static final String COLUMN_NAME_CREDIT_CARD_NUMBER = "credit_card_number";
        static final String COLUMN_NAME_VOLUNTARY = "voluntary";
        static final String COLUMN_NAME_CREATED_AT = "created_at";
        static final String COLUMN_NAME_UPDATE_AT = "update_at";
        static final String COLUMN_NAME_DELETED_AT = "deleted_at";
    }

    static class ReviewEntry implements BaseColumns {
        static final String TABLE_NAME = "review";
        static final String COLUMN_NAME_AUTHOR_ID = "author_id";
        static final String COLUMN_NAME_ARTICLE_ID = "article_id";
        static final String COLUMN_NAME_COMMENTS = "comments";
        static final String COLUMN_NAME_GRADE = "grade";
        static final String COLUMN_NAME_CREATED_AT = "created_at";
        static final String COLUMN_NAME_UPDATE_AT = "update_at";
        static final String COLUMN_NAME_DELETED_AT = "deleted_at";
    }

    static class AuthorArticleEntry implements BaseColumns {
        static final String TABLE_NAME = "author_article";
        static final String COLUMN_NAME_AUTHOR_ID = "author_id";
        static final String COLUMN_NAME_ARTICLE_ID = "article_id";
        static final String COLUMN_NAME_CREATED_AT = "created_at";
        static final String COLUMN_NAME_UPDATE_AT = "update_at";
        static final String COLUMN_NAME_DELETED_AT = "deleted_at";
    }

}
