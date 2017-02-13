package com.haoyu.aip.evaluate.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.evaluate.dao.IAgreeDao;
import com.haoyu.aip.evaluate.entity.Agree;
import com.haoyu.aip.evaluate.service.IAgreeService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;

@Service
public class AgreeService implements IAgreeService{
	@Resource
	private IAgreeDao agreeDao;

	@Override
	public Response create(Agree agree) {
		if(agree.getId()==null || agree.getId().equals("")){
			agree.setId(DigestUtils.md5Hex(agree.getRelation().getId()+ThreadContext.getUser().getId()));
		}
		return BaseServiceUtils.create(agree, (MybatisDao)this.agreeDao);
	}

	@Override
	public Response update(Agree agree) {
		return BaseServiceUtils.update(agree, (MybatisDao)this.agreeDao);
		
	}


	@Override
	public Response deleteByLogic(Agree agree) {
		return BaseServiceUtils.delete(agree.getId(), (MybatisDao)this.agreeDao);
	}

	@Override
	public Agree get(String id) {
		return (Agree) BaseServiceUtils.get(id, (MybatisDao)this.agreeDao);
	}

	@Override
	public List<Agree> list(Agree agree, PageBounds pageBounds) {
		return ((MybatisDao)this.agreeDao).selectList("select",agree,pageBounds);
	}

	@Override
	public boolean isAgreeed(String relationId, String userId) {
		Agree agree = (Agree) BaseServiceUtils.get(DigestUtils.md5Hex(relationId + userId), (MybatisDao)this.agreeDao);
		return agree==null?false:true;
	}

}
