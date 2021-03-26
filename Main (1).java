import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class Main
{
  public static void main (String[]args)
  {
    //considering that the two dates are gives as inputs in seperate Lines
    Scanner sc = new Scanner (System.in);
    String s1 = sc.nextLine ();	//start Date
    String s2 = sc.nextLine ();	//end Date

    //retrieving the values
    int yearStart = Integer.parseInt (s1.substring (0, 4));
    int yearEnd = Integer.parseInt (s2.substring (0, 4));
    int monthStart = Integer.parseInt (s1.substring (4, 6));
    int monthEnd = Integer.parseInt (s2.substring (4, 6));
    int dayStart = Integer.parseInt (s1.substring (6, 8));
    int dayEnd = Integer.parseInt (s2.substring (6, 8));
    int hourStart = Integer.parseInt (s1.substring (9, 11));
    int hourEnd = Integer.parseInt (s2.substring (9, 11));


    //"MM/dd/yyyy HH:mm:ss" converting the dates in this format
    String end =
      monthEnd + "/" + dayEnd + "/" + yearEnd + " " + hourEnd + ":" + "00" +
      ":" + "00";

    int lastDay = 30;
    int lastMonth = 12;
    for (int z = yearStart; z <= yearEnd; z++)
      {
	if (z == yearEnd)
	  lastMonth = monthEnd;

	for (int k = monthStart; k <= lastMonth; k++)
	  {
	    if (k == monthEnd && z == yearEnd)
	      lastDay = dayEnd;


	    for (int i = dayStart; i <= lastDay; i++)
	      {
		if (i > 30)
		  {
		    break;	//considering each month has 30 days after crossing the month it must again start from day 1
		  }
		String start =
		  k + "/" + i + "/" + z + " " + hourStart + ":" + "00" + ":" +
		  "00";
		long diff = findDiff (start, end);
		if (diff < 24)
		  {
		    for (int j = hourStart; j <= hourEnd + hourStart; j++)
		      {
			if (j == 24)
			  {
			    hourStart = 0;
			    i++;
			    j = 0;
			  }
			String ans =
			  (String.valueOf (z) + twoDigitConverter (k) +
			   twoDigitConverter (i) + " " +
			   twoDigitConverter (j));
			System.out.println (ans);
		      }
		  }
		else
		  {
		    //if difference in more than or equal to 24 hours 
		    if (hourStart > 0)
		      {
			for (int j = hourStart; j <= 24; j++)
			  {
			    if (j == 24)
			      {
				hourStart = 0;
				continue;
			      }
			    else
			      {
				String ans =
				  (String.valueOf (z) +
				   twoDigitConverter (k) +
				   twoDigitConverter (i) + " " +
				   twoDigitConverter (j));
				System.out.println (ans);
			      }
			  }
		      }
		    else
		      System.out.println (z + twoDigitConverter (k) +
					  twoDigitConverter (i));
		  }
	      }

	    dayStart = 1;
	  }
	monthStart = 1;
      }

  }

  //function that finds the difference between two dates in hours
  public static long findDiff (String start, String end)
  {
    SimpleDateFormat format = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
    Date d1 = null;
    Date d2 = null;

    try
    {
      d1 = format.parse (start);
      d2 = format.parse (end);

      long diff = d2.getTime () - d1.getTime ();
      return diff / (60 * 60 * 1000);
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    return 0;
  }


//function converting the dates from one digit to two digits
  public static String twoDigitConverter (int num)
  {
    String val = String.valueOf (num);
    if (val.length () == 2)
      return val;
    else
      return "0" + val;
  }

}
