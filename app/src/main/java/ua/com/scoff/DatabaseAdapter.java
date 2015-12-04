package ua.com.scoff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oleh on 12/4/15.
 */
public class DatabaseAdapter {

    SQLHelper helper;
    Context context;
    public DatabaseAdapter(Context context){
        helper = new SQLHelper(context);
        this.context = context;
    }







    static class SQLHelper extends SQLiteOpenHelper {

        private Context context;
        private static final String DATABASE_NAME = "scoff";
        private static final int DATABASE_VERSION = 1;

        // Table products
        private static final String TABLE_PRODUCTS = "products";
        private static final String PRODUCTS_ID = "id";
        private static final String PRODUCTS_DENOMINATION = "denomination";
        private static final String PRODUCTS_PROTEINS = "proteins";
        private static final String PRODUCTS_FATS = "fats";
        private static final String PRODUCTS_CARBOHYDRATES = "carbohydrates";
        private static final String PRODUCTS_CALORIES = "calories";
        private static final String PRODUCTS_FREQUENCY = "frequency";

        // Table spans
        private static final String TABLE_SPANS = "spans";
        private static final String SPANS_ID = "id";
        private static final String SPANS_NAME = "name";
        private static final String SPANS_DATETIME = "datetime";
        private static final String SPANS_ISCLOSED = "isclosed";

        // Table records
        private static final String TABLE_RECORDS = "records";
        private static final String RECORDS_DATETIME = "datetime";
        private static final String RECORDS_
        private static final String RECORDS_
        private static final String RECORDS_

        public SQLHelper(Context context) {
            super(context, name, factory, version);
            this.context = context;
            SQLiteDatabase db = this.getWritableDatabase();
            db.setForeignKeyConstraintsEnabled(true);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
