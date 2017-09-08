package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.SearchTerms;
import cn.qhjys.mall.entity.SearchTermsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SearchTermsMapper {
    int countByExample(SearchTermsExample example);

    int deleteByExample(SearchTermsExample example);

    int insert(SearchTerms record);

    int insertSelective(SearchTerms record);

    List<SearchTerms> selectByExample(SearchTermsExample example);

    int updateByExampleSelective(@Param("record") SearchTerms record, @Param("example") SearchTermsExample example);

    int updateByExample(@Param("record") SearchTerms record, @Param("example") SearchTermsExample example);
}