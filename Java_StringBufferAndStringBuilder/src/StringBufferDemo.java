
public class StringBufferDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		method_create();
		method_delete();  
		method_update();  
		method_copy();  
	}
	
	public static void method_copy() {
		StringBuffer sBuffer = new StringBuffer("abcdef");
		char[] chs = new char[4];
		sBuffer.getChars(0, 4, chs, 0);
		for (char c : chs) {
			sop("getChars" + c);
		}
	}
	
	public static void method_update(){
		StringBuffer sb = new StringBuffer("abcdef");
		sb.replace(1, 4, "java");
		sop(sb.toString());
		sb.setCharAt(2, 'k');//替换指定位置上的字符
		sop(sb.toString());
	}
	
	public static void method_delete(){
		StringBuffer sb = new StringBuffer("abcdef");
		sb.delete(1, 3);
		sop(sb.toString());
		
		//清空缓冲区
		//sb.delete(0, sb.length());
		
		sb.deleteCharAt(2);//或者 sb.delete(2,3);
		sop(sb.toString());
	}
	
	public static void method_create(){
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = sb.append(34);
		sop("sb==sb1:" + (sb == sb1));
		
		sb.append("sdfsdf").append(true).append(123);
		sop(sb.toString());
		sop(sb1.toString());
	}
	
	public static void sop(String str){  
        System.out.println(str);  
    }  
}
