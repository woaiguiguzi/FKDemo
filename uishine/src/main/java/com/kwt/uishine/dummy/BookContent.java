package com.kwt.uishine.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class BookContent {

    /**
     * 使用List集合记录系统所包含的Book对象
     */
    public static List<Book> ITEMS = new ArrayList<Book>();

    /**
     * 使用Map集合记录系统所包含的Book对象
     */
    public static Map<Integer, Book> ITEM_MAP = new HashMap<Integer, Book>();
    // 使用静态初始化代码，将Book对象添加到List集合、Map集合中
    static {
        // 使用静态初始化代码，将Book对象添加到List集合、Map集合中
        addItem(new Book(1, "论语"
                , "《论语》是儒家学派的经典著作之一，由孔子的弟子及其再传弟子编撰而成"));
        addItem(new Book(2, "老子"
                , "《老子》，又称《道德真经》《道德经》《五千言》《老子五千文》， "
                + "是中国古代先秦诸子分家前的一部著作，为其时诸子所共仰"));
        addItem(new Book(3, "鬼谷子"
                , "《鬼谷子》，又名《捭阖策》。据传是由鬼谷先生后学者根据先生言论整理而成,"
                + "该书侧重于权谋策略及言谈辩论技巧"));
        addItem(new Book(4, "周易"
                , "《周易》是一部中国古哲学书籍，是建立在阴阳二元论基础上对事物运行规律"
                + "加以论证和描述的书籍"));
    }

    private static void addItem(Book book) {
        ITEMS.add(book);
        ITEM_MAP.put(book.id, book);
    }

    /**
     * 定义一个内部类，作为系统的业务对象.
     */
    public static class Book {
        public Integer id;
        public String title;
        public String desc;

        public Book(Integer id, String title, String desc)
        {
            this.id = id;
            this.title = title;
            this.desc = desc;
        }
        @Override
        public String toString()
        {
            return title;
        }
    }
}
