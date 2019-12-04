package gr.gt.gvapi.sql;

public class Statistics {

    public static final String LABELS_BY_COUNT = ""//
            + "WITH tag_groups AS ( "//
            + "select u.name, g.description, count(g.description) as cnt "//
            + "from user u "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "group by g.description, u.name "//
            + "order by u.name, count(g.description) DESC "//
            + ")"//
            + "select * "//
            + "from tag_groups tg_out "//
            + "where tg_out.description in ( "//
            + "select description "//
            + "from tag_groups tg_in "//
            + "where tg_out.name = tg_in.name "//
            + "ORDER BY tg_in.cnt DESC "//
            + "LIMIT 10 "//
            + ")";

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

    public static final String LABELS_AVG_SORE = ""//
            + "WITH tag_groups AS ( "//
            + "select u.name, g.description, avg(g.score) as avg_score "//
            + "from user u "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "group by g.description, u.name "//
            + "order by u.name, avg(g.score) DESC "//
            + ")"//
            + "select * "//
            + "from tag_groups tg_out "//
            + "where tg_out.description in ( "//
            + "select description "//
            + "from tag_groups tg_in "//
            + "where tg_out.name = tg_in.name "//
            + "ORDER BY tg_in.avg_score DESC "//
            + "LIMIT 10 "//
            + ")";

    public static final String LABELS_BY_AVG_PER_USER = ""//
            + "select u.name, g.description, avg(g.score) as avg_score, "
            + "sum(g.score) as sum_score, count(g.description) as cnt "//
            + "from user u "//
            + "join FILE_USER_ASSOC fua on fua.user_id = u.id "//
            + "join GOOGLE_RESPONSE g on g.file_id = fua.file_id "//
            + "where g.type = 0 "//
            + "and u.name = :user " //
            + "group by g.description "//
            + "order by avg_score DESC "//
            + "LIMIT :limit";

}
