import java.io.IOException;


/**
 * java.lang.Runtime��
 * ÿһ��JavaӦ�ó�����һ��Runtime��ʵ����
 * ʹӦ�ó����ܹ��������еĻ��������ӡ�����ͨ��getRuntime������ȡ��ǰ����ʱ��
 *	
 * 
 * Runtime�Ǳ����Ϊ����ģʽ�ġ�
 * 
 * @author wow
 *
 */
public class RuntimeClassDemo {

	public static void main(String[] args) {
		
		try {
			exeCmd();
			exeCmdOpentxtFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MemoryView();
	}
	
	/**
	 * ��������Runtime����ִ��һ��cmd����
	 * @throws IOException 
	 */
	public static void exeCmd() throws IOException{
		
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("notepad.exe");
	}
	
	/**
	 * ��������Runtime����ִ��һ��cmd����notepad.exe filename.txt
	 * @throws IOException 
	 */
	public static void exeCmdOpentxtFile() throws IOException{
		
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("notepad.exe D:\\startcaft������\\�½��ı��ĵ�.txt");
	}

	
	/**
	 * Ӧ�ó�����ڴ����
	 * 
	 */
	private static void MemoryView(){
		Runtime runtime = Runtime.getRuntime();
		long mem1,mem2;
		Integer someints[] = new Integer[1000];
		//���ڴ���
		System.out.println("Total memory is : " + runtime.totalMemory());
		//JVM������еĿ����ڴ�
		mem1 = runtime.freeMemory();
		System.out.println("Free memory is : " + mem1);
		//������������
		runtime.gc();
		
		mem1 = runtime.freeMemory(); 
        System.out.println("Free memory after garbage collection : " + mem1);
        //��ʼ��һЩ���ݺ�
        for (int i = 0; i < 1000; i++) {
			someints[i] = new Integer(i);
		}
        mem2 = runtime.freeMemory();
        System.out.println("Free memory after allocation : " + mem2); 
        System.out.println("Memory used by allocation : " +(mem1-mem2));  
        //����һЩ���ݺ�
        for(int i=0; i<1000; i++){ 
        	someints[i] = null; 
        }
        //��һ�������������
        runtime.gc();
        mem2 = runtime.freeMemory();
        System.out.println("Free memory after collecting " + "discarded integers : " + mem2); 
	}
}
