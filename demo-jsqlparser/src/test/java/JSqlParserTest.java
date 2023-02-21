import com.lfx.demo.mybatis.plugin.SelectVisitorImpl;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-01-30 15:19:04
 */
@SpringBootTest
public class JSqlParserTest {


    @Test
    public void testFrom() {
        // select column1, column2 from table1 where column3=333 =>
        // select a.column1, a.column2 from table1 a inner join sys_user_org scope_suo on a.create_id = scope_suo.id and scope_suo.id in (123,456) and a.column3=333
        // select column1, column2 from table1 where column3=333 =>
        // select column1, column2 from table1 where create_id = 123 and column3=333
    }

    @Test
    public void testJoin() {
        // select column1, column2 from table1 a inner join table2 b on a.column99=b.column99 and a.column3=333 =>
        // select column1, column2 from table1 a inner join table2 b on a.column99=b.column99 inner join sys_user_org scope_suo on a.create_id = scope_suo.id and a.column3=333

        // select column1, column2 from table1 a left join table2 b on a.column99=b.column99 and b.column98=888 where a.column3=333 =>
        // select column1, column2 from table1 a inner join sys_user_org scope_suo on a.create_id = scope_suo.id left join table2 b on a.column99=b.column99 and b.column98=888 where a.column3=333

    }

    @Test
    public void testOther() {
        // 其余 => 子查询过滤
        // select column1, column2 from table1 where column3=333 => select column1, column2 from table1 where column3=333 and create_id in (select user_id from sys_user_org where org_id in (123,456))
    }


    public static final String SQL_FROM = "select column1, column2 from table1 where column3=333";
    public static final String SQL_JOIN = "select column1, column2 from table1 a inner join table2 b on a.column99=b.column99 and a.column3=333";

    @Test
    public void testSelect() throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse(SQL_FROM);
        PlainSelect selectBody = (PlainSelect) select.getSelectBody();
        System.out.println(selectBody.getSelectItems());
        System.out.println(selectBody.getWhere());


        Select select2 = (Select) CCJSqlParserUtil.parse(SQL_JOIN);
        PlainSelect selectBody2 = (PlainSelect) select2.getSelectBody();
        System.out.println(selectBody2.getSelectItems());
        System.out.println(selectBody2.getJoins());
        List<Join> joinList = selectBody2.getJoins();
        for (Join join : joinList) {
            System.out.println(join.isInner());
            System.out.println(join);
        }
    }
}
