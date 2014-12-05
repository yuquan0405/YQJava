package test;

import java.lang.reflect.Field;
import java.util.List;

import com.framework.annotation.Column;
import com.framework.annotation.GenerationType;
import com.framework.annotation.Id;
import com.framework.annotation.Table;
import com.framework.dao.EntityData;
import com.framework.dao.entity.EntityBean;
import com.framework.dao.entity.FieldBean;
import com.framework.util.StringUtil;

public class AnnotationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestBean test = new TestBean();
		
		try {
//			Field f = test.getClass().getDeclaredField("uname");
//			Column coltest = f.getAnnotation(Column.class);
//			if(coltest == null){
//				System.out.println("coltest is null");
//			}else{
//			//	System.out.println(coltest.value());
//			}
			Class tcls = test.getClass();
			//System.out.println("class name:"+tcls.getName());
	        EntityBean eb = EntityData.get(tcls);
	        AnnotationTest.delete(test);
	       
			/*
			Table tb = (Table) tcls.getAnnotation(Table.class);
			System.out.println("tableName:"+tb.name());
			Field [] fs = test.getClass().getDeclaredFields();
			//ID
			String pk = "";
			for(int i = 0; i < fs.length; i++){
				Id id = fs[i].getAnnotation(Id.class);
				if(id != null){
					pk += ","+ fs[i].getName();
				}
			}
			System.out.println("pk:"+pk);
			
			//ID
			String generID = "";
			for(int i = 0; i < fs.length; i++){
				GeneratedValue gv = fs[i].getAnnotation(GeneratedValue.class);
				if(gv != null){
					if(gv.type() == GenerationType.UUID){
						System.out.println(StringUtil.getUUID());
					}
				}
			}
			*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
	
	public static  int delete(Object obj) { 
		
		int rs = 0;
		
		EntityBean bean = EntityData.get(obj.getClass());
		List<FieldBean> primarys = bean.getPrimaryKey();
		String sql = "DELETE FROM ";
		sql += bean.getTableName();
		sql += " WHERE ";
		for(int i = 0; i < primarys.size(); i++){
			FieldBean fbean = primarys.get(i);
			if(i == 0){
				sql += fbean.getColumnName() + "=? ";
			}else{
				sql += " and " + fbean.getColumnName() + "=? ";
			}
			
		}
		System.out.println(sql);
		return rs;
	}
	

}
