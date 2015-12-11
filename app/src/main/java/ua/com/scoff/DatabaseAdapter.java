package ua.com.scoff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

    public ArrayList<String[]> getSpansData(){
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {SQLHelper.SPANS_ID, SQLHelper.SPANS_NAME, SQLHelper.SPANS_DATETIME, SQLHelper.SPANS_ISCLOSED};
        Cursor cursor = db.query(SQLHelper.TABLE_SPANS, columns, null, null, null, null, SQLHelper.SPANS_ID+" DESC");

        ArrayList<String[]> result = new ArrayList<>();
        while (cursor.moveToNext()){
            int idIndex = cursor.getColumnIndex(SQLHelper.SPANS_ID);
            int nameIndex = cursor.getColumnIndex(SQLHelper.SPANS_NAME);
            int datetimeIndex = cursor.getColumnIndex(SQLHelper.SPANS_DATETIME);
            int isClosedIndex = cursor.getColumnIndex(SQLHelper.SPANS_ISCLOSED);

            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            String datetime = cursor.getString(datetimeIndex);
            int isClosed = cursor.getInt(isClosedIndex);

            String[] row = {String.valueOf(id), name, datetime, String.valueOf(isClosed)};
            result.add(row);
        }
        cursor.close();
        db.close();

        return result;
    }


    public ArrayList<String[]> getProductsData(){
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {SQLHelper.PRODUCTS_ID, SQLHelper.PRODUCTS_DENOMINATION,
                SQLHelper.PRODUCTS_PROTEINS, SQLHelper.PRODUCTS_FATS, SQLHelper.PRODUCTS_CARBOHYDRATES,
                SQLHelper.PRODUCTS_CALORIES, SQLHelper.PRODUCTS_FREQUENCY};

        // Здесь сделать сортировку по PRODUCTS_FREQUENCY
        Cursor cursor = db.query(SQLHelper.TABLE_PRODUCTS, columns, null, null, null, null, SQLHelper.PRODUCTS_DENOMINATION);

        ArrayList<String[]> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(SQLHelper.PRODUCTS_ID);
            int denominationIndex = cursor.getColumnIndex(SQLHelper.PRODUCTS_DENOMINATION);
            int proteinsIndex = cursor.getColumnIndex(SQLHelper.PRODUCTS_PROTEINS);
            int fatsIndex = cursor.getColumnIndex(SQLHelper.PRODUCTS_FATS);
            int carbohydratesIndex = cursor.getColumnIndex(SQLHelper.PRODUCTS_CARBOHYDRATES);
            int caloriesIndex = cursor.getColumnIndex(SQLHelper.PRODUCTS_CALORIES);
            int frequencyIndex = cursor.getColumnIndex(SQLHelper.PRODUCTS_FREQUENCY);

            int id = cursor.getInt(idIndex);
            String denomination = cursor.getString(denominationIndex);
            int proteins = cursor.getInt(proteinsIndex);
            int fats = cursor.getInt(fatsIndex);
            int carbohydrates = cursor.getInt(carbohydratesIndex);
            int calories = cursor.getInt(caloriesIndex);
            int frequency = cursor.getInt(frequencyIndex);

            String[] row = {String.valueOf(id), denomination, String.valueOf(proteins),
                    String.valueOf(fats), String.valueOf(carbohydrates), String.valueOf(calories), String.valueOf(frequency)};
            result.add(row);

        }
        cursor.close();
        db.close();
        return result;
    }

    public int insertProduct(String denomination, int proteins, int fats, int carbohydrates, int caloricCapacity){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLHelper.PRODUCTS_DENOMINATION, denomination);
        contentValues.put(SQLHelper.PRODUCTS_PROTEINS, proteins);
        contentValues.put(SQLHelper.PRODUCTS_FATS, fats);
        contentValues.put(SQLHelper.PRODUCTS_CARBOHYDRATES, carbohydrates);
        contentValues.put(SQLHelper.PRODUCTS_CALORIES, caloricCapacity);
        long id = db.insert(SQLHelper.TABLE_PRODUCTS, SQLHelper.PRODUCTS_FREQUENCY, contentValues);

        db.close();
        return (int)id;
    }

    public int insertSpan(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (name == null){
            name = StaticUtils.getCurrentDateTime(true);
        }
        contentValues.put(SQLHelper.SPANS_NAME, name);
        contentValues.put(SQLHelper.SPANS_ISCLOSED, false);
        long id = db.insert(SQLHelper.TABLE_SPANS, SQLHelper.SPANS_DATETIME, contentValues);

        db.close();
        return (int)id;
    }

    public int closeOrOpenSpan(int id, boolean close){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLHelper.SPANS_ISCLOSED, close);
        String[] whereArgs = {String.valueOf(id)};
        int count = db.update(SQLHelper.TABLE_SPANS, contentValues, SQLHelper.SPANS_ID+ " =?", whereArgs);
        db.close();
        return count;
    }

    public int insertRecordToSpan(int productId){
        return 0;
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
