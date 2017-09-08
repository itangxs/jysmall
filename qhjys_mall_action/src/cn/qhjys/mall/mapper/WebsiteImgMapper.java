package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.WebsiteImg;
import cn.qhjys.mall.entity.WebsiteImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebsiteImgMapper {
    int countByExample(WebsiteImgExample example);

    int deleteByExample(WebsiteImgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WebsiteImg record);

    int insertSelective(WebsiteImg record);

    List<WebsiteImg> selectByExample(WebsiteImgExample example);

    WebsiteImg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WebsiteImg record, @Param("example") WebsiteImgExample example);

    int updateByExample(@Param("record") WebsiteImg record, @Param("example") WebsiteImgExample example);

    int updateByPrimaryKeySelective(WebsiteImg record);

    int updateByPrimaryKey(WebsiteImg record);
}