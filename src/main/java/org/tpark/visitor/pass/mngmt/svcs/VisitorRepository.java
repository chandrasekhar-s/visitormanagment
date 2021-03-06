package org.tpark.visitor.pass.mngmt.svcs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tpark.visitor.pass.mngmt.svcs.model.SearchCritera;
import org.tpark.visitor.pass.mngmt.svcs.model.Visitor;
import org.tpark.visitor.pass.mngmt.svcs.model.VisitorEntity;
@Component
public class VisitorRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public long savePass(VisitorEntity visitor){
		visitor.setIssuedDate(new Date());
		em.persist(visitor);
		em.flush();
		return visitor.getId();
	}
 	
 
	@Transactional
	 public void saveImage(VisitorEntity visitor){
		//NOT ALL TABLE MAPPED IN JPA:TODO
	    	Query query = em.createNativeQuery("INSERT INTO visitor_image (ID, IMAGE) VALUES(?,?)");
	    	String imageStr=  visitor.getImageencodestr().substring(visitor.getImageencodestr().indexOf(',')+1, visitor.getImageencodestr().length());
	        query.setParameter(1, visitor.getId());
	        query.setParameter(2, imageStr);
	        query.executeUpdate();
	        em.flush();
	    }


/*	public List<Visitor> _search(SearchCritera criteria) {
		//Lazy Query :( do better
		//Set time properly
		
		String arr[] =criteria.getFromDate().split("/");
		String fromDt=arr[2]+"-"+arr[0]+"-"+arr[1];

		String arr_1[] =criteria.getToDate().split("/");
		String toDt=arr_1[2]+"-"+arr_1[0]+"-"+arr_1[1];
		
		Query query = em.createNativeQuery("SELECT * FROM VISITOR_INFO v1 WHERE ISSUE_DATE BETWEEN :fromdate AND :todate",VisitorEntity.class);
		query.setParameter("fromdate", fromDt+" 00:00:00");
		query.setParameter("todate", toDt+" 23:59:59");
		
		System.out.println( fromDt+" 00:00:00");
		System.out.println( toDt+" 23:59:59");
		
		String img="";
		
		List<VisitorEntity> entityresult= (List<VisitorEntity>)query.getResultList();
		List<Visitor> result = new ArrayList<Visitor>();
		for (VisitorEntity visitorEntity : entityresult) {
			if(criteria.getShowimg().equals("on")){
				query = em.createNativeQuery("SELECT IMAGE FROM VISITOR_IMAGE WHERE ID=:VID");
				query.setParameter("VID",visitorEntity.getId());
				List imgList=query.getResultList();
				img= imgList.size()!=0?imgList.get(0).toString():"";
			}
				Visitor visitor = new Visitor();
				BeanUtils.copyProperties(visitorEntity, visitor);
				visitor.setImageencodestr(img);
				result.add(visitor);
			
		}
		return result;
	}*/
	
	public List<Visitor> search(SearchCritera criteria) throws ParseException {
		
		List<Visitor> result = new ArrayList<Visitor>();
		String fromDt="";
		String toDt="";
	
		
		String joinQuery = "SELECT V1.*,V2.IMAGE FROM VISITOR_INFO V1 LEFT JOIN VISITOR_IMAGE V2 ON V1.ID=V2.ID WHERE ";
							//+ " ISSUE_DATE BETWEEN :fromDate AND :toDate";
		
		String nonJoinQuery= "SELECT * FROM VISITOR_INFO v1 WHERE ";//ISSUE_DATE BETWEEN :fromdate AND :todate";
		
		boolean AND_COND=false;
		if(!criteria.getFromDate().isEmpty() && !criteria.getToDate().isEmpty()){
			String arr[] =criteria.getFromDate().split("/");
			fromDt=arr[2]+"-"+arr[0]+"-"+arr[1];
	
			String arr_1[] =criteria.getToDate().split("/");
			toDt=arr_1[2]+"-"+arr_1[0]+"-"+arr_1[1];
			joinQuery=joinQuery.concat(" ISSUE_DATE BETWEEN :fromdate AND :todate");
			nonJoinQuery=nonJoinQuery.concat(" ISSUE_DATE BETWEEN :fromdate AND :todate");
			AND_COND=true;
		}
		if(!criteria.getPassNum().isEmpty()){
			
			if(AND_COND){
				joinQuery=joinQuery.concat(" AND ");
				nonJoinQuery=nonJoinQuery.concat(" AND ");
			}
			joinQuery=joinQuery.concat("  V1.ID=:id");
			nonJoinQuery=nonJoinQuery.concat(" ID=:id");
			AND_COND=true;
		}
		if(!criteria.getBuilding().isEmpty()){
			if(AND_COND){
				joinQuery=joinQuery.concat(" AND ");
				nonJoinQuery=nonJoinQuery.concat(" AND ");
			}
			joinQuery=joinQuery.concat("  V1.BUILDING LIKE :bld");
			nonJoinQuery=nonJoinQuery.concat("  V1.BUILDING LIKE :bld");
			AND_COND=true;
		}
		
		if(!criteria.getCompany().isEmpty()){
			if(AND_COND){
				joinQuery=joinQuery.concat(" AND ");
				nonJoinQuery=nonJoinQuery.concat(" AND ");
			}
			joinQuery=joinQuery.concat("  V1.COMPANY=:comp");
			nonJoinQuery=nonJoinQuery.concat("  V1.COMPANY=:comp");
			AND_COND=true;
		}
		
		if(criteria.getShowimg().equals("true")){
			Query query = em.createNativeQuery(joinQuery);
			if(!criteria.getFromDate().isEmpty() && !criteria.getToDate().isEmpty()){
				query.setParameter("fromdate", fromDt+" 00:00:00");
				query.setParameter("todate", toDt+" 23:59:59");
			}
			//Set Query Params
			setQueryParams(criteria, query);
			List queryResult = query.getResultList();
			for (Iterator iterator = queryResult.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				Visitor v = new Visitor();
				v.setId(Long.parseLong(object[0].toString()));
				v.setFirstName(object[1].toString());
				v.setPurpose(object[2].toString());
				v.setMobile(object[3].toString());
				v.setAccompanyCount(Integer.parseInt(object[4].toString()));
				if(object[5]!=null)
					v.setAccompanyName(object[5].toString());
				v.setCompany(object[6].toString());
				v.setBuilding(object[7].toString());
				v.setVechno(object[8].toString());
				v.setPhotoId(object[9].toString());
				v.setPhotoIdType(object[10].toString());
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				v.setIssuedDate(df2.parse(object[11].toString().substring(0,object[11].toString().indexOf('.'))));
				v.setValidity(Integer.parseInt(object[12].toString()));
				if(object[13]!=null)
					v.setImageencodestr(object[13].toString());
				result.add(v);
			}
		}else{
			Query query = em.createNativeQuery(nonJoinQuery,VisitorEntity.class);
			if(!criteria.getFromDate().isEmpty() && !criteria.getToDate().isEmpty()){
				query.setParameter("fromdate", fromDt+" 00:00:00");
				query.setParameter("todate", toDt+" 23:59:59");
			}
			setQueryParams(criteria, query);
			List<VisitorEntity> entityresult= (List<VisitorEntity>)query.getResultList();
			Calendar c = Calendar.getInstance();
			for (VisitorEntity visitorEntity : entityresult) {
				Visitor visitor = new Visitor();
				BeanUtils.copyProperties(visitorEntity, visitor);
				result.add(visitor);
				
			}
		}
		
		return result;
		
	}


	/**
	 * @param criteria
	 * @param query
	 */
	private void setQueryParams(SearchCritera criteria, Query query) {
		if(!criteria.getPassNum().isEmpty()){
			query.setParameter("id",criteria.getPassNum());
		}
		if(!criteria.getBuilding().isEmpty()){
			query.setParameter("bld","%"+criteria.getBuilding()+"%");
		}
		if(!criteria.getCompany().isEmpty()){
			query.setParameter("comp",criteria.getCompany());
		}
	}
}
