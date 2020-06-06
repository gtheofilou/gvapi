package gr.gt.gvapi.sql;

public class Statistics {

    /**
     * 
     */
    public static final String LABELS_BY_COUNT_PER_USER = ""//
            + "select u.name, g.description, count(g.description) as cnt "//
            + "from user u "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "and u.name = :user " //
            + "group by g.description "//
            + "order by count(g.description) DESC "//
            + "LIMIT :limit";
    /**
     * 
     */
    public static final String LABELS_BY_COUNT_ALL_POLITICS_USERS = ""//
            + "select 'allpoliticsusers', g.description, count(g.description) as cnt "//
            + "from user u "//
            + "join TAGS_USER_ASSOC tua on tua.user_id = u.id "//
            + "join TAGS t on tua.tag_id = t.id "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "and t.name = 'Politics' "//
            + "group by g.description "//
            + "order by count(g.description) DESC "//
            + "LIMIT :limit";
    /**
     * 
     */
    public static final String LABELS_BY_COUNT_ALL_USERS = ""//
            + "select 'allusers', g.description, count(g.description) as cnt "//
            + "from user u "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "group by g.description "//
            + "order by count(g.description) DESC "//
            + "LIMIT :limit";


    /**
     * 
     */
    public static final String LABELS_BY_AVG_PER_USER = ""//
            + "select u.name, g.description, avg(g.score) as avg_score, "
            + "sum(g.score) as sum_score, count(g.description) as cnt, sum(g.score) * LOG10(1+count(g.description)) AS metric "//
            + "from user u "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "and u.name = :user " //
            + "group by g.description "//
            + "order by metric DESC "//
            + "LIMIT :limit";
    /**
     * 
     */
    public static final String LABELS_BY_AVG_ALL_POLITICS_USERS = ""//
            + "select 'allpoliticsusers', g.description, avg(g.score) as avg_score, "
            + "sum(g.score) as sum_score, count(g.description) as cnt, sum(g.score) * LOG10(1+count(g.description)) AS metric "//
            + "from user u "//
            + "join TAGS_USER_ASSOC tua on tua.user_id = u.id "//
            + "join TAGS t on tua.tag_id = t.id "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "and t.name = 'Politics' "//
            + "group by g.description "//
            + "order by metric DESC "//
            + "LIMIT :limit";
    /**
     * 
     */
    public static final String LABELS_BY_AVG_ALL_USERS = ""//
            + "select 'allusers', g.description, avg(g.score) as avg_score, "
            + "sum(g.score) as sum_score, count(g.description) as cnt, sum(g.score) * LOG10(1+count(g.description)) AS metric "//
            + "from user u "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "group by g.description "//
            + "order by metric DESC "//
            + "LIMIT :limit";


