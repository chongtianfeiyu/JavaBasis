import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/*
 * BufferedWriter:
 * �ַ����Ļ������������ࣺ���ĳ�����Ϊ����������������ܣ�
 * һ�����ļ��Ķ�д��������Ҫ�õ����弼����
 * 
 * ���Ի������ĳ����ǽ�����������Ļ����ϵģ���BufferedWriter��Ĺ��캯��Ҳ���Կ�����;
 * 
 * ������������Բ�ͬ����ϵͳ��Ƶ����һ�����з��ķ���newLine()��
 * 
 * ��������write()������ֻ�ǽ�����д�뵽�˻������У���û��д����Ӧ���ļ��У�flush()ˢ�·�������������д�뵽�ļ��У�
 * 
 * ��������close()��������ʵ���ǹر����������������
 */
public class BufferedWriterDemo {

	public static void main(String[] args) throws Exception {
		
		FileWriter fWriter = new FileWriter("Buffered.txt");
		
		//���������������
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
