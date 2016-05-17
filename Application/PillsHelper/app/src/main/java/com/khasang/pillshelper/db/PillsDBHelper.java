package com.khasang.pillshelper.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.khasang.pillshelper.Drug;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class PillsDBHelper extends SQLiteAssetHelper {
    public static final int RESULT_OK = 1;
    public static final int RESULT_ERROR = 2;

    public static final int INITIALIZE_DB = 1;
    public static final int GET_ALL_DRUGS = 2;

    private static final String DATABASE_NAME = "pills.db";
    private static final int DATABASE_VERSION = 1;

    private static volatile PillsDBHelper instance = null;

    public static PillsDBHelper getInstance(Context context){
        if(instance == null){
            synchronized (PillsDBHelper.class){
                if(instance == null){
                    instance = new PillsDBHelper(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private PillsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private Boolean initializeDB() {
        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            db.close();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private ArrayList<Drug> getAllDrugs() {
        ArrayList<Drug> drugs = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query("drug", new String[]{"_id", "name"}, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String name = c.getString(1);

            drugs.add(new Drug(id, name));

            c.moveToNext();
        }

        c.close();
        db.close();

        return drugs;
    }

    /**
     * Обобщенный метод для обращения к базе данных
     * @param request тип запроса к БД
     * @param listener обработчик ответа
     * @param <T> тип возвращаемого значения
     *
     * <p><b>Коды запросов и типы ответов:</b>
     *           <ul>
     *           <li>{@linkplain #INITIALIZE_DB} -> Boolean</li>
     *           <li>{@linkplain #GET_ALL_DRUGS} -> ArrayList&lt;Drug&gt;</li>
     *           </ul>
     */
    public <T> void runRequestAsync(int request, final Listener<T> listener) {

        new DataFetcher<T>(listener).execute(request);
    }

    public interface Listener<T> {
        void onHelperAnswerReceived(int code, T response);
    }

    public class DataFetcher<T> extends AsyncTask<Integer, Void, T> {
        Listener<T> listener;

        public DataFetcher(Listener<T> listener) {
            this.listener = listener;
        }

        @Override
        protected T doInBackground(Integer... params) {
            int request = params[0];

            switch (request) {
                case GET_ALL_DRUGS: return (T) getAllDrugs();
                case INITIALIZE_DB: return (T) initializeDB();

                default: return null;
            }
        }

        @Override
        protected void onPostExecute(T t) {
            if (t != null) {
                listener.onHelperAnswerReceived(RESULT_OK, t);
            } else {
                listener.onHelperAnswerReceived(RESULT_ERROR, null);
            }

        }
    }

}
