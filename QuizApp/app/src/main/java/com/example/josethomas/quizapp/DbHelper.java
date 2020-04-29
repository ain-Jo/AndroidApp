package com.example.josethomas.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoseThomas on 3/31/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    //DB version, table and database name
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "quizdb";
    private static final String DB_TABLE = "quiztable";

    //table column names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "optA";
    private static final String KEY_OPTB = "optB";
    private static final String KEY_OPTC = "optC";

    private SQLiteDatabase dbase;
    private int rowCount = 0;

    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT )", DB_TABLE, KEY_ID, KEY_QUES, KEY_ANSWER, KEY_OPTA, KEY_OPTB, KEY_OPTC);
        Log.d("TaskDBHelper", "Query to form table" + sqlQuery);
        db.execSQL(sqlQuery);
        addQuestions();
    }

    private void addQuestions() {
        Question q1 = new Question("고양이의 발열 수준은 어느정도입니까?", "귀가 뜨거움", "발바닥에서 땀이 남", "코가 건조하고, 눈이 빨감", "온몸이 뜨거우며 숨을 헥헥거림");
        this.addQuestionToDB(q1);
        Question q2 = new Question("고양이가 구토 증세를 보이나요? 한다면, 어떤 구토를 하나요?", "노란 토(위액토)", "사료토", "초록색 토", "혈토", "토를 하지 않음");
        this.addQuestionToDB(q2);
        Question q3 = new Question("고양이의 전반적인 상태는 어떻습니까?", "밥을 전혀 먹지 않고, 힘없이 늘어져있음", "밥을 잘 먹지 않고, 거의 돌아다니지 않음", "사료를 조금 먹고, 이따금 돌아다님", "잘 먹고, 잘 뛰어다님");
        this.addQuestionToDB(q3);
        Question q4 = new Question("고양이의 눈꺼풀 안쪽을 살펴주세요. 색깔은 어떠합니까?", "창백한 흰색, "노란색", "빨간색 실핏줄", "약간 붉은기가 도는 정상색");
        this.addQuestionToDB(q4);
        Question q5 = new Question("고양이의 발톱 상태를 점검해주세요.", "갈라짐", "부러짐", "이상없음");
        this.addQuestionToDB(q5);
        Question q6 = new Question("고양이의 변 상태를 선택해주세요.", "물설사", "되직함", "무른변", "정상변");
        this.addQuestionToDB(q6);
        Question q7 = new Question("고양이의 변 색깔을 선택해주세요.", "혈변", "녹색", "진한 갈색", "검은색");
        this.addQuestionToDB(q7);
        Question q8 = new Question("고양이의 숨소리를 들어주세요.", "눈에띄게 헉헉거림", "코가 막힌 듯 쌕쌕댐", "아무 소리 없음", "코골이가 심함(드르렁거림)");
        this.addQuestionToDB(q8);
        Question q9 = new Question("고양이의 소변 색을 점검해주세요.", "노란색", "혈뇨", "맑은색", "모르겠음");
        this.addQuestionToDB(q9);
        Question q10 = new Question("고양이의 소변 횟수를 점검해주세요.", "하루에 한 번", "하루에 두 번", "하루에 세 번 이상 자주", "전혀 소변을 보지 않음");
        this.addQuestionToDB(q10);
        Question q11 = new Question("현재 고양이의 자세는 어떻습니까?", "구부정함", "평소와 다름없음", "하루종일 엎드려있음", "힘없이 누워있음");
        this.addQuestionToDB(q11);
        Question q12 = new Question("Which city is known as the city of canals ?", "Paris", "Venice", "London", "Venice");
        this.addQuestionToDB(q12);
        Question q13 = new Question("Australia was discovered by ?", "James Cook", "Columbus", "Magallan", "James Cook");
        this.addQuestionToDB(q13);
        Question q14 = new Question("The national flower of Britain is ?", "Lily", "Rose", "Lotus", "Rose");
        this.addQuestionToDB(q14);
        Question q15 = new Question("The red cross was founded by ?", "Gullivar Crossby", "Alexandra Maria Lara", "Jean Henri Durant", "Jean Henri Durant");
        this.addQuestionToDB(q15);
        Question q16 = new Question("Which place is known as the roof of the world ?", "Alphs", "Tibet", "Nepal", "Tibet");
        this.addQuestionToDB(q16);
        Question q17 = new Question("Who invented washing machine ?", "James King", "Alfred Vincor", "Christopher Marcossi", "James King");
        this.addQuestionToDB(q17);
        Question q18 = new Question("Who won the Football world Cup in 2014 ?", "Italy", "Argentina", "Germany", "Germany");
        this.addQuestionToDB(q18);
        Question q19 = new Question("Who won the Cricket World cup in 2011 ?", "Australia", "India", "England", "India");
        this.addQuestionToDB(q19);
        Question q20 = new Question("The number regarded as the lucky number in Italy is ?", "13", "7", "9", "13");
        this.addQuestionToDB(q20);

    }

    public void addQuestionToDB(Question q){
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQuestion());
        values.put(KEY_ANSWER,q.getAnswer());
        values.put(KEY_OPTA,q.getOptA());
        values.put(KEY_OPTB,q.getOptB());
        values.put(KEY_OPTC,q.getOptC());
        dbase.insert(DB_TABLE, null, values);
    }

    public List <Question> getAllQuestions(){
        List <Question> questionList = new ArrayList<Question>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+DB_TABLE;
        Cursor cursor = dbase.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));

                questionList.add(q);

            }while (cursor.moveToNext());
        }
        return questionList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }

    public int getRowCount(){
        return rowCount;
    }
}