    /**
     * 
     */
    public static final String OCR_TF2 = ""//
            + "WITH IDF AS ( "//
            + "SELECT word, count(1) as count, sum(RTF) as sRTF, sum(TWD) as sTWD "//
            + "FROM NLP nlp "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE u.name = :user "//
            + "GROUP BY word "//
            + ")"//
            + "SELECT distinct nlp.word, idf.count, idf.srtf, idf.stwd, cast(idf.srtf as float)/cast(idf.stwd as float) as tf,u.name "//
            + "FROM NLP nlp "//
            + "JOIN IDF idf ON nlp.word = idf.word "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE u.name = :user "//
            + "AND nlp.word NOT IN (SELECT word FROM STOP_WORD) "//
            + "AND LENGTH(nlp.word) > 1 "//
            + "ORDER by tf DESC, word "//
            + "LIMIT :limit";
    /**
     * 
     */
    public static final String OCR_TF2_ALL_POLITICS_USERS = ""//
            + "WITH IDF AS ( "//
            + "SELECT word, count(1) as count, sum(RTF) as sRTF, sum(TWD) as sTWD "//
            + "FROM NLP nlp "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "join TAGS_USER_ASSOC tua on tua.user_id = u.id "//
            + "join TAGS t on tua.tag_id = t.id "//
            + "WHERE t.name = 'Politics' "//
            + "GROUP BY word "//
            + ")"//
            + "SELECT distinct nlp.word, idf.count, idf.srtf, idf.stwd, cast(idf.srtf as float)/cast(idf.stwd as float) as tf,'allpoliticsusers' "//
            + "FROM NLP nlp "//
            + "JOIN IDF idf ON nlp.word = idf.word "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "join TAGS_USER_ASSOC tua on tua.user_id = u.id "//
            + "join TAGS t on tua.tag_id = t.id "//
            + "WHERE t.name = 'Politics' "//
            + "AND nlp.word NOT IN (SELECT word FROM STOP_WORD) "//
            + "AND LENGTH(nlp.word) > 1 "//
            + "ORDER by tf DESC, word "//
            + "LIMIT :limit";
    /**
     * 
     */
    public static final String OCR_TF2_ALL_USERS = ""//
            + "WITH IDF AS ( "//
            + "SELECT word, count(1) as count, sum(RTF) as sRTF, sum(TWD) as sTWD "//
            + "FROM NLP nlp "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "GROUP BY word "//
            + ")"//
            + "SELECT distinct nlp.word, idf.count, idf.srtf, idf.stwd, cast(idf.srtf as float)/cast(idf.stwd as float) as tf,'allusers' "//
            + "FROM NLP nlp "//
            + "JOIN IDF idf ON nlp.word = idf.word "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE nlp.word NOT IN (SELECT word FROM STOP_WORD) "//
            + "AND LENGTH(nlp.word) > 1 "//
            + "ORDER by tf DESC, word "//
            + "LIMIT :limit";


    public static final String OCR_TFIDF2 = ""//
            + "WITH IDF AS ( "//
            + "SELECT word, count(1) as count "//
            + "FROM NLP "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE u.name = :user "//
            + "GROUP BY word "//
            + "),"//
            + "TD AS ( "//
            + "SELECT count(distinct nlp.file_id) as count "//
            + "FROM NLP "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE u.name = :user "//
            + ")"//
            + "SELECT nlp.*, idf.count, u.name, LOG(docs.count/idf.count)* nlp.TF as ord "//
            + "FROM NLP nlp, TD docs "//
            + "JOIN IDF idf ON nlp.word = idf.word "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE u.name = :user "//
            + "AND nlp.word NOT IN (SELECT word FROM STOP_WORD) "//
            + "AND LENGTH(nlp.word) > 1 "//
            + "ORDER by ord DESC "//
            + "LIMIT :limit";

    public static final String OCR_TFIDF2_ALL_POLITICS_USERS = "" + //
            "WITH IDF AS ( " + //
            "    SELECT\n" + //
            "        word,\n" + //
            "        COUNT(1) AS COUNT\n" + //
            "    FROM\n" + //
            "        TAGS t\n" + //
            "       JOIN TAGS_USER_ASSOC tua ON tua.tag_id = t.id AND t.name = 'Politics'\n" + //
            "       JOIN USER u ON tua.user_id = u.id\n" + //
            "       JOIN FILE_USER_ASSOC fua on u.id = fua.user_id\n" + //
            "       JOIN NLP n ON fua.file_id = n.file_id\n" + //
            "    GROUP BY\n" + //
            "        word\n" + //
            "),\n" + //
            "TD AS (\n" + //
            "    SELECT\n" + //
            "        COUNT(DISTINCT n.file_id) AS COUNT\n" + //
            "    FROM\n" + //
            "       TAGS t\n" + //
            "       JOIN TAGS_USER_ASSOC tua ON tua.tag_id = t.id AND t.name = 'Politics'\n" + //
            "       JOIN USER u ON tua.user_id = u.id\n" + //
            "       JOIN FILE_USER_ASSOC fua on u.id = fua.user_id\n" + //
            "       JOIN NLP n ON fua.file_id = n.file_id\n" + //
            ")\n" + //
            "SELECT\n" + //
            "    n.*,\n" + //
            "    idf.count,\n" + //
            "    u.name,\n" + //
            "    LOG(docs.count / idf.count) * n.TF AS ord\n" + //
            "FROM\n" + //
            "    TD docs, \n" + //
            "    TAGS t\n" + //
            "       JOIN TAGS_USER_ASSOC tua ON tua.tag_id = t.id AND t.name = 'Politics'\n" + //
            "       JOIN USER u ON tua.user_id = u.id\n" + //
            "       JOIN FILE_USER_ASSOC fua on u.id = fua.user_id\n" + //
            "       JOIN NLP n ON fua.file_id = n.file_id\n" + //
            "       JOIN IDF idf ON n.word = idf.word\n" + //
            "WHERE nlp.word NOT IN (SELECT word FROM STOP_WORD) " + //
            "AND LENGTH(nlp.word) > 1 " + //
            "ORDER BY\n" + //
            "    ord DESC\n" + //
            "LIMIT :limit";

