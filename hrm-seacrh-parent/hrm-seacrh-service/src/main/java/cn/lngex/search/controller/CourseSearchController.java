package cn.lngex.search.controller;


import cn.lngex.course.dto.CoursePageQuery;
import cn.lngex.search.config.LngexRepository;
import cn.lngex.search.doc.CourseDoc;
import cn.lngex.search.doc.vo.AggVo;
import cn.lngex.search.mapper.HighlightResultMapper;
import cn.lngex.utils.AjaxResult;
import cn.lngex.utils.PageList;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/coursesearch")
public class CourseSearchController {

    @Autowired
    private LngexRepository lngexRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private HighlightResultMapper highlightResultMapper;

    /**
     * 保存
     * @param courseDoc
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('courseDoc:add')")
    public AjaxResult save(@RequestBody CourseDoc courseDoc){
        lngexRepository.save(courseDoc);
        return AjaxResult.me();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/del")
    @PreAuthorize("hasAnyAuthority('courseDoc:delete')")
    public AjaxResult del(@RequestBody Long id){
        lngexRepository.deleteById(id);
        return AjaxResult.me();
    }

    /**
     * 面包屑
     * @return
     */
    @PostMapping("/queryCourses")
    public AjaxResult queryCourses(@RequestBody CoursePageQuery coursePageQuery){
        System.out.println(coursePageQuery);
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        HighlightBuilder.Field hfield = new HighlightBuilder.Field("name").preTags("<font style='color:red'><b>").postTags("</b></font>");
        builder.withHighlightFields(hfield);  // 名字高亮
        /* 设置分页参数 */
        builder.withPageable(PageRequest.of(coursePageQuery.getPage()-1,coursePageQuery.getRows()));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        /* 添加模糊查询 */
        String keyword = coursePageQuery.getKeyword();
        if (StringUtils.isNotBlank(keyword)) {
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"name","courseTypeName"));
        }

        String typeName = coursePageQuery.getCourseTypeName();
        if(StringUtils.isNotBlank(typeName)){
            boolQueryBuilder.must(QueryBuilders.termQuery("courseTypeName", typeName));
        }
        String tenantName = coursePageQuery.getTenantName();
        if(StringUtils.isNotBlank(tenantName)){
            boolQueryBuilder.must(QueryBuilders.termQuery("tenantName", tenantName));
        }



        //以价格过滤  小于等于最大价格
        Float priceMax = coursePageQuery.getPriceMax();
        if(priceMax!=null){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(priceMax));
        }
        //大于等于最小价格
        Float priceMin = coursePageQuery.getPriceMin();
        if(priceMin!=null){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(priceMin));
        }
        builder.withQuery(boolQueryBuilder);
        /* 排序 */
        /* 获取类型 */
        String field = coursePageQuery.getSortField();
        /* 判断类型是否为空，默认使用viewCount */
        field = StringUtils.isNotBlank(field)?field:"viewCount";
        FieldSortBuilder sortBuilder = new FieldSortBuilder(field);
        System.out.println(field);
        /* 获取排序类型 */
        String type = coursePageQuery.getSortType();
        /* 设置升序降序 */
        SortOrder order = null;
        if("asc".equals(type)){
            order = SortOrder.ASC;
        }else {
            order = SortOrder.DESC;
        }
        builder.withSort(sortBuilder.order(order));

        /* 聚合查询 */
        builder.addAggregation(AggregationBuilders.terms("tenantNameAgg").field("tenantName").order(Terms.Order.count(false)).size(20));
        builder.addAggregation(AggregationBuilders.terms("courseTypeAgg").field("courseTypeName").order(Terms.Order.count(false)).size(20));
        AggregatedPage<CourseDoc> docs = elasticsearchTemplate.queryForPage(builder.build(), CourseDoc.class, highlightResultMapper);

        /* 获取机构的聚合查询结果 */
        //获取聚合查询结果
        StringTerms tenantNameAgg = (StringTerms) docs.getAggregation("tenantNameAgg");
        //创建list集合封装机构名称聚合查询
        ArrayList<AggVo> tenantAggs = new ArrayList<>();
        List<StringTerms.Bucket> tenantList = tenantNameAgg.getBuckets();
        tenantList.forEach(bucket -> {
            AggVo aggVo = new AggVo();
            aggVo.setCount(bucket.getDocCount());
            aggVo.setName(bucket.getKeyAsString());
            tenantAggs.add(aggVo);
        });
        //获取聚合查询结果
        StringTerms typeNameAgg = (StringTerms) docs.getAggregation("courseTypeAgg");
        //创建list集合封装机构名称聚合查询
        ArrayList<AggVo> typeAggs = new ArrayList<>();
        List<StringTerms.Bucket> typeList = typeNameAgg.getBuckets();
        typeList.forEach(bucket -> {
            AggVo aggVo = new AggVo();
            aggVo.setCount(bucket.getDocCount());
            aggVo.setName(bucket.getKeyAsString());
            typeAggs.add(aggVo);
        });


        /* 启动查询 */
        //Page<CourseDoc> search = lngexRepository.search(builder.build());
        System.out.println(docs);
        PageList<CourseDoc> objectPageList = new PageList<>();
        objectPageList.setRows(docs.getContent()).setTotal(docs.getTotalElements());
        System.out.println(objectPageList);

        HashMap<String, ArrayList<AggVo>> map = new HashMap<>();
        map.put("tenantAggs", tenantAggs);
        map.put("typeAggs", typeAggs);

        return AjaxResult.me().setResultObj(objectPageList.setMap(map));
    }
}
