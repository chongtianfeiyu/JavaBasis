import com.startcaft.hibernate.dao.TeacherDAO;


public class SessionMockDemo {

	public static void main(String[] args) throws Exception {

		TeacherDAO tDao = new TeacherDAO();
		tDao.Insert();
	}

}
