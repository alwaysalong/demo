//package com.sh.pri.dao;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.ejb.Stateless;
//import javax.persistence.Query;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//
///**
// * 
// * @ClassName: AccountOrderDaoImpl 
// * @Description: 
// * @author admin
// * @date 2017年11月16日 下午2:13:33 
// *
// */
//@Stateless(name = "accountOrderDao")
//public class AccountOrderDaoImpl extends AbstractDaoImpl implements IAccountOrderDao {
//
//	public static final Log log = LogFactory.getLog(AccountOrderDaoImpl.class);
//	@Override
//	public Map<String, Object> queryAccountOrderList(TWAccountOrderDto dto,
//			int maxResult, int firstResult, boolean isPage) throws Exception {
//		log.info("AccountAndPostNoMapDaoImpl.queryAccountOrderList method invoke start>>>>>>>>>");
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		Query query = null;
//		List<Object[]> resultList = null;
//		List<TWAccountOrderDto> list = new ArrayList<TWAccountOrderDto>();
//		try {
//			StringBuilder sb = new StringBuilder();
//				 sb .append("SELECT AO.ORDER_ID AS orderId,")
//					.append(" AO.REQ_NO AS reqNo,") 
//					.append(" AO.ORDER_MONEY AS orderMoney,")
//					.append(" AO.USER_ONLY_ID AS userOnlyId,") 
//					.append(" DECODE(AO.STATUS,'0','待充值','1','充值中','2','充值成功','3','充值失败') AS status,")//oracle中别名DECODE
//					.append(" TO_CHAR(AO.ORDER_CREATE_TIME,'YYYY-MM-DD HH24:MI:SS') AS orderCreateTime,")//oracle中指定查出时间的格式,to_char是转成字符
//					.append(" AOD.PAY_ID AS payId,") 
//					.append(" AOD.PAY_TYPE AS payType,") 
//					.append(" TO_CHAR(AOD.PAY_TIME,'YYYY-MM-DD HH24:MI:SS') AS payTime")
//					.append(" FROM T_W_ACCOUNT_ORDER AO LEFT JOIN T_W_ACCOUNT_ORDER_DETAIL AOD ") 
//					.append(" ON AO.ORDER_ID = AOD.ORDER_ID "); 
//					
//				 StringBuilder where = new StringBuilder();
//				 where.append(" WHERE AO.VALID_FLAG ='1' AND AO.ORDER_TYPE ='1001'");
//			if(dto != null){
//				if(StringUtils.isNotBlank(dto.getOrderId())){
//					where.append(" AND AO.ORDER_ID = "+"'"+dto.getOrderId()+"'");
//				}
//				if(StringUtils.isNotBlank(dto.getReqNo())){
//					where.append(" AND AO.REQ_NO = "+"'"+dto.getReqNo()+"'");
//				}
//				if(StringUtils.isNotBlank(dto.getUserOnlyId())){
//					where.append(" AND AO.USER_ONLY_ID = "+"'"+dto.getUserOnlyId()+"'");
//				}
//				if(StringUtils.isNotBlank(dto.getStatus())){
//					where.append(" AND AO.STATUS = "+"'"+dto.getStatus()+"'");
//				}
//				if(StringUtils.isNotBlank(dto.getPayId())){
//					where.append(" AND AOD.PAY_ID = "+"'"+dto.getPayId()+"'");
//				}
//				if (StringUtils.isNotBlank(dto.getPayType())) {
//					where.append(" AND AOD.PAY_TYPE = " +"'"+ dto.getPayType()+"'");
//				}
//				if (StringUtils.isNotBlank(dto.getCreateStartTime()) && StringUtils.isNotBlank(dto.getCreateEndTime())) {
//					
//					//oracle中指定时间间隔 的查询sql,把string类型的时间格式转成指定的时间格式,到数据库中查找
//					where.append("AND AO.create_time between to_date('");
//					where.append(dto.getCreateStartTime());
//					where.append("  00:00:00");
//					where.append("','yyyy-mm-dd hh24:mi:ss') and to_date('");
//					where.append(dto.getCreateEndTime());
//					where.append(" 23:59:59");
//					where.append("','yyyy-mm-dd hh24:mi:ss')");
//				}
//			}
//			log.info("查询sql:"+sb.toString()+where.toString()+" ORDER BY AO.CREATE_TIME DESC");
//			query = em.createNativeQuery(sb.toString()+where.toString()+" ORDER BY AO.CREATE_TIME DESC");
//			if (isPage) {
//				query.setFirstResult(firstResult);
//				query.setMaxResults(maxResult);
//			}
//			resultList = query.getResultList();
//			int count = 0;
//			if (isPage) {
//				StringBuilder countSql = new StringBuilder();
//				countSql.append(" SELECT COUNT(*) FROM T_W_ACCOUNT_ORDER AO LEFT JOIN T_W_ACCOUNT_ORDER_DETAIL AOD ON AO.ORDER_ID = AOD.ORDER_ID  ");
//				log.info("查询总数:"+countSql.toString()+where.toString());
//				query = em.createNativeQuery(countSql.toString()+where.toString());
//				count = ((Number)query.getSingleResult()).intValue();
//			}
//			if(null!=resultList && !resultList.isEmpty()){
//				for(int i=0;i<resultList.size();i++){
//					TWAccountOrderDto accountOrderDto = new TWAccountOrderDto();
//					accountOrderDto.setOrderId(Tools.ts(resultList.get(i)[0]));
//					accountOrderDto.setReqNo(Tools.ts(resultList.get(i)[1]));
//					accountOrderDto.setOrderMoney(Tools.ts(BigDecimalUtil.m2(Tools.ts(resultList.get(i)[2]))));
//					accountOrderDto.setUserOnlyId(Tools.ts(resultList.get(i)[3]));
//					accountOrderDto.setStatus(Tools.ts(resultList.get(i)[4]));
//					accountOrderDto.setOrderCreateTime(Tools.ts(resultList.get(i)[5]));
//					accountOrderDto.setPayId(Tools.ts(resultList.get(i)[6]));
//					accountOrderDto.setPayType(Tools.ts(resultList.get(i)[7]));
//					accountOrderDto.setPayTime(Tools.ts(resultList.get(i)[8]));
//					list.add(accountOrderDto);
//				}
//			}
//			resultMap.put("resultList", list);
//			resultMap.put("count", count);
//			log.info("AccountAndPostNoMapDaoImpl.queryAccountOrderList method invoke end........");
//			return resultMap;
//		} catch (Exception e) {
//			log.error("查询异常", e);
//			throw new PriException(e.getMessage());
//		}
//	}
//}
