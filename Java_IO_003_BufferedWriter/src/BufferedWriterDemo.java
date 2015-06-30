import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/*
 * BufferedWriter:
 * 字符流的缓冲区技术子类：它的出现是为了提高流操作的性能；
 * 一般做文件的读写操作，都要用到缓冲技术；
 * 
 * 所以缓冲区的出现是建立在流对象的基础上的，从BufferedWriter类的构造函数也可以看出来;
 * 
 * 缓冲区中有针对不同操作系统设计的输出一个换行符的方法newLine()；
 * 
 * 缓冲区的write()方法，只是将数据写入到了缓冲区中，并没有写到对应的文件中，flush()刷新方法才是真正的写入到文件中；
 * 
 * 缓冲区的close()方法，其实就是关闭了相关联的流对象。
 */
public class BufferedWriterDemo {

	public static void main(String[] args) throws Exception {
		
		FileWriter fWriter = new FileWriter("Buffered.txt");
		
		//创建缓冲区输出流
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		
		for (int i = 0; i < 10; i++) {
			
			bWriter.write("abcd" + i);
			bWriter.newLine();
			bWriter.flush();
		}
		
		bWriter.close();
		System.out.println("buffer write done...");
	}
}
