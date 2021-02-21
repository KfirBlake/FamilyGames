package com.blake.kids.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.blake.kids.model.QuestionInfo;

import java.util.ArrayList;

/**
 * Created by Kfir Blake on 19/02/2021.
 */
public class DBHelperLetterQuiz extends SQLiteOpenHelper
{
    private static final String TAG = "DBHelperLetterQuiz";

    public static final String DATABASE_NAME = "LetterQuiz.db";
    public static final String PICTURE_TABLE_NAME = "pictures";
    public static final String PICTURE_COLUMN_ID = "id";
    public static final String PICTURE_COLUMN_ASSETS = "assets";
    public static final String PICTURE_COLUMN_FILE_NAME = "fileName";
    public static final String PICTURE_COLUMN_ANSWER = "answer";
    public static final String PICTURE_COLUMN_QUESTION = "question";
    private static final String CREATE_TABLE = "CREATE TABLE " + PICTURE_TABLE_NAME + " ( "
            + PICTURE_COLUMN_ID + " STRING PRIMARY KEY, "
            + PICTURE_COLUMN_ASSETS + " VARCHAR(15), "
            + PICTURE_COLUMN_FILE_NAME + " VARCHAR(30), "
            + PICTURE_COLUMN_ANSWER + " VARCHAR(15), "
            + PICTURE_COLUMN_QUESTION + " VARCHAR(50)); ";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + PICTURE_TABLE_NAME;
    public DBHelperLetterQuiz(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public ArrayList<QuestionInfo> createAllQuestions()
    {
        ArrayList<QuestionInfo> allQuestionInfos = new ArrayList<>();
        allQuestionInfos.add(new QuestionInfo("pictures", "agala_16_5.jpg", "עגלה","מסיעים בה תינוקות וגם בובות"));
        allQuestionInfos.add(new QuestionInfo("pictures", "apple1_22_8.jpg", "תפוח","פרי טעים בצבע אדום או ירוק"));
        allQuestionInfos.add(new QuestionInfo("pictures", "bad_13_5.jpg", "מיטה","מקום שישנים בו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "bag_22_19.jpg", "תיק","אפשר לשים שם מחברות וספרים או בגדים ולשים על הגב"));
        allQuestionInfos.add(new QuestionInfo("pictures", "bamba_2_5.jpg", "במבה","חטיף צהוב עשוי מבוטנים שמותר גם לילדים קטנים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "bambok_2_19.jpg", "במבוק","עשוי מעץ, עגול, ואפשר להשתמש בו לגדר"));
        allQuestionInfos.add(new QuestionInfo("pictures", "batol_2_19.jpg", "בקבוק","אפשר לשים בתוכו מים או פטל"));
        allQuestionInfos.add(new QuestionInfo("pictures", "book_15_20.jpg", "ספר","קוראים בו כדי ללמוד או לפני השינה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "car_13_22.jpg", "מכונית","יש לו ארבע גלגלים ואפשר ליסוע איתו למקומות רחוקים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "carmack_13_19.jpg", "מק","הוא מסיע את ספידי ממקום למקום"));
        allQuestionInfos.add(new QuestionInfo("pictures", "carmater_13_20.jpg", "מטור","החבר הכי טוב של ספידי"));
        allQuestionInfos.add(new QuestionInfo("pictures", "carot_3_20.jpg", "גזר","ירק בצבע כתום, שארנבים אוהבים לאכול"));
        allQuestionInfos.add(new QuestionInfo("pictures", "caw_17_5.jpg", "פרה","החיה שנותנת לנו חלב"));
        allQuestionInfos.add(new QuestionInfo("pictures", "chair1_11_1.jpg", "כסא","יושבים עליו ליד השולחן"));
        allQuestionInfos.add(new QuestionInfo("pictures", "closet_1_25.jpg", "ארון","נמצא בחדר ושמים בתוכו בגדים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "cup_11_15.jpg", "כוס","שותים בעזרתו מים או פטל או תה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "devek_4_19.jpg", "דבק","בעזרתו מדביקים דפים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "door_4_22.jpg", "דלת","כדי להכנס לבית עוברים דרך ה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "elefent_17_12.jpg", "פיל","החיה הכי גדולה שחיה על האדמה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "fork_13_3.jpg", "מזלג","אוכלים בעזרתו ויש לו ארבע שינים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "frozenanna_1_5.jpg", "אנה","אחות של אלזה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "frozenelsa1_1_5.jpg", "אלזה","אחות של אנה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "frozenolaf_1_26.jpg", "אולף","חבר של אנה שעשוי משלג"));
        allQuestionInfos.add(new QuestionInfo("pictures", "galgal_3_12.jpg", "גלגל","יש אותו 4 פעמים למכונית ו 2 פעמים לאופניים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "gate_21_20.jpg", "שער","כדי להכנס למקום עוברים דרכו, ויש אותו בחומה של ירושלים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "hourse_15_15.jpg", "סוס","חיה גדולה שאפשר לרכוב עליה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "house_2_22.jpg", "בית","אנחנו גרים בתוך "));
        allQuestionInfos.add(new QuestionInfo("pictures", "jessie_3_10.jpg", "גסי","הכלב החמוד והיפה שלנו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "lemon1_12_25.jpg", "לימון","מוסיפים אותו לסלט והוא קצת חמוץ"));
        allQuestionInfos.add(new QuestionInfo("pictures", "maader_13_20.jpg", "מעדר","בעזרתו עובדים בגינה, ואפשר לחפור איתו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "maglesha_13_5.jpg", "מגלשה","בגן שעשועים אפשר להחליק בתוכו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "mangal_13_12.jpg", "מנגל","עליו עושים על האש"));
        allQuestionInfos.add(new QuestionInfo("pictures", "mashpch_13_23.jpg", "משפך","כדי להשקות את הפרחים משתמשים ב"));
        allQuestionInfos.add(new QuestionInfo("pictures", "meritsa_13_5.jpg", "מריצה","בעזרתו אפשר לסחוב דברים כבדים ממקום למקום, יש לו שתי גלגלים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "mibreshet_13_19.jpg", "מסרק","כדי לסדר את השיער, משתמשים ב"));
        allQuestionInfos.add(new QuestionInfo("pictures", "miror_13_5.jpg", "מראה","כדי לראות את עצמנו משתמשים ב"));
        allQuestionInfos.add(new QuestionInfo("pictures", "moanamoana1_13_5.jpg", "מואנה","הילדה ששטה על הים כדי להציל את טה פיטי"));
        allQuestionInfos.add(new QuestionInfo("pictures", "monkey_19_26.jpg", "קוף","חיה שאוהבת לטפס ולקפוץ על עצים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "naknik_14_19.jpg", "נקניק","אבא אוכל כל הזמן סנדוויץ עם"));
        allQuestionInfos.add(new QuestionInfo("pictures", "orange1_22_7.jpg", "תפוז","פרי אדר כתום עגול שיש בו פלחים "));
        allQuestionInfos.add(new QuestionInfo("pictures", "pah_17_8.jpg", "פח","זורקים לתוכו זבל"));
        allQuestionInfos.add(new QuestionInfo("pictures", "pazel_17_12.jpg", "פזל","משחק שמרכיבים בו הרבה חלקים ויוצא תמונה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "pelephon_17_25.jpg", "פלפון","אפשר לדבר איתו עם אנשים, ואפשר לשחק איתו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "pen_16_9.jpg", "עט","כותבים בערתו על דף, ואי אפשר למחוק אותו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "pencil_16_25.jpg", "עפרון","כותבים איתו על דף, ואפשר להשתמש במחק כדי למחוק אותו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "petel_17_12.jpg", "פטל","אפשר להוסיף אותו למים, שותים אותו ויש לו טעם מתוק"));
        allQuestionInfos.add(new QuestionInfo("pictures", "picel_13_25.jpg", "מלפפון","פרי ירוק וטעים ששמים בסלט"));
        allQuestionInfos.add(new QuestionInfo("pictures", "pillo_11_22.jpg", "כרית","בלילה שמים עליו את הראש כשישנים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "pilpel1_17_12.jpg", "פלפל","שמים אותו בסלט ויש אותו בצבעים שונים, אדום ירוק כתום צהוב"));
        allQuestionInfos.add(new QuestionInfo("pictures", "pita_17_5.jpg", "פיתה","אוכלים אותו, אבל אסור בפסח, בצורה עגולה, ואפשר לעשות ממנו סנדוויץ "));
        allQuestionInfos.add(new QuestionInfo("pictures", "plate1_18_22.jpg", "צלחת","שמים את האוכל עליו, והוא בצורה עגולה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "poff_17_26.jpg", "פוף","אפשר להשען עליו, אפשר לקפוץ עליו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "refrigerator_13_20.jpg", "מקרר","מקום קר ששמים בו אוכל"));
        allQuestionInfos.add(new QuestionInfo("pictures", "remote_21_9.jpg", "שלט","בעזרתו אפשר להדליק את הטלוויזיה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "roop_8_12.jpg", "חבל","אפשר לקשור איתו, אפשר לקפוץ איתו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "sapapa_15_5.jpg", "ספפה","שוכבים עליו, נחים עליו, ספה קטנה לילדים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "sevivon1_15_25.jpg", "סביבון","מסובבים אותו, ומשחקים איתו בחג בחנוכה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "shoes_14_24.jpg", "נעל","לובשים אותו על הרגל כדי שיהיה אפשר ללכת ולא יהיה כואב ברגלים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "socks_3_24.jpg", "גרב","גורבים אותו על הרגל מתחת לנעל"));
        allQuestionInfos.add(new QuestionInfo("pictures", "spidi_15_10.jpg", "ספידי","המכונית הכי מהירה בטלוויזיה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "spoon_11_22.jpg", "כפית","אפשר לאכול איתו, ובעזרתו שמים סוכר בתוך הכוס"));
        allQuestionInfos.add(new QuestionInfo("pictures", "swing_14_5.jpg", "נדנדה","בגן שעשועים אפשר להתנדנד איתו"));
        allQuestionInfos.add(new QuestionInfo("pictures", "table_21_25.jpg", "שולחן","כולם יושבים לידו כשאוכלים ארוחה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "tanor_22_20.jpg", "תנור","מקום חם מאוד שבתוכו מכינים אוכל ועוגות"));
        allQuestionInfos.add(new QuestionInfo("pictures", "tedi_4_10.jpg", "דובי","בובה נעימה בצורה של חיה"));
        allQuestionInfos.add(new QuestionInfo("pictures", "tigris_9_15.jpg", "טיגריס","חיה טורפת שיש לה פסים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "tiras_22_15.jpg", "תירס","פרי שאוכלים אותו בצבע צהוב ויש לו המון גרעינים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "tree_16_27.jpg", "עץ","גודלים עליו פירות, אפשר לטפס עליו, ויש עליו המון עלים"));
        allQuestionInfos.add(new QuestionInfo("pictures", "wallet_1_19.jpg", "ארנק","שומרים בתוכו כסף"));
        allQuestionInfos.add(new QuestionInfo("pictures", "window_8_25.jpg", "חלון","נמצא בתוך החדר, ודרכו אפשר לראות מה יש בחוץ"));


        return allQuestionInfos;
    }

    public void addAllQuestions()
    {
        deleteAllPictureTable();
        ArrayList<QuestionInfo> allQuestions = createAllQuestions();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (QuestionInfo question : allQuestions) {
                values.put(PICTURE_COLUMN_ASSETS, question.getAssetsName());
                values.put(PICTURE_COLUMN_FILE_NAME, question.getFileName());
                values.put(PICTURE_COLUMN_ANSWER, question.getAnswer());
                values.put(PICTURE_COLUMN_QUESTION, question.getQuestion());
                db.insert(PICTURE_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public ArrayList<QuestionInfo> getAllQuestions()
    {
        ArrayList<QuestionInfo> array = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + PICTURE_TABLE_NAME, null);
        //cursor.moveToFirst();

        while (cursor.moveToNext())
        {
            QuestionInfo articleInfo = new QuestionInfo();
            articleInfo.setId(cursor.getString(0));
            articleInfo.setAssetsName(cursor.getString(1));
            articleInfo.setFileName(cursor.getString(2));
            articleInfo.setAnswer(cursor.getString(3));
            articleInfo.setQuestion(cursor.getString(4));
            array.add(articleInfo);
        }
        return array;
    }

    public void deleteAllPictureTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PICTURE_TABLE_NAME, null, null);
    }

}
