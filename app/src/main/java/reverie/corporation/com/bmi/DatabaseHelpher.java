package reverie.corporation.com.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class DatabaseHelpher extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="health";
    private static final int DATABASE_VERSION = 1;
    private static final String HEALTH_TABLE = "bmi";
    private static final String BMI_TABLE = "CREATE TABLE "+HEALTH_TABLE +"(result REAL, created_at INTEGER DEFAULT ('now'))";

Context context;

    public DatabaseHelpher(Context context) {
        /*super(context, DATABASE_NAME, null, DATABASE_VERSION);*/
        super(context, Environment.getExternalStorageDirectory()
                + File.separator+ DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(BMI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HEALTH_TABLE);

        // Create tables again
        onCreate(db);
    }

 /*   private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }*/

/* Insert into database*/
    public void insertIntoDB(double result, int created_at){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("result", result);
        values.put("created_at", created_at);

        // 3. insert
        db.insert(HEALTH_TABLE, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }
/* Retrive  data from database */
    public double getDataFromDB(){

        /*String query = "select * from "+HEALTH_TABLE +"where strftime('%d', created_at) = '18'";*/
        String query = "SELECT AVG(result) FROM "+HEALTH_TABLE +"WHERE STRFTIME('%d', created_at) = '18'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        double resultno = 0;

        if (cursor.moveToFirst()){
            do {
                resultno = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }

        Log.d("result data: ", ""+resultno);

        return resultno;
    }

}
