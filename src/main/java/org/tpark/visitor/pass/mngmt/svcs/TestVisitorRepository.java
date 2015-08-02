package org.tpark.visitor.pass.mngmt.svcs;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestVisitorRepository {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void getImage(int id) throws IOException {
		// NOT ALL TABLE MAPPED IN JPA:TODO
		Query query = em
				.createNativeQuery("SELECT ID, IMAGE FROM visitor_image");
		List resultList = query.getResultList();
		Object[] image = (Object[]) resultList.get(0);
		System.out.println(image[1]);
		byte[] imgaeArray = new sun.misc.BASE64Decoder()
				.decodeBuffer(new String(image[1].toString()));
		OutputStream op = new BufferedOutputStream(new FileOutputStream(
				"D:/me.png"));
		op.write(imgaeArray);
		op.close();
		em.flush();
	}
}
