package ua.com.scoff;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

        private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "+TABLE_PRODUCTS+"("
                    + PRODUCTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PRODUCTS_DENOMINATION + " VARCHAR(255), "
                    + PRODUCTS_PROTEINS + " INTEGER NOT NULL, "
                    + PRODUCTS_FATS + " INTEGER NOT NULL, "
                    + PRODUCTS_CARBOHYDRATES + " INTEGER NOT NULL, "
                    + PRODUCTS_CALORIES + " INTEGER NOT NULL, "
                    + PRODUCTS_FREQUENCY + " INTEGER DEFAULT 0"
                + ");";
        private static final String DROP_TABLE_PRODUCTS = "DROP TABLE IF EXISTS " + TABLE_PRODUCTS;

        // Table spans
        private static final String TABLE_SPANS = "spans";
        private static final String SPANS_ID = "id";
        private static final String SPANS_NAME = "name";
        private static final String SPANS_DATETIME = "datetime";
        private static final String SPANS_ISCLOSED = "isclosed";

        private static final String CREATE_TABLE_SPANS = "CREATE TABLE " + TABLE_SPANS + "("
                    + SPANS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SPANS_NAME + " VARCHAR(255), "
                    + SPANS_DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                    + SPANS_ISCLOSED + " BOOLEAN DEFAULT 0"
                + ");";
        private static final String DROP_TABLE_SPANS = "DROP TABLE IF EXISTS " + TABLE_SPANS;

        // Table records
        private static final String TABLE_RECORDS = "records";
        private static final String RECORDS_ID = "id";
        private static final String RECORDS_DATETIME = "datetime";
        private static final String RECORDS_RELATED_PRODUCT = "related_product";
        private static final String RECORDS_RELATED_SPAN = "related_span";

        private static final String CREATE_TABLE_RECORDS = "CREATE TABLE " + TABLE_RECORDS + "("
                    + RECORDS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + RECORDS_DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                    + "FOREIGN KEY(" + RECORDS_RELATED_PRODUCT + ") REFERENCES "
                                + TABLE_PRODUCTS + "(" + PRODUCTS_ID + ")  ON DELETE CASCADE, "
                    + "FOREIGN KEY(" + RECORDS_RELATED_SPAN + ") REFERENCES "
                                + TABLE_SPANS + "(" + SPANS_ID + ")  ON DELETE CASCADE"
                + ");";
        private static final String DROP_TABLE_RECORDS = "DROP TABLE IF EXISTS " + TABLE_RECORDS;

        public SQLHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            SQLiteDatabase db = this.getWritableDatabase();
            db.setForeignKeyConstraintsEnabled(true);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_PRODUCTS);
                db.execSQL(CREATE_TABLE_SPANS);
                db.execSQL(CREATE_TABLE_RECORDS);
            } catch (SQLException e){
                e.printStackTrace();
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            try {
                db.execSQL(DROP_TABLE_PRODUCTS);
                db.execSQL(DROP_TABLE_SPANS);
                db.execSQL(DROP_TABLE_RECORDS);
            } catch (SQLException e){
                e.printStackTrace();
                Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
            }
        }
    }
}
