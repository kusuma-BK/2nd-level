package hibernatesecondlevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Demo {

	public static void main(String[] args) {
		Employee a = null;

		Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session1 = sf.openSession();
		session1.beginTransaction();

		a = (Employee) session1.get(Employee.class, 1);
		System.out.println(a);

		session1.getTransaction().commit();
		session1.close();

		Session session2 = sf.openSession();
		session2.beginTransaction();

		a = (Employee) session2.get(Employee.class, 1);
		System.out.println(a);

		session2.getTransaction().commit();
		session2.close();

	}
}