    public static final String OCR_TFIDF2_ALL_USERS = ""//
            + "WITH " //
            + "TD AS ( "//
            + "SELECT count(distinct nlp.file_id) as count "//
            + "FROM NLP "//
            + "),"//
            + "IDF AS ( "//
            + "SELECT word, count(1) as count "//
            + "FROM NLP "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "GROUP BY word "//
            + ")"//
            + "SELECT nlp.*, idf.count, u.name, LOG(docs.count/idf.count)* nlp.TF as ord "//
            + "FROM NLP nlp, TD docs "//
            + "JOIN IDF idf ON nlp.word = idf.word "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE nlp.word NOT IN (SELECT word FROM STOP_WORD) "//
            + "AND LENGTH(nlp.word) > 1 "//
            + "ORDER by ord DESC "//
            + "LIMIT :limit";

    // GERASIMOS
    public static final String OCR_GERASIMOS = ""//
            + "WITH IDF AS ( "//
            + "SELECT word, count(1) as count "//
            + "FROM NLP "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE u.name = :user "//
            + "GROUP BY word "//
            + ")"//
            + "SELECT distinct nlp.word, idf.count, u.name, idf.count * LOG10(1+idf.count) AS metric "//
            + "FROM NLP nlp "//
            + "JOIN IDF idf ON nlp.word = idf.word "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE u.name = :user "//
            + "AND nlp.word NOT IN (SELECT word FROM STOP_WORD) "//
            + "AND LENGTH(nlp.word) > 1 "//
            + "ORDER by metric DESC "//
            + "LIMIT :limit";

    // GERASIMOS
    public static final String OCR_GERASIMOS_ALL_POLITICS_USERS = ""//
            + "WITH IDF AS ( "//
            + "SELECT word, count(1) as count "//
            + "FROM NLP "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "join TAGS_USER_ASSOC tua on tua.user_id = u.id "//
            + "join TAGS t on tua.tag_id = t.id "//
            + "WHERE t.name = 'Politics' "//
            + "GROUP BY word "//
            + ")"//
            + "SELECT distinct nlp.word, idf.count, 'allpoliticsusers', idf.count * LOG10(1+idf.count) AS metric "//
            + "FROM NLP nlp "//
            + "JOIN IDF idf ON nlp.word = idf.word "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "join TAGS_USER_ASSOC tua on tua.user_id = u.id "//
            + "join TAGS t on tua.tag_id = t.id "//
            + "WHERE t.name = 'Politics' "//
            + "AND nlp.word NOT IN (SELECT word FROM STOP_WORD) "//
            + "AND LENGTH(nlp.word) > 1 "//
            + "ORDER by metric DESC "//
            + "LIMIT :limit";

    // GERASIMOS
    public static final String OCR_GERASIMOS_ALL_USERS = ""//
            + "WITH IDF AS ( "//
            + "SELECT word, count(1) as count "//
            + "FROM NLP "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "GROUP BY word "//
            + ")"//
            + "SELECT distinct nlp.word, idf.count, 'allpoliticsusers', idf.count * LOG10(1+idf.count) AS metric "//
            + "FROM NLP nlp "//
            + "JOIN IDF idf ON nlp.word = idf.word "//
            + "JOIN FILE_USER_ASSOC fua on fua.file_id= nlp.file_id "//
            + "JOIN USER u on u.id=fua.user_id "//
            + "WHERE nlp.word NOT IN (SELECT word FROM STOP_WORD) "//
            + "AND LENGTH(nlp.word) > 1 "//
            + "ORDER by metric DESC "//
            + "LIMIT :limit";

}
