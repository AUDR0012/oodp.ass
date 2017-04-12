package audrey;

import java.util.ArrayList;
import java.util.Date;
import audrey.Enumerator.*;

public class Developer {


	public static void addData(ArrayList<Logger> userList, ArrayList<Student> studentList, ArrayList<Course> courseList)
	{
		ArrayList<Group> grs = new ArrayList<Group>();
		ArrayList<Session> ses = new ArrayList<Session>();

		Course co;
		Group gr;
		Session se;
		Comparable us;
		Logger lo;

		se = new Session(Session_Type.LECTURE, "CS2", Day.TUESDAY, setTime("08:30"), 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS2", Day.THURSDAY, setTime("09:30"), 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "SSR1", Day.MONDAY, setTime("09:30"), 1, "TR+37", "Wk2-13");
		ses.add(se);
		se = new Session(Session_Type.LAB, "SSR1", Day.MONDAY, setTime("10:30"), 2, "SWLAB1", "Wk2,4,6,8,10,12");
		ses.add(se);
		gr = new Group(10191, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS2", Day.TUESDAY, setTime("08:30"), 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS2", Day.THURSDAY, setTime("09:30"), 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "SSR2", Day.TUESDAY, setTime("14:30"), 1, "TR+45", "Wk2-13");
		ses.add(se);
		se = new Session(Session_Type.LAB, "SSR2", Day.TUESDAY, setTime("10:30"), 2, "SWLAB1", "Wk2,4,6,8,10,12");
		ses.add(se);
		gr = new Group(10192, 0, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS2", Day.TUESDAY, setTime("08:30"), 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS2", Day.THURSDAY, setTime("09:30"), 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "SSR3", Day.MONDAY, setTime("16:30"), 1, "TR+37", "Wk2-13");
		ses.add(se);
		se = new Session(Session_Type.LAB, "SSR3", Day.MONDAY, setTime("10:30"), 2, "SWLAB1", "Wk1,3,5,7,9,11,13");
		ses.add(se);
		gr = new Group(10193, 1, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS2", Day.TUESDAY, setTime("08:30"), 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS2", Day.THURSDAY, setTime("09:30"), 1, "LT8", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "BCG2", Day.FRIDAY, setTime("13:30"), 1, "TR+45", "Wk2-13");
		ses.add(se);
		se = new Session(Session_Type.LAB, "BCG2", Day.FRIDAY, setTime("10:30"), 2, "SWLAB1", "Wk2,4,6,8,10,12");
		ses.add(se);
		gr = new Group(10194, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("CZ2005", "OPERATING SYSTEMS", Course_Type.CORE, 3, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		se = new Session(Session_Type.LECTURE, "CS1", Day.MONDAY, setTime("10:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Day.THURSDAY, setTime("14:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "FS1", Day.TUESDAY, setTime("09:30"), 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "FS1", Day.TUESDAY, setTime("08:30"), 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10201, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Day.MONDAY, setTime("10:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Day.THURSDAY, setTime("14:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "FS2", Day.THURSDAY, setTime("11:30"), 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "FS2", Day.THURSDAY, setTime("10:30"), 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10202, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Day.MONDAY, setTime("10:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Day.THURSDAY, setTime("14:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "FS3", Day.TUESDAY, setTime("15:30"), 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "FS3", Day.TUESDAY, setTime("14:30"), 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10203, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Day.MONDAY, setTime("10:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Day.THURSDAY, setTime("14:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "DD1", Day.TUESDAY, setTime("09:30"), 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "DD1", Day.TUESDAY, setTime("08:30"), 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10403, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Day.MONDAY, setTime("10:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Day.THURSDAY, setTime("14:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "FS4", Day.FRIDAY, setTime("15:30"), 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "FS4", Day.FRIDAY, setTime("14:30"), 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10442, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.LECTURE, "CS1", Day.MONDAY, setTime("10:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.LECTURE, "CS1", Day.THURSDAY, setTime("14:30"), 1, "LT4", "");
		ses.add(se);
		se = new Session(Session_Type.TUTORIAL, "DD2", Day.FRIDAY, setTime("09:30"), 1, "SWLAB2", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "DD2", Day.FRIDAY, setTime("08:30"), 1, "SWLAB2", "");
		ses.add(se);
		gr = new Group(10457, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("CZ1007", "DATA STRUCTURES", Course_Type.CORE, 3, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		se = new Session(Session_Type.TUTORIAL, "G1", Day.WEDNESDAY, setTime("13:30"), 2, "LHS-TR+29", "Wk3-13");
		ses.add(se);
		gr = new Group(20201, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.TUTORIAL, "G2", Day.WEDNESDAY, setTime("15:30"), 2, "LHS-TR+29", "Wk3-13");
		ses.add(se);
		gr = new Group(20202, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("HW0001", "ENGLISH PROFICIENCY", Course_Type.GER_CORE, 0, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		se = new Session(Session_Type.TUTORIAL, "SCE1", Day.WEDNESDAY, setTime("14:30"), 2, "LHS-TR+45", "Wk2-13");
		ses.add(se);
		gr = new Group(10411, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		se = new Session(Session_Type.TUTORIAL, "SCE2", Day.WEDNESDAY, setTime("14:30"), 2, "LHS-TR+9", "Wk2-13");
		ses.add(se);
		gr = new Group(10412, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("HW0288", "ENGINEERING COMMUNICATION II", Course_Type.GER_CORE, 2, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		se = new Session(Session_Type.LECTURE, "CS3", Day.FRIDAY, setTime("08:30"), 1, "LT19", "");
		ses.add(se);
		se = new Session(Session_Type.LAB, "CS3", Day.FRIDAY, setTime("09:30"), 1, "SCSE LABS", "");
		ses.add(se);
		gr = new Group(10413, 10, ses);
		grs.add(gr);
		ses = new ArrayList<Session>();
		co = new Course("CZ3004", "MULTIDISCIPLINARY DESIGN PROJECT", Course_Type.CORE, 4, grs);
		courseList.add(co);
		grs = new ArrayList<Group>();

		us = new Admin("LIANG QIANHUI");
		lo = new Logger("QHLIANG0", Formatter.hashPassword("QHLIANG0"), us);
		userList.add(lo);
		us = new Admin("TAN KHENG LEONG");
		lo = new Logger("KHENGLEO", Formatter.hashPassword("KHENGLEO"), us);
		userList.add(lo);
		us = new Admin("PHAN CONG MINH");
		lo = new Logger("PHAN0050", Formatter.hashPassword("PHAN0050"), us);
		userList.add(lo);
		us = new Student("ANG CHIN SIONG", Gender.MALE, "U1642357D", 2, "CHINSO01@E.NTU.EDU.SG", setDate("11-02-93"),
				"Singaporean", "91254632", Notification_Status.EMAIL);
		lo = new Logger("CHINSONG", Formatter.hashPassword("CHINSONG"), us);
		userList.add(lo);
		us = new Student("AUDREY HO HAI YI", Gender.FEMALE, "U1624153E", 1, "AUDREY02@E.NTU.EDU.SG", setDate("02-04-95"),
				"Singaporean", "97216542", Notification_Status.EMAIL);
		lo = new Logger("AUDREY", Formatter.hashPassword("AUDREY"), us);
		userList.add(lo);
		us = new Student("ONG WEI FENG KELVIN", Gender.MALE, "U1637974J", 3, "KELVIN03@E.NTU.EDU.SG",
				setDate("23-09-96"), "Malaysian", "94415325", Notification_Status.EMAIL);
		lo = new Logger("KELVIN", Formatter.hashPassword("KELVIN"), us);
		userList.add(lo);
		us = new Student("TOH JIAN HAO", Gender.MALE, "U1647253G", 2, "JIANHA04@E.NTU.EDU.SG", setDate("11-01-92"),
				"Singaporean", "92141632", Notification_Status.EMAIL);
		lo = new Logger("JIANHAO", Formatter.hashPassword("JIANHAO"), us);
		userList.add(lo);
		us = new Student("WONG KIN SUM", Gender.MALE, "U1624152R", 1, "KINSUM05@E.NTU.EDU.SG", setDate("21-08-94"),
				"Singaporean", "94522516", Notification_Status.EMAIL);
		lo = new Logger("KINSUM", Formatter.hashPassword("KINSUM"), us);
		userList.add(lo);
		us = new Student("JESSIE WONG PEI XIN", Gender.FEMALE, "U1524482P", 4, "JESSIE13@E.NTU.EDU.SG",
				setDate("07-05-95"), "Japanese", "81801268", Notification_Status.EMAIL);
		lo = new Logger("JESSIE", Formatter.hashPassword("JESSIE"), us);
		userList.add(lo);
		us = new Student("LESLIE LAU WEI QI", Gender.MALE, "U1622987E", 2, "LLAU8122@E.NTU.EDU.SG", setDate("28-04-93"),
				"Malaysian", "83421088", Notification_Status.EMAIL);
		lo = new Logger("LESLIE", Formatter.hashPassword("LESLIE"), us);
		userList.add(lo);
		us = new Student("LIM HONG SIONG BENJAMIN", Gender.MALE, "U1687459J", 3, "BENLIM03@E.NTU.EDU.SG",
				setDate("04-05-91"), "Chinese", "81643759", Notification_Status.EMAIL);
		lo = new Logger("BENLIM", Formatter.hashPassword("BENLIM"), us);
		userList.add(lo);
		us = new Student("TAN YI HONG", Gender.MALE, "U1647953K", 2, "TANYH019@E.NTU.EDU.SG", setDate("28-04-90"),
				"Singaporean", "98763544", Notification_Status.EMAIL);
		lo = new Logger("YIHONG", Formatter.hashPassword("YIHONG"), us);
		userList.add(lo);
		us = new Student("LOW GUO WANG", Gender.MALE, "U1636745L", 2, "GUOWANG6@E.NTU.EDU.SG", setDate("17-02-92"),
				"Chinese", "87329563", Notification_Status.EMAIL);
		lo = new Logger("GUOWANG", Formatter.hashPassword("GUOWANG"), us);
		userList.add(lo);
		us = new Student("WONG JIA MIN", Gender.FEMALE, "U1636974M", 3, "WONGJM93@E.NTU.EDU.SG", setDate("26-08-96"),
				"Singaporean", "97356971", Notification_Status.EMAIL);
		lo = new Logger("JIAMIN", Formatter.hashPassword("JIAMIN"), us);
		userList.add(lo);

		for (Logger l : userList)
		{
			l.setAccessSTime(Formatter.getDate("01-04-2017 00:00", "dd-MM-yyyy hh:mm"));
			l.setAccessETime(Formatter.getDate("01-05-2017 00:00", "dd-MM-yyyy hh:mm"));
			if (l.getUser() instanceof Student)
			{
				studentList.add((Student) l.getUser());
			}
		}

		MySTARS.writeData(userList, courseList);
	}
	
	public static Date setDate(String text)
	{
		return Formatter.getDate(text, "dd-MM-yy");
	}
	
	public static Date setTime(String text)
	{
		return Formatter.getDate(text, "hh:mm");
	}
}
