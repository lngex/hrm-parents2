package cn.lngex.search.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "lngex" , type = "User")
public class UserDoc {

    @Id
    private Long id;

    /**
     * FieldType.Text 可以分词
     * analyzer 指定分词器
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Keyword)
    private Integer age;

    public Long getId() {
        return id;
    }

    public UserDoc setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDoc setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserDoc setAge(Integer age) {
        this.age = age;
        return this;
    }
}
