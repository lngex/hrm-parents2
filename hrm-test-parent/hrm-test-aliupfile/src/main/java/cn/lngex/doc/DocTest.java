package cn.lngex.doc;

import cn.lngex.TestApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TestApp.class)
@RunWith(SpringRunner.class)
public class DocTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    
    @Test
    public void test1() {
        System.out.println("=======================");
        //创建索引
        elasticsearchTemplate.createIndex(CourseDoc.class);
        //做文档映射
        elasticsearchTemplate.putMapping(CourseDoc.class);
        System.out.println("创建成功");
    }


    @Test
    public void test2() {
        System.out.println(elasticsearchTemplate);
    }
}
