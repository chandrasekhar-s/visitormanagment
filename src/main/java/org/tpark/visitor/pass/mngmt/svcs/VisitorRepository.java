package org.tpark.visitor.pass.mngmt.svcs;

import java.util.ArrayList;
import java.util.Date;
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


	public List<Visitor> search(SearchCritera criteria) {
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
	}
}
