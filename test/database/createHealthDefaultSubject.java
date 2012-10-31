package database;

import health.database.DAO.SubjectDAO;

public class createHealthDefaultSubject {
public static void main(String args[])
{
	SubjectDAO dao=new SubjectDAO();
	System.out.println(dao.findHealthSubject("leoncool").getTitle());
	
}
}
