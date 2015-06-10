import java.io.IOException;


/**
 * java.lang.Runtime类
 * 每一个Java应用程序都有一个Runtime的实例，
 * 使应用程序能够与其运行的环境想连接。可以通过getRuntime方法获取当前运行时。
 *	
 * 
 * Runtime是被设计为单例模式的。
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
	 * 可以利用Runtime对象执行一个cmd命令
	 * @throws IOException 
	 */
	public static void exeCmd() throws IOException{
		
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("notepad.exe");
	}
	
	/**
	 * 可以利用Runtime对象执行一个cmd命令notepad.exe filename.txt
	 * @throws IOException 
	 */
	public static void exeCmdOpentxtFile() throws IOException{
		
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("notepad.exe D:\\startcaft的云盘\\新建文本文档.txt");
	}

	
	/**
	 * 应用程序的内存管理
	 * 
	 */
	private static void MemoryView(){
		Runtime runtime = Runtime.getRuntime();
		long mem1,mem2;
		Integer someints[] = new Integer[1000];
		//总内存量
		System.out.println("Total memory is : " + runtime.totalMemory());
		//JVM虚拟机中的空闲内存
		mem1 = runtime.freeMemory();
		System.out.println("Free memory is : " + mem1);
		//运行垃圾回收
		runtime.gc();
		
		mem1 = runtime.freeMemory(); 
        System.out.println("Free memory after garbage collection : " + mem1);
        //初始化一些数据后
        for (int i = 0; i < 1000; i++) {
			someints[i] = new Integer(i);
		}
        mem2 = runtime.freeMemory();
        System.out.println("Free memory after allocation : " + mem2); 
        System.out.println("Memory used by allocation : " +(mem1-mem2));  
        //销毁一些数据后
        for(int i=0; i<1000; i++){ 
        	someints[i] = null; 
        }
        //在一起进行垃圾回收
        runtime.gc();
        mem2 = runtime.freeMemory();
        System.out.println("Free memory after collecting " + "discarded integers : " + mem2); 
	}
}
