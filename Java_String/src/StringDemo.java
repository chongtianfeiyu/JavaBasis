import java.util.Iterator;


public class StringDemo {

	public static void main(String[] args) {
		String s1 = "abc";//s1����һ��String���͵ı�����"abc"��һ�����������ڳ�������
						  //�ַ��������ص��ǣ�һ������ʼ���Ͳ����Ա��ı䡣
		s1 = "kk";
		System.out.println(s1);//ֻ��s1��ָ�� �ı��� abc������� ����û�иı䡣
		
		String s2 = new String("abc");//new String() "abc"
		String s3 = "abc";//���ڳ��������� abc ���� �����ٿ��ٿռ��� �½�����
		
		
		System.out.println(s3 == s2);//false,s3��s2ָ��������ͬ�Ķ���
		System.out.println(s3.equals(s2));//��Ȼs3��s2ָ��������ͬ�Ķ��󣬵���������ֵ����"abc"��
	
		System.out.println("====================================");
		method_get();
		System.out.println("====================================");  
        method_trans(); 
        System.out.println("====================================");  
        method_replace(); 
        System.out.println("====================================");  
        method_subString();  
        System.out.println("====================================");  
        method_split(); 
        System.out.println("====================================");  
        method_7(); 
	}
	
	
	public static void method_7(){
		String s = "   Hello Java     ";
		sop(s.toUpperCase());
		sop(s.toLowerCase());
		sop(s.trim());
	}
	
	
	public static void method_split() {
		String s = "zhangs,lisi,wangwu";
		String[] names = s.split(",");
		
		for (String string : names) {
			sop(string);
		}
	}
	
	
	public static void method_subString(){
		String s = "hellojava";
		sop(s.substring(2));//��ָ��λ�ÿ�ʼ����β������Ǳ겻���ڣ�������ַ����Ǳ�Խ���쳣��
		sop(s.subSequence(2, 4));//����ͷ��������β
	}
	
	
	public static void method_replace() {
		String s = "hello csharp";
		String s1 = s.replace('q', 'n');//���Ҫ�滻���ַ�����ԭ�ַ����в����ڣ��򷵻صĻ���ԭ�ַ���
		
		sop(s);
		sop(s1);
		String s2 = s.replace("csharp", "java");
		sop(s2);
	}
	
	
	private static void method_get() {
		String str = "abcdeakpf";
		//����
		sop(str.length());
		//����������ȡ�ַ���
		sop(str.charAt(4));//�����ʵ��ַ����в����ڵĽǱ�ʱ���ᷢ��StringIndexOfBoundsException��
		
		
		//�����ַ���ȡ����
		sop(str.indexOf('m', 3));//���Ҳ��� ָ�����ַ���ʱ�� ����-1
		
		//��������һ���ַ����ֵ�λ��
		sop(str.lastIndexOf('a'));
		
		//����ָ���Ǳ��ϵ�ASCII��
		sop(str.codePointAt(3));//c �� ASCII
		
		//����ָ������֮ǰ���ַ��� ASCII��
		sop(str.codePointBefore(3));//b ��ASCII
		
		//�����ı���Χ�е�Unicode�������
		sop(str.codePointCount(0, 6));
	}
	
	
	public static void method_trans(){
		char[] arr = {'a','b','c','d','e','f','g'};
		String s = new String(arr);
		sop("s=" + s);
		s = new String(arr, 1, 3);//1�ǽǱ꣬3�Ǹ�����
		sop("s=" + s);
		

		sop(String.copyValueOf(arr));
		sop(String.copyValueOf(arr, 1, 3));
	}
	
	
	
	private static void sop(Object obj){
		System.out.println(obj);
	}
}
