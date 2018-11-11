package com.eve.bookmarks;

import com.alibaba.fastjson.JSON;
import com.eve.bookmarks.dao.UserMapper;
import com.eve.bookmarks.entitys.po.User;
import com.eve.bookmarks.service.BookMarkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkTest {

    @Autowired
    private BookMarkService bookMarkService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        String bookMarks = "[{\"children\":[{\"children\":[{\"index\":0,\"id\":\"5\",\"title\":\"百度一下，你就知道\",\"dateAdded\":1538752191490,\"parentId\":\"1\",\"url\":\"https://www.baidu.com/\"},{\"children\":[{\"index\":0,\"id\":\"11\",\"title\":\"【图片】安利一个新出的集换卡牌游戏：Faeria【steam吧】_百度贴吧\",\"dateAdded\":1538813021127,\"parentId\":\"6\",\"url\":\"https://tieba.baidu.com/p/5016597374?red_tag=0053745738\"}],\"index\":1,\"id\":\"6\",\"title\":\"hello\",\"dateAdded\":1538752200194,\"dateGroupModified\":1540085883614,\"parentId\":\"1\"},{\"children\":[{\"index\":0,\"id\":\"9\",\"title\":\"1.3　Manifest文件格式-图灵社区\",\"dateAdded\":1538752447161,\"parentId\":\"7\",\"url\":\"http://www.ituring.com.cn/book/miniarticle/60136\"},{\"index\":1,\"id\":\"18\",\"title\":\"chrome插件开发之调试 - QustDong的专栏 - CSDN博客\",\"dateAdded\":1540135692734,\"parentId\":\"7\",\"url\":\"https://blog.csdn.net/qustdong/article/details/46046553\"},{\"index\":2,\"id\":\"20\",\"title\":\"Bootstrap4 教程 | 菜鸟教程\",\"dateAdded\":1541236417902,\"parentId\":\"7\",\"url\":\"http://www.runoob.com/bootstrap4/bootstrap4-tutorial.html\"},{\"index\":3,\"id\":\"22\",\"title\":\"语法 - art-template\",\"dateAdded\":1541300354265,\"parentId\":\"7\",\"url\":\"https://aui.github.io/art-template/zh-cn/docs/syntax.html\"},{\"index\":4,\"id\":\"24\",\"title\":\"【图片】杀戮尖塔故障机器人——抓牌思路以及混牌打法_杀戮尖塔吧_百度贴吧\",\"dateAdded\":1541834541940,\"parentId\":\"7\",\"url\":\"https://tieba.baidu.com/p/5775853315?red_tag=1316653211\"}],\"index\":2,\"id\":\"7\",\"title\":\"前端\",\"dateAdded\":1538752445351,\"dateGroupModified\":1541834541940,\"parentId\":\"1\"},{\"index\":3,\"id\":\"13\",\"title\":\"开源中国\",\"dateAdded\":1540085883614,\"parentId\":\"1\",\"url\":\"https://my.oschina.net/u/3779759/\"},{\"children\":[{\"index\":0,\"id\":\"15\",\"title\":\"Pendo 笔记：写任何想法和计划的「时间轴」清奇笔记本 - 简书\",\"dateAdded\":1540086241358,\"parentId\":\"14\",\"url\":\"https://www.jianshu.com/p/e2068ff4aeca\"},{\"index\":1,\"id\":\"16\",\"title\":\"在线 Markdown 编译器\",\"dateAdded\":1540087529138,\"parentId\":\"14\",\"url\":\"http://tool.oschina.net/markdown\"}],\"index\":4,\"id\":\"14\",\"title\":\"管理工具\",\"dateAdded\":1540086239243,\"dateGroupModified\":1540135692734,\"parentId\":\"1\"}],\"index\":0,\"id\":\"1\",\"title\":\"书签栏\",\"dateAdded\":1531816333296,\"dateGroupModified\":1540086241358,\"parentId\":\"0\"},{\"children\":[],\"index\":1,\"id\":\"2\",\"title\":\"其他书签\",\"dateAdded\":1531816333296,\"parentId\":\"0\"}],\"id\":\"0\",\"title\":\"\",\"dateAdded\":1541853672388}]";
        User user = userMapper.selectByPrimaryKey(1L);
        Map<String, Object> re = bookMarkService.syncBookMark(bookMarks, 0L, user);
        System.out.printf("result:"+ JSON.toJSONString(re));
    }
}
