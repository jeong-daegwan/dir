package main;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class FileCopy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Calendar cal = Calendar.getInstance();
//		cal.set(2016, 1, 5, 0, 0, 0);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		
//		for (int i=0; i<1; i++) {
//			for (int j=0; j<20; j++) {
//				String source = "/data/spidertm/sparrow/solr/collections/DEFAULT/20160204" + (j == 0 ? "" : ("_" + j));
//				String create = "/data/spidertm/sparrow/solr/collections/DEFAULT/" + sdf.format(cal.getTime()) + (j == 0 ? "" : ("_" + j));
//				if (!new File(source).exists()) break;
//				if (new File(create).exists()) continue;
//				System.out.println("create dir : " + create);
//				copy(new File(source), new File(create));
//				System.out.println("create success : " + create);
//			}
//			cal.add(Calendar.DATE, 1);
//		}
		long start = System.currentTimeMillis();
		File source = new File(args[0]);
		File target = new File(args[1]);
		try {
			copy(source, target);
			long end = System.currentTimeMillis();
			System.out.println(">>> copy success. time : " + (end - start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(">>> copy fail.");
		}
	}

	public static void copy(File sourceF, File targetF) throws Exception {
		  File[] ff = sourceF.listFiles();

		  for (File file : ff) 
		  {
		   File temp = new File(targetF.getAbsolutePath() + File.separator
		     + file.getName());
		   
		   if (file.isDirectory())  
		   {
		    temp.mkdirs();  
		    copy(file, temp); 
		   } 
		   else
		   {
				System.out.println(temp.getAbsolutePath());
		    FileInputStream fis = null;
		    FileOutputStream fos = null;
		    if (!temp.getParentFile().exists()) temp.getParentFile().mkdirs();
		    try {

		     fis = new FileInputStream(file);
		     fos = new FileOutputStream(temp);

		     byte[] b = new byte[4096];
		     int cnt = 0;
		     while ((cnt = fis.read(b)) != -1) {
		      fos.write(b, 0, cnt);
		     }
		    } finally {
		      fis.close();
		      fos.close();
		    }
		   }
		  }
		 }

}
