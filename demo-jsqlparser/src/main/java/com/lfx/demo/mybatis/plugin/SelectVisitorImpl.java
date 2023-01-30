package com.lfx.demo.mybatis.plugin;

import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.values.ValuesStatement;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-01-30 15:32:52
 */
public class SelectVisitorImpl implements SelectVisitor {
    @Override
    public void visit(PlainSelect plainSelect) {

        System.out.println("plainSelect" + plainSelect.getSelectItems());
    }

    @Override
    public void visit(SetOperationList setOperationList) {
        System.out.println("setOperationList" + setOperationList.getOperations());
    }

    @Override
    public void visit(WithItem withItem) {
        System.out.println("withItem" + withItem.getWithItemList());
    }

    @Override
    public void visit(ValuesStatement valuesStatement) {
        System.out.println("valuesStatement" + valuesStatement.getExpressions());
    }
}
